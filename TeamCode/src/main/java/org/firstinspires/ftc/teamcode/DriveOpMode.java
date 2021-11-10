package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.ControlArmCommand;
import org.firstinspires.ftc.teamcode.commands.DriveWithGamepadCommand;
import org.firstinspires.ftc.teamcode.commands.HomeArmCommand;
import org.firstinspires.ftc.teamcode.commands.MoveDuckSpinnerSpinCommand;
import org.firstinspires.ftc.teamcode.commands.MoveIntakeRampDownCommand;
import org.firstinspires.ftc.teamcode.commands.MoveIntakeRampUpCommand;
import org.firstinspires.ftc.teamcode.commands.MoveOuttakeDownCommand;
import org.firstinspires.ftc.teamcode.commands.MoveOuttakeUpCommand;
import org.firstinspires.ftc.teamcode.commands.SpinIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.SpinOuttakeCommand;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.DuckSpinner;
import org.firstinspires.ftc.teamcode.subsystems.IntakeRamp;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSpinner;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;

@TeleOp(name = "Telop")

public class DriveOpMode extends CommandOpMode {


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
        drive.setDefaultCommand
                (new DriveWithGamepadCommand(gamepad1, drive)
        );

        // Driver 1
        GamepadEx driver = new GamepadEx(gamepad1);

        GamepadButton duckSpinnerButton = new GamepadButton(driver, GamepadKeys.Button.LEFT_BUMPER);
        duckSpinnerButton.whileHeld(new MoveDuckSpinnerSpinCommand(duckSpinner));

        GamepadButton intakeRampUp = new GamepadButton(driver, GamepadKeys.Button.A);
        intakeRampUp.whileHeld(new MoveIntakeRampUpCommand(intakeramp));

        GamepadButton intakeRampDown = new GamepadButton(driver, GamepadKeys.Button.B);
        intakeRampDown.whileHeld(new MoveIntakeRampDownCommand(intakeramp));

        GamepadButton spinIntakeIn = new GamepadButton(driver, GamepadKeys.Button.RIGHT_BUMPER);
        spinIntakeIn.whileHeld(new SpinIntakeCommand(intakeSpinner));

        // Driver2
        GamepadButton moveUpButton = new GamepadButton(driver, GamepadKeys.Button.A);
        moveUpButton.whileHeld(new MoveOuttakeUpCommand(outtake));

        GamepadButton moveDownButton = new GamepadButton(driver, GamepadKeys.Button.B);
        moveDownButton.whileHeld(new MoveOuttakeDownCommand(outtake));

        GamepadButton spinIntakeOut = new GamepadButton(driver, GamepadKeys.Button.Y);
        spinIntakeOut.whileHeld(new SpinOuttakeCommand(intakeSpinner));

        GamepadButton armHomeButton = new GamepadButton(driver, GamepadKeys.Button.A);
        armHomeButton.whenPressed(new HomeArmCommand(arm));

        GamepadButton armCommandButton = new GamepadButton(driver, GamepadKeys.Button.Y);
        armCommandButton.whenPressed(new ControlArmCommand(arm,1000, telemetry));
}
}
