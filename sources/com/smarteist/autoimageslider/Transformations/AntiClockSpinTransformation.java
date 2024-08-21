package com.smarteist.autoimageslider.Transformations;

import android.view.View;
import com.smarteist.autoimageslider.SliderPager;

public class AntiClockSpinTransformation implements SliderPager.PageTransformer {
    public void transformPage(View view, float f) {
        view.setTranslationX((-f) * ((float) view.getWidth()));
        if (((double) Math.abs(f)) < 0.5d) {
            view.setVisibility(0);
            view.setScaleX(1.0f - Math.abs(f));
            view.setScaleY(1.0f - Math.abs(f));
        } else if (((double) Math.abs(f)) > 0.5d) {
            view.setVisibility(8);
        }
        if (f < -1.0f) {
            view.setAlpha(0.0f);
        } else if (f <= 0.0f) {
            view.setAlpha(1.0f);
            view.setRotation((1.0f - Math.abs(f)) * 360.0f);
        } else if (f <= 1.0f) {
            view.setAlpha(1.0f);
            view.setRotation((1.0f - Math.abs(f)) * -360.0f);
        } else {
            view.setAlpha(0.0f);
        }
    }
}
