package com.opra.lms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class payment extends AppCompatActivity {
    FirebaseAuth fAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent i=getIntent();
        String current_user=fAuth.getCurrentUser().getEmail();
        String cost=i.getStringExtra("cost");
        String name=i.getStringExtra("name");
        TextView txtname=findViewById(R.id.textView6);
        TextView txtcost=findViewById(R.id.textView7);
        txtname.setText(name);
        txtcost.setText("₹"+cost+"/-");
        Button pay=findViewById(R.id.btn_pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference thiscourse = FirebaseFirestore.getInstance().collection("course").document(name);
                thiscourse.update("user", FieldValue.arrayUnion(current_user));
                Toast.makeText(payment.this, "Registered!!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(payment.this,home.class);
                startActivity(i);
                payment.this.finish();
            }
        });

    }
}