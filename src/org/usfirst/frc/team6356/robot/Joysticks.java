package org.usfirst.frc.team6356.robot;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team6356.robot.RobotMap;


public class Joysticks {
	private static final Joystick DRIVER = new Joystick(RobotMap.LeftJoystick.portNumber);
	private static final Joystick OPERATOR = new Joystick(RobotMap.RightJoystick.portNumber);
	
	public static Joystick get_DRIVER(){
		return DRIVER;
	}
	
	public static Joystick get_OPERATOR(){
		return OPERATOR;
	}
	
	public static double[] get_driveAxis(){
		double left = DRIVER.getRawAxis(RobotMap.LeftJoystick.leftVerticalAxis);
		double right = DRIVER.getRawAxis(RobotMap.LeftJoystick.rightVerticalAxis);
		return new double[]{left, right};
	}
	
	public static double[] getMecanumAxis(){
		//need update airplane stick port in robot map
		double x = DRIVER.getRawAxis(RobotMap.LeftJoystick.leftHorizontalAxis);
		double y = DRIVER.getRawAxis(RobotMap.LeftJoystick.rightVerticalAxis);
		double rotate = getRotateAxis();
		return new double[]{x, y, rotate};
	}
	
	public static double getRotateAxis(){
		double rotation = 0;
		if(DRIVER.getRawAxis(RobotMap.LeftJoystick.leftTrigger)!=0){
			rotation = - DRIVER.getRawAxis(RobotMap.LeftJoystick.leftTrigger);
		}
		
		if(DRIVER.getRawAxis(RobotMap.LeftJoystick.rightTrigger)!=0){
			rotation = DRIVER.getRawAxis(RobotMap.LeftJoystick.rightTrigger);
		}
		return rotation;
	}
}
