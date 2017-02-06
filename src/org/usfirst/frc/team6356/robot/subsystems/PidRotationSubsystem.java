package org.usfirst.frc.team6356.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6356.robot.RobotPreference;
import org.usfirst.frc.team6356.robot.commands.CommandMecanumDrive;
import org.usfirst.frc.team6356.robot.Robot;


public class PidRotationSubsystem extends PIDSubsystem{
	
	double tolerance_degrees;
	boolean processCameraData;

	public PidRotationSubsystem(){
		super("Z-Rotation",
				RobotPreference.getAutoRotateP(),RobotPreference.getAutoRotateI(),RobotPreference.getAutoRotateD());
        getPIDController().setInputRange(-180,180);
        getPIDController().setOutputRange(-1, 1);
        getPIDController().setContinuous(true);
        tolerance_degrees = 1.5;
        setAbsoluteTolerance(tolerance_degrees);
        getPIDController().setSetpoint(0);
        LiveWindow.addActuator("Drive", "AutoRotation", getPIDController());
        LiveWindow.addSensor("test", "gyro", Robot.sensors.getGyro());
	}

	public void startProcessCameraData(){
		processCameraData = true;
	}
	
	public void finishProcessCameraData(){
		processCameraData = false;
	}
	@Override
	protected double returnPIDInput() {
//		if(!processCameraData)
//			return Robot.sensors.getAngle();
//		if(processCameraData)		
//			return Robot.sensors.getAdjustAngle();
		
		SmartDashboard.putNumber("adjustAngle", Robot.sensors.getAdjustAngle());
		return Robot.sensors.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.mecanumDrive.pidRotateDrive(output, output);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CommandMecanumDrive());
	}
	
	
}
