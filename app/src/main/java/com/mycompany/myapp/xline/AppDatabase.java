package com.mycompany.myapp.xline;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import static android.content.ContentValues.TAG;

@Database(entities = {DataValues.class,Recents.class,soundtracker.class,GroundClearance.class,Explore_Articles.class, Spacings.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private  static final  String LOG_TAG = AppDatabase.class.getSimpleName();
    private  static final Object LOCK = new Object();
    private  static final String DATABASE_NAMEA = "Alllist";
    private  static final String DATABASE_NAMEB = "questionlist";
    private static AppDatabase sInstance, qInstance;

    public static AppDatabase getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(TAG, "creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppDatabase.DATABASE_NAMEA)
                        .build();
//                Toast.makeText(context,"created base",Toast.LENGTH_LONG).show();

            }
//            if(qInstance==null){
//                synchronized (LOCK){
//                    Log.d(TAG, "creating new database instance");
//                    qInstance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,AppDatabase.DATABASE_NAMEB)
//                            .allowMainThreadQueries()
//                            .build();
//
//
//                }

//        }
        }
            return sInstance;

}
    public abstract DataValuesDao dataValuesDao();
    public abstract RecentsDao recentsDao();
    public abstract SoundTrackerDao soundTrackerDao();
    public abstract GroundClearanceDao groundClearanceDao();
    public abstract Explore_articlesDao explore_articlesDao();
    public abstract SpacingsDao spacingsDao();
}
