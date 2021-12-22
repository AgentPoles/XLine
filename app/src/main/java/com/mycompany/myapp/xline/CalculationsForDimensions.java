package com.mycompany.myapp.xline;

import java.util.ArrayList;

public class CalculationsForDimensions {
    public CalculationsForDimensions(){

    }
    public double calculationForCSAusingShortCircuit(double standardShortCircuit, double persistenceTime, double constantK){
        return standardShortCircuit*Math.sqrt(persistenceTime)/constantK;
    }
    public double constantK(double temperatureRise, double specificWeight, double specificHeatCapacity, double specificResistance){
        double numerator = temperatureRise*specificHeatCapacity*specificWeight;
        double denomenator = 0.24*specificResistance;
        return Math.sqrt(numerator/denomenator);
    }
    public double CalculationForHorizontalSpacing(double voltage, double sag, double constant){
double inner = Math.round(voltage/11)*0.15;
 return inner+sag+constant;
    }
public double constant(double voltage){
        if(voltage<=11){
            return 2.4;
        }
        if(voltage>11&&voltage<=33){
            return 3.0;

        }
        if(voltage>33&&voltage<=132){
            return 4.0;
    }
    if(voltage>131&&voltage<=330){
            return 6;
    }
    if(voltage>330){
            return 9;
    }
    return 0.0000;
}



}
