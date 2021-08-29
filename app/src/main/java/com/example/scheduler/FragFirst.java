package com.example.scheduler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.jetbrains.annotations.NotNull;

public class FragFirst extends Fragment implements View.OnClickListener {
    private int frag_num;
    TextView data_t;
    private CalendarDay dates;
    private boolean[] dotList;
    private boolean preDecoCheck[];

    public FragFirst(){

    }

    // newInstance constructor for creating fragment with arguments
    public static FragFirst newInstance(int num, CalendarDay date){
        FragFirst fragment = new FragFirst();
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
        View view = inflater.inflate(R.layout.frame_1p,container,false);
        dotList = new boolean[]{true, true, false, false, false, false};
        preDecoCheck = new boolean[]{false};

        String dateTrans = "test";
        Bundle bundle = new Bundle();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstancedState){
        super.onViewCreated(view,savedInstancedState);
        data_t = (TextView) view.findViewById(R.id.tvName1);
        data_t.setText("Date : " + dates);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        dates = ((MainActivity)MainActivity.mContext).getSelectDay;
        ((MainActivity)MainActivity.mContext).AddDecorator(dotList, dates, preDecoCheck[0]);
    }
}
