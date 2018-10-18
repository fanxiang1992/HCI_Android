package com.example.hcipaintframework;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Paint;

public class Line implements CanvasShape {

    PointF mouseStart;
    PointF mouseEnd;
    int brush;
    int color;

    public Line(PointF mouseStart, PointF mouseEnd, int brush, int color) {
        this.mouseStart = mouseStart;
        this.mouseEnd = mouseEnd;
        this.brush = brush;
        this.color = color;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(brush);
        canvas.drawLine(mouseStart.x, mouseStart.y, mouseEnd.x, mouseEnd.y, paint);
    }
}
