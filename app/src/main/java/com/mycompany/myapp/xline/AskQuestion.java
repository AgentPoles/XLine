package com.mycompany.myapp.xline;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class AskQuestion extends AppCompatActivity {
      EditText edemail, ediquestion;
      Context context;
    FloatingActionButton fab;
    ProgressBar progressBar;
      FirebaseDatabase firebaseDatabase;
      DatabaseReference QuestionReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      context = getApplicationContext();
      firebaseDatabase = FirebaseDatabase.getInstance();
      QuestionReference = firebaseDatabase.getReference("quuestions");
      fab = (FloatingActionButton) findViewById(R.id.sendQuestion);
        edemail = (EditText)findViewById(R.id.edemail);
        ediquestion = (EditText)findViewById(R.id.ediquestion);
progressBar = (ProgressBar)findViewById(R.id.showprogress);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(edemail.getText()==null||edemail.getText().toString().equals("")){
                    Snackbar.make(view, "you have not entered email", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    if(ediquestion.getText()==null||ediquestion.getText().toString().equals("")){
                        Snackbar.make(view, "you have not ask any question", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    else{
                   if(!confirmconnection()){
                       Snackbar.make(view, "please enable internet access", Snackbar.LENGTH_LONG)
                               .setAction("Action", null).show();
                   }
                   else{
                       progressBar.setVisibility(View.VISIBLE);
                       fab.setVisibility(View.GONE);

Double timestamp = (double) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
String path = String.valueOf(timestamp);
String email = edemail.getText().toString();
String question = ediquestion.getText().toString();
String fullpath = QuestionReference.push().toString();
Question question1 = new Question(email,question);
QuestionReference.push().setValue(question).addOnCompleteListener(
        new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull final Task<Void> task) {
                if(task.isSuccessful()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            fab.setVisibility(View.VISIBLE);
                            displayo("your question has been asked");
                            displayi("answers will be provided soonest via your email");
                            ediquestion.setText(null);
                        }
                    });
                }
                else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            fab.setVisibility(View.VISIBLE);
                            displayo(task.getException().toString());
                            displayo("i encountered an error asking your question");
                            displayi("please check your connection and try again");
                        }
                    });
                }
            }
        }
);
                   }
                    }
                }
            }
        });
    }
    public boolean confirmconnection(){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
    public void displayo(String ji) {
        String msg = ji;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, msg, duration);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        TextView myTextView = new TextView(context);
        ImageView cv = new ImageView(context);
        cv.setImageResource(R.drawable.ic_glasy);
        myTextView.setText(msg);
        Resources g = getResources();
        myTextView.setTextColor(Color.parseColor("#0a2149"));
        myTextView.setBackgroundDrawable(g.getDrawable(android.R.drawable.editbox_background));
        int lHeight = LinearLayout.LayoutParams.WRAP_CONTENT;
        int lWidth = LinearLayout.LayoutParams.WRAP_CONTENT;
        ll.addView(cv, new LinearLayout.LayoutParams(lHeight, lWidth));
        ll.addView(myTextView, new LinearLayout.LayoutParams(lHeight, lWidth));
        ll.setPadding(40, 50, 0, 50);
        toast.setView(ll);
        toast.show();
    }
    public void displayi(String ji) {
        String msg = ji;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, msg, duration);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        TextView myTextView = new TextView(context);
        ImageView cv = new ImageView(context);
        cv.setImageResource(R.drawable.anna);
        myTextView.setText(msg);
        Resources g = getResources();
        myTextView.setTextColor(Color.parseColor("#0a2149"));
        myTextView.setBackgroundDrawable(g.getDrawable(android.R.drawable.editbox_background));
        int lHeight = LinearLayout.LayoutParams.WRAP_CONTENT;
        int lWidth = LinearLayout.LayoutParams.WRAP_CONTENT;
        ll.addView(cv, new LinearLayout.LayoutParams(lHeight, lWidth));
        ll.addView(myTextView, new LinearLayout.LayoutParams(lHeight, lWidth));
        ll.setPadding(40, 50, 0, 50);
        toast.setView(ll);
        toast.show();
    }
}
