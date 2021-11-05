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

@TeleOp

public class DriveOpMode extends CommandOpMode {


    private DuckSpinner duckSpinner;
    private Drive drive;

    @Override
    public void initialize(){
        drive = new Drive(hardwareMap);
        duckSpinner = new DuckSpinner(hardwareMap);
        
        drive.setDefaultCommand
                (new DriveWithGamepadCommand(gamepad1, drive)
        );
        
        GamepadEx driver = new GamepadEx(gamepad1);

        GamepadButton moveUpButton = new GamepadButton(driver, GamepadKeys.Button.A);
        moveUpButton.whileHeld(new MoveDuckSpinnerSpinCommand(duckSpinner));
    }
}
}
