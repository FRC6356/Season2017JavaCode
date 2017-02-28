package org.usfirst.frc.team6356.robot.subsystems;

import org.usfirst.frc.team6356.robot.Joysticks;
import org.usfirst.frc.team6356.robot.RobotMap;
import org.usfirst.frc.team6356.robot.commands.CommandClimb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public void lockRope() {
		RobotMap.ropeLocker.set(true);
		RobotMap.ropeGrabber.set(true);
	}

	public void lockLocker() {
		RobotMap.ropeLocker.set(true);
	}

	public void lockGrabber() {
		RobotMap.ropeLocker.set(true);
	}

	public void releaseRope() {
		RobotMap.ropeLocker.set(false);
		RobotMap.ropeGrabber.set(false);
	}

	public void releaseLocker() {
		RobotMap.ropeLocker.set(false);
	}

	public void releaseGrabber() {
		RobotMap.ropeGrabber.set(false);
	}

	public void setClimbSpeed(double speed) {
		if (speed < 0.2 && speed > -0.2)
			RobotMap.climber.set(speed);
	}

	public boolean ropeIsLocked() {
		return RobotMap.ropeLocker.get();
	}

	public boolean ropeIsGrabbed() {
		return RobotMap.ropeGrabber.get();
	}

	public void climbSpeedController() {
		setClimbSpeed(Joysticks.getClimbAxis());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CommandClimb());
	}
}
