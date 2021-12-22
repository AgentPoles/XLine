package com.mycompany.myapp.xline;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Entity(tableName = "datavalues")
public class DataValues {

    @PrimaryKey(autoGenerate = true)
    private int _id;
    private int size;
    private double numberAl;
    private double diameterAl;
    private double numberGSW;
    private double diameterGSW;
   private  double calculatedCSAal;
   private double calculatedCSAgsw;
   private double approxOverAllDiameter;
   private double approxWeightOfConductor;
    private double calculatedBreakingLoad;
    private double durrentCarryingCapacity;
    private double standardLengthperReel;
    private double dcResValue;

@Ignore
    public DataValues(){

    }
    @Ignore
    public DataValues(int size, double numberAl, double diameterAl, double numberGSW, double diameterGSW,
                      double calculatedCSAal, double calculatedCSAgsw, double approxOverAllDiameter, double approxWeightOfConductor,
                      double calculatedBreakingLoad, double dcResValue,double durrentCarryingCapacity, double standardLengthperReel){
        this.size = size;
        this.numberAl = numberAl;
        this.diameterAl = diameterAl;
        this.numberGSW = numberGSW;
        this.diameterGSW = diameterGSW;
        this.calculatedCSAal = calculatedCSAal;
        this.calculatedCSAgsw = calculatedCSAgsw;
        this.approxOverAllDiameter = approxOverAllDiameter;
        this.approxWeightOfConductor = approxWeightOfConductor;
        this.calculatedBreakingLoad = calculatedBreakingLoad;
//        this.dCResistanceatMax = dCResistanceatMax;
        this.durrentCarryingCapacity = durrentCarryingCapacity;
        this.standardLengthperReel = standardLengthperReel;
        this.dcResValue = dcResValue;
    }

    public DataValues(int _id, int size, double numberAl, double diameterAl, double numberGSW, double diameterGSW,
                      double calculatedCSAal, double calculatedCSAgsw, double approxOverAllDiameter, double approxWeightOfConductor,
                      double calculatedBreakingLoad,double dcResValue, double durrentCarryingCapacity, double standardLengthperReel){
        this.size = size;
        this.numberAl = numberAl;
        this.diameterAl = diameterAl;
        this.numberGSW = numberGSW;
        this.diameterGSW = diameterGSW;
        this.calculatedCSAal = calculatedCSAal;
        this.calculatedCSAgsw = calculatedCSAgsw;
        this.approxOverAllDiameter = approxOverAllDiameter;
        this.approxWeightOfConductor = approxWeightOfConductor;
        this.calculatedBreakingLoad = calculatedBreakingLoad;
//        this.dCResistanceatMax = dCResistanceatMax;
        this.durrentCarryingCapacity = durrentCarryingCapacity;
        this.standardLengthperReel = standardLengthperReel;
        this.dcResValue = dcResValue;
    }

    public double getApproxOverAllDiameter() {
        return approxOverAllDiameter;
    }

    public double getCalculatedCSAal() {
        return calculatedCSAal;
    }

    public double getCalculatedCSAgsw() {
        return calculatedCSAgsw;
    }

    public double getDiameterAl() {
        return diameterAl;
    }

    public double getDiameterGSW() {
        return diameterGSW;
    }

    public double getNumberAl() {
        return numberAl;
    }

    public double getNumberGSW() {
        return numberGSW;
    }

    public int get_id() {
        return _id;
    }

    public double getDcResValue() {
        return dcResValue;
    }

    public void setDcResValue(double dcResValue) {
        this.dcResValue = dcResValue;
    }

    public int getSize() {
        return size;
    }

    public double getApproxWeightOfConductor() {
        return approxWeightOfConductor;
    }

    public double getCalculatedBreakingLoad() {
        return calculatedBreakingLoad;
    }

//    public double getdCResistanceatMax() {
//        return dCResistanceatMax;
//    }

    public double getDurrentCarryingCapacity() {
        return durrentCarryingCapacity;
    }

    public double getStandardLengthperReel() {
        return standardLengthperReel;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setApproxOverAllDiameter(double approxOverAllDiameter) {
        this.approxOverAllDiameter = approxOverAllDiameter;
    }

    public void setCalculatedCSAal(double calculatedCSAal) {
        this.calculatedCSAal = calculatedCSAal;
    }

    public void setApproxWeightOfConductor(double approxWeightOfConductor) {
        this.approxWeightOfConductor = approxWeightOfConductor;
    }

    public void setCalculatedCSAgsw(double calculatedCSAgsw) {
        this.calculatedCSAgsw = calculatedCSAgsw;
    }

    public void setDiameterAl(double diameterAl) {
        this.diameterAl = diameterAl;
    }

    public void setDiameterGSW(double diameterGSW) {
        this.diameterGSW = diameterGSW;
    }

    public void setCalculatedBreakingLoad(double calculatedBreakingLoad) {
        this.calculatedBreakingLoad = calculatedBreakingLoad;
    }

//    public void setdCResistanceatMax(double dCResistanceatMax) {
//        this.dCResistanceatMax = dCResistanceatMax;
//    }

    public void setNumberAl(double numberAl) {
        this.numberAl = numberAl;
    }

    public void setNumberGSW(double numberGSW) {
        this.numberGSW = numberGSW;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setDurrentCarryingCapacity(double durrentCarryingCapacity) {
        this.durrentCarryingCapacity = durrentCarryingCapacity;
    }

    public void setStandardLengthperReel(double standardLengthperReel) {
        this.standardLengthperReel = standardLengthperReel;
    }
}
///package com.mycompany.myapp.xline;
//
//        import android.arch.lifecycle.LiveData;
//        import android.arch.persistence.room.Dao;
//        import android.arch.persistence.room.Delete;
//        import android.arch.persistence.room.Insert;
//        import android.arch.persistence.room.OnConflictStrategy;
//        import android.arch.persistence.room.Query;
//        import android.arch.persistence.room.Update;
//
//        import java.util.List;
//@Dao
//public interface DataValuesDao {
//    @Query("SELECT*FROM datavalues ORDER BY _id")
//    LiveData<List<DataValues>> LoadAllDataValues();
//    @Query("SELECT*FROM datavalues ORDER BY _id")
//    List<DataValues> LoadAllOrdinaryDataValues();
//    @Insert
//    void insertDataValues(DataValues dataValues);
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    void updateDataValues(DataValues dataValues);
//    @Delete
//    void deleteDataValue(DataValues dataValues);
//    @Query("SELECT*FROM datavalues WHERE _id = :id")
//    LiveData<DataValues> LoadDataValuebyId (int id);
//    @Query("SELECT*FROM datavalues WHERE _id= :id")
//    DataValues LoadOrdinaryDataValuebyid (int id);
//}
//
