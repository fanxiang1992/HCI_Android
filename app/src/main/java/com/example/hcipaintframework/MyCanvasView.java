package com.example.hcipaintframework;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import java.util.ArrayList;

import android.graphics.PointF;
import android.view.View;



public class MyCanvasView extends View {
    private PaintState_Model model;
    // TO BE CHANGED IN LATER ASSIGNMENTS
    private ArrayList<CanvasShape> a_shapeList;

    public MyCanvasView(Context context) {
        super(context);
        MyCanvasController controller = new MyCanvasController();
        a_shapeList = new ArrayList<>();
        this.setOnTouchListener(controller);
    }

    public MyCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        MyCanvasController controller = new MyCanvasController();
        a_shapeList = new ArrayList<>();
        this.setOnTouchListener(controller);
    }

    private ArrayList<CanvasShape> getShapeList() {
        return a_shapeList;
    }

    public void setStateModel(PaintState_Model pvm) {
        model = pvm;
    }

    /**
     * TO ADD: you don't need to write this function but I think it would be useful.
     * addShape takes a start, end and (optional) guide point
     * and generates a shape based on the current paint state.
     * @param startPt start point or minimum point
     * @param endPt end point or maximum point
     * @param guidePt point used for curves but null for other shapes
     * @return the generated shape based on the paint state. If a curve
     * with no guide point or an error occurs then return null.
     */
    public CanvasShape addShape(PointF startPt, PointF endPt, PointF guidePt){
        CanvasShape shape = ShapeFactory.makeShape(model.getCurrShape(), startPt, guidePt, endPt,
                model.getBrushSize(), model.getbColor(), model.isFillShape());
        a_shapeList.add(shape);
        return shape;
    }

    public void clear() {
        a_shapeList.clear();
        invalidate();
    }

    // TODO
    // Finally, you will want to override the draw method for canvases so you can draw all the shapes
    @Override
    protected void onDraw(Canvas canvas) {
        for (CanvasShape shape: a_shapeList) {
            shape.draw(canvas);
        }
    }
}
