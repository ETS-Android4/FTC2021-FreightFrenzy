package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

public class MoveIntakeRampDownCommand extends CommandBase {
    private IntakeRamp intakeramp;

    public MoveIntakeRampDownCommand(IntakeRamp intakeramp) {
        this.intakeramp = intakeramp;
        addRequirements(intakeramp);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        intakeramp.down();

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
