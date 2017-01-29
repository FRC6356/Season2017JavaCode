package org.usfirst.frc.team6356.robot.subsystems;

import org.usfirst.frc.team6356.robot.RobotMap;
import org.usfirst.frc.team6356.robot.Joysticks;
import org.usfirst.frc.team6356.robot.commands.CommandMecanumDrive;

//import edu.wpi.first.wpilibj.AnalogGyro;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;


public class MecanumDriveSubsystem extends Subsystem{

	RobotDrive MecanumDrive;
	
	public MecanumDriveSubsystem(){
		super();
		MecanumDrive = new RobotDrive(RobotMap.Pwm.leftTopDrive, RobotMap.Pwm.leftBotDrive, RobotMap.Pwm.rightTopDrive, RobotMap.Pwm.rightBotDrive);
	}
	
	public void driveWithMecanumDrive(double xSpeed, double ySpeed, double rotateSpeed, double gyroAngle){
		MecanumDrive.mecanumDrive_Cartesian(xSpeed, ySpeed, rotateSpeed, gyroAngle);
	}
	
	public void driveWithMecnumDrive(Joystick joystick){
		double[] mecanum = Joysticks.getMecanumAxis();
		driveWithMecanumDrive(mecanum[0], mecanum[1], mecanum[2], 0);
	}
	
	public void pidRotateDrive(double speed, double direction){
		MecanumDrive.drive(speed, direction);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CommandMecanumDrive());
		
	}
	
}
