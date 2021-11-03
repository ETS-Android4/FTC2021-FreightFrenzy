package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.Gamepad;

public class DriveWithGamepadCommand extends CommandBase {
    private Gamepad gamepad;
    private Drive drive;

    public DriveWithGamepadCommand(Gamepad gamepad, Drive drive) {
        this.gamepad = gamepad;
        this.drive = drive;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double forward = gamepad.left_stick_y;
        double turn = gamepad.left_stick_x;
        double strafe = gamepad.right_stick_x;

        forward = -forward;

       drive.arcadeDrive(forward,turn,strafe, true);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
