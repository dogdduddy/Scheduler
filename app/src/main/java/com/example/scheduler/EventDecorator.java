package com.example.scheduler;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class EventDecorator extends AppCompatActivity implements DayViewDecorator {
    private final Drawable drawable;
    private final boolean[] dotList;
    private final HashSet<CalendarDay> dates;
    private final int[] ColorList = {Color.GREEN, Color.RED, Color.BLACK,
            Color.BLUE, Color.GRAY, Color.parseColor("#8A2BE2")};

    public EventDecorator(boolean[] dotList, Activity context, Collection<CalendarDay> dates) {
        drawable = context.getResources().getDrawable(R.drawable.calendar_background);
        this.dotList = dotList;
        this.dates = new HashSet<>(dates);
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

        return  dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new CustomMultipleDotSpan(5, dotList, ColorList));
        //view.setSelectionDrawable(drawable);
    }
}
