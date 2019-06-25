package com.example.pooja.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;



public class PagerAdapter extends FragmentStatePagerAdapter {
  int numtab;
     public PagerAdapter(FragmentManager fm, int tabCount){

        super(fm);
         this.numtab=tabCount;

     }
    @Override
    public int getCount() {
        return numtab;
    }

    @Override
    public Fragment getItem(int position) {
         switch (position){
             case 0:
                buy buy = new buy();
                return buy;
             case 1:
                 rent rent =new rent();
                 return rent;
             case 2:
                 property property=new property();
                 return property;
             default:
                 return null;
         }



    }


}
