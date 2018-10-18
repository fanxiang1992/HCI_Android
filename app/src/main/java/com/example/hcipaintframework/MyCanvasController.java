package com.example.hcipaintframework;

import android.view.View;
import android.graphics.PointF;
import android.view.DragEvent;
import android.view.MotionEvent;

// Separated from MyCanvasView since the callbacks
public class MyCanvasController implements View.OnTouchListener{
    private PointF startPt;
    private PointF endPt;
    private boolean isDragOn;
    private boolean isNeedingPt;

    public MyCanvasController() {
        startPt = new PointF();
        endPt = new PointF();
        isNeedingPt = false;
        isDragOn = false;
    }

    public void resetTouch(){
        isDragOn = false;
        isNeedingPt = false;
        startPt.set(0, 0);
        endPt.set(0, 0);
    }

    public boolean onTouch(View v, MotionEvent event) {
        MyCanvasView view = (MyCanvasView) v;
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                // Only do something on touch up IF we are waiting for a third point.
                if(isNeedingPt) {
                    PointF thirdPt = new PointF(event.getX(), event.getY());
                    view.addShape(startPt, endPt, thirdPt);
                    resetTouch();
                }
                // Otherwise just end of a drag operation.
                else {
                    isDragOn = false;
                    // Shape only created if third point is not required.
                    // Otherwise set "isNeedingPt" flag
                    endPt.set(event.getX(),event.getY());
                    CanvasShape shape = view.addShape(startPt, endPt, null);
                    if (shape == null) {
                        isNeedingPt = true;
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                if (!isNeedingPt){
                    startPt.set(event.getX(), event.getY());
                    this.isDragOn = true;
                    isNeedingPt = false;
                    view.invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                // Currently does nothing but could be used to dynamic drawing.
                break;
        }
        return true;
    }
}
