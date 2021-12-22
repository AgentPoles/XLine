package com.mycompany.myapp.xline;

public class CalculationsForCapacitance {
    public CalculationsForCapacitance(){
            }

    public double calculationForCapacitance(double relativePermitivity, double gmd, double GMR){
double numerator = 2*Math.PI*relativePermitivity*8.854*Math.pow(10,-12);
double denomenatorFraction = gmd/GMR;
double fraction = numerator/Math.log(denomenatorFraction);
return fraction;
        }

        public double CalculationForShuntAmittance(double frequency, double capacitance, double lenghtofline){
        double value = 2*Math.PI*frequency*capacitance*lenghtofline;
        return value;
        }

        public double CapacitiveReactance(double frequency, double capacitance, double lenghtofline){
        double answer = 1/CalculationForShuntAmittance(frequency,capacitance,lenghtofline);
        return answer;
        }

        public double calculationForLineToLineCapacitanceForTwoWires(double linetoNeutralCapacitance){
        return linetoNeutralCapacitance/2;
        }

        public double calculationForLineToNeutralCapacitanceInThePresenceOfEarth(double distanceABprime, double distanceBCprime, double distanceACprime, double radiusofConductor,
                                                                                 double distanceAN, double distanceBN, double distanceCN, double spacingbetweenAB, double relativePermitivity){
        double one = distanceABprime* distanceACprime * distanceBCprime;
        double two = distanceAN*distanceBN*distanceCN*8;
            double numerator = 2*Math.PI*relativePermitivity*8.854*Math.pow(10,-12);
        double fractionone = spacingbetweenAB/radiusofConductor;
        double fractiontwo = one/two;
        double three = Math.cbrt(fractiontwo);
        double denomentor = Math.log(fractionone)-Math.log(three);
        return numerator/denomentor;
        }
}
