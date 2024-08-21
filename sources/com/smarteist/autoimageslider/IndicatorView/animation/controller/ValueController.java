package com.smarteist.autoimageslider.IndicatorView.animation.controller;

import com.smarteist.autoimageslider.IndicatorView.animation.data.Value;
import com.smarteist.autoimageslider.IndicatorView.animation.type.ColorAnimation;
import com.smarteist.autoimageslider.IndicatorView.animation.type.DropAnimation;
import com.smarteist.autoimageslider.IndicatorView.animation.type.FillAnimation;
import com.smarteist.autoimageslider.IndicatorView.animation.type.ScaleAnimation;
import com.smarteist.autoimageslider.IndicatorView.animation.type.ScaleDownAnimation;
import com.smarteist.autoimageslider.IndicatorView.animation.type.SlideAnimation;
import com.smarteist.autoimageslider.IndicatorView.animation.type.SwapAnimation;
import com.smarteist.autoimageslider.IndicatorView.animation.type.ThinWormAnimation;
import com.smarteist.autoimageslider.IndicatorView.animation.type.WormAnimation;

public class ValueController {
    private ColorAnimation colorAnimation;
    private DropAnimation dropAnimation;
    private FillAnimation fillAnimation;
    private ScaleAnimation scaleAnimation;
    private ScaleDownAnimation scaleDownAnimation;
    private SlideAnimation slideAnimation;
    private SwapAnimation swapAnimation;
    private ThinWormAnimation thinWormAnimation;
    private UpdateListener updateListener;
    private WormAnimation wormAnimation;

    public interface UpdateListener {
        void onValueUpdated(Value value);
    }

    public ValueController(UpdateListener updateListener2) {
        this.updateListener = updateListener2;
    }

    public ColorAnimation color() {
        if (this.colorAnimation == null) {
            this.colorAnimation = new ColorAnimation(this.updateListener);
        }
        return this.colorAnimation;
    }

    public ScaleAnimation scale() {
        if (this.scaleAnimation == null) {
            this.scaleAnimation = new ScaleAnimation(this.updateListener);
        }
        return this.scaleAnimation;
    }

    public WormAnimation worm() {
        if (this.wormAnimation == null) {
            this.wormAnimation = new WormAnimation(this.updateListener);
        }
        return this.wormAnimation;
    }

    public SlideAnimation slide() {
        if (this.slideAnimation == null) {
            this.slideAnimation = new SlideAnimation(this.updateListener);
        }
        return this.slideAnimation;
    }

    public FillAnimation fill() {
        if (this.fillAnimation == null) {
            this.fillAnimation = new FillAnimation(this.updateListener);
        }
        return this.fillAnimation;
    }

    public ThinWormAnimation thinWorm() {
        if (this.thinWormAnimation == null) {
            this.thinWormAnimation = new ThinWormAnimation(this.updateListener);
        }
        return this.thinWormAnimation;
    }

    public DropAnimation drop() {
        if (this.dropAnimation == null) {
            this.dropAnimation = new DropAnimation(this.updateListener);
        }
        return this.dropAnimation;
    }

    public SwapAnimation swap() {
        if (this.swapAnimation == null) {
            this.swapAnimation = new SwapAnimation(this.updateListener);
        }
        return this.swapAnimation;
    }

    public ScaleDownAnimation scaleDown() {
        if (this.scaleDownAnimation == null) {
            this.scaleDownAnimation = new ScaleDownAnimation(this.updateListener);
        }
        return this.scaleDownAnimation;
    }
}
