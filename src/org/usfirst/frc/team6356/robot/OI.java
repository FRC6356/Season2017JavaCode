package org.usfirst.frc.team6356.robot;

import org.usfirst.frc.team6356.robot.commands.autonomous.CommandAutoDist;
import org.usfirst.frc.team6356.robot.commands.autonomous.CommandAutoParallel;
import org.usfirst.frc.team6356.robot.commands.autonomous.CommandVisionRotation;
import org.usfirst.frc.team6356.robot.commands.autonomous.CommandVisionTranslation;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class OI {
	public Joystick Driver;
	public Joystick Operator;
	public JoystickButton test;
	public JoystickButton test2;
	public JoystickButton blue;
	public JoystickButton yellow;
	
	public OI(){
		Driver = Joysticks.get_DRIVER();
		Operator = Joysticks.get_OPERATOR();
		
		initializeButton(this.test, this.Driver, 1, new CommandVisionRotation());
		initializeButton(this.test2, this.Driver, 2, new CommandVisionTranslation());
		initializeButton(this.blue, this.Driver, 3, new CommandAutoParallel());
		initializeButton(this.yellow, this.Driver, 4, new CommandAutoDist(245));
	}

	public void initializeButton(JoystickButton Button, Joystick Joystick, int buttonNumber, Command command){
		Button = new JoystickButton(Joystick, buttonNumber);
		Button.whenPressed(command);
	}
}
