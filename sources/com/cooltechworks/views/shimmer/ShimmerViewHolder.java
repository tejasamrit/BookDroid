package com.cooltechworks.views.shimmer;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import p012io.supercharge.shimmerlayout.ShimmerLayout;

public class ShimmerViewHolder extends RecyclerView.ViewHolder {
    private ShimmerLayout mShimmerLayout;

    public ShimmerViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        super(layoutInflater.inflate(C0832R.layout.viewholder_shimmer, viewGroup, false));
        ShimmerLayout shimmerLayout = (ShimmerLayout) this.itemView;
        this.mShimmerLayout = shimmerLayout;
        layoutInflater.inflate(i, shimmerLayout, true);
    }

    public void setShimmerAngle(int i) {
        this.mShimmerLayout.setShimmerAngle(i);
    }

    public void setShimmerColor(int i) {
        this.mShimmerLayout.setShimmerColor(i);
    }

    public void setShimmerViewHolderBackground(Drawable drawable) {
        if (drawable != null) {
            setBackground(drawable);
        }
    }

    public void setShimmerAnimationDuration(int i) {
        this.mShimmerLayout.setShimmerAnimationDuration(i);
    }

    public void setAnimationReversed(boolean z) {
        this.mShimmerLayout.setAnimationReversed(z);
    }

    public void bind() {
        this.mShimmerLayout.startShimmerAnimation();
    }

    private void setBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT > 16) {
            this.mShimmerLayout.setBackground(drawable);
        } else {
            this.mShimmerLayout.setBackgroundDrawable(drawable);
        }
    }
}
