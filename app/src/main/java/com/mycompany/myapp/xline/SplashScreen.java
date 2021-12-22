package com.mycompany.myapp.xline;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
AppDatabase mdb;
SharedPreferences mySharedPreferences;
SharedPreferences.Editor myEditor;
    public static String name = "name";
    public static String earthwire = "earthwire";
    public static String fourh = "fourh";
    public static String threek = "threek";
    public static String  sixk = "sixk";
    public static String elevenk = "elevenk";
    public static String thirththreek = "thirtythreek";
    public static String sixtyk = "sixtyk";
    public static String onethirtyk = "onethirtyk";
    public static String threethirtyk = "threethirtyk";
    public static String FIRST_TIME = "firsttime";

    private int MY_DATA_CHECK_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mdb = AppDatabase.getsInstance(getApplicationContext());
        mySharedPreferences =  getSharedPreferences("savedname", Context.MODE_PRIVATE);
        if(mySharedPreferences.getBoolean(FIRST_TIME,true)) {
            loadAll();
            setUpTTS();
        }
        Intent intent = new Intent(SplashScreen.this,Home.class);
        startActivity(intent);
        finish();
//        setContentView(R.layout.activity_splash_screen);

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
    public void loadAll(){
        AppExecutor.getsInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                if(mySharedPreferences.getBoolean(FIRST_TIME,true)){
                    addDataValues();
                    setUpGroundClearance();
                    setUpArticles();
                    addSpacings();
                    myEditor = mySharedPreferences.edit();
                    myEditor.putBoolean(FIRST_TIME,false);
                    myEditor.apply();
                }
                else {

                }
            }
        });
    }
    public void setUpTTS(){
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

    }
    //act on result of TTS data check
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                //the user has the necessary data - create the TTS
//                myTTS = new TextToSpeech(this, this);


            }
            else {
                //no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    public void setUpGroundClearance(){
        AppExecutor.getsInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                GroundClearance ga = new GroundClearance(0.415, 5.8, 5.2,  5.2 );
                GroundClearance gb = new GroundClearance(3.3,  5.8 ,5.5 ,5.5 );
                GroundClearance gc = new GroundClearance(6.6, 5.8 , 5.5 , 5.5);
                GroundClearance gd = new GroundClearance(11, 5.8 , 5.5 ,5.5);
                GroundClearance ge = new GroundClearance(33 , 6.0 , 6.0 , 5.8);
                GroundClearance gf = new GroundClearance(66 , 7.0 , 7.0 ,5.8);
                GroundClearance gg = new GroundClearance(132 , 7.0 , 7.0, 5.8);
                GroundClearance gh = new GroundClearance(330, 7.0 , 7.0 , 5.8);

                mdb.groundClearanceDao().insertGroundClearance(ga);
                mdb.groundClearanceDao().insertGroundClearance(gb);
                mdb.groundClearanceDao().insertGroundClearance(gc);
                mdb.groundClearanceDao().insertGroundClearance(gd);
                mdb.groundClearanceDao().insertGroundClearance(ge);
                mdb.groundClearanceDao().insertGroundClearance(gf);
                mdb.groundClearanceDao().insertGroundClearance(gg);
                mdb.groundClearanceDao().insertGroundClearance(gh);
            }
        });


    }
    public void setUpArticles(){
        AppExecutor.getsInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {



                Explore_Articles explore_articles = new Explore_Articles("General Provisions", getResources().getString(R.string.general_provisions));
                Explore_Articles explore_articles1 = new Explore_Articles("Over head Transmission Conductors",getResources().getString(R.string.overhead_transmission_conductors));
                Explore_Articles explore_articles2 = new Explore_Articles("Properties Of Electrical Conductors",getResources().getString(R.string.properties_of_electrical_conductors));
                Explore_Articles explore_articles3 = new Explore_Articles("Insulation of Conductors",getResources().getString(R.string.insulation_of_conductors));
                Explore_Articles explore_articles4 = new Explore_Articles("Dielectric Strength",getResources().getString(R.string.dielectric_strength));
                Explore_Articles explore_articles5 = new Explore_Articles("Support Structure",getResources().getString(R.string.support_structure));
                Explore_Articles explore_articles6 = new Explore_Articles("Regulations for installation",getResources().getString(R.string.regulations_for_installation));
                Explore_Articles explore_articles7 = new Explore_Articles("Regulations for urban areas",getResources().getString(R.string.restriction_in_urban));
                Explore_Articles explore_articles8 = new Explore_Articles("Regulations for side by side installations",getResources().getString(R.string.regulations_for_side_by_side_installation));
                Explore_Articles explore_articles9 = new Explore_Articles("Installations with distribution conductors",getResources().getString(R.string.installtation_with_db_conductors));
                Explore_Articles explore_articles10 = new Explore_Articles("Insulation of Conductors",getResources().getString(R.string.insulation_of_conductors));
                Explore_Articles explore_articles11 = new Explore_Articles("Insulation of Conductors",getResources().getString(R.string.insulation_of_conductors));

                mdb.explore_articlesDao().insertExploreArticles(explore_articles);
                mdb.explore_articlesDao().insertExploreArticles(explore_articles1);
                mdb.explore_articlesDao().insertExploreArticles(explore_articles2);
                mdb.explore_articlesDao().insertExploreArticles(explore_articles3);
                mdb.explore_articlesDao().insertExploreArticles(explore_articles4);
                mdb.explore_articlesDao().insertExploreArticles(explore_articles5);
                mdb.explore_articlesDao().insertExploreArticles(explore_articles6);
                mdb.explore_articlesDao().insertExploreArticles(explore_articles7);
                mdb.explore_articlesDao().insertExploreArticles(explore_articles8);
                mdb.explore_articlesDao().insertExploreArticles(explore_articles9);

            }
        });
    }
    public void addSpacings(){
        Spacings spacings = new Spacings(earthwire,30,20,30,30,30,120,60,120,240);
        Spacings spacings1 = new Spacings(fourh,20,120,120,120,120,120,150,180,270);
        Spacings spacings2 = new Spacings(threek, 30,0,0,120,120,120,150,180,270);
        Spacings spacings3 = new Spacings(sixk,30,0,9,120,120,120,150,180,270);
        Spacings spacings4 = new Spacings(elevenk,30,0,0,0,120,0,150,180,270);
        Spacings spacings5 = new Spacings(thirththreek,30,0,0,0,0,0,150,180,270);
        Spacings spacings6 = new Spacings(sixtyk,60,0,0,0,0,0,150,210,300);
        Spacings spacings7 = new Spacings(onethirtyk,120,0,0,0,0,0,0,240,360);
        Spacings spacings8 = new Spacings(threethirtyk,240,0,0,0,0,0,0,0,480);

        mdb.spacingsDao().insertSpacing(spacings);
        mdb.spacingsDao().insertSpacing(spacings1);
        mdb.spacingsDao().insertSpacing(spacings2);
        mdb.spacingsDao().insertSpacing(spacings3);
        mdb.spacingsDao().insertSpacing(spacings4);
        mdb.spacingsDao().insertSpacing(spacings5);
        mdb.spacingsDao().insertSpacing(spacings6);
        mdb.spacingsDao().insertSpacing(spacings7);
        mdb.spacingsDao().insertSpacing(spacings8);

    }

}




