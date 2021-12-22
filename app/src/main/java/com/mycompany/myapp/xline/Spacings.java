
package com.mycompany.myapp.xline;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "spacings")
    public class Spacings {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
        private double earthwire;
        private double fourh;
        private double threek;
        private double sixk;
        private double elevenk;
        private double thirtythreek;
        private double sixtyk;
        private double onethirtyk;
        private double threethirtyk;

        @Ignore
    Spacings(){

    }
@Ignore
    Spacings(String name, double earthwire, double fourh, double threek, double sixk, double elevenk, double thirtythreek, double sixtyk, double onethirtyk, double threethirtyk){
        this.earthwire = earthwire;
        this.fourh = fourh;
        this.threek = threek;
        this.sixk = sixk;
        this.elevenk = elevenk;
        this.threethirtyk =thirtythreek;
        this.sixtyk = sixtyk;
        this.onethirtyk = onethirtyk;
        this.threethirtyk = threethirtyk;
    }
    Spacings(int id, String name, double earthwire, double fourh, double threek, double sixk, double elevenk, double thirtythreek, double sixtyk, double onethirtyk, double threethirtyk){
        this.earthwire = earthwire;
        this.fourh = fourh;
        this.threek = threek;
        this.sixk = sixk;
        this.elevenk = elevenk;
        this.threethirtyk =thirtythreek;
        this.sixtyk = sixtyk;
        this.onethirtyk = onethirtyk;
        this.threethirtyk = threethirtyk;
        this.id = id;
    }
    public double getEarthwire() {
        return earthwire;
    }

    public double getElevenk() {
        return elevenk;
    }

    public double getFourh() {
        return fourh;
    }

    public double getOnethirtyk() {
        return onethirtyk;
    }

    public double getSixk() {
        return sixk;
    }

    public double getSixtyk() {
        return sixtyk;
    }

    public double getThirtythreek() {
        return thirtythreek;
    }

    public double getThreek() {
        return threek;
    }

    public double getThreethirtyk() {
        return threethirtyk;
    }

    public void setEarthwire(double earthwire) {
        this.earthwire = earthwire;
    }

    public void setElevenk(double elevenk) {
        this.elevenk = elevenk;
    }

    public void setFourh(double fourh) {
        this.fourh = fourh;
    }

    public void setOnethirtyk(double onethirtyk) {
        this.onethirtyk = onethirtyk;
    }

    public void setSixk(double sixk) {
        this.sixk = sixk;
    }

    public void setSixtyk(double sixtyk) {
        this.sixtyk = sixtyk;
    }

    public void setThirtythreek(double thirtythreek) {
        this.thirtythreek = thirtythreek;
    }

    public void setThreek(double threek) {
        this.threek = threek;
    }

    public void setThreethirtyk(double threethirtyk) {
        this.threethirtyk = threethirtyk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
