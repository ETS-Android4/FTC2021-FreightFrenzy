package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

public class ControlArmCommand extends CommandBase {

    private final double kTollerance = 50;

    public final Arm arm_;

    private final double goalCounts_;

    public ControlArmCommand(Arm arm, double counts) {
        goalCounts_ = counts;
        arm_ = arm;
    }

    public void initialize() {
    }

    public void execute() {
        double error = goalCounts_ - arm_.getExtensionCounts();
        if (error > 0) {
            arm_.extend();
        } else {
            arm_.retract();
        }
    }

    public boolean isFinished() {
        return Math.abs(goalCounts_ - arm_.getExtensionCounts()) < kTollerance;
    }

    public void end(boolean interrupted) {
        arm_.stop();
    }

}