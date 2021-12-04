package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Outtake;

public class StowOuttakeCommand extends CommandBase {

    private Outtake outtake;

    public StowOuttakeCommand(Outtake outtake) {
        this.outtake = outtake;
        addRequirements(outtake);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        outtake.stow();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
