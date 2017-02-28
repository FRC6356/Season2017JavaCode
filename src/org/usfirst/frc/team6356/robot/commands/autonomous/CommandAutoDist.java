package org.usfirst.frc.team6356.robot.commands.autonomous;

import org.usfirst.frc.team6356.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandAutoDist extends Command {

	private Timer timer;
	private double distance;
	private double last;
	private boolean timeout;

	public CommandAutoDist(double dist) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.mecanumDrive);
		timer = new Timer();
		distance = dist;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timeout = false;
		Robot.mecanumDrive.resetDistPID();
		Robot.mecanumDrive.resetTurnPID();
		Robot.mecanumDrive.setTurnPID(Robot.sensors.getAngle());
		Robot.mecanumDrive.setDistPID(distance);
		timer.start();
		last = timer.get();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.mecanumDrive.startAutoDist();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if ((timer.get() - last) > 3)
			timeout = true;

		return Robot.mecanumDrive.distOntarget() || timeout;

	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.mecanumDrive.endAutoDist();
		Robot.mecanumDrive.endAutoTurn();
		Robot.mecanumDrive.driveWithMecanumDrive(0, 0, 0, 0);
		timer.stop();
		System.out.println("Auto Distance stopped at " + Robot.sensors.getDistance());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
