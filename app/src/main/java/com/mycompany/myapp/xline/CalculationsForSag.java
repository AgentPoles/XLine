package com.mycompany.myapp.xline;

public class CalculationsForSag {
    public CalculationsForSag(){

    }
    public double even_Pole_Height_simple_SagA(double weightPerUnitLength, double span, double tensileStrength){
        return ((weightPerUnitLength* Math.pow((span/2),2))/(2*(tensileStrength*1000)));
    }
    public double even_Pole_Height_simple_SagB(double densityOfConductor, double crossSectionalAreaOfConductor, double span, double ultimatetensileStrength, float safetyFactor, double weightPerUnitLength){
            double w = densityOfConductor * crossSectionalAreaOfConductor;
        double tensileStrength = (ultimatetensileStrength*1000)/safetyFactor;
        return even_Pole_Height_simple_SagA(w ,span,tensileStrength);
    }
    public double even_Pole_Height_complex_wind_SagB(double densityOfConductor, double crossSectionalAreaOfConductor, double span, double ultimatetensileStrength, float safetyFactor, double windPressure){
        double Wc = densityOfConductor*crossSectionalAreaOfConductor;
        double tensileStrength = (ultimatetensileStrength*1000)/safetyFactor;
        double d2 = (crossSectionalAreaOfConductor*4)/Math.PI;
        double d = Math.sqrt(d2);
        double Ww = windPressure * d;
        double w2 = Math.pow(Wc,2) + Math.pow(Ww,2);
        double w = Math.sqrt(w2);
        return even_Pole_Height_simple_SagA(w ,span,tensileStrength);
    }
    public double verticalSag(double totalWeight, double weightOfWind, double sag){
        double average = weightOfWind/totalWeight;
        double tetha = Math.atan(average);
        double slantFactor = Math.cos(tetha);
        return sag*slantFactor;
    }
    public double even_Pole_Height_complex_ice_SagB(double densityOfConductor, double crossSectionalAreaOfConductor, double span, double ultimatetensileStrength, double safetyFactor, double densityOfIce, double thicknessOfIce){
        double Wc = densityOfConductor*crossSectionalAreaOfConductor;
        double tensileStrength = (ultimatetensileStrength*1000)/safetyFactor;
        double d2 = (crossSectionalAreaOfConductor*4)/Math.PI;
        double d = Math.sqrt(d2);
        double areaOfIce = Math.PI*thicknessOfIce*(d+thicknessOfIce);
        double Wi = densityOfIce * areaOfIce;
        double w = Wc + Wi;
        return even_Pole_Height_simple_SagA(w ,span,tensileStrength);
    }
    public  double even_Pole_Height_complex_ice_and_wind_SagB(double wc, double crossSectionalAreaOfConductor, double span, double ultimatetensileStrength, double safetyFactor, double densityOfIce, double thicknessOfIce, double windPressure){
        double Wc = wc;
        double tensileStrength = (ultimatetensileStrength*1000)/safetyFactor;
        double d2 = (crossSectionalAreaOfConductor*4)/Math.PI;
        double d = Math.sqrt(d2);
        double areaOfIce = Math.PI*thicknessOfIce*(d+thicknessOfIce);
        double Wi = densityOfIce * areaOfIce;
        double w1 = Wc + Wi;
        double Ww = windPressure * d;
        double w2 = Math.pow(w1,2) + Math.pow(Ww, 2);
        double w = Math.sqrt(w2);
        return even_Pole_Height_simple_SagA(w ,span,tensileStrength);
    }
    public double even_Pole_Height_complex_ice_and_wind_SagC(double weightPerLengthOfConductor, double crossSectionalAreaOfConductor, double span, double ultimatetensileStrength, double safetyFactor, double densityOfIce, double thicknessOfIce, double windPressure){
        double Wc = weightPerLengthOfConductor/1000;
        double tensileStrength = (ultimatetensileStrength*1000)/safetyFactor;
        double d2 = (crossSectionalAreaOfConductor*4)/Math.PI;
        double d = Math.sqrt(d2)*Math.pow(10,-3);
        double areaOfIce = Math.PI*thicknessOfIce*(d+thicknessOfIce);
        double Wi = densityOfIce * areaOfIce;
        double w1 = Wc + Wi;
        double Ww = windPressure * d/9.81;
        double w2 = Math.pow(w1,2) + Math.pow(Ww, 2);
        double w = Math.sqrt(w2);
        return even_Pole_Height_simple_SagA(w ,span,tensileStrength);
    }
        public double findWindEffectPerUnitLength( double windPressure, double areaOfConductor ){
            double d2 = (areaOfConductor*4)/Math.PI;
            double d = Math.sqrt(d2);
            return windPressure*d;
        }
    public double findTheIceEffectPerUnitLength(double densityOfIce, double thicknessOfIce, double areaOfConductor){
        double d2 = (areaOfConductor*4)/Math.PI;
        double d = Math.sqrt(d2);
        double areaOfIce = Math.PI*thicknessOfIce*(thicknessOfIce+d);
        return densityOfIce * areaOfIce;
    }
    public double findWeightPerUnitLengthOfConductor(double densityOfConductor, double areaOfConductor){
    return densityOfConductor*areaOfConductor;
    }

     public double saggyLengthOfConductor(double ultimateTensileStrength, double loadingPerUnitLengthOfConductor, double span){
        ultimateTensileStrength = ultimateTensileStrength*1000;
        double constant = ultimateTensileStrength/(loadingPerUnitLengthOfConductor*span);
        double innerValue = span/(2*constant);
        double result = 2*constant*Math.sinh(innerValue);
        return result;
     }

    public double findTotalWeightPerUnitLengthConductor_Ice_and_wind(double densityOfConductor, double areaOfConductor, double  windPressure, double densityOfIce, double thicknessOfIce){
    double a = findTheIceEffectPerUnitLength(densityOfIce,thicknessOfIce,areaOfConductor);
    double b = findWindEffectPerUnitLength(windPressure,areaOfConductor);
    double c = findWeightPerUnitLengthOfConductor(densityOfConductor,areaOfConductor);
    double m = Math.pow(a+c,2)+Math.pow(b,2);
    return  Math.sqrt(m);
    }

    public double findTotalWeightPerUnitLengthConductor_Ice_and_windC(double weightperUnitLengthOfConductor, double areaOfConductor, double  windPressure, double densityOfIce, double thicknessOfIce){
        double a = findTheIceEffectPerUnitLength(densityOfIce,thicknessOfIce,areaOfConductor*Math.pow(10,-6));
        double b = findWindEffectPerUnitLength(windPressure/9.81,areaOfConductor*Math.pow(10,-6));
        double c = weightperUnitLengthOfConductor/1000;
        double m = Math.pow(a+c,2)+Math.pow(b,2);
        return  Math.sqrt(m);
    }

    public double uneven_Pole_Height_complex_ice_and_wind_SagB(double wc, double crossSectionalAreaOfConductor, double span, double ultimatetensileStrength, double safetyFactor, double densityOfIce, double thicknessOfIce, double windPressure, double heightOfPoleA, double heightOfPoleB, double referencePole) {
        double heightofPoley = heightOfPoleA;
        double x3 = 0;
        double f = findTotalWeightPerUnitLengthConductor_Ice_and_windC(wc,crossSectionalAreaOfConductor,windPressure,densityOfIce,thicknessOfIce);
        double heightDifference = Math.abs(heightOfPoleA - heightOfPoleB);
        double halfSpan = span / 2;
        double tensileStrength = (ultimatetensileStrength*1000) / safetyFactor;
        if (referencePole == 1) {
            if (heightOfPoleA > heightOfPoleB) {
                x3 = (span / 2) + (tensileStrength * heightDifference) / (f * span);
            }
            else {
                x3 = (span / 2) - (tensileStrength * heightDifference) / (f * span);
            }
        }
            else {
                heightofPoley = heightOfPoleB;
                if(heightOfPoleB>heightOfPoleA){
                    x3 = (span / 2) + (tensileStrength * heightDifference) / (f * span);
                }
                else {
                    x3 = (span / 2) - (tensileStrength * heightDifference) / (f * span);
                }

            }
            double toSag = (f*Math.pow(x3,2))/(2*tensileStrength);
        return toSag;
        }
         public double clearanceMidWayEqualHeightPole(double sag, double heightOfPole){
        return heightOfPole- sag;
         }
         public double clearanceOfSagMidWayUnEqualHeightPoles( double sag, double distanceOfPointOfSagFromReference, double heightOfReferencePole, double tensileStrength, double totalWeightPerUnitLength, double span){
        double dmid = (totalWeightPerUnitLength)*Math.pow(distanceOfPointOfSagFromReference,2)/(2*tensileStrength);
        double clearance = heightOfReferencePole-(sag-dmid);
        return clearance;
         }

         public double TotalLengthOfConductor(double lengthOfLine, double span, double saggyLengthOfConductor){

        return (lengthOfLine/span)*saggyLengthOfConductor;
         }

         public double Constante(double horizontalTension, double weightPerUnitLength, double span){
        double constant = horizontalTension/(weightPerUnitLength*span);
        return constant;
         }

         public double NumberofDics(double lineVoltage){
        double top = lineVoltage/Math.cbrt(2);
        return top/11;
         }
    }
