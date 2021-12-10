package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.DuckSpinner;

public class MoveDuckSpinnerSpinBlueCommand extends CommandBase {
    private DuckSpinner duckspinner;

    public MoveDuckSpinnerSpinBlueCommand(DuckSpinner duckSpinner) {
        this.duckspinner = duckSpinner;
        addRequirements(duckspinner);
    }


    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        duckspinner.spinBlue();
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