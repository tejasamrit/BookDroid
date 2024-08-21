package com.smarteist.autoimageslider.IndicatorView.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import com.smarteist.autoimageslider.IndicatorView.animation.data.Value;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.AttributeController;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.MeasureController;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator;

public class DrawManager {
    private AttributeController attributeController = new AttributeController(this.indicator);
    private DrawController drawController;
    private Indicator indicator;
    private MeasureController measureController = new MeasureController();

    public DrawManager() {
        Indicator indicator2 = new Indicator();
        this.indicator = indicator2;
        this.drawController = new DrawController(indicator2);
    }

    public Indicator indicator() {
        if (this.indicator == null) {
            this.indicator = new Indicator();
        }
        return this.indicator;
    }

    public void setClickListener(DrawController.ClickListener clickListener) {
        this.drawController.setClickListener(clickListener);
    }

    public void touch(MotionEvent motionEvent) {
        this.drawController.touch(motionEvent);
    }

    public void updateValue(Value value) {
        this.drawController.updateValue(value);
    }

    public void draw(Canvas canvas) {
        this.drawController.draw(canvas);
    }

    public Pair<Integer, Integer> measureViewSize(int i, int i2) {
        return this.measureController.measureViewSize(this.indicator, i, i2);
    }

    public void initAttributes(Context context, AttributeSet attributeSet) {
        this.attributeController.init(context, attributeSet);
    }
}
