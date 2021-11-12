package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Arm;

public class RetractArmCommand extends CommandBase {

    private final Arm arm_;


    public RetractArmCommand(Arm arm) {
        arm_ = arm;
        addRequirements(arm);
    }

    public void initialize() {
    }

    public void execute() {
        arm_.retract();

    }

    public boolean isFinished() {
        return false;
    }

    public void end(boolean interrupted) {
        arm_.stop();
    }
}