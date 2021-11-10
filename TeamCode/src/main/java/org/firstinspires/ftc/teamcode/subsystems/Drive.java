package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drive extends SubsystemBase {

    private DcMotor leftFrontMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightFrontMotor;
    private DcMotor rightBackMotor;

    public Drive(HardwareMap hardwareMap) {
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
    }

    public void arcadeDrive(double forward, double turn, double strafe, boolean curve) {
        if (curve) {
            forward = inputCurve(forward);
            turn = inputCurve(turn);
            strafe = inputCurve(strafe);
        }

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
    }

    public double inputCurve(double input) {
        if (0 <= input) {
            // input is positive
            return input * input;
        } else {
            // input is negative
            return -(input * input);
        }
    }
}
