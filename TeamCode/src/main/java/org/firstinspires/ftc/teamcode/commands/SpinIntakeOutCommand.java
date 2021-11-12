package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSpinner;

public class SpinIntakeOutCommand extends CommandBase {
    private IntakeSpinner intakeSpinner;

    public SpinIntakeOutCommand(IntakeSpinner outtakeSpinner) {
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
