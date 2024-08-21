package com.cooltechworks.views.shimmer;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

public class ShimmerAdapter extends RecyclerView.Adapter<ShimmerViewHolder> {
    private boolean isAnimationReversed;
    private int mItemCount;
    private int mLayoutReference;
    private int mShimmerAngle;
    private int mShimmerColor;
    private int mShimmerDuration;
    private Drawable mShimmerItemBackground;

    public ShimmerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ShimmerViewHolder shimmerViewHolder = new ShimmerViewHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup, this.mLayoutReference);
        shimmerViewHolder.setShimmerColor(this.mShimmerColor);
        shimmerViewHolder.setShimmerAngle(this.mShimmerAngle);
        shimmerViewHolder.setShimmerViewHolderBackground(this.mShimmerItemBackground);
        shimmerViewHolder.setShimmerAnimationDuration(this.mShimmerDuration);
        shimmerViewHolder.setAnimationReversed(this.isAnimationReversed);
        return shimmerViewHolder;
    }

    public void onBindViewHolder(ShimmerViewHolder shimmerViewHolder, int i) {
        shimmerViewHolder.bind();
    }

    public int getItemCount() {
        return this.mItemCount;
    }

    public void setMinItemCount(int i) {
        this.mItemCount = i;
    }

    public void setShimmerAngle(int i) {
        this.mShimmerAngle = i;
    }

    public void setShimmerColor(int i) {
        this.mShimmerColor = i;
    }

    public void setShimmerItemBackground(Drawable drawable) {
        this.mShimmerItemBackground = drawable;
    }

    public void setShimmerDuration(int i) {
        this.mShimmerDuration = i;
    }

    public void setLayoutReference(int i) {
        this.mLayoutReference = i;
    }

    public void setAnimationReversed(boolean z) {
        this.isAnimationReversed = z;
    }
}
