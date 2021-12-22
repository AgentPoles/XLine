package com.mycompany.myapp.xline;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.transition.Slide;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InnerCalculator extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FirstInnerCalculator.switchFragments, Second_inner_fragment.ResultReady, Second_inner_fragment.ReturnHome,
Second_inner_fragment.OpenDrawerInterface, Second_inner_fragment.CloseDtawerInterface{

    ImageView theHolderImage;
    TextView tobefound, unitvalue, answer, unitname;
    Dialog dialog;
    Second_inner_fragment second_inner_fragment;
    FirstInnerCalculator firstInnerCalculator;
    public String MY_TAG = "second fragment";
    FragmentManager fragmentManagerf;
    Double timestamp;
    NavigationView navigationView;
    public String MY_TAGA = "first fragment";
    public static String TITLE_STRING = "title";
    public static String RESULT = "result";
    public static int RESULT_WATCHER = 0;
    public static String UNIT_VALUE = "unit_value";
    public static String SHOW_UNIT = "show_unit";
    AlphaAnimation alphaAnimation;
    public static String MOST_URGENT = "urgent";
    AppDatabase mdb;
    DrawerLayout drawer;
    SharedPreferences mySharedPreferences;
    SharedPreferences.Editor myEditor;
    int secondWatcher;
    android.support.v4.app.FragmentManager fragmentManager;
    android.support.v4.app.FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.GONE);
        fragmentManager = getSupportFragmentManager();
//        fragmentManagerf = getFragmentManager();

        mySharedPreferences =  getSharedPreferences("savedname", Context.MODE_PRIVATE);
        transaction = fragmentManager.beginTransaction();
        theHolderImage = (ImageView) findViewById(R.id.innerholderimage);
        tobefound = (TextView) findViewById(R.id.tobefound);
        answer = (TextView) findViewById(R.id.answer);
        unitvalue = (TextView) findViewById(R.id.unitvalue);
        unitname = (TextView) findViewById(R.id.unitname);
        alphaAnimation = new AlphaAnimation(1, 0);
         answer.setSelected(true);
        alphaAnimation.setDuration(2000);
        Bundle bundle = this.getIntent().getExtras();
        mdb = AppDatabase.getsInstance(getApplicationContext());
        int thumbnail = bundle.getInt("thumbnailid");
        if (savedInstanceState == null) {
//            firstInnerCalculator = new FirstInnerCalculator();
//            fragmentManager.beginTransaction().replace(R.id.ineer_calculator_fragment,firstInnerCalculator,MY_TAGA)
//                                       .commit();
            String tobefoundname = bundle.getString("tobefound");
            tobefound.setText(tobefoundname);
            try {
                Glide.with(this).load(thumbnail).into(theHolderImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            theHolderImage.startAnimation(alphaAnimation);
            CountDownTimer countDownTimer = new CountDownTimer(1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    theHolderImage.setVisibility(View.GONE);
                }
            }.start();

        } else {

//            second_inner_fragment = (Second_inner_fragment) fragmentManager.findFragmentByTag(MY_TAG);
//            if (second_inner_fragment == null) {
//                second_inner_fragment = new Second_inner_fragment();
//                second_inner_fragment.setEnterTransition(new Slide(Gravity.LEFT));
//                second_inner_fragment.setExitTransition(new Slide(Gravity.RIGHT));
//                firstInnerCalculator = (FirstInnerCalculator) fragmentManager.findFragmentByTag(MY_TAGA);
//                if(firstInnerCalculator==null){
//                    firstInnerCalculator = new FirstInnerCalculator();
//                }
//                else {
//                    fragmentManager.beginTransaction().replace(R.id.ineer_calculator_fragment, firstInnerCalculator, MY_TAGA)
//                                                        .commit();
//                }
//            } else {
//
//                fragmentManager.beginTransaction().replace(R.id.ineer_calculator_fragment, second_inner_fragment, MY_TAG)
//
//                        .commit();
//            }
        }

        dialog = new Dialog(this);
        secondWatcher = 0;
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
setUpRecents();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (secondWatcher == 1) {
                trade();
            } else {
                super.onBackPressed();
            }
        }
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tobefound.setText(savedInstanceState.getString(TITLE_STRING));
        answer.setText(savedInstanceState.getString(RESULT));
        if(savedInstanceState.getBoolean(SHOW_UNIT)){
            unitname.setVisibility(View.VISIBLE);
            unitvalue.setText(savedInstanceState.getString(UNIT_VALUE));
            unitvalue.setVisibility(View.VISIBLE);
            RESULT_WATCHER = 1;
        }
    }

    @Override
    public boolean onSearchRequested() {
        return super.onSearchRequested();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString(TITLE_STRING,tobefound.getText().toString());
        outState.putString(RESULT,answer.getText().toString());
        if(RESULT_WATCHER ==1){
            outState.putString(UNIT_VALUE,unitvalue.getText().toString());
            outState.putBoolean(SHOW_UNIT,true);
        }

        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inner_calculator, menu);
        return true;
    }

    @Override
    public void openDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }

    }

    @Override
    public void closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onGoHome() {
        trade();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
       final int id = item.getItemId();
        Second_inner_fragment.retrieveWatcher =2;
         AppExecutor.getsInstance().diskIO().execute(new Runnable() {
             @Override
             public void run() {
                 Recents recents = mdb.recentsDao().LoadOrdinaryRecentbyid(id);
                 String result = String.valueOf(recents.getValue());
                 myEditor = mySharedPreferences.edit();
                 myEditor.putString(MOST_URGENT,result);
                 myEditor.apply();
             }
         });
//        if (id == R.id.nav_camera) {
//            // Handle the camera action

//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void OnReadytoSwitch() {
        transaction = fragmentManager.beginTransaction();
        second_inner_fragment = new Second_inner_fragment();
        second_inner_fragment.setEnterTransition(new Slide(Gravity.LEFT));
        second_inner_fragment.setExitTransition(new Slide(Gravity.RIGHT));
        transaction.replace(R.id.ineer_calculator_fragment, second_inner_fragment, MY_TAG);

        transaction.commit();
        secondWatcher = 1;
    }

    @Override
    public void onResultResady(final String result, String unit, final String quantityName) {
        theHolderImage.setVisibility(View.GONE);
        unitname.setVisibility(View.VISIBLE);
        answer.setVisibility(View.VISIBLE);
        unitvalue.setVisibility(View.VISIBLE);
        answer.setText(result);
        unitvalue.setText(unit);
        tobefound.setText(quantityName);
        timestamp = (double)TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        AppExecutor.getsInstance().diskIO().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        String valuename = quantityName+String.valueOf(timestamp);
                        try {
                            Recents recents = new Recents(timestamp, valuename, Double.parseDouble(result));
                            mdb.recentsDao().insertRecents(recents);
                        }
                        catch (Exception e){

                        }
                    }
                }
        );trade();
    }
    public void setUpRecents(){
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
//        LiveData<List<Info>> infos = mDataBase.infoDao().LoadAllInfo();
        viewModel.getRecents().observe(this, new Observer<List<Recents>>() {
                    @Override
                    public void onChanged(@Nullable List<Recents> recents) {
//                Processors.getInstance().setInfod(infos);
                        List<Recents> anrecents = new ArrayList<Recents>();
                        anrecents.clear();
                        if (recents.size() > 0) {
                            for (int i = recents.size(); i > 0; i--) {
//                            if (questions.get(i).getMclass().equals(name)) {
//                    if (questions.get(i).isAnswered()) {
//                                    questions.get(i).setRead(Spreferences.ReadState(mysharedpreference, questions.get(i).getQueid(), myeditor, questions.get(i).getNoanswer(), questions.get(i).getNocomment()));

                                if (anrecents.size() < 10) {
                                    anrecents.add(recents.get(i-1));
                                } else {
                                    break;
                                }
                                Collections.sort(anrecents, new Comparator<Recents>() {
                                    @Override
                                    public int compare(Recents t0, Recents t1) {
                                        return (int) (t0.getTimestamp() - t1.getTimestamp());
                                    }
                                });


                            }
                            try {
                                operateMenu(navigationView, anrecents);
                            }
                            catch (Exception e){

                            }
//                        infoAdapter.setInfoLists(anquestions);

//                    Information.InfoListsand = Processors.getInstance().getInfod();
//                    Information.infoAdapterand.notifyDataSetChanged();
                        }
                    }
                }
            );
        }

    public void operateMenu(NavigationView navigationView, List<Recents> recents){
        navigationView.getMenu().setGroupCheckable(R.id.groupid,true,true);
        navigationView.getMenu().clear();
        if(recents.size()<10) {
            for (int i = 0; i < recents.size(); i++) {
                navigationView.getMenu().add(R.id.groupid, recents.get(recents.size() - (i + 1)).getId(), i, recents.get(recents.size() - (i + 1)).getRecentname());
            }
        }
            else {
            for (int i = 0; i <= 10; i++) {
                navigationView.getMenu().add(R.id.groupid, recents.get(10-(i+1)).getId(), i, recents.get(10-(i+1)).getRecentname());
            }
        }
    }
    public void trade() {
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(second_inner_fragment);

        transaction.commit();
        secondWatcher = 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStop() {


        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (secondWatcher == 1) {
            onBackPressed();
        }
        super.onSaveInstanceState(outState);
    }
}

