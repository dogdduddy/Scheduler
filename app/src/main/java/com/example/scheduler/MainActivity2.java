package com.example.scheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ViewPager2 viewPager2;
    Button btnToggle;
    ArrayList<DataPage> list;
    ViewPagerAdapter viewPagerAdapter;

    int i = 7;

    @Override
    protected void onResume() {
        super.onResume();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                /*
                Log.d("MainActivity","Position  :  " + position);
                super.onPageSelected(position);
                Log.d("MainActivity","Del List :  " + list.get(0).getTitle());
                list.remove(0);
                String ti = Integer.toString(i) + " Page";
                //list.add(new DataPage(android.R.color.holo_orange_dark, ti));
                Log.d("MainActivity","Add List :  " + list.get(5).getTitle());
                Log.d("MainActivity","Thi List :  " + list.get(2).getTitle());
                i++;
                viewPager2.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPagerAdapter.notifyDataSetChanged();
                    }
                });
                viewPager2.setAdapter(viewPagerAdapter);
                if(position == 5) {

                    viewPager2.setCurrentItem(0);


                }                 */
                viewPager2.post(new Runnable() {
                    @Override
                    public void run() {
                        String ti = Integer.toString(i) + " Page";
                        list.add(new DataPage(android.R.color.holo_orange_dark, ti));
                        list.remove(0);
                        viewPagerAdapter.notifyDataSetChanged();
                        viewPagerAdapter.setArrayList(list);
                        viewPager2.setAdapter(viewPagerAdapter);
                        //viewPager2.setCurrentItem(2, false);
                    }
                });
                i++;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        viewPager2 = findViewById(R.id.viewPager2);

        list = new ArrayList<>();
        list.add(new DataPage(android.R.color.black, "1 Page"));
        list.add(new DataPage(android.R.color.holo_red_light, "2 Page"));
        list.add(new DataPage(android.R.color.holo_green_dark, "3 Page"));
        list.add(new DataPage(android.R.color.holo_orange_dark, "4 Page"));
        list.add(new DataPage(android.R.color.holo_blue_light, "5 Page"));
        list.add(new DataPage(android.R.color.holo_blue_bright, "6 Page"));

        viewPagerAdapter = new ViewPagerAdapter(list);
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.setCurrentItem(2, false);
    }
}