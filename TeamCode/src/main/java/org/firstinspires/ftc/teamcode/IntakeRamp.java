package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeRamp {

    private Servo servo;

    public IntakeRamp(HardwareMap hardwareMap){
        servo = hardwareMap.get(Servo.class, "servo");
    }

    public void up() {
        servo.setPosition(1);
    }

    public void down() {
        servo.setPosition(0);
    }
}
