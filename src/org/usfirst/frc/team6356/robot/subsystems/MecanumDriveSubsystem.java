package org.usfirst.frc.team6356.robot.subsystems;

import org.usfirst.frc.team6356.robot.RobotMap;
import org.usfirst.frc.team6356.robot.RobotPreference;
import org.usfirst.frc.team6356.robot.Joysticks;
import org.usfirst.frc.team6356.robot.ModuleManager;
import org.usfirst.frc.team6356.robot.Robot;
import org.usfirst.frc.team6356.robot.commands.CommandMecanumDrive;
import org.usfirst.frc.team6356.robot.enums.DriveMode;
import org.usfirst.frc.team6356.robot.enums.DriveSpeed;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class MecanumDriveSubsystem extends Subsystem {

	public RobotDrive MecanumDrive;
	private PIDController m_turnPID;
	private PIDController m_tranPID;
	private PIDController m_distPID;

	public MecanumDriveSubsystem() {
		super();
		MecanumDrive = new RobotDrive(RobotMap.leftFrontMotor, RobotMap.leftRearMotor, RobotMap.rightFrontMotor,
				RobotMap.rightRearMotor);
		MecanumDrive.setInvertedMotor(MotorType.kFrontRight, true);
		MecanumDrive.setInvertedMotor(MotorType.kRearRight, true);

		m_turnPID = new PIDController(RobotPreference.getAutoRotateP(), RobotPreference.getAutoRotateI(),
				RobotPreference.getAutoRotateD(), new PIDSource() {

					@Override
					public void setPIDSourceType(PIDSourceType pidSource) {
						// TODO Auto-generated method stub

					}

					@Override
					public PIDSourceType getPIDSourceType() {
						// TODO Auto-generated method stub
						return PIDSourceType.kDisplacement;
					}

					@Override
					public double pidGet() {
						// TODO Auto-generated method stub
						return Robot.sensors.getAngle();
					}
				}, new PIDOutput() {

					@Override
					public void pidWrite(double output) {
						// TODO Auto-generated method stub
						if (!ModuleManager.isVisionProcessingGearLift) {
							pidRotateDrive(output);
							return;
						}

						if (ModuleManager.isVisionProcessingGearLift) {
							pidRotateDrive(output*0.5);
							return;
						}

					}

				});
		m_turnPID.setContinuous(true);
		m_turnPID.setOutputRange(-1, 1);
		m_turnPID.setAbsoluteTolerance(2);

		m_tranPID = new PIDController(0.0035, 0.0, 0.01, new PIDSource() {

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				// TODO Auto-generated method stub

			}

			@Override
			public PIDSourceType getPIDSourceType() {
				// TODO Auto-generated method stub
				return PIDSourceType.kDisplacement;
			}

			@Override
			public double pidGet() {
				return Robot.vision.gearLiftPosition();

			}
		}, new PIDOutput() {

			@Override
			public void pidWrite(double output) {
				// TODO Auto-generated method stub
				if (output < 0.3) {
					translationDrive(output);
					return;
				}
				if (output >= 0.3) {
					translationDrive(output * 0.5);
					return;
				}
			}

		});

		m_tranPID.setInputRange(0, 680);
		m_tranPID.setOutputRange(-1, 1);
		m_tranPID.setAbsoluteTolerance(3);

		m_distPID = new PIDController(0.00131, 0, 0, RobotMap.ultrasonic, new PIDOutput() {

			@Override
			public void pidWrite(double output) {

				driveWithMecanumDrive(0, output * 0.6, -0.03, 0);
			}

		});
		LiveWindow.addActuator("MDRIVE", "TRAN", m_tranPID);
		m_distPID.setContinuous(true);
		m_distPID.setInputRange(0, 3500);
		m_distPID.setOutputRange(-1, 1);
		m_distPID.setAbsoluteTolerance(10);
	}

	public void driveWithMecanumDrive(double xSpeed, double ySpeed, double rotateSpeed, double gyroAngle) {
		MecanumDrive.mecanumDrive_Cartesian(xSpeed, ySpeed, rotateSpeed, gyroAngle);
	}

	public void driveWithMecnumDrive(Joystick joystick, DriveMode DRIVE_MODE, DriveSpeed DRIVE_SPEED) {
		double kSpeed = 1;
		double[] speed = Joysticks.getMecanumAxis();
		switch (DRIVE_SPEED) {
		default:
			kSpeed = 1;
			break;

		case FAST:
			kSpeed = 1;
			break;

		case SLOW:
			kSpeed = 0.5;

		}
		switch (DRIVE_MODE) {
		default:
			driveWithMecanumDrive(speed[0] * kSpeed, speed[1] * kSpeed, speed[2] * kSpeed, 0);
			break;

		case INTAKE:
			driveWithMecanumDrive(speed[0] * kSpeed, speed[1] * kSpeed, speed[2] * kSpeed, 0);
			break;

		case SHOOT:
			driveWithMecanumDrive(-speed[0] * kSpeed, -speed[1] * kSpeed, speed[2] * kSpeed, 0);
			break;
		}
	}

	public void pidRotateDrive(double speed) {
		// MecanumDrive.setLeftRightMotorOutputs(leftOutput, rightOutput);
		driveWithMecanumDrive(0, 0, speed, 0);
	}

	public void translationDrive(double speed) {
		driveWithMecanumDrive(-speed, 0, -0.01, 0);
	}

	public void setTurnPID(double setpoint) {
		m_turnPID.setSetpoint(setpoint);
	}

	public void setDistPID(double setpoint) {
		m_distPID.setSetpoint(setpoint);
	}

	public void resetTurnPID() {
		m_turnPID.reset();
	}

	public void resetTranPID() {
		m_tranPID.reset();
	}

	public void resetDistPID() {
		m_distPID.reset();
	}

	public void startAutoTurn() {
		m_turnPID.enable();
	}

	public void startAutoTran() {
		if (ModuleManager.isVisionProcessingGearLift) {
			m_tranPID.setSetpoint(330);
			m_tranPID.enable();
		}
	}

	public void startAutoDist() {
		m_distPID.enable();
	}

	public void endAutoTurn() {
		if (m_turnPID.isEnabled()) {
			m_turnPID.disable();
		}
	}

	public void endAutoTran() {
		if (m_tranPID.isEnabled()) {
			m_tranPID.disable();
		}
	}

	public void endAutoDist() {
		if (m_distPID.isEnabled()) {
			m_distPID.disable();
		}
	}

	public boolean tranOnTarget() {
		return m_tranPID.onTarget();
	}

	public boolean turnOntarget() {
		return m_turnPID.onTarget();
	}

	public boolean distOntarget() {
		return m_distPID.onTarget();
	}

	public double getTurnSetpoint() {
		return m_turnPID.getSetpoint();
	}

	public boolean turnPIDEnabled() {
		return m_turnPID.isEnabled();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CommandMecanumDrive());
	}

}
