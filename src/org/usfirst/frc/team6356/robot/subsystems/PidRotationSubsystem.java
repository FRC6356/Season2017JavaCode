package org.usfirst.frc.team6356.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

import org.usfirst.frc.team6356.robot.RobotPreference;
import org.usfirst.frc.team6356.robot.commands.CommandMecanumDrive;
import org.usfirst.frc.team6356.robot.Robot;


public class PidRotationSubsystem extends PIDSubsystem{
	
	double tolerance_degrees;

	public PidRotationSubsystem(){
		super("Z-Rotation",
				RobotPreference.getAutoRotateP(),RobotPreference.getAutoRotateI(),RobotPreference.getAutoRotateD());
		getPIDController().setContinuous( true );
        getPIDController().setInputRange(-180,180);
        getPIDController().setOutputRange(-1, 1);
        tolerance_degrees = 0.1;
        getPIDController().setAbsoluteTolerance(tolerance_degrees);
        
        setSetpoint(0.0);
	}

	@Override
	protected double returnPIDInput() {
		return Robot.sensors.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.mecanumDrive.pidRotateDrive(output*0.7, output*0.5);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CommandMecanumDrive());
	}
	
	
}
