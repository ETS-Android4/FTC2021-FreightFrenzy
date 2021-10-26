package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

public class MoveOuttakeDownCommand extends CommandBase {

    private Outtake outtake;

    public MoveOuttakeDownCommand(Outtake outtake) {
        this.outtake = outtake;
        addRequirements(outtake);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        outtake.down();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
