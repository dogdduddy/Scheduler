package com.example.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class slide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);



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