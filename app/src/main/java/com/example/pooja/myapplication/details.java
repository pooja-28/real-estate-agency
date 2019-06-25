package com.example.pooja.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class details extends AppCompatActivity {
TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8;
String noobhk,city,address,propertytype,budget,building,propertystatus;
Uri vv;

    MediaController mc;
    Uri videouri;
    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;
double roomsqft;
VideoView video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        txt4=findViewById(R.id.txt4);
        txt5=findViewById(R.id.txt5);
        txt6=findViewById(R.id.txt6);
        txt7=findViewById(R.id.txt7);
        txt8=findViewById(R.id.txt8);
     video=findViewById(R.id.video);


        propertytype=getIntent().getStringExtra("propertytype");
        noobhk=getIntent().getStringExtra("noofbhk");
        roomsqft=getIntent().getDoubleExtra("roomsqft",100);
        String s=String.valueOf(roomsqft);
        budget=getIntent().getStringExtra("budget");
        address=getIntent().getStringExtra("address");
        city=getIntent().getStringExtra("city");
        building=getIntent().getStringExtra("building");
        propertystatus=getIntent().getStringExtra("propertystatus");
        vv=getIntent().getExtras().getParcelable("video");

        txt1.setText(propertytype);
        txt2.setText(city);
        txt3.setText(address);
        txt4.setText(s);
        txt5.setText(budget);
        txt6.setText(noobhk);
        txt7.setText(building);
        txt8.setText(propertystatus);

        videouri=vv;
        video.setVideoURI(videouri);
        video.requestFocus();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        mc=new MediaController(details.this);
                        video.setMediaController(mc);
                        mc.setAnchorView(video);
                    }
                });
            }
        });
        video.start();
    }
}
