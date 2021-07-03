package com.example.scheduler;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.navigation.NavigationView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


// 메인 月달력과 함께 햄버거 등 툴바가 표시되는 페이지

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMonthChangedListener, OnDateSelectedListener {

    private LayoutInflater inflater;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavBar();


        MaterialCalendarView materialCalendarView = findViewById(R.id.calendarView);

        // by병선, "onMonthChanged 메소드 실행 전에 툴바 연도, 월 초기화", 210702
        TextView toolYear = (TextView)findViewById(R.id.toolYear);
        TextView toolMonth = (TextView)findViewById(R.id.toolMonth);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String getTime = sdf.format(date);
        toolYear.setText(getTime.substring(0,4));
        toolMonth.setText(getTime.substring(5));

        //by병선, "dateChange, monthChange 메소드 사용 코드", 210702
        materialCalendarView.setOnDateChangedListener(this);
        materialCalendarView.setOnMonthChangedListener(this);
        // by병선, "상단 bar 없애기", 210702
        materialCalendarView.setTopbarVisible(false);

        ArrayList<CalendarDay> calendarDayList = new ArrayList<>();
        calendarDayList.add(CalendarDay.today());
        calendarDayList.add(CalendarDay.from(2021,07,02));

        EventDecorator eventDecorator = new EventDecorator(134, calendarDayList);

        materialCalendarView.addDecorator(new EventDecorator(Color.GREEN, calendarDayList));
    }

    public void NavBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //Toast.makeText(this, "현재 페이지입니다.", Toast.LENGTH_SHORT).show();
        if (id == R.id.month) {
            Toast.makeText(this, "월간 달력 페이지 입니다.", Toast.LENGTH_SHORT).show();
            // 햄버거에서 월간 클릭했을 때 나오는 텍스트
        }
        else if (id == R.id.week) {
            Intent intent = new Intent(MainActivity.this, Week.class);
            startActivity(intent);
            Toast.makeText(this, "주간 달력 페이지 입니다.", Toast.LENGTH_SHORT).show();
            // 햄버거에서 주간 클릭했을 때 나오는 텍스트
        }
        else if (id == R.id.todo) {
            Intent intent = new Intent(MainActivity.this, Todo.class);
            startActivity(intent);
            Toast.makeText(this, "할일 페이지 입니다.", Toast.LENGTH_SHORT).show();
            // 햄버거에서 할일 클릭했을 때 나오는 텍스트
        }
        else if (id == R.id.setting) {
            Intent intent = new Intent(MainActivity.this, Setting.class);
            startActivity(intent);
            Toast.makeText(this, "설정 페이지 입니다.", Toast.LENGTH_SHORT).show();
            // 햄버거에서 세팅 클릭했을 때 나오는 텍스트
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // by병선, "달력의 month 이동 시의 이벤트", 210702
    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        if(date.getYear() == CalendarDay.today().getYear()) {
            TextView toolYear = (TextView)findViewById(R.id.toolYear);
            TextView toolMonth = (TextView)findViewById(R.id.toolMonth);
            toolYear.setText(String.valueOf(date.getYear()));
            // 그냥 6, 7 로 출력 되어서 0을 붙여줌
            toolMonth.setText("0"+String.valueOf(date.getMonth()));
        }
    }
    // by병선, "day 클릭 시의 이벤트", 210702
    @Override
    public void onDateSelected(@NonNull @org.jetbrains.annotations.NotNull MaterialCalendarView widget, @NonNull @org.jetbrains.annotations.NotNull CalendarDay date, boolean selected) {
        Log.d("MainActivity", "/////////////////////////"+CalendarDay.today());
    }
}



