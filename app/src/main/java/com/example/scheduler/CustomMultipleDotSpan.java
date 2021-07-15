package com.example.scheduler;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.os.Build;
import android.text.style.LineBackgroundSpan;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;

public class CustomMultipleDotSpan extends AppCompatActivity implements LineBackgroundSpan {
    public static final float DEFAULT_RADIUS = 3;
    private final float radius;
    private boolean[] dotList;
    private int[] ColorList;

    public CustomMultipleDotSpan() {
        this.radius = DEFAULT_RADIUS;
        //this.color.get(0) = 0;
    }

    public CustomMultipleDotSpan(int color) {
        this.radius = DEFAULT_RADIUS;
        //this.color[0] = 0;
    }

    public CustomMultipleDotSpan(float radius) {
        this.radius = radius;
        //this.color[0] = 0;
    }

    public CustomMultipleDotSpan(float radius, boolean[] dotList, int[] ColorList) {
        this.radius = radius;
        this.dotList = dotList;
        this.ColorList = ColorList;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void drawBackground(
            Canvas canvas, Paint paint,
            int left, int right, int top, int baseline, int bottom,
            CharSequence charSequence,
            int start, int end, int lineNum
    ) {
        // 부채꼴 그리기용
        RectF rect = new RectF();
        // dotList는 6개로 선언 된 배열이므로 true의 갯수를 확인함
        int cnt = 0;
        for(int i=0; i < 6; i++ ) {
            if(dotList[i] == true)
                cnt += 1;
        }
        drawDot(canvas, paint, left, right, top, baseline, bottom, charSequence, start, end, lineNum, cnt);

    }
    private void drawDot(Canvas canvas, Paint paint1,
                         int left, int right, int top, int baseline, int bottom,
                         CharSequence charSequence,
                         int start, int end, int lineNum, int cnt) {
        // Dot 삭제용
        Paint paint2 = new Paint();
        
        int middle = (left + right) / 2;
        int leftMost;

        // Dot 중앙 정렬 위해서
        if(cnt == 1)
            leftMost = 0;
        else
            leftMost = cnt * -5;
        // pain1은 Dot 뿐만 아니라 배경 텍스트 색에도 영향을 줌
        paint2.setColor(Color.WHITE);
        canvas.drawRect(middle-23,bottom-3,middle+33, bottom+3,paint2);

        for (int i = 0; i < cnt; i++) {
            int oldColor = paint1.getColor();
            if (dotList[i] != false) {
                paint1.setColor(ColorList[i]);
            }

            // Dot의 초기 x좌표 위치 조정
            canvas.drawCircle(middle + leftMost, bottom + radius, radius, paint1);
            // 텍스트도 영향을 받기에 Black으로 값을 주기 위해 마지막에 set
            paint1.setColor(oldColor);
            // 네모 => 연속 일정 설정 가능할 듯
            //canvas.drawRect(left,top+20,right,bottom,paint);
            // 부채꼴
            //canvas.drawArc(left+60,top+20, right+50, bottom+60,180, 90, true, paint);

            // dot 4개 이상 부터는 아래쪽에 위치 되도록 / 6개 제한은 리스트에서 하기로
            if(i == 2) {
                bottom += 20;
                leftMost -= 40;
            }
            else
                leftMost = leftMost + 20;
        }
    }
}
