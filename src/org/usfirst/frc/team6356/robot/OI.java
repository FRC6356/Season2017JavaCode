package org.usfirst.frc.team6356.robot;

import org.usfirst.frc.team6356.robot.commands.CommandVisionRotation;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick joystick;
	public JoystickButton test;
	
	public OI(){
		joystick = Joysticks.get_DRIVER();
		test = new JoystickButton(joystick, 1);
		test.whileHeld(new CommandVisionRotation());
	}

}
