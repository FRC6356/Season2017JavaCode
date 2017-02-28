package org.usfirst.frc.team6356.robot.subsystems;

import org.usfirst.frc.team6356.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void setFrontIntakeSpeed(double speed){
		RobotMap.intakeFront.set(speed);
	}
	
	public void setSideIntakeSpeed(double speed){
		RobotMap.intakeSide.set(speed);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

