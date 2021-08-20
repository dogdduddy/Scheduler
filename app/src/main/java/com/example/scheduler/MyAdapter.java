package com.example.scheduler;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.jetbrains.annotations.NotNull;


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
        Log.d("MainActivity", "getSelectDay :  " + date);

        if(((MainActivity)MainActivity.mContext).adapterFirst) {
            position = 1;
            ((MainActivity)MainActivity.mContext).setAdapterFirst();
        }
        else if(position == 0)
            position = 3;
        else if(position == 4)
            position = 1;

        switch(position) {
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
        return ItemList.length;
    }

}
