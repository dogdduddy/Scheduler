package com.example.scheduler;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.text.SimpleDateFormat;

public class FragThird extends Fragment {
    private int frag_num;
    private TextView data_t;
    private CalendarDay dates;

    public FragThird(){

    }

    // newInstance constructor for creating fragment with arguments
    public static FragThird newInstance(int num, CalendarDay date){
         FragThird fragment = new FragThird();
        Bundle args = new Bundle();
        args.putInt("num",num);
        fragment.dates = date;
        fragment.setArguments(args);
        return fragment;
    }

    // Store instance variables ba
    // sed on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        frag_num = getArguments().getInt("num",0);
        SimpleDateFormat trans = new SimpleDateFormat("yyyy-MM-dd");
        CalendarDay a = dates;
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_3p,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstancedState){
        super.onViewCreated(view,savedInstancedState);
        data_t = (TextView) view.findViewById(R.id.tvName3);
        data_t.setText("Date : " + dates);
        Log.d("MainActivity", "FragTest 3 : " + dates);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private TextWatcher watcher (CalendarDay date ) {
        final boolean[][] preDeco = {new boolean[6]};
        final boolean[] preDecoCheck = new boolean[1];

        Log.d("MainActivity", "2222222/// "+date);

        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                boolean[] dotList = {false, false, false, false, false, false};

                // 중복 draw 문제 해결하기
                //ArrayList<CalendarDay> calendarDays = new ArrayList<>();
                /*
                if(!((editText1.getText().toString().length()) == 0))
                    dotList[0] = true;
                if(!((editText2.getText().toString().length()) == 0))
                    dotList[1] = true;
                if(!((editText3.getText().toString().length()) == 0))
                    dotList[2] = true;
                Log.d("MainActivity", "333333/// "+date);
                 */
                if(!(preDeco[0].length == 0))
                    preDecoCheck[0] = true;
                else
                    preDecoCheck[0] = false;
                // 중복 처리중
                //calendarDays.add(date);

                //AddDecorator(dotList, calendarDays, preDecoCheck[0]);
                ((MainActivity)MainActivity.mContext).AddDecorator(dotList, dates, preDecoCheck[0]);
                preDeco[0] = dotList;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}
