package com.futuremind.recyclerviewfastscroll.viewprovider;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.view.View;
import com.futuremind.recyclerviewfastscroll.C0860R;

public class VisibilityAnimationManager {
    protected AnimatorSet hideAnimator;
    private float pivotXRelative;
    private float pivotYRelative;
    protected AnimatorSet showAnimator;
    protected final View view;

    protected VisibilityAnimationManager(final View view2, int i, int i2, float f, float f2, int i3) {
        this.view = view2;
        this.pivotXRelative = f;
        this.pivotYRelative = f2;
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(view2.getContext(), i2);
        this.hideAnimator = animatorSet;
        animatorSet.setStartDelay((long) i3);
        this.hideAnimator.setTarget(view2);
        AnimatorSet animatorSet2 = (AnimatorSet) AnimatorInflater.loadAnimator(view2.getContext(), i);
        this.showAnimator = animatorSet2;
        animatorSet2.setTarget(view2);
        this.hideAnimator.addListener(new AnimatorListenerAdapter() {
            boolean wasCanceled;

            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (!this.wasCanceled) {
                    view2.setVisibility(4);
                }
                this.wasCanceled = false;
            }

            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                this.wasCanceled = true;
            }
        });
        updatePivot();
    }

    public void show() {
        this.hideAnimator.cancel();
        if (this.view.getVisibility() == 4) {
            this.view.setVisibility(0);
            updatePivot();
            this.showAnimator.start();
        }
    }

    public void hide() {
        updatePivot();
        this.hideAnimator.start();
    }

    /* access modifiers changed from: protected */
    public void updatePivot() {
        View view2 = this.view;
        view2.setPivotX(this.pivotXRelative * ((float) view2.getMeasuredWidth()));
        View view3 = this.view;
        view3.setPivotY(this.pivotYRelative * ((float) view3.getMeasuredHeight()));
    }

    public static abstract class AbsBuilder<T extends VisibilityAnimationManager> {
        protected int hideAnimatorResource = C0860R.animator.fastscroll__default_hide;
        protected int hideDelay = 1000;
        protected float pivotX = 0.5f;
        protected float pivotY = 0.5f;
        protected int showAnimatorResource = C0860R.animator.fastscroll__default_show;
        protected final View view;

        public abstract T build();

        public AbsBuilder(View view2) {
            this.view = view2;
        }

        public AbsBuilder<T> withShowAnimator(int i) {
            this.showAnimatorResource = i;
            return this;
        }

        public AbsBuilder<T> withHideAnimator(int i) {
            this.hideAnimatorResource = i;
            return this;
        }

        public AbsBuilder<T> withHideDelay(int i) {
            this.hideDelay = i;
            return this;
        }

        public AbsBuilder<T> withPivotX(float f) {
            this.pivotX = f;
            return this;
        }

        public AbsBuilder<T> withPivotY(float f) {
            this.pivotY = f;
            return this;
        }
    }

    public static class Builder extends AbsBuilder<VisibilityAnimationManager> {
        public Builder(View view) {
            super(view);
        }

        public VisibilityAnimationManager build() {
            return new VisibilityAnimationManager(this.view, this.showAnimatorResource, this.hideAnimatorResource, this.pivotX, this.pivotY, this.hideDelay);
        }
    }
}
