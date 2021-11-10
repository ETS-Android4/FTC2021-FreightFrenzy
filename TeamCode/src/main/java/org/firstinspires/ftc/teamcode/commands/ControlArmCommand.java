package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Arm;

public class ControlArmCommand extends CommandBase {

    private final double kTollerance = 50;
    private final Arm arm_;
    private final double goalCounts_;
    private Telemetry telemetry_;


    public ControlArmCommand(Arm arm, double counts, Telemetry telemetry) {
        goalCounts_ = counts;
        arm_ = arm;
        telemetry_ = telemetry;
        addRequirements(arm);
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
        telemetry_.addData("armError",error);

    }

    public boolean isFinished() {
        return Math.abs(goalCounts_ - arm_.getExtensionCounts()) < kTollerance;
    }

    public void end(boolean interrupted) {
        arm_.stop();
    }

}