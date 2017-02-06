package org.usfirst.frc.team6356.robot.commands;

import org.usfirst.frc.team6356.robot.Joysticks;
import org.usfirst.frc.team6356.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandMecanumDrive extends Command{

	public CommandMecanumDrive(){
		requires(Robot.mecanumDrive);
		requires(Robot.pidRotation);
	}
	
	protected void initialize(){
		
	}
	
	protected void execute() {
		double speed = Robot.sensors.getAngle();
		Robot.mecanumDrive.driveWithMecnumDrive(Joysticks.get_DRIVER());
		SmartDashboard.putNumber("angle", speed);
		Robot.pidRotation.enable();
		LiveWindow.run();
		Robot.vision.log();
		
	}
	
	@Override
	protected boolean isFinished() {
		return false;
		//never finished while driving with Joystick.
	}
	
	protected void end() {
    	Robot.mecanumDrive.driveWithMecanumDrive(0, 0, 0, 0);
    	Robot.pidRotation.disable();
	}
	
	protected void interrupted() {
    	end();
    }
}
