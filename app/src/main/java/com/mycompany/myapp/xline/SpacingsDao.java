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
public interface SpacingsDao {
    @Query("SELECT*FROM spacings ORDER BY id")
    LiveData<List<Spacings>> LoadAllSpacings();
    @Query("SELECT*FROM spacings ORDER BY id")
    List<Spacings> LoadAllOrdinarySpacings();
    @Insert
    void insertSpacing(Spacings spacings);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSpacings(Spacings spacings);
    @Delete
    void deleteSpacings(Spacings spacings);
    @Query("SELECT*FROM spacings WHERE id = :id")
    LiveData<Spacings> LoadSpacingbyId (int id);
    @Query("SELECT*FROM spacings WHERE id= :id")
    Spacings LoadOrdinarySpacingbyid (int id);

}
