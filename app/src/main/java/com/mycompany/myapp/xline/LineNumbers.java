package com.mycompany.myapp.xline;

import java.util.ArrayList;

public class LineNumbers {
    public LineNumbers(){

    }
    public ArrayList<Double> shortLineNimbers(double resistance, double inductance,double lengthOfLine, double frequency){
        ArrayList<Double> results = new ArrayList<>();
        results.add(1.0000);
        double inductiveReactance = 2*Math.PI* frequency*inductance*lengthOfLine;
       double impedance = impedance(resistance,inductiveReactance);
        results.add(impedance);
        results.add(0.0000);
        results.add(1.0000);
        return results;
        }
        public  double impedance(double resistance, double inductiveReactance){
            double impedancesq = Math.pow(resistance,2) + Math.pow(inductiveReactance,2);
            double impedance = Math.sqrt(impedancesq);
            return  impedance;
        }

    public double inductiveReactance(double inductance, double frequency, double lengthOfline){
        return 2*Math.PI* frequency*inductance*lengthOfline;
    }
    public double CalculationForShuntAmittance(double frequency, double capacitance, double lenghtofline){
        double value = 2*Math.PI*frequency*capacitance*lenghtofline;
        return value;
    }
    public double CapacitiveReactance(double frequency, double capacitance, double lenghtofline){
        double answer = 1/CalculationForShuntAmittance(frequency,capacitance,lenghtofline);
        return answer;
    }

        public ArrayList<Double> MediumLineNumbers (double impedance, double admittance){
            ArrayList<Double> results = new ArrayList<>();
        double anumerator = impedance*admittance/2;
        results.add(anumerator+1);
        results.add(impedance);
        double bnumerator = anumerator/2+1;
        results.add(admittance*bnumerator);
        results.add(anumerator);
        return results;
        }


        public  double yFactor(double impedance, double admittance){
        double numerator = impedance*admittance;
        return Math.sqrt(numerator);
        }


        public double yPrime(double yfactor, double lengthOfLine, double admittance){
        double process = yfactor*lengthOfLine/2;
        double numerator = admittance*Math.tanh(process);
        return numerator/process;
        }

        public double zPrime(double yfactor, double lengthOfLine, double impedance){
        double process = yfactor *lengthOfLine;
        double numerator = impedance*Math.sinh(process);
        return  numerator/process;
        }

        public ArrayList<Double> longLineNumbers(double zprime, double yprime){
        ArrayList<Double> results = new ArrayList<Double>();
        double processa = zprime*yprime/2;
        results.add(processa +1);
        results.add(zprime);
        double processb = processa/2+1;
        results.add(yprime*processb);
        results.add(processa);
        return results;
        }
    }


