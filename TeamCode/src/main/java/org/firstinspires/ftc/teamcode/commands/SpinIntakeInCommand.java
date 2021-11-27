package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSpinner;

public class SpinIntakeInCommand extends CommandBase {
    private IntakeSpinner intakeSpinner;
    private Arm arm;

    public SpinIntakeInCommand(IntakeSpinner intakeSpinner, Arm arm) {
        this.intakeSpinner = intakeSpinner;
        this.arm = arm;
        addRequirements(intakeSpinner);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (arm.isFullyRetracted()) {
            intakeSpinner.intake();
        } else {
            intakeSpinner.stop();
        }

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
