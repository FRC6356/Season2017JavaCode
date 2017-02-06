package org.usfirst.frc.team6356.robot.subsystems;

import org.usfirst.frc.team6356.robot.RobotMap;
import org.usfirst.frc.team6356.robot.Joysticks;
import org.usfirst.frc.team6356.robot.commands.CommandMecanumDrive;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;


public class MecanumDriveSubsystem extends Subsystem{

	public RobotDrive MecanumDrive;
	
	public MecanumDriveSubsystem(){
		super();
		MecanumDrive = new RobotDrive(RobotMap.Pwm.leftTopDrive, RobotMap.Pwm.leftBotDrive, RobotMap.Pwm.rightTopDrive, RobotMap.Pwm.rightBotDrive);
		MecanumDrive.setInvertedMotor(MotorType.kFrontRight, true);
		MecanumDrive.setInvertedMotor(MotorType.kRearRight, true);
	}
	
	public void driveWithMecanumDrive(double xSpeed, double ySpeed, double rotateSpeed, double gyroAngle){
		MecanumDrive.mecanumDrive_Cartesian(xSpeed, ySpeed, rotateSpeed, gyroAngle);
	}
	
	public void driveWithMecnumDrive(Joystick joystick){
		double[] speed = Joysticks.getMecanumAxis();
		driveWithMecanumDrive(speed[0], speed[1], speed[2], 0);
	}
	
	public void pidRotateDrive(double leftspeed, double rightspeed){
//		MecanumDrive.setLeftRightMotorOutputs(leftOutput, rightOutput);
		MecanumDrive.drive(leftspeed, rightspeed);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CommandMecanumDrive());
	}
	
}
