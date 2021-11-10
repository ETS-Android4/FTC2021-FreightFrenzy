package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSpinner;

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
