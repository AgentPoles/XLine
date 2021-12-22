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
public interface RecentsDao {
    @Query("SELECT*FROM recents ORDER BY id")
    LiveData<List<Recents>> LoadAllRecents();
    @Query("SELECT*FROM recents ORDER BY id")
    List<Recents> LoadAllOrdinaryRecents();
    @Insert
    void insertRecents(Recents recents);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateRecents(Recents recents);
    @Delete
    void deleteRecents(Recents recents);
    @Query("SELECT*FROM recents WHERE id = :id")
    LiveData<Recents> LoadRecentbyId (int id);
    @Query("SELECT*FROM recents WHERE id= :id")
    Recents LoadOrdinaryRecentbyid (int id);
}
