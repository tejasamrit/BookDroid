package com.smarteist.autoimageslider.IndicatorView.animation.controller;

import com.smarteist.autoimageslider.IndicatorView.animation.controller.ValueController;
import com.smarteist.autoimageslider.IndicatorView.animation.data.Value;
import com.smarteist.autoimageslider.IndicatorView.animation.type.BaseAnimation;
import com.smarteist.autoimageslider.IndicatorView.animation.type.DropAnimation;
import com.smarteist.autoimageslider.IndicatorView.animation.type.WormAnimation;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Orientation;
import com.smarteist.autoimageslider.IndicatorView.utils.CoordinatesUtils;

public class AnimationController {
    private Indicator indicator;
    private boolean isInteractive;
    private ValueController.UpdateListener listener;
    private float progress;
    private BaseAnimation runningAnimation;
    private ValueController valueController;

    public AnimationController(Indicator indicator2, ValueController.UpdateListener updateListener) {
        this.valueController = new ValueController(updateListener);
        this.listener = updateListener;
        this.indicator = indicator2;
    }

    public void interactive(float f) {
        this.isInteractive = true;
        this.progress = f;
        animate();
    }

    public void basic() {
        this.isInteractive = false;
        this.progress = 0.0f;
        animate();
    }

    public void end() {
        BaseAnimation baseAnimation = this.runningAnimation;
        if (baseAnimation != null) {
            baseAnimation.end();
        }
    }

    /* renamed from: com.smarteist.autoimageslider.IndicatorView.animation.controller.AnimationController$1 */
    static /* synthetic */ class C22561 {

        /* renamed from: $SwitchMap$com$smarteist$autoimageslider$IndicatorView$animation$type$IndicatorAnimationType */
        static final /* synthetic */ int[] f479x1c9e89f9;

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType[] r0 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f479x1c9e89f9 = r0
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f479x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x001d }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.COLOR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f479x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.SCALE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f479x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.WORM     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f479x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x003e }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.FILL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f479x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.SLIDE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f479x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.THIN_WORM     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = f479x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.DROP     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = f479x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x006c }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.SWAP     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = f479x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.SCALE_DOWN     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.smarteist.autoimageslider.IndicatorView.animation.controller.AnimationController.C22561.<clinit>():void");
        }
    }

    private void animate() {
        switch (C22561.f479x1c9e89f9[this.indicator.getAnimationType().ordinal()]) {
            case 1:
                this.listener.onValueUpdated((Value) null);
                return;
            case 2:
                colorAnimation();
                return;
            case 3:
                scaleAnimation();
                return;
            case 4:
                wormAnimation();
                return;
            case 5:
                fillAnimation();
                return;
            case 6:
                slideAnimation();
                return;
            case 7:
                thinWormAnimation();
                return;
            case 8:
                dropAnimation();
                return;
            case 9:
                swapAnimation();
                return;
            case 10:
                scaleDownAnimation();
                return;
            default:
                return;
        }
    }

    private void colorAnimation() {
        int selectedColor = this.indicator.getSelectedColor();
        int unselectedColor = this.indicator.getUnselectedColor();
        BaseAnimation duration = this.valueController.color().with(unselectedColor, selectedColor).duration(this.indicator.getAnimationDuration());
        if (this.isInteractive) {
            duration.progress(this.progress);
        } else {
            duration.start();
        }
        this.runningAnimation = duration;
    }

    private void scaleAnimation() {
        int selectedColor = this.indicator.getSelectedColor();
        int unselectedColor = this.indicator.getUnselectedColor();
        int radius = this.indicator.getRadius();
        float scaleFactor = this.indicator.getScaleFactor();
        BaseAnimation duration = this.valueController.scale().with(unselectedColor, selectedColor, radius, scaleFactor).duration(this.indicator.getAnimationDuration());
        if (this.isInteractive) {
            duration.progress(this.progress);
        } else {
            duration.start();
        }
        this.runningAnimation = duration;
    }

    private void wormAnimation() {
        int selectedPosition = this.indicator.isInteractiveAnimation() ? this.indicator.getSelectedPosition() : this.indicator.getLastSelectedPosition();
        int selectingPosition = this.indicator.isInteractiveAnimation() ? this.indicator.getSelectingPosition() : this.indicator.getSelectedPosition();
        WormAnimation duration = this.valueController.worm().with(CoordinatesUtils.getCoordinate(this.indicator, selectedPosition), CoordinatesUtils.getCoordinate(this.indicator, selectingPosition), this.indicator.getRadius(), selectingPosition > selectedPosition).duration(this.indicator.getAnimationDuration());
        if (this.isInteractive) {
            duration.progress(this.progress);
        } else {
            duration.start();
        }
        this.runningAnimation = duration;
    }

    private void slideAnimation() {
        BaseAnimation duration = this.valueController.slide().with(CoordinatesUtils.getCoordinate(this.indicator, this.indicator.isInteractiveAnimation() ? this.indicator.getSelectedPosition() : this.indicator.getLastSelectedPosition()), CoordinatesUtils.getCoordinate(this.indicator, this.indicator.isInteractiveAnimation() ? this.indicator.getSelectingPosition() : this.indicator.getSelectedPosition())).duration(this.indicator.getAnimationDuration());
        if (this.isInteractive) {
            duration.progress(this.progress);
        } else {
            duration.start();
        }
        this.runningAnimation = duration;
    }

    private void fillAnimation() {
        int selectedColor = this.indicator.getSelectedColor();
        int unselectedColor = this.indicator.getUnselectedColor();
        int radius = this.indicator.getRadius();
        int stroke = this.indicator.getStroke();
        BaseAnimation duration = this.valueController.fill().with(unselectedColor, selectedColor, radius, stroke).duration(this.indicator.getAnimationDuration());
        if (this.isInteractive) {
            duration.progress(this.progress);
        } else {
            duration.start();
        }
        this.runningAnimation = duration;
    }

    private void thinWormAnimation() {
        int selectedPosition = this.indicator.isInteractiveAnimation() ? this.indicator.getSelectedPosition() : this.indicator.getLastSelectedPosition();
        int selectingPosition = this.indicator.isInteractiveAnimation() ? this.indicator.getSelectingPosition() : this.indicator.getSelectedPosition();
        WormAnimation duration = this.valueController.thinWorm().with(CoordinatesUtils.getCoordinate(this.indicator, selectedPosition), CoordinatesUtils.getCoordinate(this.indicator, selectingPosition), this.indicator.getRadius(), selectingPosition > selectedPosition).duration(this.indicator.getAnimationDuration());
        if (this.isInteractive) {
            duration.progress(this.progress);
        } else {
            duration.start();
        }
        this.runningAnimation = duration;
    }

    private void dropAnimation() {
        int selectedPosition = this.indicator.isInteractiveAnimation() ? this.indicator.getSelectedPosition() : this.indicator.getLastSelectedPosition();
        int selectingPosition = this.indicator.isInteractiveAnimation() ? this.indicator.getSelectingPosition() : this.indicator.getSelectedPosition();
        int coordinate = CoordinatesUtils.getCoordinate(this.indicator, selectedPosition);
        int coordinate2 = CoordinatesUtils.getCoordinate(this.indicator, selectingPosition);
        int paddingTop = this.indicator.getPaddingTop();
        int paddingLeft = this.indicator.getPaddingLeft();
        if (this.indicator.getOrientation() != Orientation.HORIZONTAL) {
            paddingTop = paddingLeft;
        }
        int radius = this.indicator.getRadius();
        DropAnimation with = this.valueController.drop().duration(this.indicator.getAnimationDuration()).with(coordinate, coordinate2, (radius * 3) + paddingTop, radius + paddingTop, radius);
        if (this.isInteractive) {
            with.progress(this.progress);
        } else {
            with.start();
        }
        this.runningAnimation = with;
    }

    private void swapAnimation() {
        BaseAnimation duration = this.valueController.swap().with(CoordinatesUtils.getCoordinate(this.indicator, this.indicator.isInteractiveAnimation() ? this.indicator.getSelectedPosition() : this.indicator.getLastSelectedPosition()), CoordinatesUtils.getCoordinate(this.indicator, this.indicator.isInteractiveAnimation() ? this.indicator.getSelectingPosition() : this.indicator.getSelectedPosition())).duration(this.indicator.getAnimationDuration());
        if (this.isInteractive) {
            duration.progress(this.progress);
        } else {
            duration.start();
        }
        this.runningAnimation = duration;
    }

    private void scaleDownAnimation() {
        int selectedColor = this.indicator.getSelectedColor();
        int unselectedColor = this.indicator.getUnselectedColor();
        int radius = this.indicator.getRadius();
        float scaleFactor = this.indicator.getScaleFactor();
        BaseAnimation duration = this.valueController.scaleDown().with(unselectedColor, selectedColor, radius, scaleFactor).duration(this.indicator.getAnimationDuration());
        if (this.isInteractive) {
            duration.progress(this.progress);
        } else {
            duration.start();
        }
        this.runningAnimation = duration;
    }
}
