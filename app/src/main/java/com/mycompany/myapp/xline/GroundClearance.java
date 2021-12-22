package com.mycompany.myapp.xline;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "groundclearance")
public class GroundClearance {
    @PrimaryKey
    private int id;
    private double voltageValue;
    private double overPeople;
    private double overEmptySpace;
    private double overModerate;


    @Ignore
    public GroundClearance(){

    }

public GroundClearance(double voltageValue, double overPeople, double overModerate, double overEmptySpace){
        this.overPeople = overPeople;
        this.overModerate = overModerate;
        this.overEmptySpace = overEmptySpace;
        this.voltageValue = voltageValue;
    }
    @Ignore
    public GroundClearance(int id,double voltageValue, double overPeople, double overModerate, double overEmptySpace){
        this.overPeople = overPeople;
        this.overModerate = overModerate;
        this.overEmptySpace = overEmptySpace;
        this.voltageValue = voltageValue;
        this.id = id;
    }
    public double getVoltageValue() {
        return voltageValue;
    }

    public double getOverEmptySpace() {
        return overEmptySpace;
    }

    public double getOverModerate() {
        return overModerate;
    }

    public double getOverPeople() {
        return overPeople;
    }

    public void setOverEmptySpace(double overEmptySpace) {
        this.overEmptySpace = overEmptySpace;
    }

    public void setOverModerate(double overModerate) {
        this.overModerate = overModerate;
    }

    public void setOverPeople(double overPeople) {
        this.overPeople = overPeople;
    }

    public void setVoltageValue(double voltageValue) {
        this.voltageValue = voltageValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
