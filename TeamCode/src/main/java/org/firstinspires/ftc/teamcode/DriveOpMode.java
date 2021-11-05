package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp

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

        drive.setDefaultCommand
                (new DriveWithGamepadCommand(gamepad1, drive)
        );
        
        GamepadEx driver = new GamepadEx(gamepad1);

        GamepadButton duckSpinnerButton = new GamepadButton(driver, GamepadKeys.Button.A);
        duckSpinnerButton.whileHeld(new MoveDuckSpinnerSpinCommand(duckSpinner));
        
        GamepadButton moveUpButton = new GamepadButton(driver, GamepadKeys.Button.A);
        moveUpButton.whileHeld(new MoveOuttakeUpCommand(outtake));

        GamepadButton moveDownButton = new GamepadButton(driver, GamepadKeys.Button.B);
        moveDownButton.whileHeld(new MoveOuttakeDownCommand(outtake));
        intakeramp = new IntakeRamp(hardwareMap);
        intakeSpinner = new IntakeSpinner(hardwareMap);

        GamepadButton intakeRampUp = new GamepadButton(driver, GamepadKeys.Button.A);
        intakeRampUp.whileHeld(new MoveIntakeRampUpCommand(intakeramp));

        GamepadButton intakeRampDown = new GamepadButton(driver, GamepadKeys.Button.B);
        intakeRampDown.whileHeld(new MoveIntakeRampDownCommand(intakeramp));

        GamepadButton spinIntakeIn = new GamepadButton(driver, GamepadKeys.Button.X);

        spinIntakeIn.whileHeld(new SpinIntakeCommand(intakeSpinner));
        GamepadButton spinIntakeOut = new GamepadButton(driver, GamepadKeys.Button.Y);
        spinIntakeOut.whileHeld(new SpinOuttakeCommand(intakeSpinner));

        GamepadButton armHomeButton = new GamepadButton(driver, GamepadKeys.Button.A);
        armHomeButton.whenPressed(new HomeArmCommand(arm));

        GamepadButton armCommandButton = new GamepadButton(driver, GamepadKeys.Button.Y);
        armCommandButton.whenPressed(new ControlArmCommand(arm,1000, telemetry));
}
}
