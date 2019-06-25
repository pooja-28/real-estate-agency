package com.example.pooja.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class Main12Activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText edt1,edt2,edt3,edt4,edt5;
 ProgressBar progressBar;

    public static final String TAG = "REGISTRATION:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
        mAuth = FirebaseAuth.getInstance();
        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);
        edt3=findViewById(R.id.edt3);
        edt4=findViewById(R.id.edt4);
        edt5=findViewById(R.id.edt5);
        progressBar=findViewById(R.id.progressbar);




    }
    public void registeruser() {
        final String email = edt4.getText().toString();
        String password = edt2.getText().toString();
        String confirmpass = edt3.getText().toString();
        final String name = edt1.getText().toString();
        final String phone1=edt5.getText().toString();

        if (phone1.isEmpty()) {
            edt5.setError("phone number is required");
            edt5.requestFocus();
            Toast.makeText(this, "Please enter phone number", Toast.LENGTH_LONG).show();
            return;
        }

        if (email.isEmpty()) {
            edt4.setError("email is required");
            edt4.requestFocus();
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edt4.setError("email incorrect");

            edt4.requestFocus();
            Toast.makeText(this, "please enter a proper email address", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.isEmpty()) {
            edt2.setError("password is required");
            edt2.requestFocus();
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length() < 6) {
            edt2.setError("minimum length of password is 6");
            Toast.makeText(this, "Please enter with minimum lenth of 6 ", Toast.LENGTH_LONG).show();
            edt2.requestFocus();
            return;
        }
        if (email.equals("info@gmail.com") && password.equals("143")) {
            edt2.setError("Not available");
            edt2.requestFocus();
            Toast.makeText(this, "Not available", Toast.LENGTH_LONG).show();
            return;
        }
        if (!confirmpass.equals(password)) {
            edt3.setError("password is not matching");
            Toast.makeText(this, "password not matching", Toast.LENGTH_LONG).show();
            edt3.requestFocus();
            return;
        }
     final long phone=Long.valueOf(phone1);
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);

                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        } else {

                            userinfo userinfo=new userinfo(email,name,phone);
                            FirebaseDatabase.getInstance().getReference("Userprofile")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(userinfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getApplicationContext(), "successfully registered", Toast.LENGTH_LONG).show();
                                     emailverification();
                                }
                            });

                        }
                        }

                });
}

public void emailverification(){
    final FirebaseUser user = mAuth.getCurrentUser();
    user.sendEmailVerification()
            .addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Main12Activity.this,
                                "Verification email sent to " + user.getEmail(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e(TAG, "sendEmailVerification", task.getException());
                        Toast.makeText(Main12Activity.this,
                                "Failed to send verification email.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
}
    public void submitbtn(View view) {
        registeruser();



    }

    public void reset(View view) {
        edt1.setText(" ");
        edt2.setText(" ");
        edt3.setText(" ");
        edt4.setText(" ");
        edt5.setText(" ");

    }

    public void login(View view) {

        Intent i=new Intent(Main12Activity.this,Main7Activity.class);
        startActivity(i);
    }
}
