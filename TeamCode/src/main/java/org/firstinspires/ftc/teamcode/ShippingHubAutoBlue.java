package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.MapSelectCommand;
import com.arcrobotics.ftclib.command.PrintCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.google.common.collect.ImmutableMap;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.commands.ControlArmCommand;
import org.firstinspires.ftc.teamcode.commands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveStrafeCommand;
import org.firstinspires.ftc.teamcode.commands.HomeArmCommand;
import org.firstinspires.ftc.teamcode.commands.TiltIntakeRampUpCommand;
import org.firstinspires.ftc.teamcode.commands.TiltOuttakeInCommand;
import org.firstinspires.ftc.teamcode.commands.TiltOuttakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.TurnInPlace;
import org.firstinspires.ftc.teamcode.commands.WaitForVisionCommand;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.DuckSpinner;
import org.firstinspires.ftc.teamcode.subsystems.IntakeRamp;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSpinner;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;
import org.firstinspires.ftc.teamcode.subsystems.VisionPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name = "Shipping Hub Bloo", group = "Blue")



public class ShippingHubAutoBlue extends CommandOpMode {

    OpenCvWebcam webcam;

    private DuckSpinner duckSpinner;
    private Drive drive;
    private Outtake outtake;
    private IntakeRamp intakeramp;
    private IntakeSpinner intakeSpinner;
    private Arm arm;


    @Override
    public void initialize(){
        /*
         * Instantiate an OpenCvCamera object for the camera we'll be using.
         * In this sample, we're using a webcam. Note that you will need to
         * make sure you have added the webcam to your configuration file and
         * adjusted the name here to match what you named it in said config file.
         *
         * We pass it the view that we wish to use for camera monitor (on
         * the RC phone). If no camera monitor is desired, use the alternate
         * single-parameter constructor instead (commented out below)
         */
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        /*
         * Specify the image processing pipeline we wish to invoke upon receipt
         * of a frame from the camera. Note that switching pipelines on-the-fly
         * (while a streaming session is in flight) *IS* supported.
         */
        VisionPipeline pl = new VisionPipeline();
        webcam.setPipeline(pl);
        /*
         * Open the connection to the camera device. New in v1.4.0 is the ability
         * to open the camera asynchronously, and this is now the recommended way
         * to do it. The benefits of opening async include faster init time, and
         * better behavior when pressing stop during init (i.e. less of a chance
         * of tripping the stuck watchdog)
         *
         * If you really want to open synchronously, the old method is still available.
         */
        webcam.setMillisecondsPermissionTimeout(2500); // Timeout for obtaining permission is configurable. Set before opening.
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                /*
                 * Tell the webcam to start streaming images to us! Note that you must make sure
                 * the resolution you specify is supported by the camera. If it is not, an exception
                 * will be thrown.
                 *
                 * Keep in mind that the SDK's UVC driver (what OpenCvWebcam uses under the hood) only
                 * supports streaming from the webcam in the uncompressed YUV image format. This means
                 * that the maximum resolution you can stream at and still get up to 30FPS is 480p (640x480).
                 * Streaming at e.g. 720p will limit you to up to 10FPS and so on and so forth.
                 *
                 * Also, we specify the rotation that the webcam is used in. This is so that the image
                 * from the camera sensor can be rotated such that it is always displayed with the image upright.
                 * For a front facing camera, rotation is defined assuming the user is looking at the screen.
                 * For a rear facing camera or a webcam, rotation is defined assuming the camera is facing
                 * away from the user.
                 */
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });

        drive = new Drive(hardwareMap,telemetry);
        duckSpinner = new DuckSpinner(hardwareMap);
        outtake = new Outtake(hardwareMap);
        arm = new Arm(hardwareMap, telemetry);
        intakeramp = new IntakeRamp(hardwareMap);
        intakeSpinner = new IntakeSpinner(hardwareMap);

        intakeramp.setDefaultCommand(
                new TiltIntakeRampUpCommand(intakeramp)
        );

        outtake.stow();
        sleep(1500);
        intakeramp.down();
        intakeSpinner.stow();

        WaitForVisionCommand waitForVisionCommand = new WaitForVisionCommand(pl);
        schedule(new SequentialCommandGroup(
            new WaitUntilCommand(()-> isStarted()),
            // 0. scan marker, up to 5 sec
           waitForVisionCommand.withTimeout(5000),
            new HomeArmCommand(arm),
            // 1. strafe right, arm scoring position
            new DriveStrafeCommand(telemetry,drive,-20*49,0.5),

            // 2. go -forward, chassis scoring position
            new DriveForwardCommand(telemetry,drive,-19,0.5),

            /* 3. place block in correct level, if marker undetected place on 3rd level
                if marker on left extend _____
                if marker in center extend _____
                if marker on right extend _____
                else, extend _____
             */
            new InstantCommand(()->intakeSpinner.stop()),
            new MapSelectCommand<>(
                    ImmutableMap.of(
                            VisionPipeline.MarkerPlacement.LEFT, new ControlArmCommand(arm, 165,telemetry),
                            VisionPipeline.MarkerPlacement.CENTER, new ControlArmCommand(arm, 758,telemetry),
                            VisionPipeline.MarkerPlacement.RIGHT, new ControlArmCommand(arm, 1700,telemetry),
                            VisionPipeline.MarkerPlacement.UNKNOWN, new ControlArmCommand(arm, 1700,telemetry)
                    ),
                    () -> waitForVisionCommand.getPlacement()
            ),

            // 4. bucket out position
            new TiltOuttakeOutCommand(outtake).withTimeout(1000),

            // 5. retract everything
            new TiltOuttakeInCommand(outtake).withTimeout(100),
            new HomeArmCommand(arm),

            // 6. go forward
            new DriveForwardCommand(telemetry,drive,10,0.5),

            // 7. rotate 90 degrees counterclockwise
            new TurnInPlace(drive,-90,telemetry),

            // 8. go forward into warehouse
            new DriveForwardCommand(telemetry,drive,6*12,0.5)

        ));
    }
}
