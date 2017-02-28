package org.usfirst.frc.team6356.robot.commands;

import org.usfirst.frc.team6356.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 *
 */
public class CommandVisionRotation extends Command {

	private PIDController _controller;
	double degree;
	boolean finish  = true;
    public CommandVisionRotation() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.mecanumDrive);
    	Robot.sensors.resetGyro();
            // Kp, Ki, Kd, input (gyro), output (chassis)
    	 this._controller = new PIDController(0.1, 0.0, 0,
                 new PIDSource() {
                     @Override
                     public void setPIDSourceType(PIDSourceType pidSource) {
                     }

                     @Override
                     public PIDSourceType getPIDSourceType() {
                         return PIDSourceType.kDisplacement;
                     }

                     @Override
                     public double pidGet() {
                         return Robot.sensors.getAngle();
                     }
                 }, new PIDOutput() {
             @Override
             public void pidWrite(double output) {
            	 Robot.mecanumDrive.driveWithMecanumDrive(0, 0, output*0.4, 0);
             }
         });
    	 this._controller.setContinuous(true);
         this._controller.setOutputRange(-1, 1);
         this._controller.setAbsoluteTolerance(2); 
         
    }
  
    // Called just before this Command runs the first time
    protected void initialize() {
    	degree = Robot.sensors.getAdjustAngle();
    	SmartDashboard.putNumber("testAngle", degree);
    	Robot.test = -degree;
    	Robot.sensors.resetGyro();
        // Reset the PID
        this._controller.reset();
        this._controller.setSetpoint(degree);
        SmartDashboard.putNumber("setSetpoint", this._controller.getSetpoint());
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	this._controller.enable();
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putBoolean("onTarget", this._controller.onTarget());
    	return this._controller.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	 Robot.mecanumDrive.driveWithMecanumDrive(0, 0, 0, 0);
    	 this._controller.reset();
    	 this._controller.disable();
    	 
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
