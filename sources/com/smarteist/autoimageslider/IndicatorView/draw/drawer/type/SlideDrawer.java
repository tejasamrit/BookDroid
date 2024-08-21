package com.smarteist.autoimageslider.IndicatorView.draw.drawer.type;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.smarteist.autoimageslider.IndicatorView.animation.data.Value;
import com.smarteist.autoimageslider.IndicatorView.animation.data.type.SlideAnimationValue;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Orientation;

public class SlideDrawer extends BaseDrawer {
    public SlideDrawer(Paint paint, Indicator indicator) {
        super(paint, indicator);
    }

    public void draw(Canvas canvas, Value value, int i, int i2) {
        if (value instanceof SlideAnimationValue) {
            int coordinate = ((SlideAnimationValue) value).getCoordinate();
            int unselectedColor = this.indicator.getUnselectedColor();
            int selectedColor = this.indicator.getSelectedColor();
            int radius = this.indicator.getRadius();
            this.paint.setColor(unselectedColor);
            float f = (float) i;
            float f2 = (float) i2;
            float f3 = (float) radius;
            canvas.drawCircle(f, f2, f3, this.paint);
            this.paint.setColor(selectedColor);
            if (this.indicator.getOrientation() == Orientation.HORIZONTAL) {
                canvas.drawCircle((float) coordinate, f2, f3, this.paint);
            } else {
                canvas.drawCircle(f, (float) coordinate, f3, this.paint);
            }
        }
    }
}
