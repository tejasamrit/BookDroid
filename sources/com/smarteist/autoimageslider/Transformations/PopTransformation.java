package com.smarteist.autoimageslider.Transformations;

import android.view.View;
import com.smarteist.autoimageslider.SliderPager;

public class PopTransformation implements SliderPager.PageTransformer {
    public void transformPage(View view, float f) {
        view.setTranslationX((-f) * ((float) view.getWidth()));
        if (((double) Math.abs(f)) < 0.5d) {
            view.setVisibility(0);
            view.setScaleX(1.0f - Math.abs(f));
            view.setScaleY(1.0f - Math.abs(f));
        } else if (((double) Math.abs(f)) > 0.5d) {
            view.setVisibility(8);
        }
    }
}
