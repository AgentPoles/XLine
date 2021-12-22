package com.mycompany.myapp.xline;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "recents")
public class Recents {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double timestamp;
    private String recentname;
    private double value;
  @Ignore
    public Recents(){

    }
    @Ignore
    public Recents(double timestamp, String recentname,double value){
        this.value = value;
        this.recentname = recentname;
        this.timestamp = timestamp;
    }
    public Recents(int id, double timestamp, String recentname, double value){
        this.id = id;
        this.timestamp = timestamp;
        this.recentname =recentname;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public double getValue() {
        return value;
    }

    public String getRecentname() {
        return recentname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    public void setRecentname(String recentname) {
        this.recentname = recentname;
    }
}
