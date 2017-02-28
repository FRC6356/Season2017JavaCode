package org.usfirst.frc.team6356.robot.commands.autonomous;

import org.usfirst.frc.team6356.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandAutoParallel extends Command {

	private Timer timer;
	private double last;
	private double angle;
	private boolean timeout;

	public CommandAutoParallel() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.mecanumDrive);
		timer = new Timer();
		angle = 0;
	}

	public CommandAutoParallel(double angle) {
		requires(Robot.mecanumDrive);
		timer = new Timer();
		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.mecanumDrive.resetTurnPID();
		Robot.mecanumDrive.setTurnPID(angle);
		timer.start();
		last = timer.get();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.mecanumDrive.startAutoTurn();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if ((timer.get() - last) > 4)
			timeout = true;
		return Robot.mecanumDrive.turnOntarget() || timeout;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.mecanumDrive.endAutoTurn();
		timer.stop();
		timeout = false;
		System.out.println("Auto Parallel stopped! End with gyro angle: " + Robot.sensors.getAngle());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
