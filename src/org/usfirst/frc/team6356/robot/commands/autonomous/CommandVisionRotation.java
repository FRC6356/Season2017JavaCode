package org.usfirst.frc.team6356.robot.commands.autonomous;

import org.usfirst.frc.team6356.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CommandVisionRotation extends Command {

	// private PIDController _controller;
	double degree;
	double last;
	private Timer timer;
	boolean translationFinished = false;
	private boolean timeout;

	public CommandVisionRotation() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		timer = new Timer();
		requires(Robot.mecanumDrive);
		// Robot.sensors.resetGyro();
		// // Kp, Ki, Kd, input (gyro), output (chassis)
		// this._controller = new PIDController(0.1, 0.0, 0,
		// new PIDSource() {
		// @Override
		// public void setPIDSourceType(PIDSourceType pidSource) {
		// }
		//
		// @Override
		// public PIDSourceType getPIDSourceType() {
		// return PIDSourceType.kDisplacement;
		// }
		//
		// @Override
		// public double pidGet() {
		// return Robot.sensors.getAngle();
		// }
		// }, new PIDOutput() {
		// @Override
		// public void pidWrite(double output) {
		//// if(translationFinished)
		// Robot.mecanumDrive.driveWithMecanumDrive(0, 0, output*0.4, 0);
		//// if(!translationFinished){
		//// Robot.mecanumDrive.driveWithMecanumDrive(output*0.3, 0, 0, 0);
		//// }
		////
		// SmartDashboard.putNumber("visionPIDOutput", output);
		// }
		// });
		// this._controller.setContinuous(true);
		// this._controller.setOutputRange(-1, 1);
		// this._controller.setAbsoluteTolerance(2);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// degree = Robot.sensors.getAdjustAngle();
		// SmartDashboard.putNumber("testAngle", degree);
		// Robot.sensors.resetGyro();
		// // Reset the PID
		// this._controller.reset();
		// this._controller.setSetpoint(degree);
		// SmartDashboard.putNumber("setSetpoint",
		// this._controller.getSetpoint());
		Robot.mecanumDrive.resetTurnPID();
		degree = Robot.sensors.getAdjustAngle() + Robot.sensors.getAngle();
		Robot.mecanumDrive.setTurnPID(degree);
		timer.start();
		last = timer.get();
		System.out.println("initialized Auto Turn");

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.mecanumDrive.startAutoTurn();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		// SmartDashboard.putBoolean("onTarget", this._controller.onTarget());
		// return this._controller.onTarget();
		if ((timer.get() - last) >= 4)
			timeout = true;
		return Robot.mecanumDrive.tranOnTarget() || timeout;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.mecanumDrive.driveWithMecanumDrive(0, 0, 0, 0);
		Robot.mecanumDrive.endAutoTurn();
		timer.stop();
		System.out.println("Auto Turn ended!");
		// this._controller.reset();
		// this._controller.disable();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
