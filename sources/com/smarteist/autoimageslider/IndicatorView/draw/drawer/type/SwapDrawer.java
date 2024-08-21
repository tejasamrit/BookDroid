package com.smarteist.autoimageslider.IndicatorView.draw.drawer.type;

import android.graphics.Paint;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator;

public class SwapDrawer extends BaseDrawer {
    public SwapDrawer(Paint paint, Indicator indicator) {
        super(paint, indicator);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void draw(android.graphics.Canvas r9, com.smarteist.autoimageslider.IndicatorView.animation.data.Value r10, int r11, int r12, int r13) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.smarteist.autoimageslider.IndicatorView.animation.data.type.SwapAnimationValue
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            com.smarteist.autoimageslider.IndicatorView.animation.data.type.SwapAnimationValue r10 = (com.smarteist.autoimageslider.IndicatorView.animation.data.type.SwapAnimationValue) r10
            com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator r0 = r8.indicator
            int r0 = r0.getSelectedColor()
            com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator r1 = r8.indicator
            int r1 = r1.getUnselectedColor()
            com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator r2 = r8.indicator
            int r2 = r2.getRadius()
            com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator r3 = r8.indicator
            int r3 = r3.getSelectedPosition()
            com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator r4 = r8.indicator
            int r4 = r4.getSelectingPosition()
            com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator r5 = r8.indicator
            int r5 = r5.getLastSelectedPosition()
            int r6 = r10.getCoordinate()
            com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator r7 = r8.indicator
            boolean r7 = r7.isInteractiveAnimation()
            if (r7 == 0) goto L_0x0045
            if (r11 != r4) goto L_0x003e
            int r6 = r10.getCoordinate()
            goto L_0x0053
        L_0x003e:
            if (r11 != r3) goto L_0x0052
            int r6 = r10.getCoordinateReverse()
            goto L_0x0052
        L_0x0045:
            if (r11 != r5) goto L_0x004c
            int r6 = r10.getCoordinate()
            goto L_0x0053
        L_0x004c:
            if (r11 != r3) goto L_0x0052
            int r6 = r10.getCoordinateReverse()
        L_0x0052:
            r0 = r1
        L_0x0053:
            android.graphics.Paint r10 = r8.paint
            r10.setColor(r0)
            com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator r10 = r8.indicator
            com.smarteist.autoimageslider.IndicatorView.draw.data.Orientation r10 = r10.getOrientation()
            com.smarteist.autoimageslider.IndicatorView.draw.data.Orientation r11 = com.smarteist.autoimageslider.IndicatorView.draw.data.Orientation.HORIZONTAL
            if (r10 != r11) goto L_0x006b
            float r10 = (float) r6
            float r11 = (float) r13
            float r12 = (float) r2
            android.graphics.Paint r13 = r8.paint
            r9.drawCircle(r10, r11, r12, r13)
            goto L_0x0073
        L_0x006b:
            float r10 = (float) r12
            float r11 = (float) r6
            float r12 = (float) r2
            android.graphics.Paint r13 = r8.paint
            r9.drawCircle(r10, r11, r12, r13)
        L_0x0073:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smarteist.autoimageslider.IndicatorView.draw.drawer.type.SwapDrawer.draw(android.graphics.Canvas, com.smarteist.autoimageslider.IndicatorView.animation.data.Value, int, int, int):void");
    }
}
