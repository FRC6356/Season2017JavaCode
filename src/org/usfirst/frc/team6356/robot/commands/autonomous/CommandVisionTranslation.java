package org.usfirst.frc.team6356.robot.commands.autonomous;

import org.usfirst.frc.team6356.robot.ModuleManager;
import org.usfirst.frc.team6356.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandVisionTranslation extends Command {

	private Timer timer;
	private double last;
	private boolean timeout;
    public CommandVisionTranslation() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	timer = new Timer();
    	requires(Robot.mecanumDrive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.mecanumDrive.resetTranPID();
    	Robot.mecanumDrive.resetTurnPID();
    	Robot.mecanumDrive.setTurnPID(-120);
    	ModuleManager.isVisionProcessingGearLift = true;
    	timer.start();
    	last = timer.get();
    	timeout = false;
    	System.out.println("translation begin");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(ModuleManager.isVisionProcessingGearLift){
    		Robot.mecanumDrive.startAutoTurn();
    		Robot.mecanumDrive.startAutoTran();
    	}
    		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if((timer.get() - last)>=2.5){
    		timeout = true;
    	}
    	
        return Robot.mecanumDrive.tranOnTarget()||timeout;
    }

    // Called once after isFinished returns true
    protected void end() {
    	ModuleManager.isVisionProcessingGearLift = false;
    	Robot.mecanumDrive.endAutoTran();
    	Robot.mecanumDrive.endAutoTurn();
    	System.out.println("Auto translation finished!");
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
