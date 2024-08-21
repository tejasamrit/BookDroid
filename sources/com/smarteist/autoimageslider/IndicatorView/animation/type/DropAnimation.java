package com.smarteist.autoimageslider.IndicatorView.animation.type;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.smarteist.autoimageslider.IndicatorView.animation.controller.ValueController;
import com.smarteist.autoimageslider.IndicatorView.animation.data.type.DropAnimationValue;
import java.util.Iterator;

public class DropAnimation extends BaseAnimation<AnimatorSet> {
    private int heightEnd;
    private int heightStart;
    private int radius;
    private DropAnimationValue value = new DropAnimationValue();
    private int widthEnd;
    private int widthStart;

    private enum AnimationType {
        Width,
        Height,
        Radius
    }

    public DropAnimation(ValueController.UpdateListener updateListener) {
        super(updateListener);
    }

    public AnimatorSet createAnimator() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        return animatorSet;
    }

    public DropAnimation progress(float f) {
        if (this.animator != null) {
            long j = (long) (f * ((float) this.animationDuration));
            boolean z = false;
            Iterator<Animator> it = ((AnimatorSet) this.animator).getChildAnimations().iterator();
            while (it.hasNext()) {
                ValueAnimator valueAnimator = (ValueAnimator) it.next();
                long duration = valueAnimator.getDuration();
                long j2 = z ? j - duration : j;
                if (j2 >= 0) {
                    if (j2 >= duration) {
                        j2 = duration;
                    }
                    if (valueAnimator.getValues() != null && valueAnimator.getValues().length > 0) {
                        valueAnimator.setCurrentPlayTime(j2);
                    }
                    if (!z && duration >= this.animationDuration) {
                        z = true;
                    }
                }
            }
        }
        return this;
    }

    public DropAnimation duration(long j) {
        super.duration(j);
        return this;
    }

    public DropAnimation with(int i, int i2, int i3, int i4, int i5) {
        int i6 = i5;
        if (hasChanges(i, i2, i3, i4, i5)) {
            this.animator = createAnimator();
            int i7 = i;
            this.widthStart = i7;
            int i8 = i2;
            this.widthEnd = i8;
            this.heightStart = i3;
            this.heightEnd = i4;
            this.radius = i6;
            int i9 = (int) (((double) i6) / 1.5d);
            long j = this.animationDuration / 2;
            ValueAnimator createValueAnimation = createValueAnimation(i7, i8, this.animationDuration, AnimationType.Width);
            long j2 = j;
            ValueAnimator createValueAnimation2 = createValueAnimation(i3, i4, j2, AnimationType.Height);
            ValueAnimator createValueAnimation3 = createValueAnimation(i5, i9, j2, AnimationType.Radius);
            ValueAnimator createValueAnimation4 = createValueAnimation(i4, i3, j2, AnimationType.Height);
            ((AnimatorSet) this.animator).play(createValueAnimation2).with(createValueAnimation3).with(createValueAnimation).before(createValueAnimation4).before(createValueAnimation(i9, i5, j2, AnimationType.Radius));
        }
        return this;
    }

    private ValueAnimator createValueAnimation(int i, int i2, long j, final AnimationType animationType) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i, i2});
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.setDuration(j);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                DropAnimation.this.onAnimatorUpdate(valueAnimator, animationType);
            }
        });
        return ofInt;
    }

    /* access modifiers changed from: private */
    public void onAnimatorUpdate(ValueAnimator valueAnimator, AnimationType animationType) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        int i = C22592.f480xb6e4af05[animationType.ordinal()];
        if (i == 1) {
            this.value.setWidth(intValue);
        } else if (i == 2) {
            this.value.setHeight(intValue);
        } else if (i == 3) {
            this.value.setRadius(intValue);
        }
        if (this.listener != null) {
            this.listener.onValueUpdated(this.value);
        }
    }

    /* renamed from: com.smarteist.autoimageslider.IndicatorView.animation.type.DropAnimation$2 */
    static /* synthetic */ class C22592 {

        /* renamed from: $SwitchMap$com$smarteist$autoimageslider$IndicatorView$animation$type$DropAnimation$AnimationType */
        static final /* synthetic */ int[] f480xb6e4af05;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.smarteist.autoimageslider.IndicatorView.animation.type.DropAnimation$AnimationType[] r0 = com.smarteist.autoimageslider.IndicatorView.animation.type.DropAnimation.AnimationType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f480xb6e4af05 = r0
                com.smarteist.autoimageslider.IndicatorView.animation.type.DropAnimation$AnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.DropAnimation.AnimationType.Width     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f480xb6e4af05     // Catch:{ NoSuchFieldError -> 0x001d }
                com.smarteist.autoimageslider.IndicatorView.animation.type.DropAnimation$AnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.DropAnimation.AnimationType.Height     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f480xb6e4af05     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.smarteist.autoimageslider.IndicatorView.animation.type.DropAnimation$AnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.DropAnimation.AnimationType.Radius     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.smarteist.autoimageslider.IndicatorView.animation.type.DropAnimation.C22592.<clinit>():void");
        }
    }

    private boolean hasChanges(int i, int i2, int i3, int i4, int i5) {
        if (this.widthStart == i && this.widthEnd == i2 && this.heightStart == i3 && this.heightEnd == i4 && this.radius == i5) {
            return false;
        }
        return true;
    }
}
