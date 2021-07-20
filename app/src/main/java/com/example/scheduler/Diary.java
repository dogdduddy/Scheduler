package com.example.scheduler;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Diary extends AppCompatActivity {

    ImageView menu_show;
    MenuBuilder menuBuilder;

    @SuppressLint({"RestrictedApi", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        menu_show = (ImageView) findViewById(R.id.menu_btn);
        menuBuilder = new MenuBuilder(this);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.popupmenu, menuBuilder);

        FloatingActionButton addbtn = findViewById(R.id.diary_add_btn);
        addbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Diary.this, MakeDiary.class);
                startActivity(intent);
            }

            /* 스낵바 // 토스트랑 비슷 한 기능 // 밑에서 화면 조금 올라옴
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            */
        });

        menu_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuPopupHelper optionMenu = new MenuPopupHelper(Diary.this, menuBuilder, view);
                optionMenu.setForceShowIcon(true);

                menuBuilder.setCallback(new MenuBuilder.Callback() {
                    @Override

                    public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                        Intent intent;
                        switch (item.getItemId()) {

                            case R.id.diary:
                                Toast.makeText(Diary.this, "다이어리", Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.calendar:
                                intent = new Intent(Diary.this, MainActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.shop:
                                intent = new Intent(Diary.this, Shop.class);
                                startActivity(intent);
                                return true;

                            case R.id.setting:
                                intent = new Intent(Diary.this, Setting.class);
                                startActivity(intent);
                                return true;

                            default:
                                return true;
                        }
                    }
                    @Override
                    public void onMenuModeChange(@NonNull MenuBuilder menu) {
                    }
                });
                optionMenu.show();
            }
        });
    }
}
