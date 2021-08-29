package com.example.scheduler;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class FragSecond extends Fragment {
    private int frag_num;
    private TextView data_t;
    private CalendarDay dates;

    public FragSecond(){

    }

    // newInstance constructor for creating fragment with arguments
    public static FragSecond newInstance(int num, CalendarDay date){
        FragSecond fragment = new FragSecond();
        Bundle args = new Bundle();
        fragment.dates = date;
        return fragment;
    }

    // Store instance variables ba
    // sed on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_2p,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstancedState){
        super.onViewCreated(view,savedInstancedState);
        data_t = (TextView) view.findViewById(R.id.tvName2);
        data_t.setText("Date : " + dates);
        Log.d("MainActivity", "FragTest 2 : " + dates);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
