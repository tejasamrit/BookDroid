package com.booklal.booklal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> implements Filterable {
    /* access modifiers changed from: private */
    public List<AllBooks> booksList;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public List<AllBooks> filteredBookList;

    public BookAdapter(Context context2, List<AllBooks> list) {
        this.context = context2;
        this.booksList = list;
        this.filteredBookList = list;
    }

    public BookViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BookViewHolder(LayoutInflater.from(this.context).inflate(C0699R.layout.list_view, (ViewGroup) null));
    }

    public void onBindViewHolder(BookViewHolder bookViewHolder, int i) {
        final AllBooks allBooks = this.filteredBookList.get(i);
        bookViewHolder.bookName.setText(allBooks.getNameOfBook());
        String descriptionOfBook = allBooks.getDescriptionOfBook();
        String str = allBooks.getpDistance();
        String bookSellingPrice = allBooks.getBookSellingPrice();
        if (descriptionOfBook.length() > 100) {
            descriptionOfBook = descriptionOfBook.substring(0, 100) + "...";
        }
        bookViewHolder.bookDescription.setText(descriptionOfBook);
        bookViewHolder.listDistance.setText(str);
        bookViewHolder.bookSellingPrice.setText(bookSellingPrice);
        Glide.with((View) bookViewHolder.bookImage).load(allBooks.getImgURL()).into(bookViewHolder.bookImage);
        bookViewHolder.list_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(BookAdapter.this.context, ShowBookDetailsActivity.class);
                intent.putExtra("bookKey", allBooks.getBookID());
                intent.putExtra("dist", allBooks.getpDistance());
                BookAdapter.this.context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return this.filteredBookList.size();
    }

    public Filter getFilter() {
        return new Filter() {
            /* access modifiers changed from: protected */
            public Filter.FilterResults performFiltering(CharSequence charSequence) {
                ArrayList arrayList = new ArrayList();
                String trim = charSequence.toString().trim();
                if (trim.isEmpty()) {
                    BookAdapter bookAdapter = BookAdapter.this;
                    List unused = bookAdapter.filteredBookList = bookAdapter.booksList;
                } else {
                    for (AllBooks allBooks : BookAdapter.this.booksList) {
                        String bookAuthor = allBooks.getBookAuthor();
                        String bookAuthor2 = allBooks.getBookAuthor();
                        if (bookAuthor != null) {
                            String str = "";
                            for (int i = 0; i < bookAuthor.length(); i++) {
                                if (bookAuthor.charAt(i) != '.') {
                                    str = str + bookAuthor.charAt(i);
                                }
                            }
                            bookAuthor = str.replaceAll("  ", " ");
                        }
                        if (bookAuthor2 != null) {
                            String str2 = "";
                            for (int i2 = 0; i2 < bookAuthor2.length(); i2++) {
                                if (!(bookAuthor2.charAt(i2) == ' ' || bookAuthor2.charAt(i2) == '-' || bookAuthor2.charAt(i2) == '.')) {
                                    str2 = str2 + bookAuthor2.charAt(i2);
                                }
                            }
                            bookAuthor2 = str2.replaceAll("  ", " ");
                        }
                        String bookPublication = allBooks.getBookPublication();
                        String bookPublication2 = allBooks.getBookPublication();
                        if (bookPublication != null) {
                            String str3 = "";
                            for (int i3 = 0; i3 < bookPublication.length(); i3++) {
                                if (bookPublication.charAt(i3) != '.') {
                                    str3 = str3 + bookPublication.charAt(i3);
                                }
                            }
                            bookPublication = str3.replaceAll("  ", " ");
                        }
                        if (bookPublication2 != null) {
                            String str4 = "";
                            for (int i4 = 0; i4 < bookPublication2.length(); i4++) {
                                if (bookPublication2.charAt(i4) != ' ') {
                                    if (!(bookPublication2.charAt(i4) == '-' || bookPublication2.charAt(i4) == '.')) {
                                        str4 = str4 + bookPublication2.charAt(i4);
                                    }
                                }
                            }
                            bookPublication2 = str4.replaceAll("  ", " ");
                        }
                        if ((allBooks.getNameOfBook().trim() + " " + allBooks.getBookPublication().trim() + " " + (bookPublication + " " + bookPublication2 + " ") + " " + allBooks.getDescriptionOfBook().trim() + " " + (bookAuthor + " " + bookAuthor2 + " ") + " " + allBooks.getBookAuthor()).toLowerCase().contains(trim.toLowerCase())) {
                            arrayList.add(allBooks);
                        }
                    }
                    List unused2 = BookAdapter.this.filteredBookList = arrayList;
                    if (BookAdapter.this.filteredBookList.size() == 0) {
                        Toast.makeText(BookAdapter.this.context, "No Results Found.", 0).show();
                    }
                }
                Filter.FilterResults filterResults = new Filter.FilterResults();
                filterResults.values = BookAdapter.this.filteredBookList;
                return filterResults;
            }

            /* access modifiers changed from: protected */
            public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                List unused = BookAdapter.this.filteredBookList = (List) filterResults.values;
                BookAdapter.this.notifyDataSetChanged();
            }
        };
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        TextView bookDescription;
        ImageView bookImage;
        TextView bookName;
        TextView bookSellingPrice;
        TextView listDistance;
        RelativeLayout list_layout;

        public BookViewHolder(View view) {
            super(view);
            this.bookImage = (ImageView) view.findViewById(C0699R.C0702id.list_view_bookImage);
            this.bookName = (TextView) view.findViewById(C0699R.C0702id.list_view_book_Name);
            this.bookDescription = (TextView) view.findViewById(C0699R.C0702id.list_view_book_Description);
            this.listDistance = (TextView) view.findViewById(C0699R.C0702id.list_distance);
            this.list_layout = (RelativeLayout) view.findViewById(C0699R.C0702id.list_view);
            this.bookSellingPrice = (TextView) view.findViewById(C0699R.C0702id.list_view_book_SellingPrice);
        }
    }
}
