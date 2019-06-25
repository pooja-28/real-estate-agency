package com.example.pooja.myapplication;

import android.animation.TypeConverter;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;

import java.util.List;

public class Main2Activity extends Main11Activity {
    Uri  videouri;
    EditText edtcity, edtaddress, edtbuilderinfo;
   VideoView video;
    RadioButton sale, rent, contruct;
    EditText edtprice, edtsqft;
    Spinner spinnerprice;
    RadioGroup rg;
    CheckBox cvilla, cproperty, capt;
    String propertytype, propertystatus;
    String imga;
    Spinner bhk;
    String id;
    StorageReference childRef;
    int count = 0;
   int PICK_VIDEO_REQUEST=1;
    TextView select;
    ProgressDialog pd;

    StorageReference storage;

MediaController mc;

    DatabaseReference db;
    FirebaseDatabase database;
    adddata data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance();
        db = database.getReference("propertyinfo");
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_main2, null, false);
        d1.addView(contentView, 0);

        storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://realestateapp-2f5ab.appspot.com").child("videos");


        bhk = findViewById(R.id.spinnerbed);
        edtcity = findViewById(R.id.edtcity);
        edtaddress = findViewById(R.id.edtaddress);
        edtbuilderinfo = findViewById(R.id.edtbuilder);
        cvilla = findViewById(R.id.cvilla);
        capt = findViewById(R.id.capt);
        cproperty = findViewById(R.id.cproperty);
        bhk = findViewById(R.id.spinnerbed);
        rent = findViewById(R.id.rbrent);
        sale = findViewById(R.id.rbsale);
        select = findViewById(R.id.txtselect);
        contruct = findViewById(R.id.rbconst);
        rg = findViewById(R.id.rbg);
        edtprice = findViewById(R.id.edtprice);
        edtsqft = findViewById(R.id.edtsqft);
        spinnerprice = findViewById(R.id.spinnerprice);


        video=findViewById(R.id.video);
video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                mc=new MediaController(Main2Activity.this);
                video.setMediaController(mc);
                mc.setAnchorView(video);
            }
        });
    }
});

        video.start();
        pd = new ProgressDialog(this);
        pd.setMessage("Uploading....");
    }
    private void addproperty() {
        String city = edtcity.getText().toString().trim();
        String address = edtaddress.getText().toString().trim();
        String building = edtbuilderinfo.getText().toString().trim();
        String noofbhk = bhk.getSelectedItem().toString();
        String bud = edtprice.getText().toString();
        double budget = Double.parseDouble(bud);
        String spinnerbudget = spinnerprice.getSelectedItem().toString();
        String totalbudget = budget + " " + spinnerbudget;
        String rsqft = edtsqft.getText().toString();
        double roomsqft = Double.parseDouble(rsqft);
        if (cvilla.isChecked()) {
            count = count + 1;
        } else if (capt.isChecked()) {
            count = count + 1;
        } else if (cproperty.isChecked()) {
            count = count + 1;
        }
        if (count == 1) {
            if (cvilla.isChecked()) {
                propertytype = cvilla.getText().toString();

            } else if (capt.isChecked()) {
                propertytype = capt.getText().toString();

            } else if (cproperty.isChecked()) {
                propertytype = cproperty.getText().toString();
            }
        } else {
            Toast.makeText(this, "please select only one box", Toast.LENGTH_LONG).show();
            return;
        }
        if (sale.isChecked()) {
            propertystatus = sale.getText().toString();

        } else if (rent.isChecked()) {
            propertystatus = rent.getText().toString();

        } else {
            propertystatus = contruct.getText().toString();
        }

        if (city.isEmpty() || address.isEmpty() || building.isEmpty() || noofbhk.isEmpty() || spinnerbudget.isEmpty()) {
            Toast.makeText(this, "please select all the appopriate fields", Toast.LENGTH_LONG).show();
            return;

        } else {

            id = db.push().getKey();
            data = new adddata(id, city, address, building, propertystatus, propertytype, noofbhk, totalbudget, roomsqft);
            db.child(id).setValue(data);
            edtbuilderinfo.setText("");
            edtaddress.setText("");
            edtcity.setText("");
            rg.clearCheck();

            if (capt.isChecked()) {
                capt.setChecked(false);
            }
            if (cvilla.isChecked()) {
                cvilla.setChecked(false);
            }
            if (cproperty.isChecked()) {
                cproperty.setChecked(false);
            }
            Toast.makeText(getApplicationContext(), "the  details has been added",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void select(View view) {
        ChooseImage();
    }

    private void ChooseImage() {
        Intent i=new Intent();
        i.setType("video/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,"select video"),PICK_VIDEO_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode==PICK_VIDEO_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            videouri=data.getData();
            video.setVideoURI(videouri);
        }
        }
    private void uploadvideo() {
        childRef =  storage.child("user").child(videouri.getLastPathSegment());
        childRef.putFile(videouri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                         childRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                db.child(id).child("video").setValue(uri.toString());
                                Toast.makeText(Main2Activity.this,"video uploaded",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(Main2Activity.this,exception.toString(),Toast.LENGTH_LONG).show();
                    }
                });
    }

         public void btntoadd (View view){

               uploadvideo();
                addproperty();
            }



}
