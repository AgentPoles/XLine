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
public interface SoundTrackerDao {
    @Query("SELECT*FROM soundtracker ORDER BY id")
    LiveData<List<soundtracker>> LoadAllTrackers();
    @Query("SELECT*FROM soundtracker ORDER BY id")
    List<soundtracker> LoadAllOrdinaryTrackers();
    @Insert
    void insertSoundTracker(soundtracker soundtracker);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSoundtracker(soundtracker soundtracker);
    @Delete
    void deleteSoundTracker(soundtracker soundtracker);
    @Query("SELECT*FROM soundtracker WHERE id= :id")
    LiveData<soundtracker> LoadSoundTrackerbyId (int id);
    @Query("SELECT*FROM soundtracker WHERE id= :id")
    soundtracker LoadOrdinarySoundTrackerbyid (int id);
}
