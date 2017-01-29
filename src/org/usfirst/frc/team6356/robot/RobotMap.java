package org.usfirst.frc.team6356.robot;

public class RobotMap {
	public static class Pwm{
		public static final int leftTopDrive = 0;
		public static final int leftBotDrive = 1;
		public static final int rightTopDrive = 2;
		public static final int rightBotDrive = 3;
	}
	
	public static class LeftJoystick{
		public static final int portNumber = 0;
		public static final int leftHorizontalAxis = 0;
		public static final int leftVerticalAxis = 1;
		public static final int rightHorizontalAxis = 4;
		public static final int rightVerticalAxis = 5;
		public static final int leftTrigger = 2;
		public static final int rightTrigger = 3;
	}
	
	public static class RightJoystick{
		public static final int portNumber = 1;
		public static final int xAxis = 0;
		public static final int yAxis = 1;
		public static final int rotateAxis = 2;
	}
	
	public static class IO{
		public static final int gyro = 0;
	}
}
