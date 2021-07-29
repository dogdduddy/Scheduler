package com.example.scheduler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.jetbrains.annotations.NotNull;

public class FragFirst extends Fragment {
    private int frag_num;
    TextView data_t;

    public FragFirst(){

    }

    // newInstance constructor for creating fragment with arguments
    public static FragFirst newInstance(int num, CalendarDay date){
        FragFirst fragment = new FragFirst();
        Bundle args = new Bundle();
        //args.putInt("num",num);
        fragment.frag_num = num;
        //fragment.setArguments(args);
        return fragment;
    }

    // Store instance variables ba
    // sed on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //frag_num = getArguments().getInt("num",0);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_1p,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstancedState){
        super.onViewCreated(view,savedInstancedState);
        data_t = (TextView) view.findViewById(R.id.tvName1);
        data_t.setText("Page " + frag_num + " !");
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
