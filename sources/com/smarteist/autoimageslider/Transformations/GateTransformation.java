package com.smarteist.autoimageslider.Transformations;

import android.view.View;
import com.smarteist.autoimageslider.SliderPager;

public class GateTransformation implements SliderPager.PageTransformer {
    private String TAG = "GateAnimationn";

    public void transformPage(View view, float f) {
        view.setTranslationX((-f) * ((float) view.getWidth()));
        if (f < -1.0f) {
            view.setAlpha(0.0f);
        } else if (f <= 0.0f) {
            view.setAlpha(1.0f);
            view.setPivotX(0.0f);
            view.setRotationY(Math.abs(f) * 90.0f);
        } else if (f <= 1.0f) {
            view.setAlpha(1.0f);
            view.setPivotX((float) view.getWidth());
            view.setRotationY(Math.abs(f) * -90.0f);
        } else {
            view.setAlpha(0.0f);
        }
    }
}
