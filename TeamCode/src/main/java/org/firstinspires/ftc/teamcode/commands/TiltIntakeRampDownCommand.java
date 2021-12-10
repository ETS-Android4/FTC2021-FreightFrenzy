package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeRamp;

public class TiltIntakeRampDownCommand extends CommandBase {
    private IntakeRamp intakeramp;

    public TiltIntakeRampDownCommand(IntakeRamp intakeramp) {
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
