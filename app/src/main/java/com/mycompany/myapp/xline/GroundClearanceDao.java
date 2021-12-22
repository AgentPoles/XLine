package com.mycompany.myapp.xline;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface GroundClearanceDao {
    @Query("SELECT*FROM groundclearance ORDER BY id")
    LiveData<List<GroundClearance>> LoadAllGroundClearances();
    @Query("SELECT*FROM groundclearance ORDER BY id")
    List<GroundClearance> LoadAllOrdinaryGroundClearances();
    @Insert
    void insertGroundClearance(GroundClearance groundClearance);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateGroundClearance(GroundClearance groundClearance);
    @Delete
    void deleteGroundClearance(GroundClearance groundClearance);
    @Query("SELECT*FROM groundclearance WHERE id = :id")
    LiveData<GroundClearance> LoadGroundClearanceById (int id);
    @Query("SELECT*FROM groundclearance WHERE id= :id")
    GroundClearance LoadOrdinaryGroundClearancebyid (int id);
}

