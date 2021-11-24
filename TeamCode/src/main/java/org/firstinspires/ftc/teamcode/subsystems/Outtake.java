package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Outtake extends SubsystemBase {

    private Servo servo; 

    public Outtake(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "servoTheGreat");
    }

    public void in() {
        servo.setPosition(0.675);
    }


    public void out() {
        servo.setPosition(0.1);
    }


}
