package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class DriveOpMode extends OpMode {

    private DcMotor leftFrontMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightFrontMotor;
    private DcMotor rightBackMotor;

    private IntakeRamp intakeramp;

    public void init(){
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

        intakeramp = new IntakeRamp(hardwareMap);
    }

    public void loop() {
        // gamepad
        double forward = gamepad1.left_stick_y;
        double turn = gamepad1.left_stick_x;
        double strafe = gamepad1.right_stick_x;

        forward = -forward;

        forward = inputCurve(forward);

        turn = inputCurve(turn);

        strafe = inputCurve(strafe);

        //Calculate speed for each motor
        double frontLeft = forward + turn + strafe;
        double frontRight = forward - turn - strafe;
        double backLeft = forward + turn - strafe;
        double backRight = forward - turn + strafe;

        //set motor
        leftFrontMotor.setPower(frontLeft);
        leftBackMotor.setPower(backLeft);
        rightFrontMotor.setPower(frontRight);
        rightBackMotor.setPower(backRight);

        //servo stuff
        if (gamepad1.a){
            intakeramp.up();
        }
        if (gamepad1.b){
            intakeramp.down();
        }

        telemetry.addData("forward", forward);
        telemetry.addData("frontLeft", frontLeft);
        telemetry.addData("frontRight", frontRight);
        telemetry.addData("backLeft", backLeft);
        telemetry.addData("backRight", backRight);
        telemetry.update();

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