package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.Gamepad;

public class DriveWithGamepadCommand extends CommandBase {
    private Gamepad gamepad;
    private Drive drive;


    public DriveWithGamepadCommand(Gamepad gamepad, Drive drive) {
        this.gamepad = gamepad;
        this.drive = drive;
        addRequirements(drive);
    }
    @Override
    public void initialize() {
    }
        @Override
        public void execute() {

    }

    @Override
    public boolean isFinished() { return false; }


    @Override
    public void end(boolean interrupted) {

    }

}
