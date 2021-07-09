package com.example.scheduler;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.List;

// 메인 月달력과 함께 햄버거 등 툴바가 표시되는 페이지

public class MainActivity extends AppCompatActivity implements OnMonthChangedListener, OnDateSelectedListener {

    private LayoutInflater inflater;
    ImageView menu_show;
    MenuBuilder menuBuilder;

    @SuppressLint({"RestrictedApi", "WrongViewCast"})
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialCalendarView materialCalendarView = findViewById(R.id.calendarView);

        // by병선, "onMonthChanged 메소드 실행 전에 툴바 연도, 월 초기화", 210702
        TextView toolYear = (TextView) findViewById(R.id.toolYear);
        TextView toolMonth = (TextView) findViewById(R.id.toolMonth);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String getTime = sdf.format(date);
        toolYear.setText(getTime.substring(0, 4));
        toolMonth.setText(getTime.substring(5));

        //by병선, "dateChange, monthChange 메소드 사용 코드", 210702
        materialCalendarView.setOnDateChangedListener(this);
        materialCalendarView.setOnMonthChangedListener(this);
        // by병선, "상단 bar 없애기", 210702
        materialCalendarView.setTopbarVisible(false);

        ArrayList<CalendarDay> calendarDayList = new ArrayList<>();
        calendarDayList.add(CalendarDay.today());
        calendarDayList.add(CalendarDay.from(2021, 07, 02));

        int[] color = {Color.GREEN, Color.GRAY, Color.RED};
        EventDecorator eventDecorator = new EventDecorator(color, calendarDayList);
        materialCalendarView.addDecorator(new EventDecorator(color, calendarDayList));

        /*
        // 뒷배경 흐리게 하기
        WindowManager.LayoutParams layoutParams= new WindowManager.LayoutParams();
        layoutParams.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount= 0.7f;getWindow().setAttributes(layoutParams);
        setContentView(R.drawable.); // 요거에 뭐 넣느냐에 따라 전체 화면 크기 조정됨

        // 사이즈 조절
        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        int width = (int) (((DisplayMetrics) dm).widthPixels * 0.9); // Display 사이즈의 90%
        int height = (int) (dm.heightPixels * 0.9); // Display 사이즈의 90%
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;
        */

        menu_show = (ImageView) findViewById(R.id.menu_btn);
        menuBuilder = new MenuBuilder(this);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.popupmenu, menuBuilder);

        menu_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuPopupHelper optionMenu = new MenuPopupHelper(MainActivity.this, menuBuilder, view);
                optionMenu.setForceShowIcon(true);

                menuBuilder.setCallback(new MenuBuilder.Callback() {
                    @Override

                    public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                        Intent intent;
                        switch (item.getItemId()) {

                            case R.id.calendar:
                                Toast.makeText(MainActivity.this, "캘린더", Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.diary:
                                intent = new Intent(MainActivity.this, Diary.class);
                                startActivity(intent);
                                return true;

                            case R.id.shop:
                                intent = new Intent(MainActivity.this, Shop.class);
                                startActivity(intent);
                                return true;

                            case R.id.setting:
                                intent = new Intent(MainActivity.this, Setting.class);
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