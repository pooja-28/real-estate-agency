package com.example.pooja.myapplication;
import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main7Activity extends AppCompatActivity {
    EditText edtemail, edtpassword;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        mAuth = FirebaseAuth.getInstance();
        edtemail = findViewById(R.id.edtemail);
        edtpassword = findViewById(R.id.edtpassword);
        progressBar = findViewById(R.id.progressbar);

        if(mAuth.getCurrentUser()!=null){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String id=user.getUid();
            if(id.equals("c3lLpvxKDOZmWGlgKSRfjR7RtJc2")){
                finish();
                Intent i = new Intent(Main7Activity.this, MainActivity.class);
                startActivity(i);
            }
             else {
                finish();
                Intent b = new Intent(Main7Activity.this, Main5Activity.class);
                startActivity(b);
            }
        }
    }

    public void userlogin() {
        final String email = edtemail.getText().toString();
        final String password = edtpassword.getText().toString();
        if (email.isEmpty()) {
            edtemail.setError("email is required");
            edtemail.requestFocus();
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtemail.setError("email incorrect");

            edtemail.requestFocus();
            Toast.makeText(this, "please enter a proper email address", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.isEmpty()) {
            edtpassword.setError("password is required");
            edtpassword.requestFocus();
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length() < 6) {
            edtpassword.setError("minimum length of password is 6");
            Toast.makeText(this, "Please enter with minimum 6 number", Toast.LENGTH_LONG).show();
            edtpassword.requestFocus();
            return;
        }


            // progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);

                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();

                            } else {

                                if (email.equals("info@gmail.com") && password.equals("143")) {


                                    Intent i = new Intent(Main7Activity.this, MainActivity.class);
                                    startActivity(i);

                                } else {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if ( user.isEmailVerified()) {

                                        Intent j = new Intent(Main7Activity.this, Main5Activity.class);
                                        startActivity(j);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"please verify your email before login",Toast.LENGTH_LONG).show();
                                    }

                                }
                            }
                        }
                    });
        }




    public void loginbtn(View view) {
        userlogin();
    }

    public void register(View view) {

        Intent i = new Intent(Main7Activity.this, Main12Activity.class);
        startActivity(i);
    }

    public void btnforgot(View view) {
        Intent i = new Intent(Main7Activity.this, forgotpass.class);
        startActivity(i);
    }


}
