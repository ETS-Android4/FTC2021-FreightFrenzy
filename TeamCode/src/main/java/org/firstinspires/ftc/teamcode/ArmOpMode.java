package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp

public class ArmOpMode extends CommandOpMode {

    private Arm arm;

    public void initialize(){

        arm = new Arm(hardwareMap, telemetry);

        // command stuff
        GamepadEx driver = new GamepadEx(gamepad1);

        GamepadButton armHomeButton = new GamepadButton(driver, GamepadKeys.Button.A);
        armHomeButton.whenPressed(new HomeArmCommand(arm));

        GamepadButton armCommandButton = new GamepadButton(driver, GamepadKeys.Button.Y);
        armCommandButton.whenPressed(new ControlArmCommand(arm,1000, telemetry));
    }

}