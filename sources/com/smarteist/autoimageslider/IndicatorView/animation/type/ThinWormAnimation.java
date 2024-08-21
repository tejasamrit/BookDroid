package com.smarteist.autoimageslider.IndicatorView.animation.type;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.smarteist.autoimageslider.IndicatorView.animation.controller.ValueController;
import com.smarteist.autoimageslider.IndicatorView.animation.data.type.ThinWormAnimationValue;
import com.smarteist.autoimageslider.IndicatorView.animation.type.WormAnimation;

public class ThinWormAnimation extends WormAnimation {
    private ThinWormAnimationValue value = new ThinWormAnimationValue();

    public ThinWormAnimation(ValueController.UpdateListener updateListener) {
        super(updateListener);
    }

    public ThinWormAnimation duration(long j) {
        super.duration(j);
        return this;
    }

    public WormAnimation with(int i, int i2, int i3, boolean z) {
        int i4 = i;
        int i5 = i3;
        boolean z2 = z;
        if (hasChanges(i, i2, i3, z)) {
            this.animator = createAnimator();
            this.coordinateStart = i4;
            this.coordinateEnd = i2;
            this.radius = i5;
            this.isRightSide = z2;
            int i6 = i5 * 2;
            this.rectLeftEdge = i4 - i5;
            this.rectRightEdge = i4 + i5;
            this.value.setRectStart(this.rectLeftEdge);
            this.value.setRectEnd(this.rectRightEdge);
            this.value.setHeight(i6);
            WormAnimation.RectValues createRectValues = createRectValues(z2);
            long j = (long) (((double) this.animationDuration) * 0.8d);
            long j2 = (long) (((double) this.animationDuration) * 0.2d);
            long j3 = (long) (((double) this.animationDuration) * 0.5d);
            long j4 = (long) (((double) this.animationDuration) * 0.5d);
            long j5 = j4;
            long j6 = j;
            ValueAnimator createWormAnimator = createWormAnimator(createRectValues.fromX, createRectValues.toX, j6, false, this.value);
            ValueAnimator createWormAnimator2 = createWormAnimator(createRectValues.reverseFromX, createRectValues.reverseToX, j6, true, this.value);
            createWormAnimator2.setStartDelay(j2);
            int i7 = i6;
            int i8 = i3;
            long j7 = j3;
            ValueAnimator createHeightAnimator = createHeightAnimator(i7, i8, j7);
            ValueAnimator createHeightAnimator2 = createHeightAnimator(i8, i7, j7);
            createHeightAnimator2.setStartDelay(j5);
            ((AnimatorSet) this.animator).playTogether(new Animator[]{createWormAnimator, createWormAnimator2, createHeightAnimator, createHeightAnimator2});
        }
        return this;
    }

    private ValueAnimator createHeightAnimator(int i, int i2, long j) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i, i2});
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.setDuration(j);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ThinWormAnimation.this.onAnimateUpdated(valueAnimator);
            }
        });
        return ofInt;
    }

    /* access modifiers changed from: private */
    public void onAnimateUpdated(ValueAnimator valueAnimator) {
        this.value.setHeight(((Integer) valueAnimator.getAnimatedValue()).intValue());
        if (this.listener != null) {
            this.listener.onValueUpdated(this.value);
        }
    }

    public ThinWormAnimation progress(float f) {
        if (this.animator != null) {
            long j = (long) (f * ((float) this.animationDuration));
            int size = ((AnimatorSet) this.animator).getChildAnimations().size();
            for (int i = 0; i < size; i++) {
                ValueAnimator valueAnimator = (ValueAnimator) ((AnimatorSet) this.animator).getChildAnimations().get(i);
                long startDelay = j - valueAnimator.getStartDelay();
                long duration = valueAnimator.getDuration();
                if (startDelay > duration) {
                    startDelay = duration;
                } else if (startDelay < 0) {
                    startDelay = 0;
                }
                if ((i != size - 1 || startDelay > 0) && valueAnimator.getValues() != null && valueAnimator.getValues().length > 0) {
                    valueAnimator.setCurrentPlayTime(startDelay);
                }
            }
        }
        return this;
    }
}
