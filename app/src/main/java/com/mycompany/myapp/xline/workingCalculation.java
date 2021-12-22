package com.mycompany.myapp.xline;

public class workingCalculation {
    private String  value;
    private int number;
    public workingCalculation(){

    }
    public workingCalculation(String value, int number){


    this.value = value;
    this.number = number;}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
