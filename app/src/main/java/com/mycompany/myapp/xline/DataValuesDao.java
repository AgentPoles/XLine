package com.mycompany.myapp.xline;

        import android.arch.lifecycle.LiveData;
        import android.arch.persistence.room.Dao;
        import android.arch.persistence.room.Delete;
        import android.arch.persistence.room.Insert;
        import android.arch.persistence.room.OnConflictStrategy;
        import android.arch.persistence.room.Query;
        import android.arch.persistence.room.Update;

import com.mycompany.myapp.xline.DataValues;

import java.util.List;
@Dao
public interface DataValuesDao {
    @Query("SELECT*FROM datavalues ORDER BY _id")
    LiveData<List<DataValues>> LoadAllDataValues();
    @Query("SELECT*FROM datavalues ORDER BY _id")
    List<DataValues> LoadAllOrdinaryDataValues();
    @Insert
    void insertDataValues(DataValues dataValues);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateDataValues(DataValues dataValues);
    @Delete
    void deleteDataValue(DataValues dataValues);
    @Query("SELECT*FROM datavalues WHERE _id = :id")
    LiveData<DataValues> LoadDataValuebyId (int id);
    @Query("SELECT*FROM datavalues WHERE _id= :id")
    DataValues LoadOrdinaryDataValuebyid (int id);
}

