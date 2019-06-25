package com.example.pooja.myapplication;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends Main11Activity {
ListView listview;
DatabaseReference db;
List<userinfo> userinfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db= FirebaseDatabase.getInstance().getReference("Userprofile");
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main4, null, false);
        d1.addView(contentView, 0);
         userinfoList=new ArrayList<>();
    }
    @Override
    protected void onStart() {
        super.onStart();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 userinfoList.clear();
                    for (DataSnapshot usersnapshot : dataSnapshot.getChildren()) {
                        userinfo u = usersnapshot.getValue(userinfo.class);
                        userinfoList.add(u);
                    }
                    userlist u =new userlist(Main4Activity.this,userinfoList);
                   listview=findViewById(R.id.list);
                    listview.setAdapter(u);
                }
                @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}