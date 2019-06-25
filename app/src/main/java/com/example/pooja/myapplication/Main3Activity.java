package com.example.pooja.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends Main11Activity {
    ListView listview;
    DatabaseReference db;
    List<editinfo> editinfoList;
    List<adddata> adddataList;
    EditText edtcity, edtaddress, edtbuilderinfo;
    RadioButton sale, rent, contruct;
    EditText edtprice, edtsqft;
    Spinner spinnerprice;
    RadioGroup rg;
    CheckBox cvilla, cproperty, capt;
    Spinner bhk;
    String propertytype;
    String id;
    TextView select;
    int count = 0;
    int PICK_VIDEO_REQUEST=1;
    Uri  videouri;
    MediaController mc;
    VideoView video;
    StorageReference storage;
    StorageReference childRef;
   AlertDialog b;
   String i;

    ProgressDialog   pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = FirebaseDatabase.getInstance().getReference("propertyinfo");


        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main3, null, false);
        d1.addView(contentView, 0);

        storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://realestateapp-2f5ab.appspot.com").child("videos");


        adddataList = new ArrayList<>();
        editinfoList = new ArrayList<>();
        Button btnupdate = findViewById(R.id.buttonUpdate);


        listview = findViewById(R.id.list);

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adddata d = adddataList.get(position);
                showUpdateDeleteDialog(d.getId(), d.getCity(), d.getAddress(), d.getBuilding(), d.getPropertystatus(), d.getPropertytype(), d.getNoofbhk(), d.getTotalbudget(), d.getRoomsqft());
                i = d.getId();
                return true;

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             //   editinfoList.clear();
                adddataList.clear();
                for (DataSnapshot usersnapshot : dataSnapshot.getChildren()) {
                   adddata u=usersnapshot.getValue(adddata.class);
                    adddataList.add(u);
                }
                adddatalist u=new adddatalist(Main3Activity.this,adddataList);
                listview.setAdapter(u);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private boolean updatedetail(String id, String city, String address, String building, String propertystatus, String propertytype, String noofbhk,
                                 String totalbudget, double roomsqft) {
        //getting the specified artist reference
        adddata data = new adddata(id,city, address, building, propertystatus, propertytype, noofbhk,totalbudget, roomsqft);
        db.child(i).setValue(data);
        Toast.makeText(getApplicationContext(), "Property info Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private void showUpdateDeleteDialog(String id, String city, String address, String building, String propertystatus, String propertytype, String noofbhk, String totalbudget, double roomsqft) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.updatedialog, null);
        dialogBuilder.setView(dialogView);
        bhk = dialogView.findViewById(R.id.spinnerbed);
        edtcity = dialogView.findViewById(R.id.edtcity);
        edtaddress = dialogView.findViewById(R.id.edtaddress);
        edtbuilderinfo = dialogView.findViewById(R.id.edtbuilder);
        cvilla = dialogView.findViewById(R.id.cvilla);
        capt = dialogView.findViewById(R.id.capt);
        cproperty = dialogView.findViewById(R.id.cproperty);
        bhk = dialogView.findViewById(R.id.spinnerbed);
        rent = dialogView.findViewById(R.id.rbrent);
        sale = dialogView.findViewById(R.id.rbsale);
        select = dialogView.findViewById(R.id.txtselect);
        contruct = dialogView.findViewById(R.id.rbconst);
        rg = dialogView.findViewById(R.id.rbg);

        edtprice = dialogView.findViewById(R.id.edtprice);
        edtsqft = dialogView.findViewById(R.id.edtsqft);
        spinnerprice = dialogView.findViewById(R.id.spinnerprice);
        video=dialogView.findViewById(R.id.video);

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        mc=new MediaController(Main3Activity.this);
                        video.setMediaController(mc);
                        mc.setAnchorView(video);
                    }
                });
            }
        });

        video.start();
        b = dialogBuilder.create();
        b.show();

    }

    public void btnupdate(View view) {
        uploadvideo();
        addproperty();


    }

    private void addproperty () {
        String city = edtcity.getText().toString().trim();
        String address = edtaddress.getText().toString().trim();
        String building = edtbuilderinfo.getText().toString().trim();
        String noofbhk = bhk.getSelectedItem().toString();
        String bud=edtprice.getText().toString();
        double budget=Double.parseDouble(bud);
        String spinnerbudget=spinnerprice.getSelectedItem().toString();
        String totalbudget=budget+" "+spinnerbudget;
        String propertystatus;
        String rsqft=edtsqft.getText().toString();
        double roomsqft=Double.parseDouble(rsqft);


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
                propertytype= cproperty.getText().toString();

            }
        } else {
            Toast.makeText(this, "please select only one box", Toast.LENGTH_LONG).show();
            return;
        }

        if (sale.isChecked()) {
            propertystatus = sale.getText().toString();

        } else if (rent.isChecked()) {
            propertystatus = sale.getText().toString();

        } else {
            propertystatus = contruct.getText().toString();
        }


        if (city.isEmpty() || address.isEmpty() || building.isEmpty() || noofbhk.isEmpty() || spinnerbudget.isEmpty()) {
            Toast.makeText(this, "please select all the appopriate fields", Toast.LENGTH_LONG).show();

        } else {

          updatedetail(id,city,address,building,propertystatus,propertytype,noofbhk,totalbudget,roomsqft);
          b.dismiss();
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
                                Toast.makeText(Main3Activity.this,"video uploaded",Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(Main3Activity.this,exception.toString(),Toast.LENGTH_LONG).show();
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }


    public void btndelete(View view) {

        db.child(i).removeValue();
        Toast.makeText(Main3Activity.this,"Property deleted",Toast.LENGTH_LONG).show();
    }
}




