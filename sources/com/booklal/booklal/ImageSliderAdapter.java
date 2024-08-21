package com.booklal.booklal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import java.util.List;

public class ImageSliderAdapter extends SliderViewAdapter<MyViewHolder> {
    String bookID;
    /* access modifiers changed from: private */
    public Context context;
    List<String> imageList;

    ImageSliderAdapter(List<String> list, Context context2, String str) {
        this.imageList = list;
        this.context = context2;
        this.bookID = str;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0699R.layout.imageslider_item, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        Glide.with((View) myViewHolder.imageView).load(this.imageList.get(i)).into(myViewHolder.imageView);
        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ImageSliderAdapter.this.context, ShowFullBookImage.class);
                intent.putExtra("bookPos", i);
                intent.putExtra("bookKey", ImageSliderAdapter.this.bookID);
                ImageSliderAdapter.this.context.startActivity(intent);
            }
        });
    }

    public int getCount() {
        return this.imageList.size();
    }

    class MyViewHolder extends SliderViewAdapter.ViewHolder {
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(C0699R.C0702id.imageview_slider);
        }
    }
}
