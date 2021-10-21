package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class DriveForwardsOpMode extends OpMode {

    private DcMotor leftFrontMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightFrontMotor;
    private DcMotor rightBackMotor;

    private int distance = 12;

    public void init(){
        leftFrontMotor = hardwareMap.get(DcMotor.class, "drive_lf");
        leftBackMotor = hardwareMap.get(DcMotor.class, "drive_lb");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "drive_rf");
        rightBackMotor = hardwareMap.get(DcMotor.class, "drive_rb");

        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBackMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);


    }

    public void loop(){
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        int goal = distance; // TODO convert from inches to counts


        rightBackMotor.setTargetPosition(goal);

    }

}