package com.smarteist.autoimageslider.IndicatorView.animation.data.type;

import com.smarteist.autoimageslider.IndicatorView.animation.data.Value;

public class WormAnimationValue implements Value {
    private int rectEnd;
    private int rectStart;

    public int getRectStart() {
        return this.rectStart;
    }

    public void setRectStart(int i) {
        this.rectStart = i;
    }

    public int getRectEnd() {
        return this.rectEnd;
    }

    public void setRectEnd(int i) {
        this.rectEnd = i;
    }
}
