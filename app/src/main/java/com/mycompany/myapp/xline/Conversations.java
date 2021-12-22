package com.mycompany.myapp.xline;

public class Conversations {
    private String text;
    private int stage;
    private int layoutId;
    private boolean lrog;
    private boolean isAnn;
    private String time;
    private String title;

    public Conversations(){

    }
    public Conversations(String text, int stage, int layoutId, boolean lrog, boolean isAnn, String time, String title){
        this.isAnn = isAnn;
        this.text = text;
        this.stage = stage;
        this.layoutId = layoutId;
        this.lrog = lrog;
        this.time = time;
        this.title = title;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setAnn(boolean ann) {
        isAnn = ann;
    }

    public int getStage() {
        return stage;
    }

    public String getText() {
        return text;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public void setLrog(boolean log) {
        this.lrog = log;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public void setText(String text) {
        this.text = text;

    }

    public boolean isAnn() {
        return isAnn;
    }

    public boolean isLrog() {
        return lrog;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
