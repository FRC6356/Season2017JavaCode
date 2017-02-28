package org.usfirst.frc.team6356.robot.commands;

import org.usfirst.frc.team6356.robot.Robot;
import org.usfirst.frc.team6356.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandLockRope extends Command {

    public CommandLockRope() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!Robot.climber.ropeIsLocked()){
    		Robot.climber.lockLocker();
    	}
    	else if(Robot.climber.ropeIsLocked()){
    		Robot.climber.releaseLocker();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
