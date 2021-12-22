package com.mycompany.myapp.xline;

public class Losses {
    public Losses(){

    }
    public double copperLossesFromCurrent(double current, double resistance, double numberOfPhases){
        return Math.pow(current,2)*resistance*numberOfPhases;
    }
    public double copperLossesFromRandC(double lossesFromC, double lossesFromR, double surfaceAreaOfConductor){
        return surfaceAreaOfConductor*(lossesFromC + lossesFromR);
    }
    public double lossesFromRadiation(double atmosphericPressure, double windVelocity, double airTemperature, double conductorDiameter, double riseInAirTemperature){
        double one = atmosphericPressure*windVelocity;
        double onemajor = Math.sqrt(one);
        double two = Math.sqrt(conductorDiameter);
        double three = Math.pow(airTemperature,0.123);
        double numerator = 0.218*onemajor*riseInAirTemperature;
        double denomenator = two * three;
        return numerator/denomenator;
    }
    public double conductorAmpercity(double lossesFromC, double lossesFromR, double surfaceAreaOfConductor, double resistance){
        double numerator = surfaceAreaOfConductor*(lossesFromC + lossesFromR);
        double denomenator = resistance;
        double fraction = numerator/denomenator;
        return Math.sqrt(fraction);
    }
}

