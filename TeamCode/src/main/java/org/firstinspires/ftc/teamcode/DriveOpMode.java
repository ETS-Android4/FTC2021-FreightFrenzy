package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.robocol.Command;

@TeleOp

public class DriveOpMode extends CommandOpMode {

    private Drive drive;

    public void initialize(){
        drive = new Drive(hardwareMap);

        drive.setDefaultCommand(
                new DriveWithGamepadCommand(gamepad1, drive)
        );
    }

    }