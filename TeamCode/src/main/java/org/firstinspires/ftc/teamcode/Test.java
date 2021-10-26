package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Test {

    private Servo servo;

    public Test(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "servo");
    }

    public void up() {
        servo.setPosition(1);
    }

    public void down() {
        servo.setPosition(0);
    }


}
