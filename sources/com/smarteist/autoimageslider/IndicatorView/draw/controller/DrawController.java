package com.smarteist.autoimageslider.IndicatorView.draw.controller;

import android.graphics.Canvas;
import android.view.MotionEvent;
import com.smarteist.autoimageslider.IndicatorView.animation.data.Value;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator;
import com.smarteist.autoimageslider.IndicatorView.draw.drawer.Drawer;
import com.smarteist.autoimageslider.IndicatorView.utils.CoordinatesUtils;

public class DrawController {
    private Drawer drawer;
    private Indicator indicator;
    private ClickListener listener;
    private Value value;

    public interface ClickListener {
        void onIndicatorClicked(int i);
    }

    public DrawController(Indicator indicator2) {
        this.indicator = indicator2;
        this.drawer = new Drawer(indicator2);
    }

    public void updateValue(Value value2) {
        this.value = value2;
    }

    public void setClickListener(ClickListener clickListener) {
        this.listener = clickListener;
    }

    public void touch(MotionEvent motionEvent) {
        if (motionEvent != null && motionEvent.getAction() == 1) {
            onIndicatorTouched(motionEvent.getX(), motionEvent.getY());
        }
    }

    private void onIndicatorTouched(float f, float f2) {
        int position;
        if (this.listener != null && (position = CoordinatesUtils.getPosition(this.indicator, f, f2)) >= 0) {
            this.listener.onIndicatorClicked(position);
        }
    }

    public void draw(Canvas canvas) {
        int count = this.indicator.getCount();
        for (int i = 0; i < count; i++) {
            drawIndicator(canvas, i, CoordinatesUtils.getXCoordinate(this.indicator, i), CoordinatesUtils.getYCoordinate(this.indicator, i));
        }
    }

    private void drawIndicator(Canvas canvas, int i, int i2, int i3) {
        boolean isInteractiveAnimation = this.indicator.isInteractiveAnimation();
        int selectedPosition = this.indicator.getSelectedPosition();
        int selectingPosition = this.indicator.getSelectingPosition();
        boolean z = true;
        boolean z2 = !isInteractiveAnimation && (i == selectedPosition || i == this.indicator.getLastSelectedPosition());
        if (!isInteractiveAnimation || !(i == selectedPosition || i == selectingPosition)) {
            z = false;
        }
        boolean z3 = z2 | z;
        this.drawer.setup(i, i2, i3);
        if (this.value == null || !z3) {
            this.drawer.drawBasic(canvas, z3);
        } else {
            drawWithAnimation(canvas);
        }
    }

    /* renamed from: com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController$1 */
    static /* synthetic */ class C22661 {

        /* renamed from: $SwitchMap$com$smarteist$autoimageslider$IndicatorView$animation$type$IndicatorAnimationType */
        static final /* synthetic */ int[] f481x1c9e89f9;

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
                f481x1c9e89f9 = r0
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f481x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x001d }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.COLOR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f481x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.SCALE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f481x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.WORM     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f481x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x003e }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.SLIDE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f481x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.FILL     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f481x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.THIN_WORM     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = f481x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.DROP     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = f481x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x006c }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.SWAP     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = f481x1c9e89f9     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType r1 = com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType.SCALE_DOWN     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController.C22661.<clinit>():void");
        }
    }

    private void drawWithAnimation(Canvas canvas) {
        switch (C22661.f481x1c9e89f9[this.indicator.getAnimationType().ordinal()]) {
            case 1:
                this.drawer.drawBasic(canvas, true);
                return;
            case 2:
                this.drawer.drawColor(canvas, this.value);
                return;
            case 3:
                this.drawer.drawScale(canvas, this.value);
                return;
            case 4:
                this.drawer.drawWorm(canvas, this.value);
                return;
            case 5:
                this.drawer.drawSlide(canvas, this.value);
                return;
            case 6:
                this.drawer.drawFill(canvas, this.value);
                return;
            case 7:
                this.drawer.drawThinWorm(canvas, this.value);
                return;
            case 8:
                this.drawer.drawDrop(canvas, this.value);
                return;
            case 9:
                this.drawer.drawSwap(canvas, this.value);
                return;
            case 10:
                this.drawer.drawScaleDown(canvas, this.value);
                return;
            default:
                return;
        }
    }
}
