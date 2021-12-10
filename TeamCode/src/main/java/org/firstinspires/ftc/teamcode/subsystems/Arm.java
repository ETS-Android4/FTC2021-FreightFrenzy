package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm extends SubsystemBase {

    private Telemetry telemetry;
    private DcMotorEx motor;
    private TouchSensor retractLimit;

    public Arm(HardwareMap hardwareMap, Telemetry telemetry) {
        if (telemetry == null) {
            throw new RuntimeException("Arm subsystem null telemetry");
        }

        // Setup motor and limit switch
        motor = hardwareMap.get(DcMotorEx.class, "arm");
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        retractLimit = hardwareMap.get(TouchSensor.class, "retractLimit");
        this.telemetry = telemetry;
    }

    // Add stuff to move arm out, in, and stop

    public void extend() {
        if (isFullyExtended()) {
            stop();
            return;
        }
        telemetry.addData("ArmState", "extending" );
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setPower(0.5);
    }


    public void retract() {
        if (isFullyRetracted()){
            stop();
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            return;
        }
        telemetry.addData("ArmState", "retracting" );
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setPower(-0.5);
    }

    public double getExtensionCounts() {
        return motor.getCurrentPosition();
    }

    public void stop() {
        telemetry.addData("ArmState", "stop" );
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setPower(0);
    }



    // Add an isFullyExtended
    public boolean isFullyExtended() {
        return getExtensionCounts() >= 1750;
    }

    // Add an isFullyRetracted
    public boolean isFullyRetracted() {
        return retractLimit.isPressed();
    }

    @Override
    public void periodic() {

        if (telemetry == null) {
            throw new RuntimeException("Arm periodic null telemetry");
        }
        telemetry.addData("ArmExtensionCounts", getExtensionCounts());
        telemetry.addData("isFullyRetracted", isFullyRetracted());
        telemetry.addData("isFullyExtended", isFullyExtended());
        telemetry.update();

    }
}

