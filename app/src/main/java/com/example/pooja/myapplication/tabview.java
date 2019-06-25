package com.example.pooja.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class tabview extends Main11Activity implements buy.OnFragmentInteractionListener,rent.OnFragmentInteractionListener,property.OnFragmentInteractionListener {
EditText location;
RadioGroup rg,rgbtn;
Spinner spinnerprice;
RadioButton bhk1,bhk2,bhk3,bhk4;
RadioButton sale,rent,contruct;
RadioButton rbtnapt,rbtncommercial,rbtnhouse;
String radiobtn,btn,keyword,spinner;
int count=0;
     ViewPager viewPager;
TabLayout tabLayout;
String Rent,Buy,Property;
    public static String POSITION = "POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_tabview, null, false);
        d1.addView(contentView, 0);










        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Buy"));
        tabLayout.addTab(tabLayout.newTab().setText("Rent"));
        tabLayout.addTab(tabLayout.newTab().setText("Property"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

         viewPager= (ViewPager) findViewById(R.id.viewpager);
        PagerAdapter tabsAdapter = new com.example.pooja.myapplication.PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(tabsAdapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    private void getrent() {

        rg = findViewById(R.id.rbg);
        rgbtn=findViewById(R.id.radiobtnbhk);
        spinnerprice = findViewById(R.id.droprent);
        bhk1 = findViewById(R.id.rbtn1bhk);
        bhk2 = findViewById(R.id.rbtn2bhk);
        bhk3 = findViewById(R.id.rbtn3bhk);
        bhk4 = findViewById(R.id.rbtn4bhk);
        rbtnapt = findViewById(R.id.rbtnapt);
        rbtncommercial = findViewById(R.id.rbtncommercial);
        rbtnhouse = findViewById(R.id.rbtnhouse);
        location = findViewById(R.id.edtlocation);
        keyword = location.getText().toString();
        spinner = spinnerprice.getSelectedItem().toString();
        Rent = "Rent";

        if (rbtnapt.isChecked()) {
            radiobtn = rbtnapt.getText().toString();

        } else if (rbtncommercial.isChecked()) {
            radiobtn = rbtncommercial.getText().toString();

        } else if (rbtnhouse.isChecked()) {
            radiobtn = rbtnhouse.getText().toString();
        }


        if (bhk1.isChecked()) {
            btn = bhk1.getText().toString();
        }
       else if (bhk2.isChecked()) {
            btn = bhk2.getText().toString();
        }
       else if (bhk3.isChecked()) {
            btn = bhk3.getText().toString();
        }
       else if (bhk4.isChecked()) {
            btn = bhk4.getText().toString();
        }

    }
    public void rentsubmit(View view) {
         getrent();

     Bundle b=new Bundle();

      b.putString("location",keyword);
      b.putString("price",spinner);
      b.putString("noofbhk",btn);
      b.putString("propertytype",radiobtn);
      b.putString("rent",Rent);
        Intent i = new Intent(this, Main5Activity.class);
        i.putExtras(b);
        startActivity(i);

    }



    public void buysubmit(View view) {
        Intent i = new Intent(this, Main5Activity.class);
        startActivity(i);
    }

    public void propertysubmit(View view) {
        Intent i = new Intent(this, Main5Activity.class);
        startActivity(i);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(POSITION, tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }
}














