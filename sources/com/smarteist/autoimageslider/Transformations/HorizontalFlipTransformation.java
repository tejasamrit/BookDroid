package com.smarteist.autoimageslider.Transformations;

import android.util.Log;
import android.view.View;
import com.smarteist.autoimageslider.SliderPager;

public class HorizontalFlipTransformation implements SliderPager.PageTransformer {
    public void transformPage(View view, float f) {
        view.setTranslationX((-f) * ((float) view.getWidth()));
        view.setCameraDistance(20000.0f);
        double d = (double) f;
        if (d >= 0.5d || d <= -0.5d) {
            view.setVisibility(4);
        } else {
            view.setVisibility(0);
        }
        if (f < -1.0f) {
            view.setAlpha(0.0f);
        } else if (f <= 0.0f) {
            view.setAlpha(1.0f);
            view.setRotationX(((1.0f - Math.abs(f)) + 1.0f) * 180.0f);
            Log.e("HORIZONTAL", "position <= 0     " + (((1.0f - Math.abs(f)) + 1.0f) * 180.0f));
        } else if (f <= 1.0f) {
            view.setAlpha(1.0f);
            view.setRotationX(((1.0f - Math.abs(f)) + 1.0f) * -180.0f);
            Log.e("HORIZONTAL", "position <= 1     " + (((1.0f - Math.abs(f)) + 1.0f) * -180.0f));
        } else {
            view.setAlpha(0.0f);
        }
    }
}
