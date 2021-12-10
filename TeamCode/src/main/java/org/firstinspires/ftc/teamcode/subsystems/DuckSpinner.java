package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DuckSpinner extends SubsystemBase {

    private DcMotor dcMotor;

    public DuckSpinner(HardwareMap hardwareMap) {

       dcMotor  = hardwareMap.get(DcMotor.class,  "spinner");
    }
   public void stop() {
        dcMotor.setPower(0);
   }
   public void spinBlue() {
      dcMotor.setPower(-1);
   }
    public void spinRed() {
        dcMotor.setPower(1);
    }
}
