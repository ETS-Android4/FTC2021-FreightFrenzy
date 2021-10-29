package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.Gamepad;

public class driveWithGamepadCommand extends CommandBase {

    private Gamepad gamepad;
    private Drive drive;

    public driveWithGamepadCommand(Gamepad gamepad, Drive drive) {
        this.gamepad = gamepad;
        this.drive = drive;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
// gamepad
        double forward = gamepad1.left_stick_y;
        double turn = gamepad1.left_stick_x;
        double strafe = gamepad1.right_stick_x;

        forward = -forward;

        forward = inputCurve(forward);

        turn = inputCurve(turn);

        strafe = inputCurve(strafe);

        //Calculate speed for each motor
        drive.arcadeDrive(forward, turn, strafe);
    }

    @Override
    public boolean isFinished() { return false; }

    @Override
    public void end(boolean interrupted) {

    }
}