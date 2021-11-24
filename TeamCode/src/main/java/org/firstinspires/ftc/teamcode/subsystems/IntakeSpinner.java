package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSpinner extends SubsystemBase {

    private DcMotor dcMotor;
    private Servo servo;

    public IntakeSpinner(HardwareMap hardwareMap){
        dcMotor = hardwareMap.get(DcMotor.class, "intakeSpinner");
        servo = hardwareMap.get(Servo.class, "servoSpinner");
        servo.setDirection(Servo.Direction.REVERSE);
        dcMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        servo.setPosition(0.0);
    }

    public void intake() {
        dcMotor.setPower(1);
        servo.setPosition(0.95);
    }

    public void stop() {
        dcMotor.setPower(0);
        servo.setPosition(0.3);

    }

    public void outtake() {
        dcMotor.setPower(-1);
        servo.setPosition(1.0);

    }
}
