package com.mycompany.myapp.xline;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.jkb.fragment.rigger.annotation.Puppet;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Design.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Design#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Design extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
     static  RelativeLayout registerdetails;
    static EditText ownersNameField;
     static EditText projectNameField;
    static FloatingActionButton newDesign;
    public static int showRelWatcher;
     public static String OWNERS_NAME = "ownersname";
    static RotateAnimation rotationAnimation, returnanimation;
    static int jWatcher = 0;
     SharedPreferences mySharedPreferences;
     SharedPreferences.Editor myEditor;
   static Dialog isThatYou;
   static String ownerName;

    private OnFragmentInteractionListener mListener;

    public Design() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Design.
     */
    // TODO: Rename and change types and number of parameters
    public static Design newInstance(String param1, String param2) {
        Design fragment = new Design();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_design, container, false);
//        ownersNameField = (EditText)view.findViewById(R.id.ownersnamefield);
//        projectNameField = (EditText)view.findViewById(R.id.ownersnamefield);
//        rotationAnimation = new RotateAnimation(0,360);
//        rotationAnimation.setDuration(1200);
        mySharedPreferences =  getActivity().getSharedPreferences("savedname", Context.MODE_PRIVATE);
//        registerdetails = (RelativeLayout) view.findViewById(R.id.reldesignregister);
//        returnanimation = new RotateAnimation(360,0);
//        newDesign = (FloatingActionButton)view.findViewById(R.id.newdesign);
//        newDesign.setOnClickListener(newDesignListener);
        isThatYou = new Dialog(getActivity());
        isThatYou.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    View.OnClickListener newDesignListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (jWatcher == 1) {
                if (!projectNameField.getText().toString().equals("") && projectNameField.getText() != null) {
                    myEditor = mySharedPreferences.edit();
                    myEditor.putString(projectNameField.getText().toString(), "projectname");
                    myEditor.apply();
                } else {
                    displayo("project name is empty");
                }
            } else {
                if (!ownersNameField.getText().toString().equals("") && ownersNameField.getText() != null) {

                    if (!projectNameField.getText().toString().equals("") && projectNameField.getText() != null) {
                        myEditor = mySharedPreferences.edit();
                        myEditor.putString(projectNameField.getText().toString(), "projectname");
                        myEditor.putString(ownersNameField.getText().toString(), OWNERS_NAME);
                        myEditor.apply();
                    } else {
                        displayo("project name is empty");
                    }

                } else {
                    displayo("please tell us your name");
                }
            }
        }
    }   ;
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
