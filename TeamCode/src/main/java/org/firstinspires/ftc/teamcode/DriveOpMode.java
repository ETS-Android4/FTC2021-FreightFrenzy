package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.ControlArmCommand;
import org.firstinspires.ftc.teamcode.commands.DriveWithGamepadCommand;
import org.firstinspires.ftc.teamcode.commands.ExtendArmCommand;
import org.firstinspires.ftc.teamcode.commands.HomeArmCommand;
import org.firstinspires.ftc.teamcode.commands.MoveDuckSpinnerSpinCommand;
import org.firstinspires.ftc.teamcode.commands.RetractArmCommand;
import org.firstinspires.ftc.teamcode.commands.TiltIntakeRampDownCommand;
import org.firstinspires.ftc.teamcode.commands.TiltIntakeRampUpCommand;
import org.firstinspires.ftc.teamcode.commands.TiltOuttakeDownCommand;
import org.firstinspires.ftc.teamcode.commands.TiltOuttakeUpCommand;
import org.firstinspires.ftc.teamcode.commands.SpinIntakeInCommand;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.DuckSpinner;
import org.firstinspires.ftc.teamcode.subsystems.IntakeRamp;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSpinner;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;

@TeleOp(name = "TeleOp")

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
        intakeRampUp.whileHeld(new TiltIntakeRampUpCommand(intakeramp));

        GamepadButton intakeRampDown = new GamepadButton(driver, GamepadKeys.Button.B);
        intakeRampDown.whileHeld(new TiltIntakeRampDownCommand(intakeramp));

        GamepadButton spinIntakeIn = new GamepadButton(driver, GamepadKeys.Button.RIGHT_BUMPER);
        spinIntakeIn.whileHeld(new SpinIntakeInCommand(intakeSpinner));

        // Driver2
        GamepadEx driver2 = new GamepadEx(gamepad2);

        GamepadButton moveUpButton = new GamepadButton(driver2, GamepadKeys.Button.A);
        moveUpButton.whileHeld(new TiltOuttakeUpCommand(outtake));

        GamepadButton moveDownButton = new GamepadButton(driver, GamepadKeys.Button.B);
        moveDownButton.whileHeld(new TiltOuttakeDownCommand(outtake));

        GamepadButton armHomeButton = new GamepadButton(driver2, GamepadKeys.Button.Y);
        armHomeButton.whenPressed(new HomeArmCommand(arm));

        GamepadButton extendArmButton = new GamepadButton(driver2, GamepadKeys.Button.DPAD_UP);
        extendArmButton.whileHeld(new ExtendArmCommand(arm));

        GamepadButton contractArmButton = new GamepadButton(driver2, GamepadKeys.Button.DPAD_DOWN);
        contractArmButton.whileHeld(new RetractArmCommand(arm));
    }
}
