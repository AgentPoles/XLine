package com.mycompany.myapp.xline;

import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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
import android.view.animation.ScaleAnimation;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.softmoore.android.graphlib.Function;
import com.softmoore.android.graphlib.Graph;
import com.softmoore.android.graphlib.GraphView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.lang.Math.sin;
import static java.lang.StrictMath.cosh;

public class Design_activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ConversationAdapter.OnGridClickedListener,
        TextToSpeech.OnInitListener{

    TextView projectname, graph_view_label;
    RecyclerView designrecyclerView;
    ConversationAdapter conversationAdapter;
    Date c;
    String time;
    String ownersName;
    Graph graph;
    private TextToSpeech myTTS;
    private int MY_DATA_CHECK_CODE = 0;
    boolean canshow = false;
    //TTS object
    private static int stage;
    DesignEstimates designEstimates;
    boolean ttsisworking;
    boolean flagDown;
    Activity activity;
    AppDatabase mdb;
    int numwatcherr = 0;
    //status check code
    calcProcessor calcProcessord;
    GraphView graphView;
    RelativeLayout rel_graph_view;
    SharedPreferences mySharedPreferences;
    SharedPreferences.Editor myEditor;
    boolean speechIsAvailable = false;
    CalculationsForSag calculationsForSag;
    EditText answer1, answer2, answer3, answerb1, answerb2, answerc1, answerc2, answerC3;
    FloatingActionButton button, buttonb,buttonr, buttonc;
    ImageView imagecancel, imagecancelb, imagecancelc;
    TextView messagea, messageb, messagec;
    CheckBox doublechecky;
    RelativeLayout relsecond, relfirst, relthird;
    ScaleAnimation scaleout, scalein;
    List<Conversations> conversations = new ArrayList<Conversations>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_design_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.GONE);
        mdb = AppDatabase.getsInstance(getApplicationContext());
        activity = this;

        designEstimates = new DesignEstimates();

        graphView = findViewById(R.id.graph_view);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        graph_view_label = findViewById(R.id.graph_view_label);
        graph_view_label.setText("graph_label");
        designrecyclerView = (RecyclerView)findViewById(R.id.design_view);
        rel_graph_view = findViewById(R.id.rel_graph_view);

        conversationAdapter = new ConversationAdapter(this,this,conversations);
        conversationAdapter.setOnItemClickedListener(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        designrecyclerView.setLayoutManager(mLayoutManager);
        designrecyclerView.setItemAnimator(new DefaultItemAnimator());
        designrecyclerView.setAdapter(conversationAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Bundle bundle = getIntent().getExtras();
        projectname = (TextView)findViewById(R.id.projectname);
        projectname.setText(bundle.getString(Home.PROJECT_NAME));
        ownersName = bundle.getString(Home.OWNERS_NAME);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        c = new Date();
        scalein = new ScaleAnimation(0, 0, 1, 0);
        scaleout = new ScaleAnimation(0, 0, 0, 1);
        messagea = (TextView) findViewById(R.id.messagea);
        messageb = (TextView)findViewById(R.id.messageb);
        messagec = (TextView)findViewById(R.id.messagec);
        scalein.setDuration(2000);
        scaleout.setDuration(800);
        relfirst = (RelativeLayout)findViewById(R.id.relfirst);
        relsecond = (RelativeLayout)findViewById(R.id.relsecond);
        relthird = (RelativeLayout)findViewById(R.id.relthird);
        answerc1 = (EditText)findViewById(R.id.windloading);
        answerc2 = (EditText)findViewById(R.id.ice_loading);
        answerC3 = (EditText)findViewById(R.id.ice_thickness);
        doublechecky = (CheckBox)findViewById(R.id.doublechecky);
        answerb2 = (EditText)findViewById(R.id.answerb2);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        calcProcessord = calcProcessor.getInstance();
        calculationsForSag = new CalculationsForSag();
//        db.setCancelable(false);
//        da.setCancelable(false);
        button = (FloatingActionButton)findViewById(R.id.proceed1);
        buttonb = (FloatingActionButton)findViewById(R.id.proceedb1) ;
        buttonc = (FloatingActionButton)findViewById(R.id.buttonc);
        buttonr = (FloatingActionButton)findViewById(R.id.buttonr);
        answer1 = (EditText)findViewById(R.id.answer1);
        answer2 = (EditText)findViewById(R.id.answer2);
        answer3 = (EditText)findViewById(R.id.answer3);
        answerb1 = (EditText)findViewById(R.id.answerb1);
        imagecancel = (ImageView)findViewById(R.id.imagecancel);
        imagecancelc = (ImageView)findViewById(R.id.imagecancelc);
        button.setOnClickListener(buttonListener);
        buttonb.setOnClickListener(buttonbListener);
        buttonc.setOnClickListener(buttoncListener);
        buttonr.setOnClickListener(buttonrListener);
        imagecancelb = (ImageView)findViewById(R.id.imagecancelb);
        imagecancelb.setOnClickListener(imageCancelListener);
        imagecancelc.setOnClickListener(imageCancelListener);
        imagecancel.setOnClickListener(imageCancelListener);
        setUpTTS();
        startIt();
        setUpSpacings();
//setUpRecents();
//        Function catenary_function = new Function() {
//            double constantey = calcProcessord.getConstante();
//            public double apply(double x) {
//                return 1*cosh(x/2);
//            }
//        };
//        graph = new Graph.Builder()
//                .addFunction(catenary_function)
//                .build();
//        graphView.setGraph(graph);
//        rel_graph_view.setVisibility(View.VISIBLE);
        try {
            CountDownTimer countDownTimer = new CountDownTimer(1500, 1500) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    myTTS.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                        @Override
                        public void onStart(String utteranceId) {

                        }

                        @Override
                        public void onDone(String utteranceId) {
                            displayo("finished");
                        }

                        @Override
                        public void onError(String utteranceId) {

                        }
                    });
                }
            };
        }
        catch (Exception e){

        }

        }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void OnGridClicked(int position) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.design_activity, menu);
        return true;
    }

  View.OnClickListener imageCancelListener = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
//     taketheimageback(relfirst);
     activity.finish();
      }
  };

    View.OnClickListener buttoncListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!answerc1.getText().toString().equals("") && answerc1.getText() != null) {
                if (!answerc2.getText().toString().equals("") && answerc2.getText() != null) {
                    if (!answerC3.getText().toString().equals("") && answerC3.getText() != null) {
                        double windPressure = Double.parseDouble(answerc1.getText().toString());
                        double ice_density = Double.parseDouble(answerc2.getText().toString());
                        double ice_thickness = Double.parseDouble(answerC3.getText().toString());
                        if (doublechecky.isChecked()) {
                            calcProcessord.setDoubled(true);
                        } else {
                            calcProcessord.setDoubled(false);
                        }
                        taketheimageback(relthird);
                        calcProcessord.setWindPressure(windPressure);
                        calcProcessord.setIceDensity(ice_density);
                        calcProcessord.setIceThickness(ice_thickness);
                        double safetyfactor = 2.5;
//                    (double densityOfConductor, double crossSectionalAreaOfConductor, double span, double ultimatetensileStrength, double safetyFactor, double densityOfIce, double thicknessOfIce, double windPressure)
                        double position = calcProcessord.getPosition();
                        double weigthperl = calcProcessord.getWcperl();
                        double area = calcProcessord.getArea();
                        double spann = calcProcessord.getSpan();
                        double ultimateTensileStrength = calcProcessord.getUtimatestrength();
                        displayo(String.valueOf(ultimateTensileStrength));
                        double sag = calculationsForSag.even_Pole_Height_complex_ice_and_wind_SagC(weigthperl, area, spann, ultimateTensileStrength, safetyfactor, ice_density, ice_thickness, windPressure);
                        calcProcessord.setSag(sag);
                        double totalloading = calculationsForSag.findTotalWeightPerUnitLengthConductor_Ice_and_windC(weigthperl, area, windPressure, ice_density, ice_thickness);
                        displayo(String.valueOf(totalloading));
                        double condLength = calculationsForSag.saggyLengthOfConductor(ultimateTensileStrength, totalloading, spann);
                        displayo(String.valueOf(condLength));
                        calcProcessord.setCondLength(condLength);
                        String text = answer1.getText().toString() + "kg/m2, " + answerc2.getText().toString() + "kg/m3, " + answerC3.getText().toString() + "m.";
                        final double constante = calculationsForSag.Constante(ultimateTensileStrength, totalloading, spann);
                        calcProcessord.setConstante(constante);
                        calcProcessord.setNumberOfdiscs(calculationsForSag.NumberofDics(calcProcessord.getVoltage()));
                        try {
                            calcProcessord.setRespacing(resolveSpacings(calcProcessord.getVoltage()));
                            if(calcProcessord.getRespacing()==null){
                                numwatcherr =1;
                            }
                        }
                        catch (Exception e){
                        numwatcherr = 1;
                        }

                        d2(text, 5, 5, false, false, null);

//                        Graph graph = new Graph.Builder()
//                                .addFunction(x -> sin(x), Color.RED)
//                                .addFunction(x -> 0.1*x*x*x, Color.BLUE)
//                                .addFunction(x -> 1/x, Color.GREEN)
//                                .setWorldCoordinates(-2*Math.PI, 2*Math.PI, -5, 5)
//                                .setXTicks(new double[] {-3, -1, 1, 3})
//                                .setYTicks(new double[] {-3, -1, 1, 3})
//                                .build();
//
//                    }
                    }
                    else {
                        displayo("please enter ice_thickness");
                    }
                }else {
                    displayo("please enter ice loading");
                }
            } else {
                displayo("please enter wind loading");
            }
        }
    };

// Design_activity
public void setUpSpacings(){
    MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
//        LiveData<List<Info>> infos = mDataBase.infoDao().LoadAllInfo();
    viewModel.getSpacings().observe(this, new Observer<List<Spacings>>() {
                @Override
                public void onChanged(@Nullable List<Spacings> spacings) {
//                Processors.getInstance().setInfod(infos);
                    List<Spacings> anspacing = new ArrayList<Spacings>();
                    anspacing.clear();
                    if (spacings.size() > 0) {
////                            if (questions.get(i).getMclass().equals(name)) {
////                    if (questions.get(i).isAnswered()) {
////                                    questions.get(i).setRead(Spreferences.ReadState(mysharedpreference, questions.get(i).getQueid(), myeditor, questions.get(i).getNoanswer(), questions.get(i).
                        calcProcessor.getInstance().setSpacings(spacings);
//                        articlesAdapter.setArticlesList(explore_articles);
//                        articlesAdapter.notifyDataSetChanged();
//                        infoAdapter.setInfoLists(anquestions);

//                    Information.InfoListsand = Processors.getInstance().getInfod();
//                    Information.infoAdapterand.notifyDataSetChanged();
                    }

                }

            }
    );

}
    View.OnClickListener buttonrListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
      answerc1.setText("790");
      answerc2.setText("0");
      answerC3.setText("0");
        }
    };

public Spacings resolveSpacings(double voltage){
    List<Spacings> spacings = calcProcessord.getSpacings();
    Spacings spacings1 = null;
    if(voltage>=330){
        for(int i= 0;i<spacings.size();i++){
            if(spacings.get(i).getName().equals(SplashScreen.threethirtyk)){
                spacings1 = spacings.get(i);
            }
        }
    }
    else {
        if(voltage>=132){

            for(int i= 0;i<spacings.size();i++){
                if(spacings.get(i).getName().equals(SplashScreen.onethirtyk)){
                    spacings1 = spacings.get(i);
                }
            }
        }
        else{
            if(voltage>=66){

                for(int i= 0;i<spacings.size();i++){
                    if(spacings.get(i).getName().equals(SplashScreen.sixtyk)){
                        spacings1 = spacings.get(i);
                    }
                }
            }
            else {
                if(voltage>=33){

                    for(int i= 0;i<spacings.size();i++){
                        if(spacings.get(i).getName().equals(SplashScreen.thirththreek)){
                            spacings1 = spacings.get(i);
                        }
                    }
                }
                else {
                    if(voltage>=11){

                        for(int i= 0;i<spacings.size();i++){
                            if(spacings.get(i).getName().equals(SplashScreen.elevenk)){
                                spacings1 = spacings.get(i);
                            }
                        }
                    }
                    else {
                        if(voltage>=6.6){

                            for(int i= 0;i<spacings.size();i++){
                                if(spacings.get(i).getName().equals(SplashScreen.sixk)){
                                    spacings1 = spacings.get(i);
                                }
                            }
                        }
                        if(voltage>=3.3){

                            for(int i= 0;i<spacings.size();i++){
                                if(spacings.get(i).getName().equals(SplashScreen.threek)){
                                    spacings1 = spacings.get(i);
                                }
                            }
                        }
                        else {
                            if(voltage>=0.415){

                                for(int i= 0;i<spacings.size();i++){
                                    if(spacings.get(i).getName().equals(SplashScreen.fourh)){
                                        spacings1 = spacings.get(i);
                                    }
                                }
                            }
                            else {
                                for(int i= 0;i<spacings.size();i++){
                                    if(spacings.get(i).getName().equals(SplashScreen.fourh)){
                                        spacings1 = spacings.get(i);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return spacings1;
}
    public void taketheimageback(final View view){
        view.startAnimation(scalein);
        CountDownTimer ctimer = new CountDownTimer(600,500) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                view.setVisibility(View.INVISIBLE);
            }
        } .start();
    }


    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
      if(!answer1.getText().toString().equals("")&&answer1.getText()!=null){
          if(!answer2.getText().toString().equals("")&&answer2.getText()!=null){
              if(!answer3.getText().toString().equals("")&&answer3.getText()!=null){
                  double current = findCurrent();

                 calcProcessord.setCurrent(current);


                d2(answer1.getText().toString()+"KW, "+answer2.getText().toString()+"KV, "+answer3.getText().toString(),0,0,false,false,null);
                taketheimageback(relfirst);
                }

              else {
                  displayo("powerfactor not entered");
              }
          }

          else {
              displayo("volts not entered");
          }
      }

      else {
          displayo("power not entered");
      }
        }
    };
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
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void d1(final String text, final int stage,final int LayoutId, final boolean isAnn,final boolean lrog, final View view){
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy");
      SimpleDateFormat sdfa = new SimpleDateFormat("h:mm a");
//      strDate = sdf.format(c.getTime());
        c = new Date();
      time = sdfa.format(c.getTime());

        this.stage = stage;
        CountDownTimer countDownTimer = new CountDownTimer(2000,1500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                final Conversations conversation = new Conversations(text,stage,LayoutId,lrog,isAnn,time,null);
                conversations.add(conversation);
                conversationAdapter.notifyDataSetChanged();
                if(speechIsAvailable){
                    speakWords(conversation.getText());
                }
                if(lrog){

                    CountDownTimer countDownTimer = new CountDownTimer(2000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            messagea.setText(conversation.getText());
                            messageb.setText(conversation.getText());
                            messagec.setText(conversation.getText());
                            view.setVisibility(View.VISIBLE);
                            view.startAnimation(scaleout);
                        }
                    }.start();
            }
        };

        }.start();
    }


    public void d2(final String text, final int stage,final int LayoutId, final boolean isAnn,final boolean lrog, final Dialog dialog) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy");
        SimpleDateFormat sdfa = new SimpleDateFormat("h:mm a");
//      strDate = sdf.format(c.getTime());
        c = new Date();
        time = sdfa.format(c.getTime());

        this.stage = stage;
        CountDownTimer countDownTimer = new CountDownTimer(2000, 1500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Conversations conversation = new Conversations(text, stage, LayoutId, lrog, isAnn, time,null);
                conversations.add(conversation);
                conversationAdapter.notifyDataSetChanged();
                CountDownTimer countDownTimer1 = new CountDownTimer(2000,1500) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
               rollit(stage + 1);

                    }
                }.start();
            }
        }.start();

        this.stage = stage  +1;
    }


    public void d3(final String text, final int stage, final int LayoutId, final boolean isAnn, final boolean lrog, final String title)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy");
        SimpleDateFormat sdfa = new SimpleDateFormat("h:mm a");
//      strDate = sdf.format(c.getTime());
        c = new Date();
        time = sdfa.format(c.getTime());

        this.stage = stage;
        CountDownTimer countDownTimer = new CountDownTimer(2000, 1500) {
            @Override
            public void onTick(long millisUntilFinished) {
                }
            @Override
            public void onFinish() {
                Conversations conversation = new Conversations(text, stage, LayoutId,lrog, isAnn, time,title);
                conversations.add(conversation);
                conversationAdapter.notifyDataSetChanged();
                if(speechIsAvailable){
                    speakWords(conversation.getTitle()+". "+conversation.getText());
                }
                CountDownTimer countDownTimer1 = new CountDownTimer(2000,1500) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }
                    @Override
                    public void onFinish() {
                        if(stage == 7550){
                            final double constantey = calcProcessord.getConstante();
                            displayo(String.valueOf(constantey));
                            Function catenary_function = new Function() {
                                public double apply(double x) {
                                    return constantey * Math.cosh(x / constantey);
                                }
                            };
                            graph = new Graph.Builder()
                                    .addFunction(catenary_function)
                                    .setYTicks(new double[] {5, 10, 15, 20, 25, 30})
                                    .build();
                            graphView.setGraph(graph);
                            rel_graph_view.setVisibility(View.VISIBLE);
                        }
                        rollit(stage + 1);
                        }
                }.start();
            }
        }.start();

        this.stage = stage  +1;
    }


    View.OnClickListener buttonbListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
    if(!answerb1.getText().toString().equals("")&&answerb1.getText()!=null) {
        if (!answerb2.getText().toString().equals("") && answerb2.getText() != null&&!answerb2.getText().toString().equals(String.valueOf(0))) {
            calcProcessor.getInstance().setLength(Double.parseDouble(answerb1.getText().toString()));
            calcProcessord.setNumberOfConductorinAbundle(Integer.parseInt(answerb2.getText().toString()));
            taketheimageback(relsecond);
            String text = answerb1.getText().toString() + "km, ";
            String textb = answerb2.getText().toString();
            obtainConductor(calcProcessord.getCurrent(),calcProcessord.getNumberOfConductorinAbundle());
            d2(text+textb, 2, 2, false, false, null);
        } else {
             displayo("please enter number of conductors per bundle select one if bundling is not desired");
        }
    }

    else {
        displayo("please enter estimate length");
    }
        }
    };

    public void setUpRecents(){
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
//        LiveData<List<Info>> infos = mDataBase.infoDao().LoadAllInfo();
        viewModel.getSoundtracker().observe(this, new Observer<List<soundtracker>>() {
                    @Override
                    public void onChanged(@Nullable List<soundtracker> soundtrackers) {

                    if(soundtrackers.size()>0)  { rollit(soundtrackers.get(0).getNumber());}
//                Processors.getInstance().setInfod(infos);
//                        List<soundtracker> soundtrackers1 = new ArrayList<soundtracker>();
//                        anrecents.clear();
//                        if (recents.size() > 0) {
//                            for (int i = recents.size(); i > 0; i--) {
////                            if (questions.get(i).getMclass().equals(name)) {
////                    if (questions.get(i).isAnswered()) {
////                                    questions.get(i).setRead(Spreferences.ReadState(mysharedpreference, questions.get(i).getQueid(), myeditor, questions.get(i).getNoanswer(), questions.get(i).getNocomment()));
//
//                                if (anrecents.size() < 10) {
//                                    anrecents.add(recents.get(i-1));
//                                } else {
//                                    break;
//                                }
//                                Collections.sort(anrecents, new Comparator<Recents>() {
//                                    @Override
//                                    public int compare(Recents t0, Recents t1) {
//                                        return (int) (t0.getTimestamp() - t1.getTimestamp());
//                                    }
//                                });


//                            }
//                            try {
//                                operateMenu(navigationView, anrecents);
//                            }
//                            catch (Exception e){
//
//                            }
//                        infoAdapter.setInfoLists(anquestions);

//                    Information.InfoListsand = Processors.getInstance().getInfod();
//                    Information.infoAdapterand.notifyDataSetChanged();
                        }
                    });

                }


    public void startIt(){
        stage = 0;


        String intro = "Hi "+ ownersName +" i'm ann, your design aid, its nice to meet you!";
           String second = ", so at what power, powerfactor and voltage values are we transmitting?";
        d1(intro + second,0,R.layout.first_dialog,true,true,relfirst);
//        if(speechIsAvailable&&canshow)
//        CountDownTimer countDownTimer = new CountDownTimer(1500,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        }.start();

//       if(speechIsAvailable){
//           speakWords(intro);
//       }

    }

    public void rollit(int number){
        String fulltext;
        switch (number){

            case 1:{
                String title = "Voltage Considerations";
                String text = designEstimates.VoltageConsiderations(calcProcessord.getVoltage());
                String textb = getResources().getString(R.string.doubleandsingle);
                d3(text+textb,1,1,true,false,title);
                break;
            }

            case 2: {
                   String text = "ok then, what is the estimate distance between positions and number of conductors in a bundle if bundling is desired, if is not desired enter 1";
                d1(text,2,2,true,true,relsecond);

            break;
            }

            case 3: {
//                String text = "thats great, now the design considerations. Based on voltage, power and power factor values, the most suitable current without derating is ";
                String title = "Cable Size";
                double current = calcProcessor.getInstance().getCurrent();
                String b = " from the calculated current of " + String.valueOf(current)+"Amperes without derating ";

                String c = "and based on clause 3.2 of NERC 2015, the minimum suitable conductor size ";
                String g = "is ";
                String e = c + g;
                if(calcProcessord.isBundled()) {
                     String noOfConductorinabundle = String.valueOf(calcProcessord.getNumberOfConductorinAbundle());
                    e = c + "for each of the "+noOfConductorinabundle+" conductors in the bundle is ";
                }

                double cablesize = calcProcessor.getInstance().getSize();
                String d = String.valueOf(cablesize)+"mm";
                if(cablesize==0){
                    String f = "the suitable cable size cannot be obtaine, try using practical values";
                            displayo(f);
                            rollit(2);
                }
                else{
                    fulltext = b + e + d;
                    d3(fulltext,3,3,true,false,title);
                }



           break;
            }
            case 4:{
                String title = "Suppport Structures";
                 fulltext = getResources().getString(R.string.support_structure);
                d3(fulltext,4,4,true,false,title);
                break;
            }
            case 5:{
                String text = "please provide the values for the loadings, if loading is absent, enter 0\n" +
                        "or click the second button to use values from the NERC standard, also if double circuit is desired, check the box" +
                        "";
                d1(text,5,5,true,true,relthird);
                break;
            }  case 6:   {
                fulltext = this.getResources().getString(R.string.safetyfactor);
                String title = "Safety Factor";
                d3(fulltext,6,6,true,false,title);
                break;
            }
            case 7: {
                String title = "Sag and total Length of WIre";
                String a = "from clause 3.6.4b of NESIS, a suitable span between supports is "+calcProcessord.getSpan();
                String b = " and based on this value, the sag and the length of conductor between each span is "+calcProcessord.getSag()+"m and "+calcProcessord.getCondLength()+"m";
               fulltext = a+b;
                d3(fulltext,7,7,true,false,title);
                break;
            }
            case 8:{
                String title = "number of disc insulator";
                String a  = "based on the entered voltage value, the number of 11kv needed in the suspension insulation strain is ";
                String b = String.valueOf(calcProcessord.getNumberOfdiscs());
                fulltext = a + b;
                d3(fulltext,8,8,true,false,title);
                break;
            }
            case 10:{
                if(numwatcherr==1){
                    rollit(11);
                }
                else {
                    Spacings spacings = calcProcessord.getRespacing();
                    if (spacings == null) {
                        rollit(11);
                    } else {
                        String title = "spacing with near by lines";
                        String a = "based on the voltage value and the NESIS standard, the";
                        String b = "clearance of line with earthwire " + String.valueOf(spacings.getEarthwire()) + " cm\n";
                        String c = "clearance of line with a 415 volt line " + String.valueOf(spacings.getFourh()) + " cm\n";
                        String d = "clearance of line with a 3.3kv line " + String.valueOf(spacings.getThreek()) + " cm\n";
                        String e = "clearance of line with 6.6kv line " + String.valueOf(spacings.getSixk()) + " cm\n";
                        String f = "clearance of line with 11kv line " + String.valueOf(spacings.getElevenk()) + " cm\n";
                        String g = "clearance of line with 33kv line " + String.valueOf(spacings.getThirtythreek()) + " cm\n";
                        String h = "clearance of line with 66kv line " + String.valueOf(spacings.getSixtyk()) + " cm\n";
                        String i = "clearance of line with 132kv line " + String.valueOf(spacings.getOnethirtyk()) + " cm\n";
                        String j = "clearance of line with 330kv " + String.valueOf(spacings.getThreethirtyk()) + " cm\n";
                        fulltext = a + b + c + d + e + f + g + h + i + j;
                        d3(fulltext, 10, 10, true, false, title);
                    }
                }
                break;
            }
            case 11: {
                 fulltext = designEstimates.RightOfWays(calcProcessord.getVoltage());
                String title = "Right of Ways";
                d3(fulltext,11,11,true,false,title);
                break;
            }

            case 12:{
                fulltext = designEstimates.MinimumTensileStrength();
                String title = "Minimum Tensile Strength";
                d3(fulltext,12,12,true,false,title);
                  break;
            }


            case 9:   {
                     fulltext = this.getResources().getString(R.string.insulators);
                     String title = "Insulation Requirements";
                     d3(fulltext,9,9,true,false,title);
                       break;
                      }
            case 13:   {
                fulltext = designEstimates.DielectricStrengthOfOHTL(calcProcessord.getVoltage());
//                     fulltext = this.getResources().getString(R.string.safetyfactor);
                     String title = "Dielectric Strength Considerations";
                     d3(fulltext,13,13,true,false,title);
                           break;
            }

                default:break;
        }

    }

    public double findCurrent (){
        double power = Double.parseDouble(answer1.getText().toString());
        calcProcessord.setPower(power);
        double voltage = Double.parseDouble(answer2.getText().toString());
        calcProcessord.setVoltage(voltage);
        double powerfactor = Double.parseDouble(answer3.getText().toString());
        calcProcessord.setPowerfactor(powerfactor);
        PowerCalculations powerCalculations = new PowerCalculations();
        return powerCalculations.current(voltage,power,powerfactor);
    }


    private void speakWords(String speech) {

        //speak straight away
        myTTS.speak(speech, TextToSpeech.QUEUE_ADD, null);
//        myTTS.setOnUtteranceProgressListener(new UtteranceProgressListener() {
//            @Override
//            public void onStart(String utteranceId) {
//
//            }
//
//            @Override
//            public void onDone(String utteranceId) {
//       AppExecutor.getsInstance().diskIO().execute(new Runnable() {
//           @Override
//           public void run() {
//               soundtracker soundtrackere = mdb.soundTrackerDao().LoadOrdinarySoundTrackerbyid(1);
//               soundtrackere.setNumber(stage+1);
//               stage = stage +1;
//               mdb.soundTrackerDao().updateSoundtracker(soundtrackere);
//           }
//       });
            }

//            @Override
//            public void onError(String utteranceId) {
//
//            }
//        });

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
                myTTS = new TextToSpeech(this, this);
                speechIsAvailable = true;

            }
            else {
                //no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    //setup TTS
    public void onInit(int initStatus) {

        //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS) {
            if(myTTS.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE)
                myTTS.setLanguage(Locale.US);
            myTTS.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String utteranceId) {

                }

                @Override
                public void onDone(String utteranceId) {
                displayo("done");
                }

                @Override
                public void onError(String utteranceId) {

                }
            });
        }
        else if (initStatus == TextToSpeech.ERROR) {
            displayo("error speaking out!");
        }
    }

    public void obtainConductor(final double current, final int numberOfConductorsperbundle){
        AppExecutor.getsInstance().networkIO().execute(new Runnable() {
            List<DataValues> dataValues;
            double maximum = 100100000;
            double size;
            int position;
            double wcPerl;
            double area;
            double span;
            double ultimateTensileStrength;
            double realcurrent;


            @Override
            public void run() {
                if(numberOfConductorsperbundle>1){
                    calcProcessord.setBundled(true);
                }
                    realcurrent = current/numberOfConductorsperbundle;
                calcProcessord.setBundledCurrent(realcurrent);
                dataValues = mdb.dataValuesDao().LoadAllOrdinaryDataValues();
                for (int i = 0; i<dataValues.size();i++){
                    if(dataValues.get(i).getDurrentCarryingCapacity()>realcurrent&&dataValues.get(i).getDurrentCarryingCapacity()<maximum){
                        maximum = dataValues.get(i).getDurrentCarryingCapacity();
                        size = dataValues.get(i).getSize();
                        area = dataValues.get(i).getSize();
                        ultimateTensileStrength = dataValues.get(i).getCalculatedBreakingLoad();
                        wcPerl = dataValues.get(i).getApproxWeightOfConductor();
                        position = i;
                    }

                }
                calcProcessor.getInstance().setSize(size);
                calcProcessord.setPosition(position);
                calcProcessord.setUtimatestrength(ultimateTensileStrength);
                calcProcessord.setArea(area);
                calcProcessord.setSpan(designEstimates.span(calcProcessord.isBundled()));
                calcProcessord.setWcperl(wcPerl);
            }

        });

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

}

