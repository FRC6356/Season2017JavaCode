package org.usfirst.frc.team6356.vision;

import  java.lang.Math;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6356.robot.ModuleManager;
import org.usfirst.frc.team6356.vision.VisionConstants;

public class Vision {
	NetworkTable table;
	double[] defaultValue = new double[0];
	
	public Vision(){
		table = NetworkTable.getTable(VisionConstants.TABLE_ADDRESS);
		defaultValue = new double[0];
	}
	
	public double[] getAreas(){
		return table.getNumberArray(VisionConstants.AREA_NAME, defaultValue);
	}
	
	public double[] getCenterX(){
		return table.getNumberArray(VisionConstants.CENTER_X, defaultValue);
		
	}
	
	public double[] getCenterY(){
		return table.getNumberArray(VisionConstants.CENTER_Y, defaultValue);
	}
	
	public double[] getHeight(){
		return table.getNumberArray(VisionConstants.HEIGHT, defaultValue);
	}
	
	public double[] getWidth(){
		return table.getNumberArray(VisionConstants.WIDTH, defaultValue);
	}
	
	public double[] getSolidity(){
		return table.getNumberArray(VisionConstants.SOLIDITY, defaultValue);
	}
	
	public double getTopLeftX(int index){
		return getCenterX()[index]-(0.5*getWidth()[index]);
	}
	
	public double getTopLeftY(int index){
		return getCenterY()[index]-(0.5*getHeight()[index]);
	}
	
	public double getBotRightX(int index){
		return getCenterX()[index] + (0.5*getWidth()[index]);
	}
	
	public double getBotRightY(int index){
		return getCenterY()[index] + (0.5*getHeight()[index]);
	}
	
	public double getHighGoalDistance(){
//		double[] heights = getHeight();
//		double biggestHeightPixels = 0;
//		int topIndex = 0;
//		int botIndex = 0;
//		for(int i = 0; i<heights.length; i++){
//			if(heights.length==1 || i == 0){
//				biggestHeightPixels = heights[i];
//				topIndex = i;
//			}
//			else if(i!=0 && heights[i]>biggestHeightPixels){
//				biggestHeightPixels = heights[i];
//				topIndex = i;
//			}
//			else if (i!=0 && heights[i]<biggestHeightPixels)
//				botIndex = i;
//		}		
//		double targetHeightPixel = getBotRightY(botIndex)-getTopLeftY(topIndex);
//		double distanceInFeet = (VisionConstants.FUEL_TARGET_HEIGHT_FEET * VisionConstants.CAMERA_Y_RESOLUTION) /
//						  	(2 * 32 * Math.tan(VisionConstants.CAMERA_HORIZONTAL_RADIAN));
		try{
		double topHeight = 0;
		double botHeight = 0;
		if(getAreas().length != 0){
		topHeight = getHeight()[1];
		botHeight = getHeight()[0];
		}
		
		double distanceInCM = (10*480)/(2*topHeight*Math.tan(1.1955505/2));
		
		return distanceInCM;
		}
		catch(Exception e){
			
		}
		return 0;
	}
	
	public double gearLiftPosition(){
		try{
			double x1 = getCenterX()[0];
			double x2 = getCenterX()[1];
			double liftPosition = (x1 + x2) / 2;
//			System.out.println("lift target found!");
			return liftPosition;
			
		}
		catch(Exception e){
//			System.out.println("cannot found the lift target!");
			ModuleManager.isVisionProcessingGearLift = false;
		}
		
		return 340;
	}
	void degreeToRadian(double degree){
		
	}
	
	public double degreePerPixel(){
		double degreePerPixel = 68.5/680;
		return degreePerPixel;
	}
	

	public void log(){
		SmartDashboard.putNumber("distance", getHighGoalDistance());
	}
}
