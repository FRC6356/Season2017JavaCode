package org.usfirst.frc.team6356.robot;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6356.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team6356.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team6356.robot.subsystems.MecanumDriveSubsystem;
import org.usfirst.frc.team6356.robot.subsystems.PidRotationSubsystem;
import org.usfirst.frc.team6356.robot.subsystems.SensorSubsystem;
import org.usfirst.frc.team6356.vision.Vision;
import org.usfirst.frc.team6356.robot.autonomous.AutoRotation;
import org.usfirst.frc.team6356.robot.enums.DriveMode;
import org.usfirst.frc.team6356.robot.enums.VisionMode;
import org.usfirst.frc.team6356.robot.enums.DriveSpeed;
import org.usfirst.frc.team6356.robot.RobotMap;


public class Robot extends IterativeRobot {
	
	public static MecanumDriveSubsystem mecanumDrive;
	public static ClimberSubsystem climber;
	public static PidRotationSubsystem pidRotation;
	public static SensorSubsystem sensors;
	public static IntakeSubsystem intake;
	public static Vision vision;
	public static OI oi;
	
	private Command autoRotation;
	public static SendableChooser<DriveMode> driveModeChooser;
	public static SendableChooser<DriveSpeed> driveSpeedChooser;
	public static SendableChooser<VisionMode> visionModeChooser;
	
	
	public Robot() {
		
	}

	@Override
	public void robotInit(){
		RobotMap.init();
		sensors = new SensorSubsystem();
//		pidRotation = new PidRotationSubsystem();
		mecanumDrive = new MecanumDriveSubsystem();
		climber = new ClimberSubsystem();
		intake = new IntakeSubsystem();
		vision = new Vision();
		oi = new OI();
		autoRotation = new AutoRotation();
		
		driveModeChooser = new SendableChooser<DriveMode>();
		driveModeChooser.addDefault("intakeMode", DriveMode.INTAKE);
		driveModeChooser.addObject("IntakeMode", DriveMode.INTAKE);
		driveModeChooser.addObject("ShootMode", DriveMode.SHOOT);
		
		driveSpeedChooser = new SendableChooser<DriveSpeed>();
		driveSpeedChooser.addDefault("FastMode", DriveSpeed.FAST);
		driveSpeedChooser.addObject("FastMode", DriveSpeed.FAST);
		driveSpeedChooser.addObject("SlowMode", DriveSpeed.SLOW);
		
		visionModeChooser = new SendableChooser<VisionMode>();
		visionModeChooser.addDefault("VisionTarget: Boiler", VisionMode.BOILER);
		visionModeChooser.addObject("VisionTarget: Boiler", VisionMode.BOILER);
		visionModeChooser.addObject("VisionTarget: GearLift", VisionMode.GEAR_LIFT);
		
		SmartDashboard.putData("Drive Mode:", driveModeChooser);
		SmartDashboard.putData("Vision Target:", visionModeChooser);
		SmartDashboard.putData("Drive Speed:", driveSpeedChooser);
	}
	
	public void autonomousInit(){
		sensors.resetGyro();
		mecanumDrive.setTurnPID(sensors.getAngle());
		autoRotation.start();
	}
	
	@Override
	 public void autonomousPeriodic(){
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("liftPosition", vision.gearLiftPosition());
	 }
	
	@Override
	public void teleopInit(){
		autoRotation.cancel();
		
		mecanumDrive.setTurnPID(sensors.getAngle());
	}
	
	@Override
	public void teleopPeriodic(){
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("liftPosition", vision.gearLiftPosition());
		SmartDashboard.putNumber("AdjustAngle", sensors.getAdjustAngle());
		SmartDashboard.putNumber("UltraDistance", sensors.getDistance());
		SmartDashboard.putNumber("TurnSetPoint", mecanumDrive.getTurnSetpoint());
		
	}
	
	@Override
	public void disabledInit(){
		
	}
	
	@Override
	public void disabledPeriodic(){
		
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
