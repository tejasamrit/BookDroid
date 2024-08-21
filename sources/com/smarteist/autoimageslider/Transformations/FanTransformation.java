package com.smarteist.autoimageslider.Transformations;

import android.view.View;
import com.smarteist.autoimageslider.SliderPager;

public class FanTransformation implements SliderPager.PageTransformer {
    public void transformPage(View view, float f) {
        view.setTranslationX((-f) * ((float) view.getWidth()));
        view.setPivotX(0.0f);
        view.setPivotY((float) (view.getHeight() / 2));
        view.setCameraDistance(20000.0f);
        if (f < -1.0f) {
            view.setAlpha(0.0f);
        } else if (f <= 0.0f) {
            view.setAlpha(1.0f);
            view.setRotationY(Math.abs(f) * -120.0f);
        } else if (f <= 1.0f) {
            view.setAlpha(1.0f);
            view.setRotationY(Math.abs(f) * 120.0f);
        } else {
            view.setAlpha(0.0f);
        }
    }
}
