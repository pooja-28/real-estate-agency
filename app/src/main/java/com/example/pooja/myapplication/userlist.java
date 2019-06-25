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

public class userlist extends ArrayAdapter<userinfo> {
    private Activity  context;
    private List<userinfo> userinfoList;

    public userlist( Activity context, List<userinfo> userinfoList){
        super(context,R.layout.listuser,userinfoList);
        this.context=context;
        this.userinfoList=userinfoList;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View listviewitem =inflater.inflate(R.layout.listuser,null,true);
        TextView txtemail= listviewitem.findViewById(R.id.txtemail1);
        TextView txtname= listviewitem.findViewById(R.id.txtname1);
        TextView txtphone= listviewitem.findViewById(R.id.txtphone);


        userinfo u= userinfoList.get(position);

        txtemail.setText(u.getEmail());
        txtname.setText(u.getName());
        long d=u.getPhone();
        String p=String.valueOf(d);
        txtphone.setText(p);
        return listviewitem;
    }
}
