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

public class FragThird extends Fragment {
    private int frag_num;
    private TextView data_t;
    private static String dates;

    public FragThird(){

    }

    // newInstance constructor for creating fragment with arguments
    public static FragThird newInstance(int num, CalendarDay date){
         FragThird fragment = new FragThird();
        Bundle args = new Bundle();
        args.putInt("num",num);
        args.putString("string",date.toString());
        fragment.setArguments(args);
        return fragment;
    }

    // Store instance variables ba
    // sed on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        frag_num = getArguments().getInt("num",0);
        dates = getArguments().getString("String", "0");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_3p,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstancdState){
        super.onViewCreated(view,savedInstancdState);
        data_t = (TextView) view.findViewById(R.id.tvName3);
        data_t.setText("Page " + frag_num);
        data_t.setText(dates);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
