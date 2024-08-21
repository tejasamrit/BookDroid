package com.github.pgreze.reactions;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, mo37160d2 = {"Lcom/github/pgreze/reactions/RoundedView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "config", "Lcom/github/pgreze/reactions/ReactionsConfig;", "(Landroid/content/Context;Lcom/github/pgreze/reactions/ReactionsConfig;)V", "paint", "Landroid/graphics/Paint;", "rect", "Landroid/graphics/RectF;", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
/* compiled from: RoundedView.kt */
public final class RoundedView extends View {
    private final ReactionsConfig config;
    private final Paint paint;
    private final RectF rect = new RectF();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RoundedView(Context context, ReactionsConfig reactionsConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reactionsConfig, "config");
        this.config = reactionsConfig;
        Paint paint2 = new Paint(1);
        paint2.setColor(reactionsConfig.getPopupColor());
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAlpha(reactionsConfig.getPopupAlphaValue());
        Unit unit = Unit.INSTANCE;
        this.paint = paint2;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        this.rect.left = 0.0f;
        this.rect.right = (float) getWidth();
        this.rect.top = 0.0f;
        this.rect.bottom = (float) getHeight();
        float popupCornerRadius = (float) this.config.getPopupCornerRadius();
        canvas.drawRoundRect(this.rect, popupCornerRadius, popupCornerRadius, this.paint);
    }
}
