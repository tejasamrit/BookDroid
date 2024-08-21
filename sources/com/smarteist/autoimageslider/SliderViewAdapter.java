package com.smarteist.autoimageslider;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.smarteist.autoimageslider.SliderViewAdapter.ViewHolder;
import java.util.LinkedList;
import java.util.Queue;

public abstract class SliderViewAdapter<VH extends ViewHolder> extends PagerAdapter {
    private DataSetListener dataSetListener;
    private Queue<VH> destroyedItems = new LinkedList();

    interface DataSetListener {
        void dataSetChanged();
    }

    public int getItemPosition(Object obj) {
        return -2;
    }

    public abstract void onBindViewHolder(VH vh, int i);

    public abstract VH onCreateViewHolder(ViewGroup viewGroup);

    public static abstract class ViewHolder {
        public final View itemView;

        public ViewHolder(View view) {
            this.itemView = view;
        }
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        ViewHolder viewHolder = (ViewHolder) this.destroyedItems.poll();
        if (viewHolder == null) {
            viewHolder = onCreateViewHolder(viewGroup);
        }
        viewGroup.addView(viewHolder.itemView);
        onBindViewHolder(viewHolder, i);
        return viewHolder;
    }

    public final void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        ViewHolder viewHolder = (ViewHolder) obj;
        viewGroup.removeView(viewHolder.itemView);
        this.destroyedItems.add(viewHolder);
    }

    public final boolean isViewFromObject(View view, Object obj) {
        return ((ViewHolder) obj).itemView == view;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        DataSetListener dataSetListener2 = this.dataSetListener;
        if (dataSetListener2 != null) {
            dataSetListener2.dataSetChanged();
        }
    }

    /* access modifiers changed from: package-private */
    public void dataSetChangedListener(DataSetListener dataSetListener2) {
        this.dataSetListener = dataSetListener2;
    }
}
