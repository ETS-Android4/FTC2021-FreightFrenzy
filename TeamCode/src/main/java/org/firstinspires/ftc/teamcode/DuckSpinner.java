package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DuckSpinner extends SubsystemBase {

    private Servo servo;

    public DuckSpinner(HardwareMap hardwareMap) {

        servo = hardwareMap.get(Servo.class,  "servo");
    }
   public void down() {
        servo.setPosition(1);
   }
   public void up() {
      servo.setPosition(0);
   }

}
