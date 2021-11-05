package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

public class SpinOuttakeCommand extends CommandBase {
    private IntakeSpinner intakeSpinner;

    public SpinOuttakeCommand(IntakeSpinner outtakeSpinner) {
        this.intakeSpinner = outtakeSpinner;
        addRequirements(outtakeSpinner);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        intakeSpinner.outtake();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        intakeSpinner.stop();

    }
}
