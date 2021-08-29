package com.example.scheduler;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MyAdapter extends FragmentStateAdapter {
    private int[] ItemList = {0, 1, 2, 3, 4};

    public MyAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        CalendarDay date = ((MainActivity)MainActivity.mContext).getSelectDay;

        switch(position%3 + 1) {
            case 1:
                Log.d("MainActivity", "Instance 1 ");
                return FragFirst.newInstance(position, date);
            case 2:
                Log.d("MainActivity", "Instance 2 ");
                return FragSecond.newInstance(position, date);
            case 3:
                Log.d("MainActivity", "Instance 3 ");
                return FragThird.newInstance(position, date);
            default:
                return null;
        }
    }

    @Override
    public boolean containsItem(long itemId) {
        return super.containsItem(itemId);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return 2000;
    }

}
