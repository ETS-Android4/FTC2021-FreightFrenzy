package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

public class MoveOuttakeUpCommand  extends CommandBase {

    private Outtake outtake;

    public MoveOuttakeUpCommand(Outtake outtake) {
        this.outtake = outtake;
        addRequirements(outtake);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        outtake.up();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
