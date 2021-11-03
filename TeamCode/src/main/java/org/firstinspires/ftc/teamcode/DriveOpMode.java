package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp

public class DriveOpMode extends OpMode {

private Drive drive;

    public void init(){
      drive = new Drive(hardwareMap);
    }

    public void loop(){
        // gamepad
        double forward = gamepad1.left_stick_y;
        double turn = gamepad1.left_stick_x;
        double strafe = gamepad1.right_stick_x;

        forward = -forward;

        forward = inputCurve(forward);

        turn = inputCurve(turn);

        strafe = inputCurve(strafe);




    }

    public double inputCurve(double input) {
        if(0 <= input) {
            // input is postive
            return input * input;
        } else {
            // input is negative
            return -(input * input);
        }
    }


}