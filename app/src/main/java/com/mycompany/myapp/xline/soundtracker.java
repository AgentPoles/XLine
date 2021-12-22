package com.mycompany.myapp.xline;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "soundtracker")
public class soundtracker {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int number;
    @Ignore
    public soundtracker(){

    }
    @Ignore
    public soundtracker(int number){
        this.number = number;
    }

    public soundtracker(int id, int number){this.number = number;

    this.id = id;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
