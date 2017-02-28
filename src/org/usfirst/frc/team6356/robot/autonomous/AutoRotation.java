package org.usfirst.frc.team6356.robot.autonomous;

import org.usfirst.frc.team6356.robot.commands.autonomous.CommandAutoDist;
import org.usfirst.frc.team6356.robot.commands.autonomous.CommandAutoDrive;
import org.usfirst.frc.team6356.robot.commands.autonomous.CommandAutoParallel;
import org.usfirst.frc.team6356.robot.commands.autonomous.CommandDoNothing;
import org.usfirst.frc.team6356.robot.commands.autonomous.CommandVisionRotation;
import org.usfirst.frc.team6356.robot.commands.autonomous.CommandVisionTranslation;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRotation extends CommandGroup {

    public AutoRotation() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential (new CommandDoNothing(0.8));
    	
    	addSequential(new CommandAutoParallel());
    	
    	addSequential(new CommandAutoDist(2000));
    	
    	addSequential(new CommandDoNothing(1));
    	
    	addSequential(new CommandAutoParallel(-120));
    	
    	addSequential(new CommandDoNothing(1));
    	
    	addSequential(new CommandVisionTranslation());
    	
    	addSequential(new CommandAutoDist(400));
    }
}
