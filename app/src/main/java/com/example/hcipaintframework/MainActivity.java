package com.example.hcipaintframework;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.databinding.DataBindingUtil;

import com.example.hcipaintframework.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PaintState_Model stateModel;
    private PaintState_Controller stateController;
    private MyCanvasView canvas;

    @Override
    protected void onStart() {
        super.onStart();
        if(binding != null) {
            // Color picker (since it's someone else's project) has been included.
            binding.ColorPicker.setOnClickListener(new ColorPickerListener(this, stateModel));
            // Shape selector controls listener
            // filled toggle controls listener
            binding.TogBLine.setOnClickListener(stateController);

            // Map the view IDs with the control to make intelligent model calls
            // TODO: REMOVE THIS FOR I5??
            stateController.addIDControlPair(binding.TogBLine.getId(), PaintState_Model.DLINE);
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setFontSpinner();

        // TODO:  We will add code here for storing and retrieving system state.
        stateModel = new PaintState_Model();

        canvas = binding.canvasView;
        canvas.setStateModel(stateModel);
        stateController = new PaintState_Controller(stateModel);
        canvas.setStateModel(stateModel);
    }


    /** Programmatically setting the brush size values in the spinner
     * rather than simply using XML
     * @return Spinner View that was altered.
    */
    private Spinner setFontSpinner() {
        return null;
    }

    public void setShape(@PaintState_Model.Shape int shape) {
        //TODO: to be modified when ViewModelProviders are used.
        if(stateModel != null) {
            stateModel.setCurrShape(shape);
        }
        if (binding != null) {
            switch (shape) {
                case PaintState_Model.DOVAL:
                    binding.TogBLine.setChecked(false);
                    break;
                case PaintState_Model.DCURVE:
                    binding.TogBLine.setChecked(false);
                    break;
                case PaintState_Model.DLINE:
                    binding.TogBLine.setChecked(true);
                    break;
                default:
                    binding.TogBLine.setChecked(false);
                    break;
            }
        }
    } // end of setShape toggle control.
}
