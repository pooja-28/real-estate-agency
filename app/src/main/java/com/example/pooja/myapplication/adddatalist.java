package com.example.pooja.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class adddatalist  extends ArrayAdapter<adddata> {
    private Activity context;
    private List<adddata> addinfoList;


    String id;

    public  adddatalist(Activity context,List<adddata> addinfoList){
        super(context,R.layout.listitem,addinfoList);
        this.context=context;
        this.addinfoList=addinfoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();

        View listviewitem =inflater.inflate(R.layout.listitem,null,true);
        TextView txtbuilding= listviewitem.findViewById(R.id.txtt1);
        TextView txtprice= listviewitem.findViewById(R.id.txtt2);
        TextView txtsqft= listviewitem.findViewById(R.id.txtt3);
        TextView txtlocation= listviewitem.findViewById(R.id.txtt4);
        adddata u= addinfoList.get(position);

        txtbuilding.setText(u.getBuilding());
        txtprice.setText(u.getTotalbudget());
        double d=u.getRoomsqft();
        String s=Double.toString(d);
        txtsqft.setText(s);
        txtlocation.setText(u.getNoofbhk());




        return listviewitem;
    }


    }


