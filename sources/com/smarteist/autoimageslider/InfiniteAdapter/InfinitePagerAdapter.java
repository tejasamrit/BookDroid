package com.smarteist.autoimageslider.InfiniteAdapter;

import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class InfinitePagerAdapter extends PagerAdapter {
    public static final int INFINITE_SCROLL_LIMIT = 32400;
    private static final String TAG = "InfinitePagerAdapter";
    private SliderViewAdapter adapter;

    public InfinitePagerAdapter(SliderViewAdapter sliderViewAdapter) {
        this.adapter = sliderViewAdapter;
    }

    public PagerAdapter getRealAdapter() {
        return this.adapter;
    }

    public int getCount() {
        if (getRealCount() < 1) {
            return 0;
        }
        return getRealCount() * INFINITE_SCROLL_LIMIT;
    }

    public int getRealCount() {
        try {
            return getRealAdapter().getCount();
        } catch (Exception unused) {
            return 0;
        }
    }

    public int getMiddlePosition(int i) {
        return i + (Math.max(0, getRealCount()) * 16200);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (getRealCount() < 1) {
            return this.adapter.instantiateItem(viewGroup, 0);
        }
        return this.adapter.instantiateItem(viewGroup, getRealPosition(i));
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (getRealCount() < 1) {
            this.adapter.destroyItem(viewGroup, 0, obj);
        } else {
            this.adapter.destroyItem(viewGroup, getRealPosition(i), obj);
        }
    }

    public void startUpdate(ViewGroup viewGroup) {
        this.adapter.startUpdate(viewGroup);
    }

    public void finishUpdate(ViewGroup viewGroup) {
        this.adapter.finishUpdate(viewGroup);
    }

    public boolean isViewFromObject(View view, Object obj) {
        return this.adapter.isViewFromObject(view, obj);
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        this.adapter.restoreState(parcelable, classLoader);
    }

    public Parcelable saveState() {
        return this.adapter.saveState();
    }

    public CharSequence getPageTitle(int i) {
        return this.adapter.getPageTitle(getRealPosition(i));
    }

    public float getPageWidth(int i) {
        return this.adapter.getPageWidth(i);
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        this.adapter.setPrimaryItem(viewGroup, i, obj);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.adapter.unregisterDataSetObserver(dataSetObserver);
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.adapter.registerDataSetObserver(dataSetObserver);
    }

    public int getItemPosition(Object obj) {
        return this.adapter.getItemPosition(obj);
    }

    public int getRealPosition(int i) {
        if (getRealCount() > 0) {
            return i % getRealCount();
        }
        return 0;
    }
}
