package com.smarteist.autoimageslider.IndicatorView;

import com.smarteist.autoimageslider.IndicatorView.animation.AnimationManager;
import com.smarteist.autoimageslider.IndicatorView.animation.controller.ValueController;
import com.smarteist.autoimageslider.IndicatorView.animation.data.Value;
import com.smarteist.autoimageslider.IndicatorView.draw.DrawManager;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator;

public class IndicatorManager implements ValueController.UpdateListener {
    private AnimationManager animationManager;
    private DrawManager drawManager;
    private C2253Listener listener;

    /* renamed from: com.smarteist.autoimageslider.IndicatorView.IndicatorManager$Listener */
    interface C2253Listener {
        void onIndicatorUpdated();
    }

    IndicatorManager(C2253Listener listener2) {
        this.listener = listener2;
        DrawManager drawManager2 = new DrawManager();
        this.drawManager = drawManager2;
        this.animationManager = new AnimationManager(drawManager2.indicator(), this);
    }

    public AnimationManager animate() {
        return this.animationManager;
    }

    public Indicator indicator() {
        return this.drawManager.indicator();
    }

    public DrawManager drawer() {
        return this.drawManager;
    }

    public void onValueUpdated(Value value) {
        this.drawManager.updateValue(value);
        C2253Listener listener2 = this.listener;
        if (listener2 != null) {
            listener2.onIndicatorUpdated();
        }
    }
}
