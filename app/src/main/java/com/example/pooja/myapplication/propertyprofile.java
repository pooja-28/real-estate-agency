package com.example.pooja.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class propertyprofile extends ArrayAdapter<cardrecyclevalue> {

    private Activity context;
    private List<cardrecyclevalue> recyclemodelList;


    public propertyprofile(Activity context, List<cardrecyclevalue> recyclemodelList) {
        super(context,R.layout.cardrecycle);
        this.context = context;
        this.recyclemodelList = recyclemodelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View listviewitem =inflater.inflate(R.layout.cardrecycle,null,true);
        TextView txt1=listviewitem.findViewById(R.id.txt1);
        TextView txt2=listviewitem.findViewById(R.id.txt2);
        TextView txt3=listviewitem.findViewById(R.id.txt3);
        TextView txt4=listviewitem.findViewById(R.id.txt4);
         cardrecyclevalue recycle=recyclemodelList.get(position);

         txt1.setText(recycle.getNoofbhk());
        txt2.setText(recycle.getAddress());

        txt3.setText(recycle.getTotalbudget());

     Double d=recycle.getRoomsqft();
     String s=Double.toString(d);


        txt4.setText(s);

         return listviewitem;
    }
}
