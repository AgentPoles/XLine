package com.mycompany.myapp.xline;

import android.animation.ArgbEvaluator;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.jkb.fragment.rigger.annotation.Puppet;

import java.lang.reflect.Array;
import java.util.ArrayList;


import static com.mycompany.myapp.xline.Design.showRelWatcher;

//@Puppet(containerViewId = 0, bondContainerView = true)
public class Home extends AppCompatActivity {

    private TextView mTextMessage;
    ViewPager mViewPager;
    MenuItem prevMenuItem;
    ArgbEvaluator argbEvaluator;
    RelativeLayout registerdetails;
     EditText ownersNameField;
    EditText projectNameField;
    AlphaAnimation alphain, alphaout;
//    FloatingActionButton newDesign;
    public static int showRelWatcher;
    public static String OWNERS_NAME = "ownersname";
    public static String FIRST_TIME = "firsttime";
    public static String PROJECT_NAME = "projectname";
    static RotateAnimation rotationAnimation, returnanimation;
    static int jWatcher = 0;
    SharedPreferences mySharedPreferences;
    SharedPreferences.Editor myEditor;
    AppDatabase mdb;
    FloatingActionButton floaty;
    public static int floatyWatcher;
    static String ownerName;
    ArrayList<Float> floatingxs = new ArrayList<Float>();
    ArrayList<Float> floatingys = new ArrayList<Float>();
    int s = Color.parseColor("#ffd883");
    int m = Color.parseColor("#040a0b");
    int d = Color.parseColor("#109D59");
    Integer[] colors = {m,s,d};
    int ss = Color.parseColor("#ffffff");
    int ms = Color.parseColor("#ff2343");
    int ds = Color.parseColor("#eee6a4");

    int se = Color.parseColor("#f8a532");
    int me = Color.parseColor("#ff2343");
    int de = Color.parseColor("#109D59");
    Integer[] colorse = {me,se,de};
    Integer[] colorss = {ms,ss,ds};
    MyFragmentAdapter myFragmentAdapter;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    mViewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActionBar jBar = getSupportActionBar();
        jBar.hide();
        int d = getWindow().getAttributes().width;
        int m = getWindow().getAttributes().height;
        float heightCentre = (float)m/2;
        float widthCenter = (float)d/2;
        argbEvaluator = new ArgbEvaluator();
        transparentStatusAndNavigation();
        floaty = (FloatingActionButton)findViewById(R.id.floating);
        float floatx = floaty.getX();
        final float floatiy = floaty.getY();
        floatingxs.add(floatx);
        floatingxs.add(widthCenter);
        floatingxs.add(widthCenter);
        floatingys.add(floatiy);
        floatingys.add(floatiy);
        floatingys.add(heightCentre);
        ownersNameField = (EditText)findViewById(R.id.ownersnamefield);
        projectNameField = (EditText)findViewById(R.id.projectnamefield);
        rotationAnimation = new RotateAnimation(0,360,50,50);
        rotationAnimation.setDuration(500);
        mySharedPreferences =  getSharedPreferences("savedname", Context.MODE_PRIVATE);
        registerdetails = (RelativeLayout) findViewById(R.id.reldesignregister);
        returnanimation = new RotateAnimation(360,0);
        mdb = AppDatabase.getsInstance(getApplicationContext());
//        newDesign = (FloatingActionButton)findViewById(R.id.newdesign);
//        newDesign.setOnClickListener(newDesignListener);
        final ColorDrawable colorDrawable = new ColorDrawable();
        mTextMessage = (TextView) findViewById(R.id.message);
        myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        mViewPager.setAdapter(myFragmentAdapter);
        mySharedPreferences =  this.getSharedPreferences("savedname", Context.MODE_PRIVATE);
        floaty.setOnClickListener(designListener);
        mViewPager.setBackgroundDrawable(colorDrawable);
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(position < (myFragmentAdapter.getCount() -1) && position < (colors.length - 1)) {
                    mViewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));
//                    navigation.setItemTextColor(Integer); argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));
                    floaty.setBackgroundTintList(ColorStateList.valueOf((int)argbEvaluator.evaluate(positionOffset,colorss[position],colorss[position+1])));
                    navigation.setItemTextColor(ColorStateList.valueOf((int)argbEvaluator.evaluate(positionOffset,colorse[position],colorse[position+1])));
//                    floaty.setX(blendposition(floatingxs.get(position),floatingxs.get(position+1),positionOffset));
//                    floaty.setY(blendposition(floatingys.get(position),floatingys.get(position+1),positionOffset));

                } else {
                    mViewPager.setBackgroundColor(colors[colors.length - 1]);
                    floaty.setBackgroundTintList(ColorStateList.valueOf(colorss[colorss.length-1]));
                    navigation.setItemTextColor(ColorStateList.valueOf(colorse[colorse.length-1]));
//                    floaty.setX(floatingxs.get(colorss.length-1));
//                    floaty.setY(floatingys.get(colorss.length-1));
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                navigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navigation.getMenu().getItem(position);
                if(position==0){
                    floaty.setOnClickListener(designListener);
                    if(floatyWatcher==0){
                        floaty.setImageResource(R.drawable.ic_addesign);
                    }
                    else {
                        if(floatyWatcher == 1){
                            floaty.setImageResource(R.drawable.ic_rocket);
                        }
                    }
                }
                if(position==1){
                    floaty.setImageResource(R.drawable.ic_cacolator);
                    floaty.setOnClickListener(myCalculator);
                    if(showRelWatcher == 1||floatyWatcher==1){
                       returnRel();
                       showRelWatcher =0;
                       floatyWatcher = 0;

                    }
                }
                if(position==2){
            floaty.setImageResource(R.drawable.ic_exploreit);
            floaty.setOnClickListener(myExploreListner);
                        if(showRelWatcher == 1||floatyWatcher==1){
                            returnRel();
                            showRelWatcher =0;
                            floatyWatcher = 0;

                        }
//                        onBackPressed();
                    }
                }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state){
                    case ViewPager.SCROLL_STATE_IDLE:
                        floaty.show();
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                    case ViewPager.SCROLL_STATE_SETTLING:
                        floaty.hide();
                        break;
                }
            }
        });
    }

    public void showRel(int j ){
        if(j == 1){
            ownersNameField.setVisibility(View.GONE);
        }
        jWatcher = j;
        showRelWatcher = 1;
        registerdetails.setVisibility(View.VISIBLE);
//        newDesign.setVisibility(View.VISIBLE);
//        newDesign.startAnimation(rotationAnimation);
    }
    public void returnRel()
    {
//        newDesign.hide();
        registerdetails.setVisibility(View.GONE);
    }
    static int blendColors(int from, int to, float ratio) {
        final float inverseRation = 1f - ratio;
    final float r = Color.red(from) * ratio + Color.red(to) * inverseRation;
    final float g = Color.green(from) * ratio + Color.green(to) * inverseRation;
    final float b = Color.blue(from) * ratio + Color.blue(to) * inverseRation;
        return Color.rgb((int) r, (int) g, (int) b);
}
    static float blendposition(float from, float to, float ratio) {
        final float inverseRation = 1f - ratio;

        return from*ratio + to*inverseRation;
    }
    private void transparentStatusAndNavigation() {
        //make full transparent statusBar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            );
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
    }


    public void checkIfNameExists(){
        floaty.startAnimation(rotationAnimation);
             floaty.setImageResource(R.drawable.ic_rocket);
        ownerName = mySharedPreferences.getString(OWNERS_NAME,null);
        if(ownerName == null){
            showRel(2);
            floatyWatcher =1;
//            floaty.hide();
        }
        else {
            makeBuilder(ownerName);
        }
    }

    View.OnClickListener designListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(floatyWatcher==0) {
                checkIfNameExists();

            }else {
                if (jWatcher == 1) {
                    if (!projectNameField.getText().toString().equals("") && projectNameField.getText() != null) {
                        myEditor = mySharedPreferences.edit();
                        myEditor.putString(PROJECT_NAME,projectNameField.getText().toString());
                        myEditor.apply();
                        startDesignActivity();
                    } else {
                        displayo("project name is empty");
                    }
                } else {
                    if (!ownersNameField.getText().toString().equals("") && ownersNameField.getText() != null) {

                        if (!projectNameField.getText().toString().equals("") && projectNameField.getText() != null) {
                            myEditor = mySharedPreferences.edit();
                            myEditor.putString(PROJECT_NAME,projectNameField.getText().toString());
                            myEditor.putString(OWNERS_NAME,ownersNameField.getText().toString());
                            myEditor.apply();
                            startDesignActivity();
                        } else {
                            displayo("project name is empty");
                        }

                    } else {
                        displayo("please tell us your name");
                    }
                }
            }


        }
    };
    public void makeBuilder(String name ){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(name + " is that you");
        builder.setCancelable(false);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showRel(1);
                floatyWatcher =1;
//                floaty.hide();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showRel(2);
                floatyWatcher = 1;
//                floaty.hide();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
View.OnClickListener myCalculator = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ActivityOptions options =
                ActivityOptions.makeSceneTransitionAnimation(Home.this, mViewPager, "viewpager");
        Intent intent = new Intent(Home.this, Calculator.class);
        startActivity(intent, options.toBundle());
    }
};
    View.OnClickListener myExploreListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(Home.this, mViewPager, "viewpagere");
            Intent intent = new Intent(Home.this, First_Explore.class);
            startActivity(intent, options.toBundle());
        }
    };
    private void setWindowFlag(final int bits, boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    public void startDesignActivity(){
        Intent intent = new Intent(this, Design_activity.class);
        intent.putExtra(OWNERS_NAME,ownersNameField.getText().toString());
        intent.putExtra(PROJECT_NAME, projectNameField.getText().toString());
        Pair<View, String> p1 = Pair.create((View)ownersNameField, "ownersname");
        Pair<View, String> p2 = Pair.create((View)projectNameField, "projectname");
        Pair<View, String> p3 = Pair.create((View)floaty, "newdesign");
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, p1, p2,p3);
        startActivity(intent, options.toBundle());
    }
    @Override
    public void onBackPressed() {
        if(showRelWatcher == 1){
            returnRel();
            showRelWatcher = 0;
            floatyWatcher = 0;
            floaty.startAnimation(rotationAnimation);
            floaty.setImageResource(R.drawable.ic_addesign);
        }
        else {
            super.onBackPressed();
        }
    }

    public void closeView(){

    }
    View.OnClickListener newDesignListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (jWatcher == 1) {
                if (!projectNameField.getText().toString().equals("") && projectNameField.getText() != null) {
                    myEditor = mySharedPreferences.edit();
                    myEditor.putString(PROJECT_NAME,projectNameField.getText().toString());
                    myEditor.apply();
                    startDesignActivity();
                } else {
                    displayo("project name is empty");
                }
            } else {
                if (!ownersNameField.getText().toString().equals("") && ownersNameField.getText() != null) {

                    if (!projectNameField.getText().toString().equals("") && projectNameField.getText() != null) {
                        myEditor = mySharedPreferences.edit();
                        myEditor.putString(PROJECT_NAME,projectNameField.getText().toString());
                        myEditor.putString(OWNERS_NAME,ownersNameField.getText().toString());
                        myEditor.apply();
                        projectNameField.setText(null);
                        ownersNameField.setText(null);
                        registerdetails.setVisibility(View.GONE);
                        showRelWatcher = 0;
                        floatyWatcher = 0;

                        startDesignActivity();
                    } else {
                        displayo("project name is empty");
                    }

                } else {
                    displayo("please tell us your name");
                }
            }
        }
    };

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

    public void loadAll(){
        AppExecutor.getsInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
             if(mySharedPreferences.getBoolean(FIRST_TIME,true)){
                 addDataValues();
                 myEditor = mySharedPreferences.edit();
                 myEditor.putBoolean(FIRST_TIME,false);
                 myEditor.apply();
             }
             else {

             }
            }
        });
    }
    public void addDataValues(){
        DataValues dataValues1 = new DataValues(16, 6 , 1.84, 1 , 1.84,  16.0 , 2.7,  5.52 ,64 ,6.08 ,1.7934 ,149, 2000);
        DataValues dataValues2 = new DataValues( 25, 6 , 2.30, 1 , 2.30 , 24.9 , 4.2 , 6.90 ,101 ,9.13 ,1.1478 ,198, 2000);
        DataValues dataValues3 = new DataValues (40, 6 ,2.91, 1 ,2.91,  39.9,  6.7 , 8.73 ,161, 14.40, 0.7174 ,267, 2000);
        DataValues dataValues4 = new DataValues (63 ,6 ,3.66, 1 , 3.66,  63.1,  10.5 , 10.98 ,255 ,21.63 ,0.4555, 358, 2000);
        DataValues dataValues5 = new DataValues(100, 6 ,4.61, 1 , 4.61 , 100.1 , 16.7,  13.83 ,405, 34.33 ,0.2869 ,481, 2000);
        DataValues dataValues6 = new DataValues ( 125 ,18 ,2.97, 1 , 2.97 , 124.7,  6.9 , 14.85 ,397, 29.17 ,0.2304 ,549, 2000);
        DataValues dataValues7= new DataValues (125, 26 ,2.47,7 , 1.92,  124.6,  20.3 , 15.64, 503, 45.69, 0.2310 ,557, 2000);
        DataValues dataValues8 = new DataValues(160, 18 ,3.36, 1 , 3.36,  159.6 , 8.9 , 16.80 ,509 ,36.18 ,0.1800 ,643, 2000);
        DataValues dataValues9 = new DataValues (160 ,26 , 2.80, 7 ,2.18,  160.1,  26.1 , 17.74 ,648, 57.69 ,0.1805 ,652, 2000);
        DataValues dataValues10 = new DataValues (200, 18 , 3.76, 1 , 3.76,  199.9,  11.1,  18.80, 637, 44.22 ,0.1440, 743, 2000);
        DataValues dataValues11 = new DataValues (200, 26 , 3.13, 7 , 2.43 , 200.1 , 32.5 , 19.81 ,808 ,70.13, 0.1444 ,754,2000);
        DataValues dataValues12 = new DataValues(250 ,22 , 3.80, 7 , 2.11,  249.5 , 24.5,  21.53, 865, 68.72 ,0.1154 ,865, 2000);
        DataValues dataValues13 = new DataValues( 250, 26 , 3.50,7 , 2.72,  250.1,  40.7,  22.16, 1011, 87.67 ,0.1155 ,872,2000);
        DataValues dataValues14 = new DataValues(315, 45 ,2.99, 7 ,1.99,  316.0 , 21.8,  23.91, 1045, 79.03 ,0.0917, 998, 2000);

        DataValues dataValues15 = new DataValues (315, 26 , 3.93, 7 ,3.05,  315.4,  51.1 , 24.87 , 1273, 106.83 ,0.0917, 1012, 2000);
        DataValues dataValues16 = new DataValues (400, 45 ,3.36, 7 ,2.24 , 399.0,  27.6,  26.88 , 1320, 98.36, 0.0722, 1164, 2000);
        DataValues  dataValues17 = new DataValues( 400, 54 ,3.07,7 ,3.07,  399.7 , 51.8 , 27.63 , 1512 ,123.04, 0.0723 ,1173 ,2000);
        DataValues dataValues18 = new DataValues( 450, 45 ,3.57, 7 , 2.38,  450.4 , 31.1 , 28.56, 1490 ,107.47, 0.0642 ,1255, 2000);
        DataValues dataValues19 = new DataValues ( 450 ,54 ,3.26, 7 , 3.26 ,450.7 , 58.4,  29.34 , 1705, 138.42 ,0.0643,1266 ,2000);
        DataValues dataValues20 = new DataValues (500, 45 , 3.76, 7 ,2.51 , 499.7,  34.6 , 30.09 , 1654 ,119.41 ,0.0578, 1343, 2000);
        DataValues dataValues21 = new DataValues ( 500 ,54 , 3.43, 7 , 3.43,  499.0,  64.7,  30.87 , 1887, 153.80, 0.0578 ,1355 ,2000);
        DataValues dataValues22 = new DataValues  (560, 45 , 3.98, 7 , 2.65,  559.8,  38.6 , 31.83 , 1852, 133.74, 0.0516 ,1444 ,2000);
        DataValues dataValues23 = new DataValues  (560 ,54 ,3.63, 19 ,2.18,  558.9,  70.9,  32.68 , 2111, 172.59 ,0.0516 ,1458 ,2000);
        DataValues dataValues24 = new DataValues(630 ,45 , 4.22 ,7 , 2.81 , 629.4 , 43.4,  33.75,  2082, 150.45 ,0.0459, 1557, 2000);
        DataValues dataValues25 = new DataValues( 630 ,54 ,3.85, 19 ,2.31 , 628.6,  79.6 ,34.65,  2373 ,191.77, 0.0459, 1572 ,2000);
        DataValues dataValues26 = new DataValues (710 ,45 , 4.48, 7 ,2.99,  709.3,  49.2 ,35.85 , 2348, 169.56, 0.0407, 1680, 2000);
        DataValues dataValues27 = new DataValues(710,54 , 4.09, 19 , 2.45 , 709.5 , 89.6,  36.79 , 2676, 216.12, 0.0407 ,1696, 1000);
        DataValues dataValues28 = new DataValues( 800, 72 , 3.76, 7 , 2.51 , 799.5 , 34.6,  30.09,  2495, 167.41 ,0.0361, 1800, 2000);
        DataValues dataValues29 = new DataValues(800, 84 , 3.48, 7 , 3.48,  799.0 , 66.6,  38.28 , 2679 ,205.33, 0.0362 ,1812 ,1000);
        DataValues dataValues30 = new DataValues (800, 54 ,4.34, 19 ,2.61, 798.8 ,101.7, 39.09, 3019 ,243.52 ,0.0362, 1828 ,1000);
        DataValues dataValues31 = new DataValues (900 ,72 , 3.99, 7 , 2.66, 900.3, 38.9,  31.92 ,2808, 188.33, 0.0321 ,1936 ,2000);
        DataValues dataValues32 = new DataValues( 900, 84 ,3.69, 7 ,3.69,  898.3,  74.9, 40.59 ,3012, 226.50, 0.0322, 2025 ,1000);
        DataValues dataValues33 = new DataValues(1000 ,72 ,4.21, 7 ,2.80,  1002.3 , 43.1 , 33.66, 3125, 209.26 ,0.0289 ,2065 ,2000);
        DataValues dataValues34 = new DataValues (1120, 72 ,4.45, 19 ,1.78,  1119.8,  47.3,  35.60,  3394 ,234.53, 0.0258 ,2209 ,2000);
        DataValues dataValues35  = new DataValues(1120, 84 ,4.12 ,19 ,2.47 , 1119.9 , 91.0 , 45.31 , 3829, 283.17, 0.0258, 2231 ,1000);
        DataValues dataValues36 = new DataValues (1250 ,84 , 4.35, 19 ,2.61 , 1248.4,  101.7 , 47.85 , 4269 ,316.04 ,0.0232 ,2360 ,1000);
        DataValues dataValues37 = new DataValues( 1250 ,72 , 4.70, 19 ,1.88 , 1249.2,  52.7,  37.60,  3787, 261.75, 0.0231, 2382 ,1000);
        mdb.dataValuesDao().insertDataValues(dataValues1);    mdb.dataValuesDao().insertDataValues(dataValues2);
        mdb.dataValuesDao().insertDataValues(dataValues3);       mdb.dataValuesDao().insertDataValues(dataValues4);
        mdb.dataValuesDao().insertDataValues(dataValues5);    mdb.dataValuesDao().insertDataValues(dataValues6);
        mdb.dataValuesDao().insertDataValues(dataValues7);       mdb.dataValuesDao().insertDataValues(dataValues8);
        mdb.dataValuesDao().insertDataValues(dataValues9);    mdb.dataValuesDao().insertDataValues(dataValues10);
        mdb.dataValuesDao().insertDataValues(dataValues11);       mdb.dataValuesDao().insertDataValues(dataValues12);
        mdb.dataValuesDao().insertDataValues(dataValues13);    mdb.dataValuesDao().insertDataValues(dataValues14);
        mdb.dataValuesDao().insertDataValues(dataValues15);       mdb.dataValuesDao().insertDataValues(dataValues16);
        mdb.dataValuesDao().insertDataValues(dataValues17);    mdb.dataValuesDao().insertDataValues(dataValues18);
        mdb.dataValuesDao().insertDataValues(dataValues19);       mdb.dataValuesDao().insertDataValues(dataValues20);
        mdb.dataValuesDao().insertDataValues(dataValues21);    mdb.dataValuesDao().insertDataValues(dataValues22);
        mdb.dataValuesDao().insertDataValues(dataValues23);       mdb.dataValuesDao().insertDataValues(dataValues24);
        mdb.dataValuesDao().insertDataValues(dataValues25);    mdb.dataValuesDao().insertDataValues(dataValues26);
        mdb.dataValuesDao().insertDataValues(dataValues27);       mdb.dataValuesDao().insertDataValues(dataValues28);
        mdb.dataValuesDao().insertDataValues(dataValues29);    mdb.dataValuesDao().insertDataValues(dataValues30);
        mdb.dataValuesDao().insertDataValues(dataValues31);       mdb.dataValuesDao().insertDataValues(dataValues32);
        mdb.dataValuesDao().insertDataValues(dataValues33);    mdb.dataValuesDao().insertDataValues(dataValues34);
        mdb.dataValuesDao().insertDataValues(dataValues35);       mdb.dataValuesDao().insertDataValues(dataValues36);
        mdb.dataValuesDao().insertDataValues(dataValues37);
    }

}
