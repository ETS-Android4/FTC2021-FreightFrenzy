package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.DriveWithGamepadCommand;
import org.firstinspires.ftc.teamcode.commands.ExtendArmCommand;
import org.firstinspires.ftc.teamcode.commands.HomeArmCommand;
import org.firstinspires.ftc.teamcode.commands.MoveDuckSpinnerSpinCommand;
import org.firstinspires.ftc.teamcode.commands.RetractArmCommand;
import org.firstinspires.ftc.teamcode.commands.SpinIntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.TiltIntakeRampDownCommand;
import org.firstinspires.ftc.teamcode.commands.TiltIntakeRampUpCommand;
import org.firstinspires.ftc.teamcode.commands.TiltOuttakeInCommand;
import org.firstinspires.ftc.teamcode.commands.TiltOuttakeOutCommand;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.DuckSpinner;
import org.firstinspires.ftc.teamcode.subsystems.IntakeRamp;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSpinner;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;

@Autonomous(name = "Test Auto")

public class AutoOpMode extends CommandOpMode {


    private DuckSpinner duckSpinner;
    private Drive drive;
    private Outtake outtake;
    private IntakeRamp intakeramp;
    private IntakeSpinner intakeSpinner;
    private Arm arm;

    @Override
    public void initialize(){
        drive = new Drive(hardwareMap);
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
    }
}
