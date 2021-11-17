package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Outtake;

public class TiltOuttakeOutCommand extends CommandBase {

    private Outtake outtake;

    public TiltOuttakeOutCommand(Outtake outtake) {
        this.outtake = outtake;
        addRequirements(outtake);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        outtake.out();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
