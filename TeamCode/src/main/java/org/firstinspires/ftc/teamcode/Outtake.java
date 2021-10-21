package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Outtake {

    private Servo servo; 

    public Outtake(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "servo");
    }

    public void up() {
        servo.setPosition(0);
    }


    public void down() {
        servo.setPosition(1);
    }


}
