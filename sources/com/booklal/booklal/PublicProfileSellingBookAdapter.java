package com.booklal.booklal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import java.util.List;

public class PublicProfileSellingBookAdapter extends RecyclerView.Adapter<PublicProfileSellingBookViewHolder> {
    private String bookID;
    private List<PublicProfileSellingBooks> booksList;
    /* access modifiers changed from: private */
    public Context context;

    /* renamed from: db */
    private FirebaseFirestore f84db;
    private FirebaseStorage mref;

    public PublicProfileSellingBookAdapter(Context context2, List<PublicProfileSellingBooks> list) {
        this.context = context2;
        this.booksList = list;
    }

    public PublicProfileSellingBookViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PublicProfileSellingBookViewHolder(LayoutInflater.from(this.context).inflate(C0699R.layout.public_profile_book_selling_list, (ViewGroup) null));
    }

    public void onBindViewHolder(PublicProfileSellingBookViewHolder publicProfileSellingBookViewHolder, int i) {
        final PublicProfileSellingBooks publicProfileSellingBooks = this.booksList.get(i);
        publicProfileSellingBookViewHolder.bookName.setText(publicProfileSellingBooks.getBookName());
        publicProfileSellingBookViewHolder.bookSellingPrice.setText(publicProfileSellingBooks.getBookPrice());
        Glide.with((View) publicProfileSellingBookViewHolder.bookImage).load(publicProfileSellingBooks.getImageURL()).into(publicProfileSellingBookViewHolder.bookImage);
        this.bookID = publicProfileSellingBooks.getBookID();
        publicProfileSellingBookViewHolder.list_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(PublicProfileSellingBookAdapter.this.context, PublicProfileShowBookDetailsActivity.class);
                intent.putExtra("bookKey", publicProfileSellingBooks.getBookID());
                PublicProfileSellingBookAdapter.this.context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return this.booksList.size();
    }

    public class PublicProfileSellingBookViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookName;
        TextView bookSellingPrice;
        RelativeLayout list_layout;

        public PublicProfileSellingBookViewHolder(View view) {
            super(view);
            this.bookImage = (ImageView) view.findViewById(C0699R.C0702id.public_profile_selling_list_view_bookImage);
            this.bookName = (TextView) view.findViewById(C0699R.C0702id.public_book_name);
            this.list_layout = (RelativeLayout) view.findViewById(C0699R.C0702id.public_profile_selling_book_layout);
            this.bookSellingPrice = (TextView) view.findViewById(C0699R.C0702id.public_book_price);
        }
    }
}
