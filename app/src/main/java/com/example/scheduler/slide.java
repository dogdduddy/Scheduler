package com.example.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class slide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        LinearLayout linearLayout = findViewById(R.id.drawer);

    }
}