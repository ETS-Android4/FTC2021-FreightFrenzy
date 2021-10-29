package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

public class HomeArmCommand extends CommandBase {

    public final Arm arm_;

    public HomeArmCommand(Arm arm, double counts) {
        arm_ = arm;
    }

    public void initialize() {
    }

    public void execute() {
        arm_.retract();
    }

    public boolean isFinished() {
        return arm_.isFullyRetracted();
    }

    public void end(boolean interrupted) {
        arm_.stop();
    }

}