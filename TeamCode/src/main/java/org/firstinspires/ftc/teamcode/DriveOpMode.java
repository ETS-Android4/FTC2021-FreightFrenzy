package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.DriveWithGamepadCommand;
import org.firstinspires.ftc.teamcode.commands.ExtendArmCommand;
import org.firstinspires.ftc.teamcode.commands.HomeArmCommand;
import org.firstinspires.ftc.teamcode.commands.MoveDuckSpinnerSpinBlueCommand;
import org.firstinspires.ftc.teamcode.commands.MoveDuckSpinnerSpinRedCommand;
import org.firstinspires.ftc.teamcode.commands.RetractArmCommand;
import org.firstinspires.ftc.teamcode.commands.TiltIntakeRampDownCommand;
import org.firstinspires.ftc.teamcode.commands.TiltIntakeRampUpCommand;
import org.firstinspires.ftc.teamcode.commands.TiltOuttakeOutCommand;
import org.firstinspires.ftc.teamcode.commands.TiltOuttakeInCommand;
import org.firstinspires.ftc.teamcode.commands.SpinIntakeInCommand;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.DuckSpinner;
import org.firstinspires.ftc.teamcode.subsystems.IntakeRamp;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSpinner;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;

@TeleOp(name = "TeleOp")

public class DriveOpMode extends CommandOpMode {
    private RevBlinkinLedDriver leds;

    private DuckSpinner duckSpinner;
    private Drive drive;
    private Outtake outtake;
    private IntakeRamp intakeramp;
    private IntakeSpinner intakeSpinner;
    private Arm arm;

    @Override
    public void initialize(){
        drive = new Drive(hardwareMap, telemetry);
        duckSpinner = new DuckSpinner(hardwareMap);
        outtake = new Outtake(hardwareMap);
        arm = new Arm(hardwareMap, telemetry);
        intakeramp = new IntakeRamp(hardwareMap);
        intakeSpinner = new IntakeSpinner(hardwareMap);
        leds = hardwareMap.get(RevBlinkinLedDriver.class, "Blingkin");
        leds.setPattern(RevBlinkinLedDriver.BlinkinPattern.TWINKLES_OCEAN_PALETTE );

        intakeramp.setDefaultCommand(
                new TiltIntakeRampUpCommand(intakeramp)
        );
        drive.setDefaultCommand(
                new DriveWithGamepadCommand(gamepad1, drive)
        );

        // Driver 1
        {
            GamepadEx driver = new GamepadEx(gamepad1);

            GamepadButton duckSpinnerBlueButton = new GamepadButton(driver, GamepadKeys.Button.X);
            duckSpinnerBlueButton.whileHeld(new MoveDuckSpinnerSpinBlueCommand(duckSpinner));
            GamepadButton duckSpinnerRedButton = new GamepadButton(driver, GamepadKeys.Button.B);
            duckSpinnerRedButton.whileHeld(new MoveDuckSpinnerSpinRedCommand(duckSpinner));

            GamepadButton spinIntakeIn = new GamepadButton(driver, GamepadKeys.Button.RIGHT_BUMPER);
            spinIntakeIn.whileHeld(new ParallelCommandGroup(
                    new SpinIntakeInCommand(intakeSpinner, arm),
                    new TiltOuttakeInCommand(outtake),
                    new TiltIntakeRampDownCommand(intakeramp)
            ));

        }

        // Driver2
        {
            GamepadEx driver2 = new GamepadEx(gamepad2);

            GamepadButton moveUpButton = new GamepadButton(driver2, GamepadKeys.Button.A);
            moveUpButton.whileHeld(new TiltOuttakeInCommand(outtake));

            GamepadButton moveDownButton = new GamepadButton(driver2, GamepadKeys.Button.B);
            moveDownButton.whileHeld(new TiltOuttakeOutCommand(outtake));
            moveDownButton.whenPressed(new TiltIntakeRampDownCommand(intakeramp));

            GamepadButton armHomeButton = new GamepadButton(driver2, GamepadKeys.Button.Y);
            armHomeButton.whenPressed(new HomeArmCommand(arm));
            armHomeButton.whenPressed(new TiltOuttakeInCommand(outtake));
            armHomeButton.whenPressed(new TiltIntakeRampUpCommand(intakeramp));

            GamepadButton extendArmButton = new GamepadButton(driver2, GamepadKeys.Button.DPAD_UP);
            extendArmButton.whileHeld(new ExtendArmCommand(arm));

            GamepadButton contractArmButton = new GamepadButton(driver2, GamepadKeys.Button.DPAD_DOWN);
            contractArmButton.whileHeld(new RetractArmCommand(arm));
        }
    }
}
