package org.usfirst.frc.team6356.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.Victor;
import org.usfirst.frc.team6356.robot.ModuleManager;

public class RobotMap {
	
	public static Spark leftFrontMotor;
	public static Spark leftRearMotor;
	public static Spark rightFrontMotor;
	public static Spark rightRearMotor;
	
	public static Victor intakeFront;
	public static Victor intakeSide;
	
	public static Victor shooter;
	
	public static Victor climber;
	
	public static Solenoid leftYellowLED;
	public static Solenoid rightYellowLED;
	
	public static Solenoid ropeLocker;
	public static Solenoid ropeGrabber;
	
	public static ADXRS450_Gyro gyro;
	public static Ultrasonic ultrasonic;
	
	
	public static void init(){
		if(ModuleManager.DRIVE_MODULE_ON){
			leftFrontMotor = new Spark(Pwm.leftTopDrive);
			leftRearMotor = new Spark(Pwm.leftBotDrive);
			rightFrontMotor = new Spark(Pwm.rightTopDrive);
			rightRearMotor = new Spark(Pwm.rightBotDrive);
			System.out.println("Drive Motors initialized!");
		}
		
		if(ModuleManager.INTAKE_MODULE_ON){
			intakeFront = new Victor(Pwm.intakeFront);
			intakeSide = new Victor(Pwm.intakeSide);
			System.out.println("Intake Motors initialized!");
		}
		
		if(ModuleManager.SHOOT_MODULE_ON){
			shooter = new Victor(Pwm.shooter);
			System.out.println("Shooter Motors initialized!");
		}
		
		if(ModuleManager.SOLENOID_MODULE_ON){
			leftYellowLED = new Solenoid(SolenoidPorts.leftYellowLED);
			rightYellowLED = new Solenoid(SolenoidPorts.rightYEllowLED);
			System.out.println("LEDs initialized!");
			
			ropeLocker = new Solenoid(SolenoidPorts.ropeLocker);
			ropeGrabber = new Solenoid(SolenoidPorts.ropeGrabber);
			System.out.println("Rope Grabbers' Solenoids initialized!");
			
		}
		
		if(ModuleManager.SENSOR_MODULE_ON){
			gyro = new ADXRS450_Gyro();
			ultrasonic = new Ultrasonic(0, 1, Unit.kMillimeters);
			System.out.println("Sensors initialized!");
		}
	}
	public static class Pwm{
		public static final int leftTopDrive = 0;
		public static final int leftBotDrive = 1;
		public static final int rightTopDrive = 2;
		public static final int rightBotDrive = 3;
		public static final int intakeFront = 4;
		public static final int intakeSide = 5;
		public static final int shooter = 6;
		public static final int climber = 7;
	}
	
	public static class SolenoidPorts{
		public static final int leftYellowLED = 0;
		public static final int rightYEllowLED = 1;
		public static final int ropeLocker = 2;
		public static final int ropeGrabber = 3;
	}
	public static class LeftJoystick{
		public static final int portNumber = 0;
		public static final int leftHorizontalAxis = 0;
		public static final int leftVerticalAxis = 1;
		public static final int rightHorizontalAxis = 4;
		public static final int rightVerticalAxis = 5;
		public static final int leftTrigger = 2;
		public static final int rightTrigger = 3;
	}
	
	public static class RightJoystick{
		public static final int portNumber = 1;
		public static final int xAxis = 0;
		public static final int yAxis = 1;
		public static final int rotateAxis = 2;
		public static final int rightYAxis = 5;
	}
	
	public static class IO{
		public static final int gyro = 0;
	}
}
