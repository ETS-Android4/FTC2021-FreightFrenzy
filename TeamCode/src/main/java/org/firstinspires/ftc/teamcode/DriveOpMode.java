package org.firstinspires.ftc.teamcode;
import com.arcrobotics.ftclib.command.CommandOpMode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.robocol.Command;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Command;

@TeleOp

public class DriveOpMode extends CommandOpMode {


    private DuckSpinner duckSpinner;
    private Drive drive;
    private Outtake outtake;
    private IntakeRamp intakeramp;
    private IntakeSpinner intakeSpinner;

    @Override
    public void initialize(){
        drive = new Drive(hardwareMap);
        duckSpinner = new DuckSpinner(hardwareMap);
        outtake = new Outtake(hardwareMap);
        
        drive.setDefaultCommand
                (new DriveWithGamepadCommand(gamepad1, drive)
        );
        
        GamepadEx driver = new GamepadEx(gamepad1);

        GamepadButton moveUpButton = new GamepadButton(driver, GamepadKeys.Button.A);
        moveUpButton.whileHeld(new MoveDuckSpinnerSpinCommand(duckSpinner));
        
        GamepadButton moveUpButton = new GamepadButton(driver, GamepadKeys.Button.A);
        moveUpButton.whileHeld(new MoveOuttakeUpCommand(outtake));

        GamepadButton moveDownButton = new GamepadButton(driver, GamepadKeys.Button.B);
        moveDownButton.whileHeld(new MoveOuttakeDownCommand(outtake));
        intakeramp = new IntakeRamp(hardwareMap);
        intakeSpinner = new IntakeSpinner(hardwareMap);

        GamepadButton moveUpButton = new GamepadButton(driver, GamepadKeys.Button.A);
        moveUpButton.whileHeld(new MoveIntakeRampUpCommand(intakeramp));

        GamepadButton moveDownButton = new GamepadButton(driver, GamepadKeys.Button.B);
        moveDownButton.whileHeld(new MoveIntakeRampDownCommand(intakeramp));

        GamepadButton spinIntakeIn = new GamepadButton(driver, GamepadKeys.Button.X);

        spinIntakeIn.whileHeld(new SpinIntakeCommand(intakeSpinner));
        GamepadButton spinIntakeOut = new GamepadButton(driver, GamepadKeys.Button.Y);
        spinIntakeOut.whileHeld(new SpinOuttakeCommand(intakeSpinner));
}
}
