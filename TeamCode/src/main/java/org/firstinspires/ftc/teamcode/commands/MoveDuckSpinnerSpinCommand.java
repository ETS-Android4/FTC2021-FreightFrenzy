package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.DuckSpinner;

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