package com.mycompany.myapp.xline;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "explore_articles")
public class Explore_Articles {
    @PrimaryKey(autoGenerate = true)
    private int article_id;
    private String articleTitle;
    private String articleBody;
    @Ignore
    Explore_Articles(){

    }
    @Ignore
    Explore_Articles(String articleTitle, String articleBody){

        this.articleBody = articleBody;
        this.articleTitle = articleTitle;
    }

    Explore_Articles(int article_id, String articleTitle, String articleBody){
       this.article_id = article_id;
        this.articleBody = articleBody;
        this.articleTitle = articleTitle;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getArticleBody() {
        return articleBody;
    }

    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }
}
