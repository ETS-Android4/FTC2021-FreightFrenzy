package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DuckSpinner extends SubsystemBase {

    private DcMotor dcMotor;

    public DuckSpinner(HardwareMap hardwareMap) {

       dcMotor  = hardwareMap.get(DcMotor.class,  "spinner");
    }
   public void stop() {
        dcMotor.setPower(0);
   }
   public void spin() {
      dcMotor.setPower(1);
   }

}
