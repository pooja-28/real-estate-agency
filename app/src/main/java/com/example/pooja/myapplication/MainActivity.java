package com.example.pooja.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends Main11Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_main, null, false);
        d1.addView(contentView, 0);
    }
       public void addbtn (View view){
              Intent i = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(i);
        }
        public void editbtn (View view){
            Intent i = new Intent(MainActivity.this, Main3Activity.class);
            startActivity(i);
        }

        public void searchbtn (View view){
            Intent i = new Intent(MainActivity.this, Main5Activity.class);
            startActivity(i);
        }

        public void profilebtn (View view){
            Intent i = new Intent(MainActivity.this, Main4Activity.class);
            startActivity(i);
        }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}


