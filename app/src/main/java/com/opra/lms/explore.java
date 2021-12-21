package com.opra.lms;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link explore#newInstance} factory method to
 * create an instance of this fragment.
 */
public class explore extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public explore() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment explore.
     */
    // TODO: Rename and change types and number of parameters
    public static explore newInstance(String param1, String param2) {
        explore fragment = new explore();
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
        View view =inflater.inflate(R.layout.fragment_explore, container, false);
        Button enroll1=(Button)view.findViewById(R.id.button22);
        Button enroll2=(Button)view.findViewById(R.id.button23);
        Button enroll3=(Button)view.findViewById(R.id.button24);
        Button enroll4=(Button)view.findViewById(R.id.button25);
        Button enroll5=(Button)view.findViewById(R.id.button26);
        Button enroll6=(Button)view.findViewById(R.id.button27);
        Button enroll7=(Button)view.findViewById(R.id.button28);
        Button enroll8=(Button)view.findViewById(R.id.button29);
        Button enroll9=(Button)view.findViewById(R.id.button30);




        enroll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Registered !",Toast.LENGTH_SHORT).show();
            }
        });
        enroll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Registered !",Toast.LENGTH_SHORT).show();
            }
        });
        enroll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Registered !",Toast.LENGTH_SHORT).show();
            }
        });
        enroll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Registered !",Toast.LENGTH_SHORT).show();
            }
        });
        enroll5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Registered !",Toast.LENGTH_SHORT).show();
            }
        });
        enroll6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Registered !",Toast.LENGTH_SHORT).show();
            }
        });
        enroll7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Registered !",Toast.LENGTH_SHORT).show();
            }
        });
        enroll8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Registered !",Toast.LENGTH_SHORT).show();
            }
        });
        enroll9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Registered !",Toast.LENGTH_SHORT).show();
            }
        });






        return view;
    }
}