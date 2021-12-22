package com.mycompany.myapp.xline;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class MySinglesViewModel extends ViewModel {
//    private LiveData<List<Info>> info;
//    private LiveData<List<Documents>> doc;
//    private LiveData<List<Folders>> folder;
//    private LiveData<List<User>> user;
//    private LiveData<question> question;
//    private LiveData<Answer> answer;
    private LiveData<Recents> recents;
    private LiveData<DataValues> datavalues;
    private LiveData<soundtracker> soundtrackerLiveData;
    private LiveData<GroundClearance> groundClearanceLiveData;
    private LiveData<Explore_Articles> explore_articlesLiveData;

    public MySinglesViewModel(AppDatabase database, int id){
//        question = database.questionDao().LoadQuestionById(id);
        recents = database.recentsDao().LoadRecentbyId(id);
        datavalues = database.dataValuesDao().LoadDataValuebyId(id);
        soundtrackerLiveData = database.soundTrackerDao().LoadSoundTrackerbyId(id);
        groundClearanceLiveData = database.groundClearanceDao().LoadGroundClearanceById(id);
        explore_articlesLiveData = database.explore_articlesDao().LoadExploreArticlebyId(id);
    }

    public LiveData<Recents> getRecents() {
        return recents;
    }

    public void setRecents(LiveData<Recents> recents) {
        this.recents = recents;
    }

    public LiveData<DataValues> getDatavalues() {
        return datavalues;
    }

    public void setDatavalues(LiveData<DataValues> datavalues) {
        this.datavalues = datavalues;
    }

    public LiveData<soundtracker> getSoundtrackerLiveData() {
        return soundtrackerLiveData;
    }

    public void setSoundtrackerLiveData(LiveData<soundtracker> soundtrackerLiveData) {
        this.soundtrackerLiveData = soundtrackerLiveData;
    }

    public LiveData<GroundClearance> getGroundClearanceLiveData() {
        return groundClearanceLiveData;
    }

    public void setGroundClearanceLiveData(LiveData<GroundClearance> groundClearanceLiveData) {
        this.groundClearanceLiveData = groundClearanceLiveData;
    }

    public LiveData<Explore_Articles> getExplore_articlesLiveData() {
        return explore_articlesLiveData;
    }

    public void setExplore_articlesLiveData(LiveData<Explore_Articles> explore_articlesLiveData) {
        this.explore_articlesLiveData = explore_articlesLiveData;
    }

}
