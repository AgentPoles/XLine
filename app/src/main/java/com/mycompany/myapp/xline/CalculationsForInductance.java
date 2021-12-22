package com.mycompany.myapp.xline;

public class CalculationsForInductance {
    public CalculationsForInductance() {}
    public double inductanceBetweenTwoPointsOutsideAconductor(double distanceOfPointAFromCenter, double distanceOfPointBfromCenter){
       double fraction = distanceOfPointAFromCenter/distanceOfPointBfromCenter;
      double preResult = Math.log(fraction);
      return  2*Math.PI*preResult;
      }
      public double inductanceOfaSinglePhaseLine(double radiusOfConductors, double spacingBetweenConductors){
        double Gmr = radiusOfConductors*0.7788;
        double fraction = spacingBetweenConductors/Gmr;
        return 2*Math.pow(10,-7)*Math.log(fraction);
      }
    public double inductanceOfaThreePhaseLineSingleCircuit(double radiusOfConductors, double spacingBetweenConductors){
        double Gmr = radiusOfConductors*0.7788;
        double fraction = spacingBetweenConductors/Gmr;
        return 2*Math.pow(10,-7)*Math.log(fraction);
    }
      public double CalculationsForThreePhaseGmd(double distanceAB, double distanceAC, double distanceBC){
        double product = distanceAB*distanceAC*distanceBC;
              return Math.cbrt(product);
      }
      public double CalculationForGmrNeglectingBundling(double radiusOfConductor){
        return 0.7788*radiusOfConductor;
      }
      public double inductiveReactance(double inductance, double frequency, double lenghtOfLine){
        return 2*Math.PI* frequency*inductance*lenghtOfLine;
      }
      public double inductanceForThreePhaseLine(double Gmd, double Gmr ){
        double fraction = Gmd/Gmr;
        return 2*Math.pow(10,-7)*Math.log(fraction);
      }
public double GmrUptoFourConductorsPerBundle(double numberOfBundling, double diameterOfConductors, double GMRofOneStrand){
  double inner =(Math.pow(diameterOfConductors,numberOfBundling-1))*GMRofOneStrand;
      if(numberOfBundling<4){
//        double inner =(Math.pow(diameterOfConductors,numberOfBundling-1))*GMRofOneStrand;
        if(numberOfBundling == 1){
          return GMRofOneStrand;
        }
        else{
          if(numberOfBundling == 2){
            return Math.sqrt(inner);
          }
          else {
            if(numberOfBundling == 3){
              return Math.cbrt(inner);
            }
          }
        }
      }
      else {
        double morreinner = Math.sqrt(inner);
        return 1.09*Math.sqrt(morreinner);
      }
      return 0.00;











}
  public  double GMRdoubleCircuit(double daa, double apriapri, double daapri,double dbb, double bpribpri, double dbbpri,double dcc, double cpricpri, double dccpri ){
        double dsa = Math.sqrt(Math.sqrt(daa*daapri*daapri*apriapri));
      double dsb = Math.sqrt(Math.sqrt(dbb*dbbpri*dbbpri*bpribpri));
      double dsc = Math.sqrt(Math.sqrt(dcc*dccpri*dccpri*cpricpri));
      return Math.cbrt(dsa*dsb*dsc);
  }
  public double GMDdoubleCircuit(double dab, double apribpri, double dabpri, double dbapri,double dbc, double bricpri, double dbcpri, double dcbpri, double dac, double apricpri, double dacpri, double dcapri ){
      double Dab = Math.sqrt(Math.sqrt(dabpri*dab*apribpri*dbapri));
      double Dbc = Math.sqrt(Math.sqrt(dbc*dbcpri*dcbpri*bricpri));
      double Dac = Math.sqrt(Math.sqrt(dac*dacpri*dcapri*apricpri));
      return Math.cbrt(Dab*Dbc*Dac);
  }
    }
