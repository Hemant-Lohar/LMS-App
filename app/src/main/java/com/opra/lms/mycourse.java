package com.opra.lms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link mycourse#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mycourse extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //following 3 lines by 007. don't touch it
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    LinearLayout layout;
    int i;

    public mycourse() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mycourse.
     */
    // TODO: Rename and change types and number of parameters
    public static mycourse newInstance(String param1, String param2) {
        mycourse fragment = new mycourse();
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
        View view = inflater.inflate(R.layout.fragment_mycourse, container, false);
        layout=view.findViewById(R.id.layout_mycourse);
        FirebaseFirestore.getInstance().collection("course")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ArrayList<String>user =new ArrayList<>();
                                Log.d("Document", document.getId());
                                user = (ArrayList<String>) document.get("user");
                                String current_user=fAuth.getCurrentUser().getEmail().toString();
                                Log.d("Document", current_user);
                                Log.d("Document", user.toString());
                                if(user.contains(current_user)){
                                    final View addedview = getLayoutInflater().inflate(R.layout.course_my, null, false);
                                    TextView name=addedview.findViewById(R.id.c_name);
                                    TextView num=addedview.findViewById(R.id.num);
                                    Button button=(Button)addedview.findViewById(R.id.btn_enroll);
                                    name.setText(document.getId());
                                    num.setText(document.get("num_session").toString()+"hrs");
                                    button.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent x=new Intent(getActivity(), sessionlist.class);
                                            x.putExtra("COURSE",document.getId());
                                            startActivity(x);
                                        }
                                    });
                                    layout.addView(addedview);
                                }

                            }
                        } else {
                            Log.d("Document", task.getException().toString());
                        }
                    }
                });









        return view;
    }
}