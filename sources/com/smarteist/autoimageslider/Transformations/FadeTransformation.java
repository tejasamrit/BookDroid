package com.smarteist.autoimageslider.Transformations;

import android.view.View;
import com.smarteist.autoimageslider.SliderPager;

public class FadeTransformation implements SliderPager.PageTransformer {
    public void transformPage(View view, float f) {
        view.setTranslationX((-f) * ((float) view.getWidth()));
        if (f < -1.0f || f > 1.0f) {
            view.setAlpha(0.0f);
            return;
        }
        int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        if (i <= 0 || f <= 1.0f) {
            view.setAlpha(i <= 0 ? f + 1.0f : 1.0f - f);
        } else if (f == 0.0f) {
            view.setAlpha(1.0f);
        }
    }
}
