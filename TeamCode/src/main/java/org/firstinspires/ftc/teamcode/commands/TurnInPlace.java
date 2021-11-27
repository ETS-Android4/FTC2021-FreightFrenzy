package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.Drive;

public class TurnInPlace extends CommandBase {

    private Drive drive = null;
    private double goal;
    private boolean isFinished;

    public TurnInPlace (Drive drive, double goal) {

        this.drive = drive;
        addRequirements(drive);

        this.goal = goal;

    }

    @Override
    public void initialize() {
        isFinished = false;
    }

    @Override
    public void execute() {

        //get our heading
        double heading = drive.getHeading();
        //calculate error: e = goal - heading
        double error = goal - heading;
        //figure out how much to turn :(
        double kP = 0.00169;
        double output = error * kP;
        double floor = 0.1;
        if (Math.abs(output) < floor) {
            output = Math.copySign(floor, output);
        }
        //turn :)
        drive.arcadeDrive(0,output, 0, false);

        double kTolerance = 2;
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
