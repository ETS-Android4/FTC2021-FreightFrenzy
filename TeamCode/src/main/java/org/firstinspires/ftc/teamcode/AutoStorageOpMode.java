package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.TiltIntakeRampUpCommand;
import org.firstinspires.ftc.teamcode.commands.TurnInPlace;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.DuckSpinner;
import org.firstinspires.ftc.teamcode.subsystems.IntakeRamp;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSpinner;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;

@Autonomous(name = "Auto Storage")

public class AutoStorageOpMode extends CommandOpMode {


    private DuckSpinner duckSpinner;
    private Drive drive;
    private Outtake outtake;
    private IntakeRamp intakeramp;
    private IntakeSpinner intakeSpinner;
    private Arm arm;

    @Override
    public void initialize(){
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

        schedule(
                new SequentialCommandGroup(
                        new DriveForwardCommand(telemetry, drive, 28.7, 0.5),
                        new TurnInPlace(drive, -90, telemetry),
                        new DriveForwardCommand(telemetry, drive, 26.5, 0.5)
                )
        );
    }
}
