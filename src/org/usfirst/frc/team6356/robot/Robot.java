package org.usfirst.frc.team6356.robot;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc.team6356.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team6356.robot.subsystems.MecanumDriveSubsystem;
import org.usfirst.frc.team6356.robot.subsystems.SensorSubsystem;



public class Robot extends IterativeRobot {
	
	public static MecanumDriveSubsystem mecanumDrive;
	public static DriveSubsystem driveSubsystem;
	public static SensorSubsystem sensors;

	public Robot() {
		
	}

	@Override
	public void robotInit(){
		mecanumDrive = new MecanumDriveSubsystem();
		sensors = new SensorSubsystem();
	}
	
	@Override
	public void teleopPeriodic(){
		Scheduler.getInstance().run();
	}
}
