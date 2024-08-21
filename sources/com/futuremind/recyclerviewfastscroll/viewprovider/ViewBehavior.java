package com.futuremind.recyclerviewfastscroll.viewprovider;

public interface ViewBehavior {
    void onHandleGrabbed();

    void onHandleReleased();

    void onScrollFinished();

    void onScrollStarted();
}
