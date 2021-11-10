package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeRamp;

public class MoveIntakeRampUpCommand extends CommandBase {
    private IntakeRamp intakeramp;

    public MoveIntakeRampUpCommand (IntakeRamp intakeramp) {
        this.intakeramp = intakeramp;
        addRequirements(intakeramp);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        intakeramp.up();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
