package com.example.scheduler;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MyAdapter extends FragmentStateAdapter {
    private int[] ItemList = {0, 1, 2, 3, 4};

    public MyAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        CalendarDay date = ((MainActivity)MainActivity.mContext).getSelectDay;

        CalendarDay dateFirst = null;
        CalendarDay dateSecond = null;
        CalendarDay dateThird = null;

        Log.d("MainActivity", "getSelectDay :  " + date);
        Log.d("MainActivity", "getSelectPosition :  " + position);

        if(((MainActivity)MainActivity.mContext).adapterFirst) {
            position = 1;
            ((MainActivity)MainActivity.mContext).setAdapterFirst();
        }
        else if(position == 0)
            position = 3;
        else if(position == 4)
            position = 1;


        /*
        // refresh
        if(position == 1){
            try {
                dateSecond = DateCal(1,date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                dateThird = DateCal(-1, date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else if(position == 2){
            // 메인에서 DateInc 발생 전에 실행 되므로 해당일 -1로 표시됨
            dateFirst = date;
            try {
                dateThird = DateCal(2, date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                dateFirst = DateCal(2,date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dateSecond = date;
        }

         */

        dateSecond = date;
        dateThird = date;

        switch(position) {
            case 1:
                Log.d("MainActivity", "Instance 1 ");
                return FragFirst.newInstance(position, dateFirst);
            case 2:
                Log.d("MainActivity", "Instance 2 ");
                FragThird.newInstance(position, dateThird);
                return FragSecond.newInstance(position, dateSecond);
            case 3:
                Log.d("MainActivity", "Instance 3 ");
                return FragThird.newInstance(position, dateThird);
            default:
                return null;
        }
    }

    @Override
    public boolean containsItem(long itemId) {
        return super.containsItem(itemId);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return ItemList.length;
    }

    public CalendarDay DateCal(int num, CalendarDay date) throws ParseException {
        // CalendarDay 값 date로 변경 후 1증가. 그리고 다시 변환
        String stringDate = date.toString();
        String[] splitDate = stringDate.split("-");
        Date dates;
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        if(splitDate[1].length() == 1)
            splitDate[1] = "0" + Integer.parseInt(splitDate[1]);
        if(splitDate[2].indexOf("}") == 1)
            splitDate[2] = "0" + splitDate[2].substring(0,1);
        else
            splitDate[2] = splitDate[2].substring(0,2);
        stringDate = splitDate[0].substring(splitDate[0].indexOf("{")+1) +"-"+ splitDate[1] +"-"+ splitDate[2];
        dates = transFormat.parse(stringDate);
        cal.setTime(dates);
        cal.add(Calendar.DATE, num);
        // cal.getTime() 으로 date형태로 변환한 값 받기 가능. but format 안 된 형태 -> 재포맷해서 String 출력
        splitDate = transFormat.format(cal.getTime()).split("-");
        // String형태에서 split으로 년,월,일 구분 후 CalendarDay 형태로 포맷

        return CalendarDay.from(Integer.parseInt(splitDate[0]),Integer.parseInt(splitDate[1]),Integer.parseInt(splitDate[2]));

    }
}
