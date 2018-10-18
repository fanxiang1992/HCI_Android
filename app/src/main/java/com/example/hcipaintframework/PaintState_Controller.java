package com.example.hcipaintframework;

import android.widget.AdapterView;
import android.view.View;
import android.widget.CompoundButton;

import android.util.Pair;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.ArrayList;

// Notice that the controller could have been included in the model class
// (and that may have been better) but I wanted to demonstrate separation of concerns.
public class PaintState_Controller implements   AdapterView.OnItemSelectedListener,
                                                View.OnClickListener,
                                                CompoundButton.OnCheckedChangeListener {
    private PaintState_Model model;
    // Hint: we can make pairs of conponent IDs and shapes so that we
    // can make a button to a particular shape to draw.
    private ArrayList<Pair<Integer, Integer>> togglePairs;

    public PaintState_Controller (PaintState_Model model) {
        this.model = model;
        togglePairs = new ArrayList<>();
    }

    public void addIDControlPair(int id, int shapeID) {
        // Add code here
    }

    /**
     * getShapeIdFromView takes a toggle button view and
     * determines the shape value associated with that button.
     *
     * @param view of the toggle button clicked
     * @return the int value of the shape associated with the button.
     */
    public int getShapeIdFromView(View view) {
        // Fill in code here
        return 0;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if((view instanceof TextView)&&(model!= null)) {
            TextView tView = (TextView) view;
            try {
                String numTxt = tView.getText().toString();
                System.out.println("Field value is: " + numTxt);
                if(numTxt != null) {
                    // Not sure why Integer.parseValue isn't working so went the long way.
                    Integer brushSize = Integer.valueOf(numTxt);
                    //TODO: fix when we get LiveData
                    model.setBrushSize(brushSize.intValue());
                }
            } catch (NullPointerException e) {
                System.out.println("Brush size change error");
            }
        }
        System.out.println("Selected element");
    }
    public void onCheckedChanged(CompoundButton button, boolean check) {
        //TODO: fix when we get LiveData
        model.setFillOn(check);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        System.out.println("Nothing selected");
    }
    public void onClick(View arg0) {
        if ((arg0 instanceof ToggleButton) && (model != null)) {
            //TODO: fix when we get LiveData
            model.setCurrShape(getShapeIdFromView(arg0));
        }
        System.out.println("Button " + arg0.toString() + " clicked");
    }
}
