package com.example.scheduler;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.PopupMenu;
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

public class MainActivity extends AppCompatActivity implements OnMonthChangedListener, OnDateSelectedListener {

    private LayoutInflater inflater;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        int[] color = {Color.GREEN, Color.GRAY, Color.RED};
        EventDecorator eventDecorator = new EventDecorator(color, calendarDayList);
        materialCalendarView.addDecorator(new EventDecorator(color, calendarDayList));

        /*
        // 뒷배경 흐리게 하기

        WindowManager.LayoutParams layoutParams= new WindowManager.LayoutParams();
        layoutParams.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount= 0.7f;getWindow().setAttributes(layoutParams);
        setContentView(R.layout.menu_btn); // 요거에 뭐 넣느냐에 따라 전체 화면 크기 조정됨

        // 사이즈 조절

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        int width = (int) (((DisplayMetrics) dm).widthPixels * 0.9); // Display 사이즈의 90%
        int height = (int) (dm.heightPixels * 0.9); // Display 사이즈의 90%
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;
        */

        findViewById(R.id.menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
                getMenuInflater().inflate(R.menu.popupmenu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.month){
                            Toast.makeText(MainActivity.this, "메뉴 1 클릭", Toast.LENGTH_SHORT).show();

                        }else if (menuItem.getItemId() == R.id.week){
                            Toast.makeText(MainActivity.this, "메뉴 2 클릭", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Week.class);
                            startActivity(intent);
                        }
                        else if (menuItem.getItemId() == R.id.todo){
                            Toast.makeText(MainActivity.this, "메뉴 투두 클릭", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Todo.class);
                            startActivity(intent);
                        }
                        else if (menuItem.getItemId() == R.id.shop){
                            Toast.makeText(MainActivity.this, "메뉴 상점 클릭", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Todo.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(MainActivity.this, "환경설정 클릭", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Setting.class);
                            startActivity(intent);
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    // by병선, "달력의 month 이동 시의 이벤트", 210702
    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        TextView toolMonth = (TextView)findViewById(R.id.toolMonth);
        TextView toolYear = (TextView)findViewById(R.id.toolYear);
        // 그냥 6, 7 로 출력 되어서 0을 붙여줌
        if(date.getMonth() <= 9)
            toolMonth.setText("0"+String.valueOf(date.getMonth()));

        else if (date.getMonth() > 9)
            toolMonth.setText(String.valueOf(date.getMonth()));

        toolYear.setText(String.valueOf(date.getYear()));
    }
    // by병선, "day 클릭 시의 이벤트", 210702
    @Override
    public void onDateSelected(@NonNull @org.jetbrains.annotations.NotNull MaterialCalendarView widget, @NonNull @org.jetbrains.annotations.NotNull CalendarDay date, boolean selected) {
        Log.d("MainActivity", "/////////////////////////"+CalendarDay.today());
    }
}