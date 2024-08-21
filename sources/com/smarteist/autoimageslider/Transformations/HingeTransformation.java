package com.smarteist.autoimageslider.Transformations;

import android.view.View;
import com.smarteist.autoimageslider.SliderPager;

public class HingeTransformation implements SliderPager.PageTransformer {
    public void transformPage(View view, float f) {
        view.setTranslationX((-f) * ((float) view.getWidth()));
        view.setPivotX(0.0f);
        view.setPivotY(0.0f);
        if (f < -1.0f) {
            view.setAlpha(0.0f);
        } else if (f <= 0.0f) {
            view.setRotation(Math.abs(f) * 90.0f);
            view.setAlpha(1.0f - Math.abs(f));
        } else if (f <= 1.0f) {
            view.setRotation(0.0f);
            view.setAlpha(1.0f);
        } else {
            view.setAlpha(0.0f);
        }
    }
}
