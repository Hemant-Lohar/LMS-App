package com.opra.lms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.razorpay.Checkout;

import org.json.JSONObject;

public class payment extends AppCompatActivity {
    FirebaseAuth fAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent i=getIntent();
        String cost=i.getStringExtra("cost");
        String name=i.getStringExtra("name");
        String current_user=fAuth.getCurrentUser().getEmail();
        TextView txtname=findViewById(R.id.textView6);
        TextView txtcost=findViewById(R.id.textView7);
        txtname.setText(name);
        txtcost.setText("₹"+cost+"/-");
        Button pay=findViewById(R.id.btn_pay);




        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makePayment();
                DocumentReference thiscourse = FirebaseFirestore.getInstance().collection("course").document(name);
                thiscourse.update("user", FieldValue.arrayUnion(current_user));
            }
        });

    }

    private void makePayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_92sXJEdsMzESab");

        checkout.setImage(R.drawable.ic_profile);

        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "LMS");
            options.put("description", "Complete Payment to Register");
            options.put("image", "https://firebasestorage.googleapis.com/v0/b/lms-app-4465c.appspot.com/o/applogo.png?alt=media&token=51ab9ad3-ec73-4fc1-aeb1-986431d6dbe3");
//                options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "50000");//pass amount in currency subunits
            options.put("prefill.email", fAuth.getCurrentUser().getEmail());
            options.put("prefill.contact","9123456789");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }


    public void onPaymentSuccess(String s) {
        String current_user=fAuth.getCurrentUser().getEmail();

        Toast.makeText(payment.this,"Payment Successful !", Toast.LENGTH_SHORT).show();
        Toast.makeText(payment.this, "Registered!!", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(payment.this,home.class);
        startActivity(i);
        payment.this.finish();
    }


    public void onPaymentError(int i, String s) {
        Toast.makeText(payment.this,"Payment Failed !", Toast.LENGTH_SHORT).show();

    }
}