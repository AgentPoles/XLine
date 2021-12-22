package com.mycompany.myapp.xline;

public class Corona {
    public Corona(){

    }
    public double PotentialGradient(double phaseToNeutralVoitage, double conductorRadius, double distanceBetweenConductors){
        double denomenator = conductorRadius*Math.log((distanceBetweenConductors/conductorRadius));
        return phaseToNeutralVoitage/denomenator;
    }
    public double CriticalDistruptiveVoltage(double irregularityFactor, double airdensityFactor, double conductorRadius, double spacingBetweenConductors){
        double inner =  conductorRadius*Math.log((spacingBetweenConductors/conductorRadius));
        return irregularityFactor*airdensityFactor*inner*21.2*100000;
    }
    public double airdensityFactor(double atmosphericPressureOfMercury, double tempInDegrees){
        double num = 3.92*atmosphericPressureOfMercury;
        double denum = 273+tempInDegrees;
        return num/denum;
    }
    public double  visualCriticalVoltage(double irrgularityFactor, double airdensityFactor, double conductorRadius, double SpacingBetweenConducotrs){
        double first = CriticalDistruptiveVoltage(irrgularityFactor,airdensityFactor,conductorRadius,SpacingBetweenConducotrs);
        double a = Math.sqrt(airdensityFactor*conductorRadius);
        double otherfactor= (1+(0.3/a));
        return first*otherfactor;
    }
    public double powerLossesDueToCorona(double airdensityFactor, double conductorRadius, double SpacingBetweenConductors, double frequency, double phaseToNeutralVoltage, double CriticalDisruptivevoltage){
        double a = (frequency+25)/airdensityFactor;
        double b = Math.sqrt(conductorRadius/SpacingBetweenConductors);
        double c = Math.pow((phaseToNeutralVoltage-CriticalDisruptivevoltage),2);
        return  242.4*Math.pow(10,-5)*a*b*c;
    }
}
