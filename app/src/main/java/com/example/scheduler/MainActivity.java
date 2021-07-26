package com.example.scheduler;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.PrimitiveIterator;

// 메인 月달력과 함께 햄버거 등 툴바가 표시되는 페이지
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMonthChangedListener, OnDateSelectedListener {
    // Calendar
    TextView toolYear;
    TextView toolMonth;
    MaterialCalendarView materialCalendarView;

    public static Context mContext;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    GestureDetector gestureDetector = null;

    // ViewPager2
    private int oldPosition = 0;
    private boolean checkCurrent = false;

    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 3;

    //Sliding Panel
    private SlidingUpPanelLayout gestureView;
    private Date selectDay;
    private TextView todoMonth;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 다른 클래스에서 MainClass 메서드 호출하기 위해
        mContext = this;

        materialCalendarView = findViewById(R.id.calendarView);

        ToolMonthInit();
        CalendarInit();
        // 투두 업 패ㅐ널
        gestureView = findViewById(R.id.main_panel);
        gestureView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });
        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                Log.d("MainActivity", "제스쳐 Down!!!!!");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                Log.d("MainActivity", "제스쳐 onShowPress!!!");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.d("MainActivity", "제스쳐 onScroll!!!");
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Log.d("MainActivity", "제스쳐 onLongPress !!!");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.d("MainActivity", "제스쳐 onFling");
                return true;
            }
        });
        //ViewPager2
        mPager = findViewById(R.id.viewpager);
        //Adapter
        pagerAdapter = new MyAdapter(this, num_page);
        mPager.setAdapter(pagerAdapter);

        //ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mPager.setCurrentItem(1000);
        mPager.setOffscreenPageLimit(3);
        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                int newPosition = position;
                int compaire = newPosition - oldPosition;

                super.onPageSelected(position);

                Log.d("MainActivity", "old  : "+ oldPosition);
                Log.d("MainActivity", "new  : "+ newPosition);

                // 두 수의 차가 -, + 인지로 위치 확인
                if(compaire > 0 ) {
                    if(checkCurrent)
                        Log.d("MainActivity", "LTR -");
                    Log.d("MainActivity", "RTL +");

                }
                else {
                    if(checkCurrent)
                        Log.d("MainActivity", "RTL +");
                    Log.d("MainActivity", "LTR -");
                }

                oldPosition = newPosition;
                checkCurrent = false;
            }
        });

        final float pageMargin= getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        final float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);

        mPager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull @NotNull View page, float position) {
                // 좌우 미리보기 없앨려면 offset에 곱하는 2 없애기
                float myOffset = position * -(2 * pageOffset + pageMargin);
                if (mPager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                    if (ViewCompat.getLayoutDirection(mPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.setTranslationX(-myOffset);
                    } else {
                        page.setTranslationX(myOffset);
                    }
                } else {
                    page.setTranslationY(myOffset);
                }
            }
        });
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

        // SldingPanel up
        gestureView.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
        // date 넘기기

        // todoMonth에 들어갈 date format
        String stringDate = date.toString();
        String[] splitDate = stringDate.split("-");
        splitDate[2] = splitDate[2].substring(0,splitDate[2].lastIndexOf("}"));
        Log.d("MainActivity", "?a/a/a/a/a// " + splitDate[2]);
        todoMonth.setText(splitDate[1] +"."+ splitDate[2]);
    }

    private TextWatcher test(CalendarDay date ) {
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

                // 중복 처리중
                //ArrayList<CalendarDay> calendarDays = new ArrayList<>();

                if(!((editText1.getText().toString().length()) == 0))
                    dotList[0] = true;
                if(!((editText2.getText().toString().length()) == 0))
                    dotList[1] = true;
                if(!((editText3.getText().toString().length()) == 0))
                    dotList[2] = true;
                Log.d("MainActivity", "333333/// "+date);

                if(preDeco[0] == dotList)
                    preDecoCheck[0] = true;
                else
                    preDecoCheck[0] = false;
                // 중복 처리중
                //calendarDays.add(date);

                //AddDecorator(dotList, calendarDays, preDecoCheck[0]);
                AddDecorator(dotList, date, preDecoCheck[0]);
                preDeco[0] = dotList;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
    // by병선, "캘린더 일정 추가 효과 Dot 추가", 210707
//    private void AddDecorator(boolean[] dotList, ArrayList<CalendarDay> calendarDays, boolean preDecoCheck) {
    private void AddDecorator(boolean[] dotList, CalendarDay calendarDays, boolean preDecoCheck) {
        Log.d("MainActivity", "4444444/// "+calendarDays);
        //materialCalendarView.addDecorator(new EventDecorator(dotList,this, calendarDays, preDecoCheck));
        materialCalendarView.addDecorator(new EventDecorator(dotList,this, calendarDays, preDecoCheck));
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
    public  void CalendarInit() {
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
    public void ToolMonthInit() {
        // by병선, "onMonthChanged 메소드 실행 전에 툴바 연도, 월 초기화", 210702
        toolYear= (TextView)findViewById(R.id.toolYear);
        toolMonth = (TextView)findViewById(R.id.toolMonth);
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = sdf.format(date);
        toolYear.setText(getTime.substring(0,4));
        toolMonth.setText(getTime.substring(5,7));
        // todoMonth 초기화
        todoMonth = findViewById(R.id.todoMonth);
        String text = getTime.substring(5,7) + "." + getTime.substring(8);
        todoMonth.setText(text);
    }

    public void DecoratorClear(DayViewDecorator decorator) {
        materialCalendarView.removeDecorator(decorator);
        Log.d("MainActivity", "Test decorator 222222");
    }
}



