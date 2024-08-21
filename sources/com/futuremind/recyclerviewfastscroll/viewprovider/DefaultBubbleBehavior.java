package com.futuremind.recyclerviewfastscroll.viewprovider;

public class DefaultBubbleBehavior implements ViewBehavior {
    private final VisibilityAnimationManager animationManager;

    public void onScrollFinished() {
    }

    public void onScrollStarted() {
    }

    public DefaultBubbleBehavior(VisibilityAnimationManager visibilityAnimationManager) {
        this.animationManager = visibilityAnimationManager;
    }

    public void onHandleGrabbed() {
        this.animationManager.show();
    }

    public void onHandleReleased() {
        this.animationManager.hide();
    }
}
