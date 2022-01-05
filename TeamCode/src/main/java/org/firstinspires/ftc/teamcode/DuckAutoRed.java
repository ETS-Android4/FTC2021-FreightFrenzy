package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveStrafeCommand;
import org.firstinspires.ftc.teamcode.commands.MoveDuckSpinnerSpinRedCommand;
import org.firstinspires.ftc.teamcode.commands.TiltIntakeRampUpCommand;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.DuckSpinner;
import org.firstinspires.ftc.teamcode.subsystems.IntakeRamp;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSpinner;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;

@Autonomous(name = "duck auto red")

public class DuckAutoRed extends CommandOpMode {

    private DuckSpinner duckSpinner;
    private Drive drive;
    private Outtake outtake;
    private IntakeRamp intakeramp;
    private IntakeSpinner intakeSpinner;
    private Arm arm;


    @Override
    public void initialize() {

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
                new InstantCommand(() -> intakeSpinner.stop(), intakeSpinner),
                new SequentialCommandGroup(
                    new DriveForwardCommand(telemetry, drive, 24, 0.5),
                    new DriveStrafeCommand(telemetry, drive, 42*49, 0.5),
                    new DriveForwardCommand(telemetry, drive, -8, 0.5),
                    new MoveDuckSpinnerSpinRedCommand(duckSpinner).withTimeout(2000),
                    new DriveStrafeCommand(telemetry, drive, -8*49, 0.5)
                )
        );
    }
}
