package org.usfirst.frc.team6356.robot;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import org.usfirst.frc.team6356.robot.subsystems.MecanumDriveSubsystem;
import org.usfirst.frc.team6356.robot.subsystems.PidRotationSubsystem;
import org.usfirst.frc.team6356.robot.subsystems.SensorSubsystem;
import org.usfirst.frc.team6356.vision.Vision;
import org.usfirst.frc.team6356.robot.autonomous.AutoRotation;


public class Robot extends IterativeRobot {
	
	public static MecanumDriveSubsystem mecanumDrive;
	public static PidRotationSubsystem pidRotation;
	public static SensorSubsystem sensors;
	public static Vision vision;
	public static OI oi;
	public Command autoRotation;
	public static double test;
	
	public Robot() {
		
	}

	@Override
	public void robotInit(){
		mecanumDrive = new MecanumDriveSubsystem();
		sensors = new SensorSubsystem();
		pidRotation = new PidRotationSubsystem();
		vision = new Vision();
		oi = new OI();
		autoRotation = new AutoRotation();
		test = 0;
	}
	
	public void autonomousInit(){
		autoRotation.start();
	}
	@Override
	 public void autonomousPeriodic(){
		Scheduler.getInstance().run();
	 }
	
	@Override
	public void teleopInit(){
		
	}
	
	@Override
	public void teleopPeriodic(){
		Scheduler.getInstance().run();
	}
	
	@Override
	public void disabledInit(){
		
	}
	
	@Override
	public void testInit(){
		SmartDashboard.putNumber("gyro", sensors.getAngle());
		LiveWindow.setEnabled(true);
	}
	
	@Override
	public void testPeriodic(){
		SmartDashboard.putData("gyro", sensors.getGyro());
		LiveWindow.run();
	}
}
