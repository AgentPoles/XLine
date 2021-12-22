package com.mycompany.myapp.xline;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private LiveData<List<Recents>> recents;
    private LiveData<List<DataValues>> dataValues;
    private LiveData<List<soundtracker>> soundtracker;
    private LiveData<List<GroundClearance>> groundClearance;
    private LiveData<List<Explore_Articles>> explore_articles;
    private LiveData<List<Spacings>> spacings;
    public MyViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getsInstance(getApplication());
//        info = appDatabase.infoDao().LoadAllInfo();
//        folder = appDatabase.folderDao().LoadAl);
//        user = appDatabase.userDao().LoadAllUsers();
//        questions = appDatabase.questionDao().LoadAllQuestions();
//        answers = appDatabase.answerdao().LoadAllAnswer();
//        classors = appDatabase.classorsDao().LoadAllClasses();
          recents = appDatabase.recentsDao().LoadAllRecents();
          dataValues = appDatabase.dataValuesDao().LoadAllDataValues();
          soundtracker = appDatabase.soundTrackerDao().LoadAllTrackers();
          groundClearance = appDatabase.groundClearanceDao().LoadAllGroundClearances();
          explore_articles = appDatabase.explore_articlesDao().LoadAllExploreArticles();
          spacings = appDatabase.spacingsDao().LoadAllSpacings();
    }

//    public LiveData<List<Info>> getInfo() {
//        return info;
//    }
//
//    public LiveData<List<Documents>> getDoc() {
//        return doc;
//    }
//
//    public LiveData<List<Folders>> getFolder() {
//        return folder;
//    }
//
//    public void setDoc(LiveData<List<Documents>> doc) {
//        this.doc = doc;
//    }
//
//    public void setFolder(LiveData<List<Folders>> folder) {
//        this.folder = folder;
//    }
//
//    public void setInfo(LiveData<List<Info>> info) {
//        this.info = info;
//    }
//
//    public LiveData<List<User>> getUser() {
//        return user;
//    }
//
//    public void setUser(LiveData<List<User>> user) {
//        this.user = user;
//    }
//
//    public LiveData<List<question>> getQuestions() {
//        return questions;
//    }
//
//    public void setQuestions(LiveData<List<question>> questions) {
//        this.questions = questions;
//    }


    public LiveData<List<DataValues>> getDataValues() {
        return dataValues;
    }

    public void setDataValues(LiveData<List<DataValues>> dataValues) {
        this.dataValues = dataValues;
    }

    public void setRecents(LiveData<List<Recents>> recents) {
        this.recents = recents;
    }

    public LiveData<List<Recents>> getRecents() {
        return recents;
    }

    public LiveData<List<com.mycompany.myapp.xline.soundtracker>> getSoundtracker() {
        return soundtracker;
    }

    public void setSoundtracker(LiveData<List<com.mycompany.myapp.xline.soundtracker>> soundtracker) {
        this.soundtracker = soundtracker;
    }

    public void setGroundClearance(LiveData<List<GroundClearance>> groundClearance) {
        this.groundClearance = groundClearance;
    }

    public LiveData<List<GroundClearance>> getGroundClearance() {
        return groundClearance;
    }

    public LiveData<List<Explore_Articles>> getExplore_articles() {
        return explore_articles;
    }

    public void setExplore_articles(LiveData<List<Explore_Articles>> explore_articles) {
        this.explore_articles = explore_articles;
    }

    public LiveData<List<Spacings>> getSpacings() {
        return spacings;
    }

    public void setSpacings(LiveData<List<Spacings>> spacings) {
        this.spacings = spacings;
    }
    //
//    public void setAnswers(LiveData<List<Answer>> answers) {
//        this.answers = answers;
//    }
//
//    public LiveData<List<Answer>> getAnswers() {
//        return answers;
//    }
//
//    public void setClassors(LiveData<List<Classors>> classors) {
//        this.classors = classors;
//    }
//
//    public LiveData<List<Classors>> getClassors() {
//        return classors;
//    }
}
