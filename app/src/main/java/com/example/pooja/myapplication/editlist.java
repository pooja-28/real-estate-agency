package com.example.pooja.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class editlist extends ArrayAdapter<editinfo> {
    private Activity context;
    private List<editinfo> editinfoList;

    public  editlist(Activity context,List<editinfo> editinfoList){
        super(context,R.layout.listitem,editinfoList);
        this.context=context;
        this.editinfoList=editinfoList;
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
        editinfo u= editinfoList.get(position);

        txtbuilding.setText(u.getBuilding());
        txtprice.setText(u.getTotalbudget());
        double d=u.getRoomsqft();
        String s=Double.toString(d);
        txtsqft.setText(s);
        txtlocation.setText(u.getNoofbhk());


        return listviewitem;
    }
}
