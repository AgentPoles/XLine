package com.mycompany.myapp.xline;

public class CalculationsForResistance {
    public CalculationsForResistance() {

    }

    public double dcResistanceGiventype(int type, double lengthOfConductor, double areaOfConductor) {
        double resistivity = fetchValueOfType(type);
return dcResistance(resistivity,lengthOfConductor,areaOfConductor);
    }

    public double fetchValueOfType(int type) {
        return 99;
    }

    public double dcResistance(double resistivity, double lengthOfConductor, double areaOfConductor) {
        double resistance = (resistivity * lengthOfConductor) / areaOfConductor;
        return resistance;
    }
    public double acResistance(double skinFactor, double resistivity, double lengthOfConductor, double areaOfConductor){
        double dcResistancee = dcResistance(resistivity,lengthOfConductor,areaOfConductor);
        return skinFactor*dcResistancee;
    }
    public double newResistivity(double temperatureAtFirst, double finalTemperature, double temperatureConstant, double initialResistivity){
        double numerator = temperatureConstant + finalTemperature;
        double denomenator = temperatureAtFirst + temperatureConstant;
        return  (numerator/denomenator)*initialResistivity;
    }
    public double ResistanceOfWoundConductorDueToSpiralling(double resistivity, double areaOfConductor, double lengthOfWoundConductor){
        return resistivity*lengthOfWoundConductor/areaOfConductor;
    }
    public double areaOfConductor(double radiusOfConductor){
        double radsuare = Math.pow(radiusOfConductor,2);
        return Math.PI*radsuare;
    }
    double relativePitchOfWoundConductor(double lenghOfOneturn, double resistanceOfLayer){
        return lenghOfOneturn/(2*resistanceOfLayer);
    }
    public double lengthOfWoundConductor(double pitchOfWoundConductor){
        double fraction = (Math.PI/pitchOfWoundConductor);
        double inner = Math.pow(fraction,2) + 1;
        return Math.sqrt(inner);
    }
    public double resistance(double totalLengthOfLine, double resistance){
return resistance*totalLengthOfLine;
    }
    public double heightOfTower(boolean isdouble, double groundClearance, double conductorSag, double earthWireSpacing, double conductorspacing){
        double result = 0.0;
        if (isdouble) {
            result = groundClearance+conductorSag+conductorspacing+conductorspacing+earthWireSpacing;
        }
        else {
            result = groundClearance+conductorSag+earthWireSpacing;
        }
        return result;
    }
    public double groundwirespacing(double voltage){
        if(voltage<60){
            return 1.2;
        }
        if(voltage>66&&voltage<132){
            return 0.6;
        }
             if(voltage>= 132 &&voltage<330){
                 return 1.2;
             }
        if (voltage>=330){
            return 2.4;
        }
        return 0.000;
    }
}
