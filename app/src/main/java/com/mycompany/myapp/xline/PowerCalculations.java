package com.mycompany.myapp.xline;

public class PowerCalculations {
    public PowerCalculations(){
    }
    public double generalPower(double current, double linevoltage, double powerfactor){
        return Math.cbrt(3)*current*linevoltage*powerfactor;
    }
    public double singlePhasePow(double current, double phasevoltage, double powerfactor){
        return current*phasevoltage*powerfactor;
    }
    public  double anyPhasePower(double phasecurrent, double phasevoltage, double powerfactor, double numberOfPhases){
        return phasecurrent*phasevoltage*powerfactor*numberOfPhases;
    }
    public double threePPower(double phasecurrent, double phasevoltage, double powerfactor){
        return (3)*phasecurrent*phasevoltage*powerfactor;
    }
    public double maximumPowerOfAtransmissionLine(double sendingendVoltage, double receivingendVoltage, double inductiveReactance){
        return 3*sendingendVoltage*receivingendVoltage/inductiveReactance;
    }
    public double powerLoss(double current, double resistance){
        return 3*Math.pow(current,2)*resistance;
    }
    public double surgeImpedanceLoading(double lineVoltage, double inductance, double capacitance){
        double fraction = inductance/capacitance;
        return Math.pow(lineVoltage,2)/fraction;
    }
    public double efficiency(double inputPower, double outputPower){
        return (outputPower/inputPower)*100;
    }
    public double current(double voltageInKV, double powerInKw, double powerFactor){
        double denominator = Math.cbrt(3)*powerFactor*voltageInKV;
        return powerInKw/denominator;
    }
}
