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
public interface Explore_articlesDao {
    @Query("SELECT*FROM explore_articles ORDER BY article_id")
    LiveData<List<Explore_Articles>> LoadAllExploreArticles();
    @Query("SELECT*FROM explore_articles ORDER BY article_id")
    List<Explore_Articles> LoadAllOrdinaryExploreArticles();
    @Insert
    void insertExploreArticles(Explore_Articles explore_articles);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateExplore_articles(Explore_Articles explore_articles);
    @Delete
    void deleteExplore_article(Explore_Articles exploreArticles);
    @Query("SELECT*FROM explore_articles WHERE article_id = :id")
    LiveData<Explore_Articles> LoadExploreArticlebyId (int id);
    @Query("SELECT*FROM explore_articles WHERE article_id= :id")
    Explore_Articles LoadOrdinaryExploreArticlebyid (int id);
}
