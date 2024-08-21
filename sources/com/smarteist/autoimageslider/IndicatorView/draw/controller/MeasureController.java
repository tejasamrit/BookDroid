package com.smarteist.autoimageslider.IndicatorView.draw.controller;

import android.util.Pair;
import android.view.View;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Orientation;

public class MeasureController {
    public Pair<Integer, Integer> measureViewSize(Indicator indicator, int i, int i2) {
        int i3;
        int i4;
        Indicator indicator2 = indicator;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        int count = indicator.getCount();
        int radius = indicator.getRadius();
        int stroke = indicator.getStroke();
        int padding = indicator.getPadding();
        int paddingLeft = indicator.getPaddingLeft();
        int paddingTop = indicator.getPaddingTop();
        int paddingRight = indicator.getPaddingRight();
        int paddingBottom = indicator.getPaddingBottom();
        int i5 = radius * 2;
        Orientation orientation = indicator.getOrientation();
        int i6 = 0;
        if (count != 0) {
            i3 = (i5 * count) + (stroke * 2 * count) + (padding * (count - 1));
            i4 = i5 + stroke;
            if (orientation != Orientation.HORIZONTAL) {
                int i7 = i3;
                i3 = i4;
                i4 = i7;
            }
        } else {
            i4 = 0;
            i3 = 0;
        }
        if (indicator.getAnimationType() == IndicatorAnimationType.DROP) {
            if (orientation == Orientation.HORIZONTAL) {
                i4 *= 2;
            } else {
                i3 *= 2;
            }
        }
        Orientation orientation2 = Orientation.HORIZONTAL;
        int i8 = i3 + paddingLeft + paddingRight;
        int i9 = i4 + paddingTop + paddingBottom;
        if (mode != 1073741824) {
            size = mode == Integer.MIN_VALUE ? Math.min(i8, size) : i8;
        }
        if (mode2 != 1073741824) {
            if (mode2 == Integer.MIN_VALUE) {
                size2 = Math.min(i9, size2);
            } else {
                size2 = i9;
            }
        }
        if (size < 0) {
            size = 0;
        }
        if (size2 >= 0) {
            i6 = size2;
        }
        indicator2.setWidth(size);
        indicator2.setHeight(i6);
        return new Pair<>(Integer.valueOf(size), Integer.valueOf(i6));
    }
}
