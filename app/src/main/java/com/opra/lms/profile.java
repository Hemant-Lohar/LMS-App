package com.opra.lms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profile<addOnCompleteListener> extends Fragment {

    FirebaseAuth fAuth = FirebaseAuth.getInstance();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profile.
     */
    // TODO: Rename and change types and number of parameters
    public static profile newInstance(String param1, String param2) {
        profile fragment = new profile();
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



        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        Button logout=view.findViewById(R.id.btnlogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.getInstance().signOut();
                Intent i = new Intent(getActivity(),signin.class);
                startActivity(i);
                getActivity().finish();
            }
        });
//        Following code is for updating user details in profile fragment.It works, don't touch it.

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("User").document(fAuth.getCurrentUser().getEmail());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot= task.getResult();
                    if(!documentSnapshot.exists()){
                        Log.d("Document","No data");
                    }else{
                        String fname= (String) documentSnapshot.get("first_name");
                        String lname= (String) documentSnapshot.get("last_name");
                        String id=fAuth.getCurrentUser().getEmail();
                        String reg="";
                        String comp="";
                        if(documentSnapshot.get("course_reg")==null || documentSnapshot.get("course_com")==null){
                            if(documentSnapshot.get("course_reg")==null){
                                reg="0";
                            }
                            if(documentSnapshot.get("course_com")==null){
                                comp="0";
                            }
                        }
                        else
                        {
                            reg =documentSnapshot.get("course_reg").toString();
                            comp =documentSnapshot.get("course_com").toString();
                        }
                        Log.d("Document","Reg:"+reg.toString());
                        Log.d("Document","comp:"+comp.toString());
                        TextView textfname = view.findViewById(R.id.textView11);
                        TextView textlname = view.findViewById(R.id.textView12);
                        TextView textid = view.findViewById(R.id.textView10);
                        TextView textreg =view.findViewById(R.id.textView19);
                        TextView textcomp = view.findViewById(R.id.textView20);
                        textfname.setText(fname);
                        textlname.setText(lname);
                        textid.setText(id);
                        textreg.setText(reg);
                        textcomp.setText(comp);



                    }
                }
                else{
                    Log.d("Document","No data");
                }
            }
        });


        return view;

    }


}