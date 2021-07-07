package com.example.scheduler;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.text.style.LineBackgroundSpan;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class CustomMultipleDotSpan implements LineBackgroundSpan {
    public static final float DEFAULT_RADIUS = 3;
    private final float radius;
    private int[] color = new int[0];

    public CustomMultipleDotSpan() {
        this.radius = DEFAULT_RADIUS;
        this.color[0] = 0;
    }

    public CustomMultipleDotSpan(int color) {
        this.radius = DEFAULT_RADIUS;
        this.color[0] = 0;
    }

    public CustomMultipleDotSpan(float radius) {
        this.radius = radius;
        this.color[0] = 0;
    }

    public CustomMultipleDotSpan(float radius, int[] color) {
        this.radius = radius;
        this.color = color;
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

        int total = color.length > 5 ? 5 : color.length;
        // Dot의 초기 x좌표 위치 조정
        int leftMost = (total - 1) * -5;

        for (int i = 0; i < total; i++) {
            int oldColor = paint.getColor();
            if (color[i] != 0) {
                paint.setColor(color[i]);
            }
            canvas.drawCircle((left + right) / 2 + leftMost, bottom + radius, radius, paint);

            // 네모 => 연속 일정 설정 가능할 듯
            //canvas.drawRect(left,top+20,right,bottom,paint);
            // 부채꼴
            //canvas.drawArc(left+60,top+20, right+50, bottom+60,180, 90, true, paint);

            // dot 4개 이상 부터는 아래쪽에 위치 되도록 / 6개 제한은 리스트에서 하기로
            paint.setColor(oldColor);
            if(i == 2) {
                bottom += 20;
                leftMost -= 40;
            }
            else
                leftMost = leftMost + 20;
        }
    }
}
