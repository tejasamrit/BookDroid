package com.smarteist.autoimageslider.Transformations;

import android.view.View;
import com.smarteist.autoimageslider.SliderPager;

public class CubeOutDepthTransformation implements SliderPager.PageTransformer {
    public void transformPage(View view, float f) {
        if (f < -1.0f) {
            view.setAlpha(0.0f);
        } else if (f <= 0.0f) {
            view.setAlpha(1.0f);
            view.setPivotX((float) view.getWidth());
            view.setRotationY(Math.abs(f) * -90.0f);
        } else if (f <= 1.0f) {
            view.setAlpha(1.0f);
            view.setPivotX(0.0f);
            view.setRotationY(Math.abs(f) * 90.0f);
        } else {
            view.setAlpha(0.0f);
        }
        if (((double) Math.abs(f)) <= 0.5d) {
            view.setScaleY(Math.max(0.4f, 1.0f - Math.abs(f)));
        } else if (Math.abs(f) <= 1.0f) {
            view.setScaleY(Math.max(0.4f, 1.0f - Math.abs(f)));
        }
    }
}
