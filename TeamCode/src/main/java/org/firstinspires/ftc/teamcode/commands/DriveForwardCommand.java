package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Drive;

public class DriveForwardCommand extends CommandBase {
    private Drive drive;
    private double goal;
    private double power;
    private boolean isFinished;

    public DriveForwardCommand(Drive drive, double goal, double power) {
        this.drive = drive;
        this.goal = goal;
        this.power = power;
        addRequirements(drive);
    }
    @Override
    public void initialize() {
        isFinished = false;
        drive.stop();
    }

    @Override
    public void execute() {
        drive.runToPosition(goal, power);
        double error = goal - drive.getLeftFrontMotorInches();
        double kTolerance = 0.25;
        isFinished = Math.abs(error) < kTolerance;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }
}
