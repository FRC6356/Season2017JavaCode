package org.usfirst.frc.team6356.robot.subsystems;

import org.usfirst.frc.team6356.robot.RobotMap;
import org.usfirst.frc.team6356.robot.Joysticks;
import org.usfirst.frc.team6356.robot.commands.CommandDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem{
	
	RobotDrive tankDrive;
	
	public DriveSubsystem(){
		super();
		
		tankDrive =  new RobotDrive(RobotMap.Pwm.leftTopDrive,RobotMap.Pwm.leftBotDrive,RobotMap.Pwm.rightTopDrive,RobotMap.Pwm.rightBotDrive);
		
	}
	public void driveWithTankMode(double leftSpeed, double rightSpeed){
		tankDrive.tankDrive(leftSpeed, rightSpeed);
	}
	
	public void driveWithTankMode(Joystick joystick){
		double tank[] = Joysticks.get_driveAxis();
		driveWithTankMode(tank[0],tank[1]);
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new CommandDrive());
	}

	
}
