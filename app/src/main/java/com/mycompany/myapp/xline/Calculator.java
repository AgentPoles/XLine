package com.mycompany.myapp.xline;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Calculator extends AppCompatActivity implements CalculatorAdapter.OnGridClickedListener {


    private RecyclerView recyclerView;
    private CalculatorAdapter adapter;
    private List<CalculatorElements> albumList;
    ArrayList<workingCalculation> workingres = new ArrayList<>();
    ArrayList<workingCalculation> workingcap = new ArrayList<>();
    ArrayList<workingCalculation> workingind = new ArrayList<>();
    ArrayList<workingCalculation> workingsag = new ArrayList<>();
    ArrayList<workingCalculation> workingpow = new ArrayList<>();
    ArrayList<workingCalculation> workingDimensions = new ArrayList<>();
    ArrayList<workingCalculation> workingPortParams = new ArrayList<>();
    ArrayList<workingCalculation> workingLosses = new ArrayList<>();
    ArrayList<workingCalculation> workingStringing = new ArrayList<>();
    ArrayList<workingCalculation> workingCorona = new ArrayList<>();

    int[] covers = new int[]{
            R.drawable.ic_res,
            R.drawable.ic_capb,
            R.drawable.ic_inductance,
            R.drawable.ic_saggy,
            R.drawable.ic_twooport,
            R.drawable.ic_powercalc,
            R.drawable.ic_calc,
            R.drawable.ic_driemm,
            R.drawable.ic_des,
            R.drawable.ic_stringing,
            R.drawable.ic_dashboard_black_24dp,
            R.drawable.ic_newcalculator,
            R.drawable.ic_notifications_black_24dp,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new CalculatorAdapter(this, this, albumList);
        adapter.setOnItemClickedListener(this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

//        try {
//            Glide.with(this).load(R.drawable.ic_newcalculator).into((ImageView) findViewById(R.id.backdrop));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
//        final CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle(" ");
//        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
//        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            boolean isShow = false;
//            int scrollRange = -1;
//
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (scrollRange == -1) {
//                    scrollRange = appBarLayout.getTotalScrollRange();
//                }
//                if (scrollRange + verticalOffset == 0) {
//                    collapsingToolbar.setTitle(getString(R.string.app_name));
//                    isShow = true;
//                } else if (isShow) {
//                    collapsingToolbar.setTitle(" ");
//                    isShow = false;
//                }
//            }
//        });
    }

    @Override
    public void OnGridClicked(int position, View viewA, View viewB, int thumbnailid, String tobefound, ArrayList<workingCalculation> workingCalculations) {
        calcProcessor.getInstance().setWorkingCalculations(workingCalculations);
        Intent intent = new Intent(this, InnerCalculator.class);
        intent.putExtra("thumbnailid", thumbnailid);
        intent.putExtra("tobefound", tobefound);
        Pair<View, String> p1 = Pair.create(viewA, "recycled");
        Pair<View, String> p2 = Pair.create(viewB, "tobefound");
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, p1, p2);
        startActivity(intent, options.toBundle());
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {

        ArrayList<String> resistanceStrings =new ArrayList<>();
        resistanceStrings.add("Dc Resistance");
        resistanceStrings.add("Ac Resistance");
        resistanceStrings.add("Resistance at a given tempetature");
        resistanceStrings.add("resistivity at a given temperature");

        definitionOfTerms(covers);

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }
                });
    }
    public void definitionOfTerms(int[] covers){
        workingCalculation workingCalculation = new workingCalculation("dcRes() (Dc Resistance)",0);
        workingCalculation workingCalculation1 = new workingCalculation("acRes() (Ac Resistance)",1);
        workingCalculation workingCalculation2 = new workingCalculation("resAtT() (Resistance at a given tempetature)",2);
        workingCalculation workingCalculation3 = new workingCalculation("rhoAtT() (Resistivity at a given temperature)",3);
        workingCalculation workingCalculation4 = new workingCalculation("resWcond() (Resistance of wound conductor)",4);

        workingCalculation workingCalculation5 = new workingCalculation("singlePcap() (Single phase to neutral Capacitance)",5);
        workingCalculation workingCalculation6 = new workingCalculation("threePcap()  (Three phase Capacitance)",6);
        workingCalculation workingCalculation7 = new workingCalculation("Xc (Capacitive Reactance)",7);
        workingCalculation workingCalculation8 = new workingCalculation("singleLLCap() (line to line capacitance for two wires)",8);
        workingCalculation workingCalculation9 = new workingCalculation("LNearthCapline() (Line to neutral capacitance in the presence of earth)",9);

        workingCalculation workingCalculation10 = new workingCalculation("indAB() (inductance between two points outside a conductor)",10);
        workingCalculation workingCalculation11 = new workingCalculation("singlePind() (Inductance of a single phase line)",11);
        workingCalculation workingCalculation12 = new workingCalculation("singleThreePind() (Inductance of a single circuit three phase line)",12);
        workingCalculation workingCalculation13 = new workingCalculation("doubleThreePind() (Inductance of a double circuit three phase line)",13);
        workingCalculation workingCalculation14 = new workingCalculation("XL (Inductive Reactance",14);

        workingCalculation workingCalculation15 = new workingCalculation("equalHsag()  (Sag for supports of equal hesigth)",15);
        workingCalculation workingCalculation16 = new workingCalculation("unEqualHsag() (Sag for supports of unequal height)", 16);
        workingCalculation workingCalculation17 = new workingCalculation("equalClrMid() (Clearance midway between supports of equal height)",17);
        workingCalculation workingCalculation18 = new workingCalculation("unEqualClrMid() (Clearance midway between supports of unequal height)",18);
        workingCalculation workingCalculation19 = new workingCalculation("Wt() (Total loading on per unit Length)",19);
        workingCalculation workingCalculation20 = new workingCalculation("vSag() (Vertical Sag)",20);

        workingCalculation workingCalculation21 = new workingCalculation("sLineParams() (Short Line Parameters)",21);
        workingCalculation workingCalculation22 = new workingCalculation("mLineParams() (medium Line Parameters)",22);
        workingCalculation workingCalculation23 = new workingCalculation("lLineParams() (Lomg Line params",23);

        workingCalculation workingCalculation24 = new workingCalculation("singlePpow() (single phase power)",24);
        workingCalculation workingCalculation25 = new workingCalculation("threePpow() (three phase power)",25);
        workingCalculation workingCalculation26 = new workingCalculation("maxThreePpow() (max three phase power)",26);
        workingCalculation workingCalculation27 = new workingCalculation("anyPpow() (single phase power)",27);
        workingCalculation workingCalculation28 = new workingCalculation("eff() (efficiency)",28);

        workingCalculation workingCalculation29 = new workingCalculation("SIL() (Surge Impedance Loading)",29);
        workingCalculation workingCalculation30 = new workingCalculation("CuLosses() (Copper Losses)",30);
        workingCalculation workingCalculation31 = new workingCalculation("RADandCONVLosses() (Losses From Radiation and Convection)",31);
        workingCalculation workingCalculation32 = new workingCalculation("CONVLosses() (Convection Losses)",32);

        workingCalculation workingCalculation33 = new workingCalculation("CondAmper() (Conductor Ampercity)",33);
        workingCalculation workingCalculation34 = new workingCalculation("threePGMD() (three phase Geometric Mean Distance)",34);
        workingCalculation workingCalculation35 = new workingCalculation("GMRn() (Geometric Mean Radius no bundling)",35);
        workingCalculation workingCalculation36 = new workingCalculation("GMRy() (Geometric Mean Radius with upto four bundle)",36);
        workingCalculation workingCalculation37 = new workingCalculation("CSAsC() (Crossectional Area using ShortCicuit Current)",37);
//        workingCalculation workingCalculation38 = new workingCalculation("singlePpow() (single phase power)",38);


        workingCalculation workingCalculation38 = new workingCalculation("stringEff()  (String Efficiency)",38);
        workingCalculation workingCalculation39 = new workingCalculation("K() (String K value)",39);
        workingCalculation workingCalculation40 = new workingCalculation("voltDistribution() (voltage diatibution in Strings)",40);

        workingCalculation workingCalculation41 = new workingCalculation("CDV()  (Critical Disrptive voltage)",41);
        workingCalculation workingCalculation42 = new workingCalculation("AirRho() (Air density factor)",42);
        workingCalculation workingCalculation43 = new workingCalculation("g() (potential gradient)",43);
        workingCalculation workingCalculation44 = new workingCalculation("VCV()  (visual critical voltage)",44);
        workingCalculation workingCalculation45 = new workingCalculation("coronaPowLosses() (power losses due to corona)",45);







        workingres.add(workingCalculation);
        workingres.add(workingCalculation1);
        workingres.add(workingCalculation2);
        workingres.add(workingCalculation3);
        workingres.add(workingCalculation4);

        workingcap.add(workingCalculation5);
        workingcap.add(workingCalculation6);
        workingcap.add(workingCalculation7);
        workingcap.add(workingCalculation8);
        workingcap.add(workingCalculation9);

        workingind.add(workingCalculation10);
        workingind.add(workingCalculation11);
        workingind.add(workingCalculation12);
        workingind.add(workingCalculation13);
        workingind.add(workingCalculation14);

        workingsag.add(workingCalculation15);
        workingsag.add(workingCalculation16);
        workingsag.add(workingCalculation17);
        workingsag.add(workingCalculation18);
        workingsag.add(workingCalculation19);
        workingsag.add(workingCalculation20);

        workingPortParams.add(workingCalculation21);
        workingPortParams.add(workingCalculation22);
        workingPortParams.add(workingCalculation23);

        workingpow.add(workingCalculation24);
        workingpow.add(workingCalculation25);
        workingpow.add(workingCalculation26);
        workingpow.add(workingCalculation27);
        workingpow.add(workingCalculation28);

        workingLosses.add(workingCalculation29);
        workingLosses.add(workingCalculation30);
        workingLosses.add(workingCalculation31);
        workingLosses.add(workingCalculation32);

        workingDimensions.add(workingCalculation33);
        workingDimensions.add(workingCalculation34);
        workingDimensions.add(workingCalculation35);
        workingDimensions.add(workingCalculation36);
        workingDimensions.add(workingCalculation37);
//        workingDimensions.add(workingCalculation38);

        workingStringing.add(workingCalculation38);
        workingStringing.add(workingCalculation39);
        workingStringing.add(workingCalculation40);

        workingCorona.add(workingCalculation41);
        workingCorona.add(workingCalculation42);
        workingCorona.add(workingCalculation43);
        workingCorona.add(workingCalculation44);
        workingCorona.add(workingCalculation45);
//        workingCorona.add(workingCalculation42);

        CalculatorElements a = new CalculatorElements("Resistance","",covers[0],workingres);
        albumList.add(a);

        a = new CalculatorElements("Capacitance", "", covers[1],workingcap);
        albumList.add(a);

        a = new CalculatorElements("Inductance", "", covers[2],workingind);
        albumList.add(a);

        a = new CalculatorElements("Sag", "", covers[3],workingsag);
        albumList.add(a);

        a = new CalculatorElements("Two port Parameters", "", covers[4],workingPortParams);
        albumList.add(a);

        a = new CalculatorElements("Power Calculations", "", covers[5],workingpow);
        albumList.add(a);

        a = new CalculatorElements("Losses", "", covers[6],workingLosses);
        albumList.add(a);

        a = new CalculatorElements("Dimensions", "", covers[7],workingDimensions);
        albumList.add(a);

        a = new CalculatorElements("Corona", "", covers[8],workingCorona);
        albumList.add(a);

        a = new CalculatorElements("Sringing", "", covers[9],workingStringing);
        albumList.add(a);
    }
    }

