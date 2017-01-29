package org.usfirst.frc.team6356.robot;

import edu.wpi.first.wpilibj.Preferences;

public class RobotPreference {
    static public double getAutoRotateP() {
        //return Preferences.getInstance().getDouble("AutoRotateP", 0.0002);
    	return 0.1;
    }
    static public double getAutoRotateI() {
        //return Preferences.getInstance().getDouble("AutoRotateI", 0.00005);
    	return 0.0;
    }
    static public double getAutoRotateD() {
        //return Preferences.getInstance().getDouble("AutoRotateD", 0.00);
    	return 0.0;
    }
    static public double getAutoRotateOnTargetToleranceDegrees() {
        return 2.0; /*Preferences.getInstance().getDouble("AutoRotateOnTargetToleranceDegrees", 2.0); */
    }
    static public double getAutoRotateDefaultTaretDegrees() {
        return Preferences.getInstance().getDouble("AutoRotateDefaultTargetDegrees",0.0);
    }
}

