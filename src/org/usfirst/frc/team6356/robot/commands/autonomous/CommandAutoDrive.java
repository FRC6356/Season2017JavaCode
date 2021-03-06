package org.usfirst.frc.team6356.robot.commands.autonomous;

import org.usfirst.frc.team6356.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandAutoDrive extends Command {

	private double time;
	private Timer timer;
    public CommandAutoDrive(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.mecanumDrive);
    	this.time = time;
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	Robot.mecanumDrive.resetTurnPID();
    	Robot.mecanumDrive.setTurnPID(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.mecanumDrive.driveWithMecanumDrive(0, -0.3, 0, 0);
    	Robot.mecanumDrive.startAutoTurn();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (timer.get()>time);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.mecanumDrive.driveWithMecanumDrive(0, 0, 0, 0);
    	Robot.mecanumDrive.endAutoTurn();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
