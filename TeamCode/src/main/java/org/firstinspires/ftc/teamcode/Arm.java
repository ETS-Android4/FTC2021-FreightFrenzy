package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class Arm extends SubsystemBase {

    private DcMotorEx motor;
    private TouchSensor retractLimit;

    public Arm(HardwareMap hardwareMap) {
        // Setup motor and limit switch
        motor = hardwareMap.get(DcMotorEx.class, "arm");
        retractLimit = hardwareMap.get(TouchSensor.class, "retractLimit");
    }

    // Add stuff to move arm out, in, and stop

    public void extend() {
        if (isFullyExtended()) {
            stop();
            return;
        }
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setPower(1);
    }


    public void retract() {
        if (isFullyRetracted()){
            stop();
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            return;
        }
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setPower(-0.5);
    }

    public double getExtensionCounts() {
        return motor.getCurrentPosition();
    }

    public void stop() {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setPower(0);
    }



    // Add an isFullyExtended
    public boolean isFullyExtended() {
        return getExtensionCounts() >= 2048;
    }

    // Add an isFullyRetracted
    public boolean isFullyRetracted() {
        return retractLimit.isPressed();
    }

}

