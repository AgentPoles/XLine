package com.mycompany.myapp.xline;

import android.os.Handler;

import java.util.List;

public class DesignEstimates {
    public DesignEstimates(){

    }

//    public void Size(final double foundCurrent, final AppDatabase mdb, final calcProcessor calceProcessor, final Handler handler){
//        double size;
//        AppExecutor.getsInstance().diskIO().execute(new Runnable() {
//            @Override
//            public void run() {
//            double maximum = 477480505;
//            int position;
//
//               List<DataValues> dataValuesList = mdb.dataValuesDao().LoadAllOrdinaryDataValues();
//               for(int i = 0; i<dataValuesList.size();i++){
//                   if(dataValuesList.get(i).getDurrentCarryingCapacity()>foundCurrent&&dataValuesList.get(i).getDurrentCarryingCapacity()<maximum){
//                       maximum = dataValuesList.get(i).getDurrentCarryingCapacity();
//                    size = dataValuesList.get(i).getSize();
//
//                   }
//               }
//               calceProcessor.setSize(size);
//
//            }
//        });
//    }
    public String VoltageConsiderations(double voltage){
        String result = "";
        if(voltage<220){

        }else {
            result = "since voltage is greater than 220kilovolts, bundled conductors are advised to reduce corona effect";
        }
   return result;
    }

public String StatementAlongSpan(double LengthOFline){
    return "from the Nif it is assumed that the span is repeated along the line, and neglecting all other intricacies, the length of the conductor required for tranmission" +
            "would be " +String.valueOf(LengthOFline);
}
    public String RightOfWays(double voltage){
        int ROW = 0;
        if(voltage>=33&&voltage<100){
            ROW = 11;
        }
        else {
            if(voltage>=100&&voltage<300){
                ROW = 30;
            }
            else if(voltage > 300){
                ROW = 50;
            }
            else {
                return "input voltage is not a transmission level voltage";
            }
        }
        String clause = "The Right of Way (RoW) is the distance of any structure from the middle conductors of\n" +
                "overhead power lines of any voltage level, from clause 3.1 of NERC, a suitable ROW for this project is "+ROW+"meters";
        return clause;
    }

    public String MinimumTensileStrength(){
        return "from clause 3.2.1 of the NERC standard, Overhead transmission conductors – including overhead ground wires but excluding\n" +
                "the case where they are cables – shall be stranded wires with a tensile strength no less\n" +
                "than 10kN/m.";
    }

public String DielectricStrengthOfOHTL(double voltage){
        String b = "";
      String initial = "The clearance between overhead transmission conductors (excluding cables) and their supporting structures, ";
      String c = "for the dielectric test, Where the operational voltage to ground is applied between the overhead transmission line and the ground continuously for ten (10) minutes to test the dielectric strength (in conformity with IEC60038) before the commencement of operation, the transmission line shall withstand such a test standard.";
      if(voltage <132){
          b = "from the NERC standard, no specific value is given for this voltage level, but it should be around 70cm";
      }
      if(voltage>=132&&voltage<300){
          b = " should be around 145cm";
      }
      if(voltage>=300){
          b = "should not be less than 145cm";
      }

      return initial + b;
}

public double span(boolean bundled){
        if(!bundled){
            return 400;
        }
        else {
            return 250;
        }
}

   public String plantclearances(double voltage){
           String initials = "The clearance between any overhead transmission conductor and any plant shall not be less than ";
           double value = 0;
           if(voltage>33) {
               double diff = voltage - 33;
               int result = (int) (diff / 10);
               value = 2 + (result * 0.06);
           }
           else {
               value = 2;
           }
           String b = String.valueOf(value);
           String c = ", except when conductor is insulated, then, contact is avoided";
           return initials+b+c;
   }

   public String restrictionInUrbanAreas(String name, double voltage){
       String b = "";
       String initial = name +", for your project, an area is said to be urban if the building to land ratio is greater than 25 percent ";
       if(voltage<132){
            b= " since the intended transmission voltage is  below 132kio volts in can be installed in urban areas, but care should be taken not to endanger lives.";
       }
       else {
           b = " since the intended voltage is up to 132 kilo volts it should not be installed in urban areas";
       }
       String c = "in either case, Supporting structures shall be provided with an indication of danger in a location where\n" +
               "it is easily read. This shall not apply when insulated conductors are used for overhead\n" +
               "transmission lines with a nominal voltage less than or equal to 33kV.";
       return initial+b+c;
   }

   public String LimitationOfSpan(){

       return " the steel towers will have a span no longer than 400 meters or no longer than 250 meters when two or more electrical conductors are arranged horizontally and the distance between the electrical conductors is less than 4 meters";
   }
   public String ClearanceOfconductorFromGround(double voltage){
        String a = "The height of overhead transmission conductors above the ground shall be not less\n" +
                "than ";
        String b = "";
        if(voltage<33){
            b = "10.06 meters or 8 meters when insulated conductors are used.";
        }
        if(voltage>33){
            double value = 0;

                double diff = voltage - 33;
                int result = (int) (diff / 10);
                value = 10.06 + (result * 0.06);
                b = String.valueOf(value) + "meters";

        }
        String c = "The height shall be secured to provide\n" +
                "for the occurrence of such case that the electrical conductor sag at the maximum\n" +
                "design operating temperature.\n" +
                "and shall not apply to overhead transmission lines for a span connecting the yard of a\n" +
                "power plant, substation or similar place to the outside.";
        return a + b + c;
   }


}
