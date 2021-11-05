package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSpinner extends SubsystemBase {

    private DcMotor dcMotor;

    public IntakeSpinner(HardwareMap hardwareMap){
        dcMotor = hardwareMap.get(DcMotor.class, "IntakeSpinner");
    }

    public void intake() {dcMotor.setPower(1);
    }

    public void stop() {dcMotor.setPower(0);
    }

    public void outtake() {dcMotor.setPower(-1);
    }
}
