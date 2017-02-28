package org.usfirst.frc.team6356.robot.commands;

import org.usfirst.frc.team6356.robot.Joysticks;
import org.usfirst.frc.team6356.robot.ModuleManager;
import org.usfirst.frc.team6356.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandMecanumDrive extends Command {

	private boolean timeModeEnabled = false;

	public CommandMecanumDrive() {
		requires(Robot.mecanumDrive);
		// requires(Robot.pidRotation);
	}

	public CommandMecanumDrive(double timeout) {
		super(timeout);
		System.out.println("time mode drive enabled with set time" + timeout);
		timeModeEnabled = true;
	}

	protected void initialize() {
		Robot.mecanumDrive.resetTurnPID();
		Robot.mecanumDrive.setTurnPID(Robot.sensors.getAngle());
//		Robot.mecanumDrive.setTurnPID(Robot.sensors.getAngle());
		System.out.println("Mecanum Drive Command initialize!");
	}

	protected void execute() {
		double angle = Robot.sensors.getAngle();
		Robot.mecanumDrive.driveWithMecnumDrive(Joysticks.get_DRIVER(), Robot.driveModeChooser.getSelected(),
				Robot.driveSpeedChooser.getSelected());
		SmartDashboard.putNumber("angle", angle);
		// Robot.pidRotation.enable();
		if (!ModuleManager.isVisionProcessingGearLift && !ModuleManager.isVisionProcessingHighGoal) {
			Robot.mecanumDrive.startAutoTurn();
		}
		Robot.vision.log();

	}

	@Override
	protected boolean isFinished() {
		if (timeModeEnabled) {
			return isTimedOut();
		}
		return false;
		// never finished while driving with Joystick.
	}

	protected void end() {
		Robot.mecanumDrive.driveWithMecanumDrive(0, 0, 0, 0);
		// Robot.pidRotation.disable();
		if (!ModuleManager.isVisionProcessingGearLift && !ModuleManager.isVisionProcessingHighGoal)
			Robot.mecanumDrive.endAutoTurn();
		System.out.println("MecanumDrive ended!");
		timeModeEnabled = false;
	}

	protected void interrupted() {
		end();
	}
}
