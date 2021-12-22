package com.mycompany.myapp.xline;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Second_inner_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Second_inner_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Second_inner_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    static int referenceNumber;
    static int retrieveWatcher = 0;
    ResultReady resultReadyListener;
    OpenDrawerInterface  openDrawerListener;
    CloseDtawerInterface  closeDrawerListener;
    ScaleAnimation scaleAnimation;
    ReturnHome returnHomeListener;
    SharedPreferences mySharedPreference;
    Bundle newSavedInstantState;
    SharedPreferences.Editor myEditor;
    EditText edione, editwo, edithree, edifour, edifive, edisix, ediseven, edieight, edinine, editen, edieleven,editwelve;
    RelativeLayout relone, reltwo, relthree, relfour, relfive, relsix, relseven, releight, relnine, relten, releleven;
    FloatingActionButton proceed, retrieve;
    NestedScrollView scrollView;
//    private FirstInnerCalculator.OnFragmentInteractionListener mListener;
//    Dialog dialog;
    int[] layouts = {R.layout.dcresistance,R.layout.ac_resistance, R.layout.resistance_at_different_temperatures, R.layout.resistivity_at_different_temperature,
            R.layout.resistance_of_wound_conductor, R.layout.single_phase_capacitance,R.layout.three_phase_capacitance, R.layout.capacitive_reactance, R.layout.three_phase_capacitance,R.layout.capacitance_in_the_presence_of_earth,
        R.layout.inductance_between_two_points_outside_a_conductor, R.layout.single_phase_inductance, R.layout.three_phase_inductance, R.layout.three_phase_inductance, R.layout.inductive_reactance,
        R.layout.sag_evenpole_height, R.layout.sag_uneven_pole_height, R.layout.clearance_equal, R.layout.clearance_mid_way, R.layout.totalweightperlength, R.layout.vertical_sag,
        R.layout.short_line_parameters, R.layout.medium_line_parameters, R.layout.long_line_parameters, R.layout.power_calcualtion, R.layout.three_phase_pow, R.layout.maximum_power_of_transmission_line,
        R.layout.phase_powers, R.layout.efficiency, R.layout.surge_impedance_loading, R.layout.copper_losses, R.layout.total_losses_from_convection_and_radiation, R.layout.losses_from_convention,
        R.layout.conductor_ampercity, R.layout.gmd_for_three_phase_without_bundling,R.layout.gmr_without_bundling,R.layout.gmr_up_to_four_bundle, R.layout.calculation_for_csa_using_shortcircuit,
        R.layout.string_efficiency, R.layout.string_k_value, R.layout.voltage_distribution, R.layout.critical_disruptive_voltage, R.layout.air_density_factor,R.layout.potential_gradient,
        R.layout.critical_disruptive_voltage,R.layout.power_loss_due_to_corona
    };
    public Second_inner_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Second_inner_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Second_inner_fragment newInstance(String param1, String param2) {
        Second_inner_fragment fragment = new Second_inner_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mySharedPreference =  getActivity().getSharedPreferences("savedname", Context.MODE_PRIVATE);
        if(mySharedPreference!=null){
            myEditor = mySharedPreference.edit();
        }
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        newSavedInstantState = savedInstanceState;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(layouts[calcProcessor.getInstance().getNumber()], container, false);

        edione = (EditText)view.findViewById(R.id.edione);
        editwo = (EditText)view.findViewById(R.id.editwo);
        edithree  = (EditText)view.findViewById(R.id.edithree);
        edifour = (EditText)view.findViewById(R.id.edifour);
        edifive  = (EditText)view.findViewById(R.id.edifive);
        edisix = (EditText)view.findViewById(R.id.edisix);
        ediseven = (EditText)view.findViewById(R.id.ediseven);
        edieight = (EditText)view.findViewById(R.id.editeight);
        edinine  = (EditText)view.findViewById(R.id.edinine);
        editen = (EditText)view.findViewById(R.id.editen);
        edieleven = (EditText)view.findViewById(R.id.edieleven);
        editwelve = (EditText)view.findViewById(R.id.editwelve);
//        edifive  = (EditText)view.findViewById(R.id.edifour);
//        edifour = (EditText)view.findViewById(R.id.edifour);
        scrollView = (NestedScrollView) view.findViewById(R.id.scrollView);
        proceed = (FloatingActionButton)view.findViewById(R.id.proceed);
        retrieve = (FloatingActionButton)view.findViewById(R.id.retrieve);
        proceed.setOnClickListener(generalListener);
        retrieve.setOnClickListener(retrieveListener);
        proceed.setVisibility(View.GONE);
        retrieve.setVisibility(View.GONE);
        scrollView.scrollTo(0,0);
       referenceNumber = calcProcessor.getInstance().getNumber();
       scaleAnimation = new ScaleAnimation(0,1,0,1);
       scaleAnimation.setDuration(500);

        CountDownTimer countDownTimer = new CountDownTimer(500,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                proceed.setVisibility(View.VISIBLE);
                retrieve.setVisibility(View.VISIBLE);
                proceed.startAnimation(scaleAnimation);
                retrieve.startAnimation(scaleAnimation);
//                CountDownTimer countDownTimer = new CountDownTimer(1000,1000) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//
//                    }
//                    @Override
//                    public void onFinish() {
//                        retrieve.show();
//
//                    }
//                }.start();
            }
        }.start();
        newSavedInstantState = savedInstanceState;
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ResultReady) {
            resultReadyListener = (ResultReady) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement resulReady");
        }
        if (context instanceof ReturnHome) {
            returnHomeListener = (ReturnHome) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement returnHome");
        }
        if (context instanceof OpenDrawerInterface) {
            openDrawerListener = (OpenDrawerInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OpenDrawerInterface");
        }
        if (context instanceof CloseDtawerInterface) {
            closeDrawerListener = (CloseDtawerInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement closeDrawerInterface");
        }
    }
    View.OnClickListener retrieveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (retrieveWatcher == 0) {
                openDrawerListener.openDrawer();
                retrieveWatcher = 1;
            } else {
                if (retrieveWatcher == 1) {
                    displayo("you have not selected any recent value");
                    openDrawerListener.openDrawer();
                }
                else {
                    if(retrieveWatcher ==2){
                        resolveWhichViewHasFocus();
                closeDrawerListener.closeDrawer();

                    }
                }
            }
        }
    };

    public void resolveWhichViewHasFocus(){
        if(edione!=null) {
            if (edione.hasFocus()) {
                if(mySharedPreference!=null){
                    edione.setText(mySharedPreference.getString(InnerCalculator.MOST_URGENT,null));
                }

            } else {
                if(editwo!=null) {
                    if (editwo.hasFocus()) {
                        if(mySharedPreference!=null){
                            editwo.setText(mySharedPreference.getString(InnerCalculator.MOST_URGENT,null));
                        }
                    } else {
                        if(edithree!=null) {
                            if (edithree.hasFocus()) {
                                if(mySharedPreference!=null){
                                    edithree.setText(mySharedPreference.getString(InnerCalculator.MOST_URGENT,null));
                                }
                            } else {
                                if(edifour!=null) {
                                    if (edifour.hasFocus()) {
                                        if(mySharedPreference!=null){
                                            edifour.setText(mySharedPreference.getString(InnerCalculator.MOST_URGENT,null));
                                        }
                                    } else {
                                        if(edifive!=null) {
                                            if (edifive.hasFocus()) {
                                                if(mySharedPreference!=null){
                                                    edifive.setText(mySharedPreference.getString(InnerCalculator.MOST_URGENT,null));
                                                }
                                            } else {
                                                if(edisix!=null) {
                                                    if (edisix.hasFocus()) {
                                                        if(mySharedPreference!=null){
                                                            edisix.setText(mySharedPreference.getString(InnerCalculator.MOST_URGENT,null));
                                                        }
                                                    }else {
                                                        if(ediseven!=null) {
                                                            if (ediseven.hasFocus()) {
                                                                if (mySharedPreference != null) {
                                                                    ediseven.setText(mySharedPreference.getString(InnerCalculator.MOST_URGENT, null));
                                                                }
                                                            }else {
                                                                if(edieight!=null) {
                                                                    if (edieight.hasFocus()) {
                                                                        if(mySharedPreference!=null){
                                                                            edieight.setText(mySharedPreference.getString(InnerCalculator.MOST_URGENT,null));
                                                                        }} else {
                                                                        if(edinine!=null) {
                                                                            if (edinine.hasFocus()) {
                                                                                if(mySharedPreference!=null){
                                                                                    edinine.setText(mySharedPreference.getString(InnerCalculator.MOST_URGENT,null));
                                                                                } } else {
                                                                                if(editen!=null) {
                                                                                    if (editen.hasFocus()) {
                                                                                        if(mySharedPreference!=null){
                                                                                            editen.setText(mySharedPreference.getString(InnerCalculator.MOST_URGENT,null));
                                                                                        }  } else {
                                                                                        if(edieleven!=null) {
                                                                                            if (edieleven.hasFocus()) {
                                                                                                if(mySharedPreference!=null){
                                                                                                    edieleven.setText(mySharedPreference.getString(InnerCalculator.MOST_URGENT,null));
                                                                                                } }
                                                                                                else {
                                                                                                if(editwelve!=null) {
                                                                                                    if (editwelve.hasFocus()) {
                                                                                                        if (mySharedPreference != null) {
                                                                                                            editwelve.setText(mySharedPreference.getString(InnerCalculator.MOST_URGENT, null));
                                                                                                        }
                                                                                                    } else {
                                                                                                        calculationCases(referenceNumber);
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                        else {
                                                                                            calculationCases(referenceNumber);
                                                                                        }
                                                                                    }
                                                                                }
                                                                                else {
                                                                                    calculationCases(referenceNumber);
                                                                                }
                                                                            }
                                                                        }
                                                                        else {
                                                                            calculationCases(referenceNumber);
                                                                        }
                                                                    }
                                                                }
                                                                else {
                                                                    calculationCases(referenceNumber);
                                                                }
                                                            }
                                                        }
                                                        else {
                                                            calculationCases(referenceNumber);
                                                        }
                                                    }
                                                }
                                                else {
                                                    calculationCases(referenceNumber);
                                                }
                                            }
                                        }
                                        else {
                                            calculationCases(referenceNumber);
                                        }
                                    }
                                }
                                else {
                                    calculationCases(referenceNumber);
                                }
                            }
                        }
                        else {
                            calculationCases(referenceNumber);
                        }
                    }
                }
                else {
                    calculationCases(referenceNumber);
                }
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        resultReadyListener = null;

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
public interface OpenDrawerInterface{
        public void openDrawer();
    }
    public interface CloseDtawerInterface{
        public void closeDrawer();
    }
    public void resolveNumbers(final int number, Dialog dialog){
        if(number<layouts.length) {
            dialog.setContentView(layouts[number]);
//            dialog.setContentView(R.layout.dcresistance);
            dialog.setCancelable(true);
            edione = (EditText)dialog.findViewById(R.id.edione);
            editwo = (EditText)dialog.findViewById(R.id.editwo);
            edithree  = (EditText)dialog.findViewById(R.id.edithree);
            edifour = (EditText)dialog.findViewById(R.id.edifour);

//            edifour = (EditText)dialog.findViewById(R.id.edi);
            changenumberState();
            proceed = (FloatingActionButton)dialog.findViewById(R.id.proceed);

            proceed.setOnClickListener(generalListener);
            dialog.show();
            referenceNumber = number;
            CountDownTimer countDownTimer = new CountDownTimer(1000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }
                @Override
                public void onFinish() {
//                    proceed.show();
                }
            }.start();
        }



    }
    //    View.OnClickListener dcResistanceistener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//        }
//    };


    View.OnClickListener generalListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try {
                checkthrough();
            }
            catch (Exception e){
                displayo(e.toString());
                displayo("sorry i encountered an error solving this problem ");
            }
//            calculationCases(referenceNumber);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void calculationCases(int number){
        switch (number){
            case 0: {
                dcResistance();
//                dialog.dismiss();
                break;

            }
            case 1:{
                acResistance();
//                dialog.dismiss();
                break;
            }
            case 2:{
                ResistanceAtDifferentTemperature();
//                dialog.dismiss();
                break;
            }
            case 3:{
                ResistivityAtDifferentTemperature();
//                dialog.dismiss();
                break;
            }
            case 4:{
                ResistanceOfWoundConductorDueToSpiralling();
//                dialog.dismiss();
                break;
            }
            case 5:{
               singlePhaseCap();
//                dialog.dismiss();
                break;
            }
            case 6:{
                threePhaseCap();
//                dialog.dismiss();
                break;
            }
            case 7:{
                capacitiveReactance();
//                dialog.dismiss();
                break;
            }
            case 8:{
             singleLLCapacitanceForTwoWires();
//                dialog.dismiss();
                break;
            }
            case 9:{
               LNearthCapline();
////                dialog.dismiss();
                break;
            }
            case 10:{
                indAB();
//                dialog.dismiss();
                break;
            }
            case 11:{
              singlePind();
//                dialog.dismiss();
                break;
            }
            case 12:{
              singleThreePind();
//                dialog.dismiss();
                break;
            }
            case 13:{
             doubleThreePind();
//                dialog.dismiss();
                break;
            }
            case 14:{
            XL();
//                dialog.dismiss();
                break;
            }
            case 15:{
                equalHsag();
//                dialog.dismiss();
                break;
            }
            case 16:{
                unEqualHsag();
//                dialog.dismiss();
                break;
            }
            case 17:{
               equalClrMid();
//                dialog.dismiss();
                break;
            }
            case 18:{
               unEqualClrMid();
//                dialog.dismiss();
                break;
            }
            case 19:{
               Wt();
//                dialog.dismiss();
                break;
            }
            case 20:{
                vSag();
//                dialog.dismiss();
                break;
            }
            case 21:{
                sLineParams();
//                dialog.dismiss();
                break;
            }
            case 22:{
                mLineParams();
//                dialog.dismiss();
                break;
            }
            case 23:{
              lLineParams();
//                dialog.dismiss();
                break;
            }
            case 24:{
                singlePpow();
//                dialog.dismiss();
                break;
            }
            case 25:{
                threePpow();
//                dialog.dismiss();
                break;
            }
            case 26:{
                maxThreePpow();
//                dialog.dismiss();
                break;
            }
            case 27:{
               anyPpow();
//                dialog.dismiss();
                break;
            }
            case 28:{
                eff();
//                dialog.dismiss();
                break;
            }
            case 29:{
                SIL();
//                dialog.dismiss();
                break;
            }
            case 30:{
                CuLosses();
//                dialog.dismiss();
                break;
            }
            case 31:{
               RADandCONVLosses();
//                dialog.dismiss();
                break;
            }
            case 32:{
                CONVLossws();
//                dialog.dismiss();
                break;
            } case 33:{
             CondAmper();
//                dialog.dismiss();
                break;
            }
            case 34:{
               threePGMD();
//                dialog.dismiss();
                break;
            }
            case 35:{
               GMRn();
//                dialog.dismiss();
                break;
            }
            case 36:{
                GMRy();
//                dialog.dismiss();
                break;
            }
            case 37:{
               CSAsC();
//                dialog.dismiss();
                break;
            }

            case 38:{
               StringEff();
//                dialog.dismiss();
                break;
            }
            case 39:{
                K();
//                dialog.dismiss();
                break;
            }
            case 40:{
                VoltageDistribution();
//                dialog.dismiss();
                break;
            }
            case 41:{
                CDV();
//                dialog.dismiss();
                break;
            }
            case 42:{
                airRHO();
//                dialog.dismiss();
                break;
            }
            case 43:{
              g();
//                dialog.dismiss();
                break;
            } case 44:{
               VCV();
//                dialog.dismiss();
                break;
            } case 45:{
              coronaPowLosses();
//                dialog.dismiss();
                break;
            }



            default:
//                dialog.dismiss();
                break;
        }

    }
    public void checkthrough(){
        if(edione!=null) {
            if ((edione.getText() == null) || (edione.getText().toString().equals(""))) {
                displayo("please leave no empty field");
            } else {
                if(editwo!=null) {
                    if ((editwo.getText() == null) || (editwo.getText().toString().equals(""))) {
                        displayo("please leave no empty field");
                    } else {
                        if(edithree!=null) {
                            if ((edithree.getText() == null) || (edithree.getText().toString().equals(""))) {
                                displayo("please leave no empty field");
                            } else {
                                if(edifour!=null) {
                                    if ((edifour.getText() == null) || (edifour.getText().toString().equals(""))) {
                                        displayo("please leave no empty field");
                                    } else {
                                        if(edifive!=null) {
                                            if ((edifive.getText() == null) || (edifive.getText().toString().equals(""))) {
                                                displayo("please leave no empty field");
                                            } else {
                                                if(edisix!=null) {
                                                    if ((edisix.getText() == null) || (edisix.getText().toString().equals(""))) {
                                                        displayo("please leave no empty field");
                                                    } else {
                                                        if(ediseven!=null) {
                                                            if ((ediseven.getText() == null) || (ediseven.getText().toString().equals(""))) {
                                                                displayo("please leave no empty field");
                                                            } else {
                                                                if(edieight!=null) {
                                                                    if ((edieight.getText() == null) || (edieight.getText().toString().equals(""))) {
                                                                        displayo("please leave no empty field");
                                                                    } else {
                                                                        if(edinine!=null) {
                                                                            if ((edinine.getText() == null) || (edinine.getText().toString().equals(""))) {
                                                                                displayo("please leave no empty field");
                                                                            } else {
                                                                                if(editen!=null) {
                                                                                    if ((editen.getText() == null) || (editen.getText().toString().equals(""))) {
                                                                                        displayo("please leave no empty field");
                                                                                    } else {
                                                                                        if(edieleven!=null) {
                                                                                            if ((edieleven.getText() == null) || (edieleven.getText().toString().equals(""))) {
                                                                                                displayo("please leave no empty field");
                                                                                            } else {
                                                                                                calculationCases(referenceNumber);
                                                                                            }
                                                                                        }
                                                                                        else {
                                                                                            calculationCases(referenceNumber);
                                                                                        }
                                                                                    }
                                                                                }
                                                                                else {
                                                                                    calculationCases(referenceNumber);
                                                                                }
                                                                            }
                                                                        }
                                                                        else {
                                                                            calculationCases(referenceNumber);
                                                                        }
                                                                    }
                                                                }
                                                                else {
                                                                    calculationCases(referenceNumber);
                                                                }
                                                            }
                                                        }
                                                        else {
                                                            calculationCases(referenceNumber);
                                                        }
                                                    }
                                                }
                                                else {
                                                    calculationCases(referenceNumber);
                                                }
                                            }
                                        }
                                        else {
                                            calculationCases(referenceNumber);
                                        }
                                    }
                                }
                                else {
                                    calculationCases(referenceNumber);
                                }
                            }
                        }
                        else {
                            calculationCases(referenceNumber);
                        }
                    }
                }
                else {
                    calculationCases(referenceNumber);
                }
            }
        }
    }


    public void changenumberState(){
        EditText [] editTexts = {
                edione, editwo, edithree, edifour, edifive, edisix, ediseven, edieight, edinine, editen, edieleven
        };
        for(int i =0;i<editTexts.length;i++){
            if(editTexts[i]!=null){
                editTexts[i].setInputType(InputType.TYPE_CLASS_NUMBER);
            }
        }
//        if(edione!=null){
//            edione.setInputType(InputType.TYPE_CLASS_NUMBER);
//        }
//        if(editwo!=null){
//            editwo.setInputType(InputType.TYPE_CLASS_NUMBER);
//        }
//        if(edithree!=null){
//            edithree.setInputType(InputType.TYPE_CLASS_NUMBER);
//        }
//        if(edifour!=null){
//            edifour.setInputType(InputType.TYPE_CLASS_NUMBER);
//        }
//        if(edifive!=null){
//            edifive.setInputType(InputType.TYPE_CLASS_NUMBER);
//        }
//        if(edisix!=null){
//            edisix.setInputType(InputType.TYPE_CLASS_NUMBER);
//        }
//        if(ediseven!=null){
//            ediseven.setInputType(InputType.TYPE_CLASS_NUMBER);
//        }
//        if(edinine!=null){
//            edinine.setInputType(InputType.TYPE_CLASS_NUMBER);
//        }
//        if(editen!=null){
//            edione.setInputType(InputType.TYPE_CLASS_NUMBER);
//        }






    }
    public void dcResistance(){
        double conductorLength = Double.parseDouble(edione.getText().toString());
        double conductorArea = Double.parseDouble(editwo.getText().toString());
        double resistivity = Double.parseDouble(edithree.getText().toString());
        CalculationsForResistance calculationsForResistance = new CalculationsForResistance();
        double result = calculationsForResistance.dcResistance(resistivity, conductorLength, conductorArea);

        resultReadyListener.onResultResady(String.valueOf(result), "ohms","dcRes()");
    }
    public void acResistance(){
        double conductorLength = Double.parseDouble(edione.getText().toString());
        double conductorArea = Double.parseDouble(editwo.getText().toString());
        double resistivity = Double.parseDouble(edithree.getText().toString());
        double skinfactor = Double.parseDouble(edifour.getText().toString());

        CalculationsForResistance calculationsForResistance= new CalculationsForResistance();
        double result = calculationsForResistance.acResistance(skinfactor,resistivity,conductorLength,conductorArea);
        resultReadyListener.onResultResady((String.valueOf(result)),"ohms", "acRes()");
    }
    public void ResistanceAtDifferentTemperature(){
        double newTemperature = Double.parseDouble(edione.getText().toString());
        double initialTemperature = Double.parseDouble(editwo.getText().toString());
        double initialResistance = Double.parseDouble(edithree.getText().toString());
        double temperatureConstant = Double.parseDouble(edifour.getText().toString());
        CalculationsForResistance calculationsForResistance= new CalculationsForResistance();
        double result = calculationsForResistance.newResistivity(initialTemperature,newTemperature,temperatureConstant,initialResistance);
        resultReadyListener.onResultResady((String.valueOf(result)),"ohms","resAtT()");
    }
    public void ResistivityAtDifferentTemperature(){
        double newTemperature = Double.parseDouble(edione.getText().toString());
        double initialTemperature = Double.parseDouble(editwo.getText().toString());
        double initialResistivity = Double.parseDouble(edithree.getText().toString());
        double temperatureConstant = Double.parseDouble(edifour.getText().toString());
        CalculationsForResistance calculationsForResistance= new CalculationsForResistance();
        double result = calculationsForResistance.newResistivity(initialTemperature,newTemperature,temperatureConstant,initialResistivity);
        resultReadyListener.onResultResady((String.valueOf(result)),"ohm meter","rhoAtT()");
    }
    public void ResistanceOfWoundConductorDueToSpiralling(){
        double resistivity = Double.parseDouble(edione.getText().toString());
        double areaofConductor = Double.parseDouble(editwo.getText().toString());
        double lengthOfOneTurn = Double.parseDouble(edithree.getText().toString());
        double resistanceOfaSingleLayer = Double.parseDouble(edifour.getText().toString());
        CalculationsForResistance calculationsForResistance= new CalculationsForResistance();
        double one = calculationsForResistance.relativePitchOfWoundConductor(lengthOfOneTurn,resistanceOfaSingleLayer);
        double two = calculationsForResistance.lengthOfWoundConductor(one);
        double result = calculationsForResistance.ResistanceOfWoundConductorDueToSpiralling(resistivity,areaofConductor,two);
        resultReadyListener.onResultResady((String.valueOf(result)),"ohms","resWcond()");
    }
    public void singlePhaseCap(){
        double conductorRadius = Double.parseDouble(edione.getText().toString());
        double conductorSpacing = Double.parseDouble(editwo.getText().toString());
        double permitivity = Double.parseDouble(edithree.getText().toString());
        CalculationsForCapacitance calculationsForCapacitance = new CalculationsForCapacitance();
       double results =  calculationsForCapacitance.calculationForCapacitance(permitivity,conductorSpacing,conductorRadius);
       resultReadyListener.onResultResady(String.valueOf(results),"farad per meter","singlePcap()");
    }
    public void threePhaseCap(){
        double Gmr = Double.parseDouble(edione.getText().toString());
        double Gmd = Double.parseDouble(editwo.getText().toString());
        double permitivity = Double.parseDouble(edithree.getText().toString());
        CalculationsForCapacitance calculationsForCapacitance = new CalculationsForCapacitance();
        double results =  calculationsForCapacitance.calculationForCapacitance(permitivity,Gmd,Gmr);
        resultReadyListener.onResultResady(String.valueOf(results),"farad per meter","threePcap()");
    }

    public void capacitiveReactance(){
        double lengthOfLine = Double.parseDouble(edione.getText().toString());
        double capacitancePerUnitLength = Double.parseDouble(edithree.getText().toString());
        double frequency = Double.parseDouble(editwo.getText().toString());

        CalculationsForCapacitance calculationsForCapacitance = new CalculationsForCapacitance();
        double results =  calculationsForCapacitance.CapacitiveReactance(frequency,capacitancePerUnitLength,lengthOfLine);
        resultReadyListener.onResultResady(String.valueOf(results),"ohms","Xc()");
    }

    public void singleLLCapacitanceForTwoWires(){
        double conductorRadius = Double.parseDouble(edione.getText().toString());
        double conductorSpacing = Double.parseDouble(editwo.getText().toString());
        double permitivity = Double.parseDouble(edithree.getText().toString());
        CalculationsForCapacitance calculationsForCapacitance = new CalculationsForCapacitance();
        double preresults =  calculationsForCapacitance.calculationForCapacitance(permitivity,conductorSpacing,conductorRadius);
        double results = calculationsForCapacitance.calculationForLineToLineCapacitanceForTwoWires(preresults);
        resultReadyListener.onResultResady(String.valueOf(results),"farad per meter","singleLLcap()");
    }
    public void LNearthCapline(){
        double ABprime = Double.parseDouble(edione.getText().toString());
        double ACprime = Double.parseDouble(editwo.getText().toString());
        double BCprime = Double.parseDouble(edithree.getText().toString());
        double A = Double.parseDouble(edifour.getText().toString());
        double B = Double.parseDouble(edifive.getText().toString());
        double C = Double.parseDouble(edisix.getText().toString());
        double Conductorradius = Double.parseDouble(ediseven.getText().toString());
        double ConductorSpacing = Double.parseDouble(edieight.getText().toString());
        double  permitivity = Double.parseDouble(edinine.getText().toString());
        CalculationsForCapacitance calculationsForCapacitance = new CalculationsForCapacitance();
        double results = calculationsForCapacitance.calculationForLineToNeutralCapacitanceInThePresenceOfEarth(ABprime,BCprime,ACprime,Conductorradius,A,B,C,ConductorSpacing,permitivity);
        resultReadyListener.onResultResady(String.valueOf(results),"farad per meter","LNearthCapline()");
    }

    public void indAB(){
        double distanceA = Double.parseDouble(edione.getText().toString());
        double distanceB = Double.parseDouble(editwo.getText().toString());
//        double permitivity = Double.parseDouble(edithree.getText().toString());
        CalculationsForInductance calculationsForInductance = new CalculationsForInductance();
        double results =  calculationsForInductance.inductanceBetweenTwoPointsOutsideAconductor(distanceA,distanceB);
        resultReadyListener.onResultResady(String.valueOf(results),"henry per meter","indAB()");
    }

    public void singlePind(){
        double conductorRadius = Double.parseDouble(edione.getText().toString());
        double conductorSpacing = Double.parseDouble(editwo.getText().toString());
        CalculationsForInductance calculationsForInductance = new CalculationsForInductance();
        double results = calculationsForInductance.inductanceOfaSinglePhaseLine(conductorRadius,conductorSpacing);
        resultReadyListener.onResultResady(String.valueOf(results),"henry per meter","singlePind()");
    }

    public void singleThreePind(){
        double GMR = Double.parseDouble(edione.getText().toString());
        double GMD = Double.parseDouble(editwo.getText().toString());
        CalculationsForInductance calculationsForInductance = new CalculationsForInductance();
        double results = calculationsForInductance.inductanceForThreePhaseLine(GMD,GMR);
        resultReadyListener.onResultResady(String.valueOf(results),"henry per meter","singleThrreePind()");
    }
    public void doubleThreePind(){
        double GMR = Double.parseDouble(edione.getText().toString());
        double GMD = Double.parseDouble(editwo.getText().toString());
        CalculationsForInductance calculationsForInductance = new CalculationsForInductance();
        double results = calculationsForInductance.inductanceForThreePhaseLine(GMD,GMR);
        resultReadyListener.onResultResady(String.valueOf(results),"henry per meter","singleThrreePind()");
    }

    public void XL(){
        double lengthOfLine = Double.parseDouble(edione.getText().toString());
        double frequency = Double.parseDouble(editwo.getText().toString());
        double InductancePerUnitLength = Double.parseDouble(edithree.getText().toString());
       CalculationsForInductance calculationsForInductance = new CalculationsForInductance();
        double results =  calculationsForInductance.inductiveReactance(InductancePerUnitLength,frequency,lengthOfLine);
        resultReadyListener.onResultResady(String.valueOf(results),"ohms","XL()");
    }
    public void equalHsag() {
        double densityOfConductor = Double.parseDouble(edione.getText().toString());
        double crossectionOfConductor = Double.parseDouble(editwo.getText().toString());
        double UltimateTensileStrength = Double.parseDouble(edithree.getText().toString());
        double span = Double.parseDouble(edifour.getText().toString());
        double  safetyFactor= Double.parseDouble(edifive.getText().toString());
        double windPressure = Double.parseDouble(edisix.getText().toString());
        double densityOfIce = Double.parseDouble(ediseven.getText().toString());
        double thicknessOfIce = Double.parseDouble(edieight.getText().toString());
//        double  permitivity = Double.parseDouble(edinine.getText().toString());
        CalculationsForSag  calculationsForSag = new CalculationsForSag();
        double results = calculationsForSag.even_Pole_Height_complex_ice_and_wind_SagB(densityOfConductor,crossectionOfConductor,span,UltimateTensileStrength,safetyFactor,densityOfIce,thicknessOfIce,windPressure);
        resultReadyListener.onResultResady(String.valueOf(results),"meters","equalHsag()");
    }
    public void unEqualHsag() {
        double densityOfConductor = Double.parseDouble(edione.getText().toString());
        double crossectionOfConductor = Double.parseDouble(editwo.getText().toString());
        double UltimateTensileStrength = Double.parseDouble(edithree.getText().toString());
        double  safetyFactor= Double.parseDouble(edifour.getText().toString());
        double windPressure = Double.parseDouble(edifive.getText().toString());
        double densityOfIce = Double.parseDouble(edisix.getText().toString());
        double thicknessOfIce = Double.parseDouble(ediseven.getText().toString());
        double  span= Double.parseDouble(edieight.getText().toString());
        double heightOfA = Double.parseDouble(edinine.getText().toString());
        double heightOfB = Double.parseDouble(editen.getText().toString());
        double referenceFindYou= Double.parseDouble(ediseven.getText().toString());
//        double  permitivity = Double.parseDouble(edinine.getText().toString());
        CalculationsForSag  calculationsForSag = new CalculationsForSag();
        double results = calculationsForSag.uneven_Pole_Height_complex_ice_and_wind_SagB(densityOfConductor,crossectionOfConductor,span,UltimateTensileStrength,safetyFactor,densityOfIce,thicknessOfIce,windPressure,heightOfA,heightOfB,referenceFindYou);
        resultReadyListener.onResultResady(String.valueOf(results),"meters","unEqualHsag()");
    }

    public void equalClrMid(){
        double heightOfPole = Double.parseDouble(edione.getText().toString());
        double Sag = Double.parseDouble(editwo.getText().toString());
//        double  permitivity = Double.parseDouble(edinine.getText().toString());
        CalculationsForSag  calculationsForSag = new CalculationsForSag();
        double results = calculationsForSag.clearanceMidWayEqualHeightPole(Sag,heightOfPole);
        resultReadyListener.onResultResady(String.valueOf(results),"meters","equalClrMid()");
    }

    public void unEqualClrMid(){
        double distanceFromCenter = Double.parseDouble(edione.getText().toString());
        double Sag = Double.parseDouble(editwo.getText().toString());
        double totalWeightPerUnitLength = Double.parseDouble(edithree.getText().toString());
        double tensileStrength = Double.parseDouble(edifour.getText().toString());
        double  heightOfreferencePole= Double.parseDouble(edifive.getText().toString());
        double span = Double.parseDouble(edisix.getText().toString());
//        double densityOfIce = Double.parseDouble(ediseven.getText().toString());
//        double thicknessOfIce = Double.parseDouble(edieight.getText().toString());
        CalculationsForSag  calculationsForSag = new CalculationsForSag();
        double results = calculationsForSag.clearanceOfSagMidWayUnEqualHeightPoles(Sag,distanceFromCenter,heightOfreferencePole,tensileStrength,totalWeightPerUnitLength,span);
        resultReadyListener.onResultResady(String.valueOf(results),"meters","unEqualClrMid()");
    }

    public void Wt(){
        double densityOfCond = Double.parseDouble(edione.getText().toString());
        double CSAofCond = Double.parseDouble(editwo.getText().toString());
        double windPressure = Double.parseDouble(edithree.getText().toString());
        double  thicknessOfIce= Double.parseDouble(edifour.getText().toString());
        double densityOfIce = Double.parseDouble(edifive.getText().toString());
//        double densityOfIce = Double.parseDouble(ediseven.getText().toString());
//        double thicknessOfIce = Double.parseDouble(edieight.getText().toString());
        CalculationsForSag  calculationsForSag = new CalculationsForSag();
        double results = calculationsForSag.findTotalWeightPerUnitLengthConductor_Ice_and_wind(densityOfCond,CSAofCond,windPressure,densityOfIce,thicknessOfIce)
;resultReadyListener.onResultResady(String.valueOf(results),"meters","Wt()");
    }

    public void vSag(){
        double totalWeight = Double.parseDouble(edione.getText().toString());
        double WeightOfWind = Double.parseDouble(editwo.getText().toString());
        double Sag = Double.parseDouble(edithree.getText().toString());
        CalculationsForSag  calculationsForSag = new CalculationsForSag();
        double results = calculationsForSag.verticalSag(totalWeight,WeightOfWind,Sag);
        resultReadyListener.onResultResady(String.valueOf(results),"meters","vSag()");
    }

    public void sLineParams(){
        double res = Double.parseDouble(edione.getText().toString());
        double ind = Double.parseDouble(editwo.getText().toString());
        double lengthOfline = Double.parseDouble(edithree.getText().toString());
        double  frequency = Double.parseDouble(edifour.getText().toString());
//        double densityOfIce = Double.parseDouble(edisix.getText().toString());
        LineNumbers lineNumbers = new LineNumbers();
       ArrayList<Double> results = lineNumbers.shortLineNimbers(res,ind,lengthOfline,frequency);
        resultReadyListener.onResultResady(results.toString(),"[A,B,C,D]","sLineParams()");
    }

    public void mLineParams(){
        double cap = Double.parseDouble(editwo.getText().toString());
        double ind = Double.parseDouble(edione.getText().toString());
        double res = Double.parseDouble(edithree.getText().toString());
        double lengthOfline = Double.parseDouble(edifour.getText().toString());
        double  frequency = Double.parseDouble(edifive.getText().toString());
//        double densityOfIce = Double.parseDouble(edisix.getText().toString());
        LineNumbers lineNumbers = new LineNumbers();
        double xl= lineNumbers.inductiveReactance(ind,frequency,lengthOfline);
        double imped = lineNumbers.impedance(res,xl);
        double y = lineNumbers.CalculationForShuntAmittance(frequency,cap,lengthOfline);
        ArrayList<Double> results = lineNumbers.MediumLineNumbers(imped,y);
        resultReadyListener.onResultResady(results.toString(),"[A,B,C,D]","mLineParams()");
    }
    public void lLineParams(){
        double cap = Double.parseDouble(editwo.getText().toString());
        double ind = Double.parseDouble(edione.getText().toString());
        double res = Double.parseDouble(edithree.getText().toString());
        double lengthOfline = Double.parseDouble(edifour.getText().toString());
        double  frequency = Double.parseDouble(edifive.getText().toString());
//        double densityOfIce = Double.parseDouble(edisix.getText().toString());
        LineNumbers lineNumbers = new LineNumbers();
        double xl= lineNumbers.inductiveReactance(ind,frequency,lengthOfline);
        double imped = lineNumbers.impedance(res,xl);
        double y = lineNumbers.CalculationForShuntAmittance(frequency,cap,lengthOfline);
        double yfactor = lineNumbers.yFactor(imped,y);
        double yprime = lineNumbers.yPrime(yfactor,lengthOfline,y);
        double zprime = lineNumbers.zPrime(yfactor,lengthOfline,imped);
        ArrayList<Double> results = lineNumbers.longLineNumbers(zprime,yprime);
        resultReadyListener.onResultResady(results.toString(),"[A,B,C,D]","lLineParams()");
    }

    public void singlePpow(){
        double current = Double.parseDouble(edione.getText().toString());
        double voltage = Double.parseDouble(editwo.getText().toString());
        double powerfactor = Double.parseDouble(edithree.getText().toString());
//        double  nPhases= Double.parseDouble(edifour.getText().toString());
//        double densityOfIce = Double.parseDouble(edifive.getText().toString());
        PowerCalculations powerCalculations = new PowerCalculations();
        double results = powerCalculations.singlePhasePow(current,voltage,powerfactor);
        resultReadyListener.onResultResady(String.valueOf(results),"watts","singlePpow()");
    }

    public void threePpow(){
        double current = Double.parseDouble(edione.getText().toString());
        double voltage = Double.parseDouble(editwo.getText().toString());
        double powerfactor = Double.parseDouble(edithree.getText().toString());
//        double  nPhases= Double.parseDouble(edifour.getText().toString());
//        double densityOfIce = Double.parseDouble(edifive.getText().toString());
        PowerCalculations powerCalculations = new PowerCalculations();
        double results = powerCalculations.threePPower(current,voltage,powerfactor);
        resultReadyListener.onResultResady(String.valueOf(results),"watts","threePpow()");
    }

    public void maxThreePpow(){
        double sendingendVoltage = Double.parseDouble(edione.getText().toString());
        double receiving = Double.parseDouble(editwo.getText().toString());
        double inductive = Double.parseDouble(edithree.getText().toString());
        PowerCalculations powerCalculations = new PowerCalculations();
        double results = powerCalculations.maximumPowerOfAtransmissionLine(sendingendVoltage,receiving,inductive);
        resultReadyListener.onResultResady(String.valueOf(results),"watts","maxthreePpow()");
    }
    public void anyPpow(){
        double current = Double.parseDouble(edione.getText().toString());
        double voltage = Double.parseDouble(editwo.getText().toString());
        double powerfactor = Double.parseDouble(edithree.getText().toString());
        double  nPhases= Double.parseDouble(edifour.getText().toString());
//        double densityOfIce = Double.parseDouble(edifive.getText().toString());
        PowerCalculations powerCalculations = new PowerCalculations();
        double results = powerCalculations.anyPhasePower(current,voltage,powerfactor,nPhases);
        resultReadyListener.onResultResady(String.valueOf(results),"watts","anyPpow()");
    }
     public void eff(){
         double inputPower = Double.parseDouble(edione.getText().toString());
         double OutPutPower = Double.parseDouble(editwo.getText().toString());
//         double inductive = Double.parseDouble(edithree.getText().toString());
         PowerCalculations powerCalculations = new PowerCalculations();
         double results = powerCalculations.efficiency(inputPower,OutPutPower);
         resultReadyListener.onResultResady(String.valueOf(results),"%","eff()");
     }


     public void SIL(){
         double inductance = Double.parseDouble(edione.getText().toString());
         double capacitance = Double.parseDouble(editwo.getText().toString());
         double lineVoltage = Double.parseDouble(edithree.getText().toString());
         PowerCalculations powerCalculations = new PowerCalculations();
         double results = powerCalculations.surgeImpedanceLoading(lineVoltage,inductance,capacitance);
         resultReadyListener.onResultResady(String.valueOf(results),"","SIL()");
     }
     public void CuLosses(){
         double resistance = Double.parseDouble(edione.getText().toString());
         double current = Double.parseDouble(editwo.getText().toString());
         double numberOfPhases = Double.parseDouble(edithree.getText().toString());

         Losses losses = new Losses();
         double result = losses.copperLossesFromCurrent(current,resistance,numberOfPhases);
         resultReadyListener.onResultResady(String.valueOf(result),"watts","cuLosses()");
     }
     public void RADandCONVLosses() {
         double surfaceAreaOfConductor = Double.parseDouble(edione.getText().toString());
         double covLosses = Double.parseDouble(editwo.getText().toString());
         double radLosses = Double.parseDouble(edithree.getText().toString());

         Losses losses = new Losses();
         double result = losses.copperLossesFromRandC(covLosses,radLosses,surfaceAreaOfConductor);
         resultReadyListener.onResultResady(String.valueOf(result), "watts", "RADandCONVLosses() ");
     }
     public void CONVLossws(){
         double airTR = Double.parseDouble(edione.getText().toString());
         double atp = Double.parseDouble(editwo.getText().toString());
         double  airT = Double.parseDouble(edithree.getText().toString());

         double windVelocity= Double.parseDouble(edifour.getText().toString());
         double  conductorDiameter= Double.parseDouble(edifive.getText().toString());
         Losses losses = new Losses();
         double result = losses.lossesFromRadiation(atp,windVelocity,airT,conductorDiameter,airTR);
         resultReadyListener.onResultResady(String.valueOf(result), "watts", "CONVLosses() ");
     }



     public void CondAmper(){
         double conv= Double.parseDouble(edione.getText().toString());
         double rad = Double.parseDouble(editwo.getText().toString());
         double  res = Double.parseDouble(edithree.getText().toString());
         double  surfaceAreaOfConductor= Double.parseDouble(edifour.getText().toString());

         Losses losses = new Losses();
         double results = losses.conductorAmpercity(conv,rad,surfaceAreaOfConductor,res);
         resultReadyListener.onResultResady(String.valueOf(results), "ampere", "condAmper() ");
     }
       public void threePGMD(){
           double num12= Double.parseDouble(edione.getText().toString());
           double num23 = Double.parseDouble(editwo.getText().toString());
           double  num13 = Double.parseDouble(edithree.getText().toString());

           CalculationsForInductance calculationsForInductance = new CalculationsForInductance();
           double results = calculationsForInductance.CalculationsForThreePhaseGmd(num12,num13,num23);
           resultReadyListener.onResultResady(String.valueOf(results), "meters", "threePGMD() ");
       }
       public void GMRn() {
           double conductorRadius= Double.parseDouble(edione.getText().toString());

           CalculationsForInductance calculationsForInductance = new CalculationsForInductance();
           double results = calculationsForInductance.CalculationForGmrNeglectingBundling(conductorRadius);
           resultReadyListener.onResultResady(String.valueOf(results), "meters", "GMRn() ");
       }
       public void GMRy(){
           double numOfBundling= Double.parseDouble(edione.getText().toString());
           double DiameterOfCond = Double.parseDouble(editwo.getText().toString());
           double  GmrOfOneStrand = Double.parseDouble(edithree.getText().toString());

           CalculationsForInductance calculationsForInductance = new CalculationsForInductance();
           double results = calculationsForInductance.GmrUptoFourConductorsPerBundle(numOfBundling,DiameterOfCond,GmrOfOneStrand);
           resultReadyListener.onResultResady(String.valueOf(results), "meters", "GMRy() ");

       }
       public void CSAsC(){
           double stansardSC = Double.parseDouble(edione.getText().toString());
           double perTime = Double.parseDouble(editwo.getText().toString());
           double tempRise = Double.parseDouble(edithree.getText().toString());
           double  specificHC= Double.parseDouble(edifour.getText().toString());
           double specificResperA = Double.parseDouble(edifive.getText().toString());
        double specificW= Double.parseDouble(edisix.getText().toString());

        CalculationsForDimensions calculationsForDimensions = new CalculationsForDimensions();
        double k = calculationsForDimensions.constantK(tempRise,specificW,specificHC,specificResperA);
        double results = calculationsForDimensions.calculationForCSAusingShortCircuit(stansardSC,perTime,k);
        resultReadyListener.onResultResady(String.valueOf(results), "meter square", "CSAsC()");
       }



       public void StringEff(){
           double vS = Double.parseDouble(edione.getText().toString());
           double vClosest = Double.parseDouble(editwo.getText().toString());
           int numDiscs = Integer.parseInt(edithree.getText().toString());
           CalculationsForStringing calculationsForStringing = new CalculationsForStringing();
           double results = calculationsForStringing.stringingEfficiency(vS,vClosest,numDiscs);
           resultReadyListener.onResultResady(String.valueOf(results), "%", "stringEFF()");
       }

       public void K(){
           double shuntC = Double.parseDouble(edione.getText().toString());
           double selfC = Double.parseDouble(editwo.getText().toString());
//           int numDiscs = Integer.parseInt(edithree.getText().toString());
           CalculationsForStringing calculationsForStringing = new CalculationsForStringing();
           double results = calculationsForStringing.stringKvalue(shuntC,selfC);
           resultReadyListener.onResultResady(String.valueOf(results), "", "K()");
       }

       public void VoltageDistribution(){

           int numDiscs = Integer.parseInt(edione.getText().toString());
           double phaseToNeutralvolt = Double.parseDouble(editwo.getText().toString());
           double k = Double.parseDouble(edithree.getText().toString());

           if(numDiscs<=4&&numDiscs>0) {
               CalculationsForStringing calculationsForStringing = new CalculationsForStringing();
               Double[] results = calculationsForStringing.voltageDistributionAcrossDiscsUptoFour(numDiscs, phaseToNeutralvolt, k);

               if(numDiscs ==1) {
                   String resulte = String.valueOf(results[0]);

                   resultReadyListener.onResultResady(resulte, "[v1]", "voltDistribution()");
               }

               else {
                   if(numDiscs ==2){
                       String resulte = String.valueOf(results[0])+" "+String.valueOf(results[1]);
                       resultReadyListener.onResultResady(resulte, "[v1, v2]", "voltDistribution()");

                   }
                   else {
                       if(numDiscs == 3){
                           String resulte = String.valueOf(results[0])+" "+String.valueOf(results[1])+" "+ String.valueOf(results[2]);
                           resultReadyListener.onResultResady(resulte, "[v1, v2, V3]", "voltDistribution()");
                       }
                       else {
                           if(numDiscs == 4){
                               String resulte = String.valueOf(results[0])+" "+String.valueOf(results[1])+" "+ String.valueOf(results[2])+" "+String.valueOf(results[3]);
                               resultReadyListener.onResultResady(resulte, "[v1, v2, V3, v4]", "voltDistribution()");
                           }
                       }
                   }
               }
           }else {
               displayo("number of discs should not be more than four or less than  or equal to zero");
           }
       }




       public void CDV(){
           double irregularityFactor = Double.parseDouble(edione.getText().toString());
           double airRho = Double.parseDouble(editwo.getText().toString());
           double conductorRadius = Double.parseDouble(edithree.getText().toString());
           double spacingBtwCond = Double.parseDouble(edifour.getText().toString());

           Corona corona = new Corona();

           double results = corona.CriticalDistruptiveVoltage(irregularityFactor,airRho,conductorRadius,spacingBtwCond);
           resultReadyListener.onResultResady(String.valueOf(results), "volts per phase", "CDV()");
       }

       public void airRHO(){
           double atmosphericPressureinM = Double.parseDouble(edione.getText().toString());
           double TempinDeg = Double.parseDouble(editwo.getText().toString());
//           int conductorRadius = Integer.parseInt(edithree.getText().toString());
//           double spacingBtwCond = Double.parseDouble(edifour.getText().toString());

           Corona corona = new Corona();

           double results = corona.airdensityFactor(atmosphericPressureinM,TempinDeg);
           resultReadyListener.onResultResady(String.valueOf(results), "", "AirRho()");
       }

       public void g(){
           double phaseToNeutralVolt = Double.parseDouble(edione.getText().toString());
           double condRadius = Double.parseDouble(editwo.getText().toString());
           double spacingBtwCond = Double.parseDouble(edithree.getText().toString());
//           double spacingBtwCond = Double.parseDouble(edifour.getText().toString());

           Corona corona = new Corona();

           double results = corona.PotentialGradient(phaseToNeutralVolt,condRadius,spacingBtwCond);
           resultReadyListener.onResultResady(String.valueOf(results), "volts per meter", "g()");
       }

       public void VCV(){
           double irregularityFactor = Double.parseDouble(edione.getText().toString());
           double airRho = Double.parseDouble(editwo.getText().toString());
           double conductorRadius = Double.parseDouble(edithree.getText().toString());
           double spacingBtwCond = Double.parseDouble(edifour.getText().toString());

           Corona corona = new Corona();

           double results = corona.visualCriticalVoltage(irregularityFactor,airRho,conductorRadius,spacingBtwCond);
           resultReadyListener.onResultResady(String.valueOf(results), "volts per phase", "VCV()");
       }

       public void coronaPowLosses(){
           double airDensity = Double.parseDouble(edione.getText().toString());
           double conductorRadius = Double.parseDouble(editwo.getText().toString());
           double spacingBtwCond = Double.parseDouble(edithree.getText().toString());
           double frequency = Double.parseDouble(edifour.getText().toString());
           double phaseToNeutralVolt = Double.parseDouble(edifive.getText().toString());
           double criticalDvolt = Double.parseDouble(edisix.getText().toString());

           Corona corona = new Corona();

           double results = corona.powerLossesDueToCorona(airDensity,conductorRadius,spacingBtwCond,frequency,phaseToNeutralVolt,criticalDvolt);
           resultReadyListener.onResultResady(String.valueOf(results), "kilowatts per Km", "coronaPowLosses()");
       }

    public interface ResultReady{
        public void onResultResady(String result, String unit, String quanntityName);
    }
    public interface ReturnHome{
        public void onGoHome();
    }
    public void displayo(String ji) {
        Context context = getActivity();
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
