package com.example.pooja.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;

public class Main5Activity extends Main11Activity {
    List<cardrecyclevalue> p;
    RecyclerView r;
    DatabaseReference db;
    Bundle bundle;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
String id=user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main5, null, false);
        d1.addView(contentView, 0);
        db = FirebaseDatabase.getInstance().getReference("propertyinfo");
        r = findViewById(R.id.recycle);
        r.setHasFixedSize(true);
        r.setLayoutManager(new LinearLayoutManager(Main5Activity.this));
        p = new ArrayList<>();

    }


    @Override
    protected void onStart() {
        super.onStart();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              p.clear();
              if(dataSnapshot.exists()){
                  for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                      cardrecyclevalue value=snapshot.getValue(cardrecyclevalue.class);
                      p.add(value);
                  }
                  recycle1 adapter=new recycle1(Main5Activity.this,p);
                  r.setAdapter(adapter);
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Main5Activity.this,databaseError.toString(),Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
if(id.equals("c3lLpvxKDOZmWGlgKSRfjR7RtJc2")) {super.onBackPressed();}
else {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Main5Activity.this.finish();
                }
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
    AlertDialog alert = builder.create();
    alert.show();
}  }
}




