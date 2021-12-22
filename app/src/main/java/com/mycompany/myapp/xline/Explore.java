package com.mycompany.myapp.xline;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import uk.co.senab.photoview.PhotoView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Explore extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
//    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
//            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
   }
    };
    Context context;
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
//            ActionBar actionBar = getSupportActionBar();
//            if (actionBar != null) {
//                actionBar.show();
//            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };
    NavigationView navigationViewe;
    DrawerLayout drawere;
 TextView article_titleb, article_bodyb;
 ImageView nerc_image, smallnercy, tablerelback;
 RelativeLayout tablerel;
 PhotoView tableimage;
 RelativeLayout tellusclick;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ultimate_inner_explore);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbart);
        setSupportActionBar(toolbar);
    Bundle bundle = getIntent().getExtras();
//   ActionBar actionBar = this.getSupportActionBar();
//   actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.green_kindof));
        mVisible = true;
        context = this.getApplicationContext();
        mControlsView = findViewById(R.id.fullscreen_content_controls);
//        mContentView = findViewById(R.id.fullscreen_content);
        article_bodyb = (TextView)findViewById(R.id.article_bodyb);
        article_titleb = (TextView)findViewById(R.id.article_numberb);
        nerc_image = (ImageView)findViewById(R.id.explore_imageb);
        smallnercy = (ImageView)findViewById(R.id.smallynercy);
        article_titleb.setText(bundle.getString(First_Explore.ARTICLE_TITLE));
        article_bodyb.setText(bundle.getString(First_Explore.ARTICLES_BODY));
        drawere = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawere, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawere.addDrawerListener(toggle);
//        toggle.syncState();

        navigationViewe = (NavigationView) findViewById(R.id.nav_view);
        navigationViewe.setNavigationItemSelectedListener(this);
        tableimage = (PhotoView) findViewById(R.id.tableimage);
        tablerel = (RelativeLayout)findViewById(R.id.tablerel);
        tablerelback = (ImageView)findViewById(R.id.tablerelback);
        tellusclick = (RelativeLayout)findViewById(R.id.tellusclick);
//        ask_your_questions.setOnClickListener(QuestionListener);
        // Set up the user interaction to manually show or hide the system UI.
//        mContentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle();
//            }
//        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
//        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
        tablerelback.setOnClickListener(tablerelbackListener);
        toggle = new ActionBarDrawerToggle(
                this, drawere, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawere.addDrawerListener(toggle);
        toggle.syncState();
        tellusclick.setOnClickListener(DownloadFullNerc);
        nerc_image.setOnClickListener(toggleit);
    }

    View.OnClickListener toggleit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
            else{
                drawer.openDrawer(GravityCompat.START);
            }
        }
    };
    View.OnClickListener  DownloadFullNerc = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (confirmconnection()) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nepawahala.ng/Uploads/article10014.pdf"));
                    startActivity(browserIntent);

                } catch (Exception e) {
                    displayo("i encountered a problem in trying to download this file");
                    displayo(e.toString());

                }

            }
            else {
                displayo("please enable internet access");
            }
        }

    };
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.hide();

        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
//        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    View.OnClickListener tablerelbackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
 tablerel.setVisibility(View.GONE);
        }
    };
    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */


    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {

            super.onBackPressed();
            }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemid = item.getItemId();
        switch (itemid){
            case R.id.threethreeonea: {
                tablerel.setVisibility(View.VISIBLE);
                tableimage.setImageResource(R.drawable.threeone);
                break;
            }
            case R.id.threefourone :{
                tablerel.setVisibility(View.VISIBLE);
                tableimage.setImageResource(R.drawable.threefourone);
                break;
            }
            case R.id.threesevenoneonea: {
                tablerel.setVisibility(View.VISIBLE);
                tableimage.setImageResource(R.drawable.threesevenoneonea);
                break;
            }
            case R.id.threesevenoneoneb: {
                tablerel.setVisibility(View.VISIBLE);
                tableimage.setImageResource(R.drawable.threesevenoneoneb);
                break;
            }
            case R.id.threesixtwo: {
                tablerel.setVisibility(View.VISIBLE);
                tableimage.setImageResource(R.drawable.threesixtwo);
                break;
            }
            case R.id.threesixthree: {
                tablerel.setVisibility(View.VISIBLE);
                tableimage.setImageResource(R.drawable.threesixthree);
                break;
            }
            default:
                return false;

        }
        return false;
    }
    public void displayo(String ji) {
        Context context = this;
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
    public boolean confirmconnection(){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
