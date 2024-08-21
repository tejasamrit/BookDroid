package com.smarteist.autoimageslider.IndicatorView.draw.drawer.type;

import android.graphics.Paint;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator;

class BaseDrawer {
    Indicator indicator;
    Paint paint;

    BaseDrawer(Paint paint2, Indicator indicator2) {
        this.paint = paint2;
        this.indicator = indicator2;
    }
}
