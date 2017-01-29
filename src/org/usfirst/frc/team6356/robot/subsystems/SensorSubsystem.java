package org.usfirst.frc.team6356.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SensorSubsystem extends Subsystem{

	private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	
	public double getAngle(){
		return gyro.getAngle();
	}
	
	public void resetGyro(){
		gyro.reset();
	}
	
	@Override
	protected void initDefaultCommand() {
		//no default command
	}

}
