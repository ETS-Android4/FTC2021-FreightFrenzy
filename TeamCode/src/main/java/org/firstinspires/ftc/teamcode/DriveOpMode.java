package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class DriveOpMode extends CommandOpMode {

    private DcMotor leftFrontMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightFrontMotor;
    private DcMotor rightBackMotor;


    private DuckSpinner duckSpinner;

    public void initialize() {
        leftFrontMotor = hardwareMap.get(DcMotor.class, "drive_lf");
        leftBackMotor = hardwareMap.get(DcMotor.class, "drive_lb");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "drive_rf");
        rightBackMotor = hardwareMap.get(DcMotor.class, "drive_rb");

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBackMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        duckSpinner = new DuckSpinner(hardwareMap);
        // command stuff
        GamepadEx driver = new GamepadEx(gamepad1);

        GamepadButton moveUpButton = new GamepadButton(driver, GamepadKeys.Button.A);
        moveUpButton.whileHeld(new MoveDuckSpinnerUpCommand(duckSpinner));

        GamepadButton moveDownButton = new GamepadButton(driver, GamepadKeys.Button.A);
        moveUpButton.whileHeld(new MoveDuckSpinnerDownCommand(duckSpinner));

    }

}