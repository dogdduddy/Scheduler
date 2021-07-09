package com.example.scheduler;


import androidx.appcompat.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class EventDecorator extends AppCompatActivity implements DayViewDecorator {
    private final int[] color;
    private final HashSet<CalendarDay> dates;

    public EventDecorator(int[] color, Collection<CalendarDay> dates) {
        this.color = color;
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
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new CustomMultipleDotSpan(5, color));
    }
}
