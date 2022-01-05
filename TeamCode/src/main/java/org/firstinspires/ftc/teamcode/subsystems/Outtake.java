package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Outtake extends SubsystemBase {

    private Servo servo;
    private double offset = 0.05 ;

    public Outtake(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "servoTheGreat");
    }

    public void in() {
        servo.setPosition(1.0-offset);
    }


    public void out() {
        servo.setPosition(0.425-offset);
    }

    public void stow() {
        servo.setPosition(0.725-offset);
    }



}
