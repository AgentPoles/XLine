package com.mycompany.myapp.xline;

public class Question {
    private String email;
    private String question;
    Question(){

    }
    Question(String email, String question){
        this.email = email;
        this.question = question;
    }

    public String getEmail() {
        return email;
    }

    public String getQuestion() {
        return question;
    }
}
