package com.booklal.booklal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import java.util.List;

public class FullImageSliderAdapter extends SliderViewAdapter<MyViewHolder> {
    List<String> fullImageList;

    FullImageSliderAdapter(List<String> list) {
        this.fullImageList = list;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0699R.layout.full_image_view_layout, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Glide.with((View) myViewHolder.imageView).load(this.fullImageList.get(i)).into(myViewHolder.imageView);
    }

    public int getCount() {
        return this.fullImageList.size();
    }

    class MyViewHolder extends SliderViewAdapter.ViewHolder {
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(C0699R.C0702id.fullImageSlider);
        }
    }
}
