package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

public class MoveDuckSpinnerDownCommand extends CommandBase {
    private DuckSpinner duckspinner;

    public MoveDuckSpinnerDownCommand(DuckSpinner duckSpinner) {
        this.duckspinner = duckspinner;
        addRequirements(duckspinner);
    }


    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        duckspinner.down();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}