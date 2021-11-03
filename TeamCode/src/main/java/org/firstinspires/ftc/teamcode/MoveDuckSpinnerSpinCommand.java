package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

public class MoveDuckSpinnerSpinCommand extends CommandBase {
    private DuckSpinner duckspinner;

    public MoveDuckSpinnerSpinCommand(DuckSpinner duckSpinner) {
        this.duckspinner = duckSpinner;
        addRequirements(duckspinner);
    }


    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        duckspinner.spin();
    }

    @Override
    public boolean isFinished() {
    return false;
    }

    @Override
    public void end(boolean interrupted) {
        duckspinner.stop();

    }
}