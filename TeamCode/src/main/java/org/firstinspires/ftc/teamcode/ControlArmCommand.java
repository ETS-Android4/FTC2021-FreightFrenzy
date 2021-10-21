package org.firstinspires.ftc.teamcode;

public class ControlArmCommand{

    private final double kTollerance = 50;

    public final Arm arm_;

    private final double goalCounts_;

    public ControlArmCommand(Arm arm, double counts) {
        goalCounts_ = counts;
        arm_ = arm;
    }

    protected void initialize() {
    }

    protected void execute() {
        double error = goalCounts_ - arm_.getExtensionCounts();
        if (error > 0) {
            arm_.extend();
        } else {
            arm_.retract();
        }
    }

    protected boolean isFinished() {
        return Math.abs(goalCounts_ - arm_.getExtensionCounts()) < kTollerance;
    }

    protected void end() {
        arm_.stop();
    }

    protected void interrupted() {
        this.end();
    }
}