package com.example.scheduler;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialCalendar;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.zip.Inflater;

public class EventDecorator extends AppCompatActivity implements DayViewDecorator {
    private final Drawable drawable;
    private final boolean[] dotList;
    private final boolean preDecoCheck;
    //private final HashSet<CalendarDay> dates;
    private final CalendarDay date;
    private final int[] ColorList = {Color.GREEN, Color.RED, Color.BLACK,
            Color.BLUE, Color.GRAY, Color.parseColor("#8A2BE2")};

    //public EventDecorator(boolean[] dotList,Activity context, Collection<CalendarDay> dates, boolean preDecoCheck) {
    public EventDecorator(boolean[] dotList,Activity context, CalendarDay date, boolean preDecoCheck) {
        drawable = context.getResources().getDrawable(R.drawable.calendar_background);
        this.dotList = dotList;
        //this.dates = new HashSet<>(dates);
        this.date = date;
        this.preDecoCheck = preDecoCheck;
    }
    /*
    public EventDecorator(List<MainActivity.Filter> filteredEvents) {
        this.dates = new HashSet<>(filteredEvents.get(0).calDayArr);
        int[] color = new int[1];
        color[0] = filteredEvents.get(0).color;
        this.color = color;
    }
     */

    @Override
    public boolean shouldDecorate(CalendarDay day) {

        //return  dates.contains(day);
        if(date == day)
            return true;
        else
            return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        // 삭제기능 paint가 아닌 라이브러리 메서드로 구현
        if(preDecoCheck)
            ((MainActivity)MainActivity.mContext).DecoratorClear(this);
        view.addSpan(new CustomMultipleDotSpan(5, dotList, ColorList));
        //view.setSelectionDrawable(drawable);
    }
}
