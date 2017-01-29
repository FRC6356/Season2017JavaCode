package org.usfirst.frc.team6356.robot.commands;

import org.usfirst.frc.team6356.robot.Robot;
import org.usfirst.frc.team6356.robot.Joysticks;
import org.usfirst.frc.team6356.vision.Vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class CommandDrive extends Command {

	Vision vision;
	
	public CommandDrive() {
        requires(Robot.driveSubsystem);
    }
	
	protected void initialize(){
		vision = new Vision();
	}
	
	protected void execute() {
		Robot.driveSubsystem.driveWithTankMode(Joysticks.get_DRIVER());
		double distance = vision.getHighGoalDistance();
		SmartDashboard.putNumber("High Goal Distance", distance);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
		//never finished while driving with Joystick.
	}
	
	protected void end() {
    	Robot.driveSubsystem.driveWithTankMode(0, 0);
    }
	
	protected void interrupted() {
    	end();
    }

}
