package com.example.scheduler;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
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
    TextView toolYear;
    TextView toolMonth;
    MaterialCalendarView materialCalendarView;

    EditText editText1;
    EditText editText2;
    EditText editText3;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        materialCalendarView = findViewById(R.id.calendarView);

        NavBar();
        ToolMonthInit();
        CalendarInit();
    }
    // by병선, "day 클릭 시의 이벤트", 210702
    @Override
    public void onDateSelected(@NonNull @org.jetbrains.annotations.NotNull MaterialCalendarView widget, @NonNull @org.jetbrains.annotations.NotNull CalendarDay date, boolean selected) {
         editText1.setVisibility(View.VISIBLE);
        editText2.setVisibility(View.VISIBLE);
        editText3.setVisibility(View.VISIBLE);
        Log.d("MainActivity", "1111111/// "+date);
        editText1.addTextChangedListener(test(date));
        editText2.addTextChangedListener(test(date));
        editText3.addTextChangedListener(test(date));
    }

    private TextWatcher test(CalendarDay date ) {
        Log.d("MainActivity", "2222222/// "+date);
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean[] dotList = {false, false, false, false, false, false};
                ArrayList<CalendarDay> calendarDays = new ArrayList<>();

                if(!((editText1.getText().toString().length()) == 0))
                    dotList[0] = true;
                if(!((editText2.getText().toString().length()) == 0))
                    dotList[1] = true;
                if(!((editText3.getText().toString().length()) == 0))
                    dotList[2] = true;
                Log.d("MainActivity", "333333/// "+date);

                calendarDays.add(date);

                AddDecorator(dotList, calendarDays);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
    // by병선, "캘린더 일정 추가 효과 Dot 추가", 210707
    private void AddDecorator(boolean[] dotList, ArrayList<CalendarDay> calendarDays) {
        Log.d("MainActivity", "4444444/// "+calendarDays);
        materialCalendarView.addDecorator(new EventDecorator(dotList,this, calendarDays));
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
        // 그냥 6, 7 로 출력 되어서 0을 붙여줌
        if(date.getMonth() <= 9)
            toolMonth.setText("0"+String.valueOf(date.getMonth()));
        else if (date.getMonth() > 9)
            toolMonth.setText(String.valueOf(date.getMonth()));

        toolYear.setText(String.valueOf(date.getYear()));
    }


    // by병선, "캘린더 설정 초기화", 210707
    private  void CalendarInit() {
        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);


        //  day 클릭 시 원
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);
        materialCalendarView.setSelectionColor(Color.GREEN);
        //by병선, "dateChange, monthChange 메소드 사용 코드", 210702
        materialCalendarView.setOnDateChangedListener(this);
        materialCalendarView.setOnMonthChangedListener(this);
        // by병선, "상단 bar 없애기", 210702
        materialCalendarView.setTopbarVisible(false);
    }

    // by병선, "ToolBar 연,월 초기화", 210707
    private void ToolMonthInit() {
        // by병선, "onMonthChanged 메소드 실행 전에 툴바 연도, 월 초기화", 210702
        toolYear= (TextView)findViewById(R.id.toolYear);
        toolMonth = (TextView)findViewById(R.id.toolMonth);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String getTime = sdf.format(date);
        toolYear.setText(getTime.substring(0,4));
        toolMonth.setText(getTime.substring(5));
    }
}



