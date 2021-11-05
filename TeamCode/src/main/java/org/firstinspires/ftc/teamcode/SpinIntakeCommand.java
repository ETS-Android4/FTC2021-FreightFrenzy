package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

public class SpinIntakeCommand extends CommandBase {
    private IntakeSpinner intakeSpinner;

    public SpinIntakeCommand(IntakeSpinner intakeSpinner) {
        this.intakeSpinner = intakeSpinner;
        addRequirements(intakeSpinner);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        intakeSpinner.intake();

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
