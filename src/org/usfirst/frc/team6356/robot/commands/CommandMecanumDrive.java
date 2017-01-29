package org.usfirst.frc.team6356.robot.commands;

import org.usfirst.frc.team6356.robot.Joysticks;
import org.usfirst.frc.team6356.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandMecanumDrive extends Command{

	public CommandMecanumDrive(){
		requires(Robot.mecanumDrive);
	}
	
	protected void initialize(){
		
	}
	
	protected void execute() {
		Robot.mecanumDrive.driveWithMecnumDrive(Joysticks.get_OPERATOR());
	}
	
	@Override
	protected boolean isFinished() {
		return false;
		//never finished while driving with Joystick.
	}
	
	protected void end() {
    	Robot.mecanumDrive.driveWithMecanumDrive(0, 0, 0, 0);
    }
	
	protected void interrupted() {
    	end();
    }
}
