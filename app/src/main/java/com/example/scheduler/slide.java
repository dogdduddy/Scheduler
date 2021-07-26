package com.example.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.zip.Inflater;

public class slide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        //((MainActivity)MainActivity.mContext).ToolMonthInit();
        //((MainActivity)MainActivity.mContext).CalendarInit();

        //MainActivity 담을 FrameLayout
        FrameLayout frameLayout = findViewById(R.id.frame_map);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_main, frameLayout, true);
    }
    public void FullSize() {
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        setLayout(height);
    }

    public void HalfSize() {
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 450, getResources().getDisplayMetrics());
        setLayout(height);
    }

    private void setLayout(int height) {
        LinearLayout linearLayout = findViewById(R.id.drawer);
        int width = LinearLayout.LayoutParams.MATCH_PARENT;

        SlidingUpPanelLayout.LayoutParams params = new SlidingUpPanelLayout.LayoutParams(width, height);
        linearLayout.setLayoutParams(params);
    }
}