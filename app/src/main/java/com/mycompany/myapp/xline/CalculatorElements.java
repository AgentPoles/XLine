package com.mycompany.myapp.xline;

import java.util.ArrayList;

public class CalculatorElements {
    private String toBeFound;
    private String otherThing;
    private int thumbnail;
    private ArrayList<String> strings;
    private ArrayList<workingCalculation> workingCalculations;

    public CalculatorElements(){

    }public
    CalculatorElements(String toBeFound, String otherThing, int thumbnail,ArrayList<workingCalculation> workingCalculations){
              this.toBeFound = toBeFound;
        this.otherThing = otherThing;
        this.thumbnail = thumbnail;
        this.workingCalculations = workingCalculations;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getOtherThing() {
        return otherThing;
    }

    public String getToBeFound() {
        return toBeFound;
    }

    public ArrayList<String> getStrings() {
        return strings;
    }

    public void setStrings(ArrayList<String> strings) {
        this.strings = strings;
    }

    public void setOtherThing(String otherThing) {
        this.otherThing = otherThing;
    }

    public void setToBeFound(String toBeFound) {
        this.toBeFound = toBeFound;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ArrayList<workingCalculation> getWorkingCalculations() {
        return workingCalculations;
    }

    public void setWorkingCalculations(ArrayList<workingCalculation> workingCalculations) {
        this.workingCalculations = workingCalculations;
    }
}
