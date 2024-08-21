package com.smarteist.autoimageslider.IndicatorView.animation.data.type;

import com.smarteist.autoimageslider.IndicatorView.animation.data.Value;

public class SlideAnimationValue implements Value {
    private int coordinate;

    public int getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(int i) {
        this.coordinate = i;
    }
}
