package com.example.hcipaintframework;
import android.graphics.PointF;
import android.widget.LinearLayout;

public class ShapeFactory {

    public static CanvasShape makeShape(@PaintState_Model.Shape int shape, PointF mouseStart,
                                        PointF mouseMid, PointF mouseEnd,
                                        int brush, int color, boolean fill) {
        // You would add your code here
        // You may also want functions for determining specific points you want based
        // on the two mouse points (drawing a rect is not the same as drawing a line)
        switch (shape) {
            case 0:
                return new Line(mouseStart, mouseEnd, brush, color);
            default:
                break;
        }
        return null;
    }

}
