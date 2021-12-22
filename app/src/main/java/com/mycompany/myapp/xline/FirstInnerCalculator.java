package com.mycompany.myapp.xline;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstInnerCalculator.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstInnerCalculator#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstInnerCalculator extends Fragment implements InnerCalculatorAdapter.OnGridClickedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView innerRecyclerview;
    FloatingActionButton proceed;
    InnerCalculatorAdapter innerCalculatorAdapter;
    List<workingCalculation> albumList;
    switchFragments readyToSwitchListener;
    static int referenceNumber;
 ResultReady resultReadyListener;
    EditText edione, editwo, edithree, edifour, edifive, edisix, ediseven, edieight, edinine, editen, edieleven;
    RelativeLayout relone, reltwo, relthree, relfour, relfive, relsix, relseven, releight, relnine, relten, releleven;
    private OnFragmentInteractionListener mListener;
  Dialog dialog;
  int[] layouts = {R.layout.dcresistance,R.layout.ac_resistance, R.layout.resistivity_at_different_temperature, R.layout.resistance_at_different_temperatures,
          R.layout.resistance_of_wound_conductor
  };
    public FirstInnerCalculator() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstInnerCalculator.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstInnerCalculator newInstance(String param1, String param2) {
        FirstInnerCalculator fragment = new FirstInnerCalculator();
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_first_inner_calculator, container, false);
        innerRecyclerview = (RecyclerView)view.findViewById(R.id.innerrecycler_view);

        albumList = new ArrayList<>();
        if(calcProcessor.getInstance().getWorkingCalculations()!=null){
            albumList = calcProcessor.getInstance().getWorkingCalculations();
        }
        innerCalculatorAdapter = new InnerCalculatorAdapter(getActivity(), getContext(), albumList);
        innerCalculatorAdapter.setOnItemClickedListener(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        innerRecyclerview.setLayoutManager(mLayoutManager);
        innerRecyclerview.setItemAnimator(new DefaultItemAnimator());
        innerRecyclerview.setAdapter(innerCalculatorAdapter);
        dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void OnGridClicked(int position, int number) {
//      resolveNumbers(number,dialog);
        calcProcessor.getInstance().setNumber(number);
        calcProcessor.getInstance().setPosition(position);
        readyToSwitchListener.OnReadytoSwitch();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//        if(context instanceof ResultReady){
//            resultReadyListener = (ResultReady)context;
//        }
//        else {
//            throw new RuntimeException((context.toString() + "must implement Result Ready"));
//        }

        if(context instanceof  switchFragments){
            readyToSwitchListener = (switchFragments)context;
        }
        else {
            throw new RuntimeException((context.toString() + "must implement switch Fragments"));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        readyToSwitchListener = null;
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

    public void resolveNumbers(final int number, Dialog dialog){
//        if(number<layouts.length) {
//            dialog.setContentView(layouts[number]);
////            dialog.setContentView(R.layout.dcresistance);
//            dialog.setCancelable(true);
//            edione = (EditText)dialog.findViewById(R.id.edione);
//            editwo = (EditText)dialog.findViewById(R.id.editwo);
//            edithree  = (EditText)dialog.findViewById(R.id.edithree);
//            edifour = (EditText)dialog.findViewById(R.id.edifour);
//            proceed = (FloatingActionButton)dialog.findViewById(R.id.proceed);
//            proceed.setOnClickListener(generalListener);
//            dialog.show();
//            referenceNumber = number;
//            CountDownTimer countDownTimer = new CountDownTimer(1000,1000) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//
//                }
//                @Override
//                public void onFinish() {
//                 proceed.show();
//                }
//            }.start();
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

           checkthrough();
//            calculationCases(referenceNumber);
        }
    };
    public void calculationCases(int number){
        switch (number){
            case 0: {
              dcResistance();
                dialog.dismiss();
              break;

            }
            case 1:{
                acResistance();
                dialog.dismiss();
                break;
            }
            case 2:{
                ResistanceAtDifferentTemperature();
                dialog.dismiss();
                break;
            }
            case 3:{
                ResistivityAtDifferentTemperature();
                dialog.dismiss();
                break;
            }
            case 4:{
                ResistanceOfWoundConductorDueToSpiralling();
                dialog.dismiss();
                break;
            }
        default:     dialog.dismiss();
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
    public interface ResultReady{
        public void onResultResady(String result, String unit, String quanntityName);
    }
    public interface switchFragments{
        public void OnReadytoSwitch();
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
