package com.example.pooja.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.List;


public class recycle1 extends RecyclerView.Adapter<recycle1.recycleviewholder>{

    private Context mCtx;

   private List<cardrecyclevalue> p;

    public recycle1(Context mCtx, List<cardrecyclevalue> p) {
        this.mCtx = mCtx;
        this.p = p;
    }


    @NonNull
    @Override
    public recycleviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.cardrecycle,parent,false);
        return new recycleviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final recycleviewholder holder, int position) {
       final cardrecyclevalue  r = p.get(position);

        holder.txt1.setText(r.getNoofbhk());
        holder.txt2.setText(r.getAddress());
        holder.txt3.setText(String.valueOf(r.getTotalbudget()));
        holder.txt4.setText(String.valueOf(r.getRoomsqft()));

                holder.rel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(mCtx, details.class);
                        i.putExtra("propertytype", r.getPropertytype());
                        i.putExtra("noofbhk", r.getNoofbhk());
                        i.putExtra("roomsqft", r.getRoomsqft());
                        i.putExtra("budget", r.getTotalbudget());
                        i.putExtra("address", r.getAddress());
                        i.putExtra("propertystatus", r.getPropertystatus());
                        i.putExtra("building", r.getBuilding());
                        i.putExtra("city", r.getCity());
                        Log.d("videobaby",r.getVideo());
                        Uri uri=Uri.parse(r.getVideo());
                        i.putExtra("video",uri );
                        mCtx.startActivity(i);
                    }
                });

    }


    @Override
    public int getItemCount() {
        return p.size();
    }

    public static class recycleviewholder extends RecyclerView.ViewHolder{

       TextView txt1,txt2,txt3,txt4;
       RelativeLayout rel;

        public recycleviewholder(final View itemView) {
            super(itemView);

            txt1=itemView.findViewById(R.id.txt1);
            txt2=itemView.findViewById(R.id.txt2);
            txt3=itemView.findViewById(R.id.txt3);
            txt4=itemView.findViewById(R.id.txt4);
            rel=itemView.findViewById(R.id.r);



        }

    }

}