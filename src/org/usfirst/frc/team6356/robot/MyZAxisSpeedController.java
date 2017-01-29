package org.usfirst.frc.team6356.robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class MyZAxisSpeedController implements PIDOutput{
	
	private double result;
	@Override
	public void pidWrite(double output) {
		result = output;
	}
	
	public double getPidZAxis(){
		return result;
	}
	
}
