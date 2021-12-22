package com.mycompany.myapp.xline;

import java.util.ArrayList;

public class CalculationsForStringing {
    public CalculationsForStringing(){

    }
    public double stringingEfficiency(double voltageAcrossTheString,double voltageAcrossCondNearestDisc, int numberOfDisc){
        return (voltageAcrossTheString*100)/(numberOfDisc*voltageAcrossCondNearestDisc);
    }
    public double stringKvalue(double shuntCapacitance, double selfCapacitance){
        return shuntCapacitance/selfCapacitance;
    }
    public Double[] voltageDistributionAcrossDiscsUptoFour(int numberOfDics, double phaseVoltageOnString, double k){
//        ArrayList<Double> results = new ArrayList<Double>();

        double denA = (1+k)*(3+k);
        double distributionOne = phaseVoltageOnString/denA;


        double numB = (1+(3*k)+(Math.pow(k,2)));
        double distributionthree = phaseVoltageOnString*numB/(denA);

        double numc = (1+k);
        double distributiionTwo = phaseVoltageOnString*numc/denA;

        double numD = (1+Math.pow(k,3)+(5* Math.pow(k,2))+(6*k));
        double distributionFour = phaseVoltageOnString*numD/denA;
//
//        results.add(distributionOne);
//        results.add(distributiionTwo);
//        results.add(distributionthree);
        switch (numberOfDics){
            case 1: {
                Double[] case1 = {distributionOne};
                return case1;
//                break;
            }
            case 2: {
                Double[] case2 = {distributionOne,distributiionTwo};
                return case2;
//                break;
            }
            case 3: {
                Double[] case3 = {distributionOne,distributiionTwo,distributionthree};
                return case3;
//                break;
            }
            case 4: {
                Double[] case4 = {distributionOne,distributiionTwo,distributionthree,distributionFour};
                return case4;
//                break;
            }
            default: return null;
        }

    }
}
