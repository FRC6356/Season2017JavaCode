package org.usfirst.frc.team6356.robot.subsystems;

import org.usfirst.frc.team6356.robot.Robot;
import org.usfirst.frc.team6356.robot.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SensorSubsystem extends Subsystem{

	private ADXRS450_Gyro gyro;
	private Ultrasonic ultrasonic;
	
	public SensorSubsystem(){
		gyro = RobotMap.gyro;
		ultrasonic = RobotMap.ultrasonic;
		ultrasonic.setAutomaticMode(true);
	}
	public ADXRS450_Gyro getGyro(){
		return gyro;
	}
	
	public double getAngle(){
		return gyro.getAngle();
	}
	
	public void resetGyro(){
		gyro.reset();
	}
	
	public double getDistance(){
		return ultrasonic.getRangeMM();
	}
	
	public double getAdjustAngle(){
		try{
			double adjustAngle = (Robot.vision.getCenterX()[1] - (680/2)) * Robot.vision.degreePerPixel();
			return adjustAngle;
		}
		catch(Exception e){
			
		}
		return getAngle();
	}
	
	@Override
	protected void initDefaultCommand() {
		//no default command
	}

}
