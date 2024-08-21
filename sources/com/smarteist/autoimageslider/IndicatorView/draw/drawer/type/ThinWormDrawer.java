package com.smarteist.autoimageslider.IndicatorView.draw.drawer.type;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.smarteist.autoimageslider.IndicatorView.animation.data.Value;
import com.smarteist.autoimageslider.IndicatorView.animation.data.type.ThinWormAnimationValue;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Orientation;

public class ThinWormDrawer extends WormDrawer {
    public ThinWormDrawer(Paint paint, Indicator indicator) {
        super(paint, indicator);
    }

    public void draw(Canvas canvas, Value value, int i, int i2) {
        if (value instanceof ThinWormAnimationValue) {
            ThinWormAnimationValue thinWormAnimationValue = (ThinWormAnimationValue) value;
            int rectStart = thinWormAnimationValue.getRectStart();
            int rectEnd = thinWormAnimationValue.getRectEnd();
            int height = thinWormAnimationValue.getHeight() / 2;
            int radius = this.indicator.getRadius();
            int unselectedColor = this.indicator.getUnselectedColor();
            int selectedColor = this.indicator.getSelectedColor();
            if (this.indicator.getOrientation() == Orientation.HORIZONTAL) {
                this.rect.left = (float) rectStart;
                this.rect.right = (float) rectEnd;
                this.rect.top = (float) (i2 - height);
                this.rect.bottom = (float) (height + i2);
            } else {
                this.rect.left = (float) (i - height);
                this.rect.right = (float) (height + i);
                this.rect.top = (float) rectStart;
                this.rect.bottom = (float) rectEnd;
            }
            this.paint.setColor(unselectedColor);
            float f = (float) i;
            float f2 = (float) i2;
            float f3 = (float) radius;
            canvas.drawCircle(f, f2, f3, this.paint);
            this.paint.setColor(selectedColor);
            canvas.drawRoundRect(this.rect, f3, f3, this.paint);
        }
    }
}
