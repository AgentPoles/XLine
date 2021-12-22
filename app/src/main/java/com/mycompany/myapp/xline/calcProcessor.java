package com.mycompany.myapp.xline;

import java.util.ArrayList;
import java.util.List;

public class calcProcessor {
    List<String> innerElementNames;
    List<workingCalculation> workingCalculations;
    int id;
    int number;
    int position;
    List<Spacings> Spacings;
    Spacings respacing;
    double size;
    double length;
    double power;
    double numberOfdiscs;
    double voltage;
    double powerfactor;
    double constante;
    double Current;
    double wcperl;
    boolean bundled;
    double windPressure;
    double iceDensity;
    double iceThickness;
    boolean doubled;
    double utimatestrength;
    double condLength;
    double bundledCurrent;
    List<Explore_Articles> Explore_Articles;
    double span;
    double area;
    int numberOfConductorinAbundle;
    double sag;

    private static calcProcessor ourInstance = new calcProcessor();
    public static calcProcessor getInstance(){return ourInstance;}
//    (double densityOfConductor, double crossSectionalAreaOfConductor, double span, double ultimatetensileStrength, double safetyFactor, double densityOfIce, double thicknessOfIce, double windPressure){
    public static calcProcessor getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(calcProcessor ourInstance) {
        calcProcessor.ourInstance = ourInstance;
    }

    public List<com.mycompany.myapp.xline.Explore_Articles> getExplore_Articles() {
        return Explore_Articles;
    }

    public void setExplore_Articles(List<com.mycompany.myapp.xline.Explore_Articles> explore_Articles) {
        Explore_Articles = explore_Articles;
    }

    public void setInnerElementNames(List<String> innerElementNames) {
        this.innerElementNames = innerElementNames;
    }

    public List<String> getInnerElementNames() {
        return innerElementNames;
    }

    public List<workingCalculation> getWorkingCalculations() {
        return workingCalculations;
    }

    public void setWorkingCalculations(List<workingCalculation> workingCalculations) {
        this.workingCalculations = workingCalculations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public int getPosition() {
        return position;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getCurrent() {
        return Current;
    }

    public void setCurrent(double current) {
        Current = current;
    }

    public double getPower() {
        return power;
    }

    public double getPowerfactor() {
        return powerfactor;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void setPowerfactor(double powerfactor) {
        this.powerfactor = powerfactor;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getArea() {
        return area;
    }

    public double getSpan() {
        return span;
    }

    public double getUtimatestrength() {
        return utimatestrength;
    }

    public double getWcperl() {
        return wcperl;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setSpan(double span) {
        this.span = span;
    }

    public void setUtimatestrength(double utimatestrength) {
        this.utimatestrength = utimatestrength;
    }

    public void setWcperl(double wcperl) {
        this.wcperl = wcperl;
    }

    public int getNumberOfConductorinAbundle() {
        return numberOfConductorinAbundle;
    }

    public void setNumberOfConductorinAbundle(int numberOfConductorinAbundle) {
        this.numberOfConductorinAbundle = numberOfConductorinAbundle;
    }

    public boolean isBundled() {
        return bundled;
    }

    public void setBundled(boolean bundled) {
        this.bundled = bundled;
    }

    public double getBundledCurrent() {
        return bundledCurrent;
    }

    public void setBundledCurrent(double bundledCurrent) {
        this.bundledCurrent = bundledCurrent;
    }

    public double getIceDensity() {
        return iceDensity;
    }

    public void setDoubled(boolean doubled) {
        this.doubled = doubled;
    }

    public double getWindPressure() {
        return windPressure;
    }

    public void setIceDensity(double iceDensity) {
        this.iceDensity = iceDensity;
    }

    public double getIceThickness() {
        return iceThickness;
    }

    public void setWindPressure(double windPressure) {
        this.windPressure = windPressure;
    }

    public boolean isDoubled() {
        return doubled;
    }

    public void setIceThickness(double iceThickness) {
        this.iceThickness = iceThickness;
    }

    public double getSag() {
        return sag;
    }

    public void setSag(double sag) {
        this.sag = sag;
    }

    public double getCondLength() {
        return condLength;
    }

    public void setCondLength(double condLength) {
        this.condLength = condLength;
    }

    public double getConstante() {
        return constante;
    }

    public void setConstante(double constante) {
        this.constante = constante;
    }

    public double getNumberOfdiscs() {
        return numberOfdiscs;
    }

    public void setNumberOfdiscs(double numberOfdiscs) {
        this.numberOfdiscs = numberOfdiscs;
    }

    public List<com.mycompany.myapp.xline.Spacings> getSpacings() {
        return Spacings;
    }

    public void setSpacings(List<com.mycompany.myapp.xline.Spacings> spacings) {
        Spacings = spacings;
    }

    public com.mycompany.myapp.xline.Spacings getRespacing() {
        return respacing;
    }

    public void setRespacing(com.mycompany.myapp.xline.Spacings respacing) {
        this.respacing = respacing;
    }
}
