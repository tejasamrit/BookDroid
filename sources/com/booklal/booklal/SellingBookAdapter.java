package com.booklal.booklal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.geofire.GeoFire;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.List;

public class SellingBookAdapter extends RecyclerView.Adapter<SellingBookViewHolder> {
    /* access modifiers changed from: private */
    public String bookID;
    private List<SellingBooks> booksList;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */

    /* renamed from: db */
    public FirebaseFirestore f107db;
    /* access modifiers changed from: private */
    public FirebaseStorage mref;

    public SellingBookAdapter(Context context2, List<SellingBooks> list) {
        this.context = context2;
        this.booksList = list;
    }

    public SellingBookViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SellingBookViewHolder(LayoutInflater.from(this.context).inflate(C0699R.layout.selling_list_view, (ViewGroup) null));
    }

    public void onBindViewHolder(SellingBookViewHolder sellingBookViewHolder, int i) {
        final SellingBooks sellingBooks = this.booksList.get(i);
        sellingBookViewHolder.bookName.setText(sellingBooks.getBookName());
        String bookDescription = sellingBooks.getBookDescription();
        String bookPrice = sellingBooks.getBookPrice();
        if (bookDescription != null && bookDescription.length() > 100) {
            bookDescription = bookDescription.substring(0, 100) + "...";
        }
        sellingBookViewHolder.bookDescription.setText(bookDescription);
        sellingBookViewHolder.bookSellingPrice.setText(bookPrice);
        Glide.with((View) sellingBookViewHolder.bookImage).load(sellingBooks.getImageURL()).into(sellingBookViewHolder.bookImage);
        this.bookID = sellingBooks.getBookID();
        sellingBookViewHolder.list_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SellingBookAdapter.this.context, LookBeforeEditBookDetailsActivity.class);
                intent.putExtra("bookKey", sellingBooks.getBookID());
                SellingBookAdapter.this.context.startActivity(intent);
            }
        });
        sellingBookViewHolder.list_layout.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                SellingBookAdapter.this.AskOption().show();
                return true;
            }
        });
        sellingBookViewHolder.mySellingDeleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SellingBookAdapter.this.AskOption().show();
            }
        });
    }

    public int getItemCount() {
        return this.booksList.size();
    }

    public class SellingBookViewHolder extends RecyclerView.ViewHolder {
        TextView bookDescription;
        ImageView bookImage;
        TextView bookName;
        TextView bookSellingPrice;
        RelativeLayout list_layout;
        ImageView mySellingDeleteButton;

        public SellingBookViewHolder(View view) {
            super(view);
            this.bookImage = (ImageView) view.findViewById(C0699R.C0702id.selling_list_view_bookImage);
            this.bookName = (TextView) view.findViewById(C0699R.C0702id.selling_list_view_book_Name);
            this.bookDescription = (TextView) view.findViewById(C0699R.C0702id.selling_list_view_book_Description);
            this.list_layout = (RelativeLayout) view.findViewById(C0699R.C0702id.selling_list_view);
            this.bookSellingPrice = (TextView) view.findViewById(C0699R.C0702id.selling_list_view_book_SellingPrice);
            this.mySellingDeleteButton = (ImageView) view.findViewById(C0699R.C0702id.delete_book);
        }
    }

    /* access modifiers changed from: private */
    public AlertDialog AskOption() {
        return new AlertDialog.Builder(this.context).setTitle((CharSequence) "Delete Book").setMessage((CharSequence) "Do you want to Delete?").setIcon((int) C0699R.C0701drawable.delete_32dp).setPositiveButton((CharSequence) "Delete", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseFirestore unused = SellingBookAdapter.this.f107db = FirebaseFirestore.getInstance();
                SellingBookAdapter.this.f107db.collection("AllBook").document(SellingBookAdapter.this.bookID).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    public void onSuccess(Void voidR) {
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    public void onFailure(Exception exc) {
                    }
                });
                new GeoFire(FirebaseDatabase.getInstance().getReference("AllBooks").child("geofire")).removeLocation(SellingBookAdapter.this.bookID);
                FirebaseStorage unused2 = SellingBookAdapter.this.mref = FirebaseStorage.getInstance();
                StorageReference reference = SellingBookAdapter.this.mref.getReference("BookImages/");
                reference.child(SellingBookAdapter.this.bookID + "P1.jpeg").delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    public void onSuccess(Void voidR) {
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    public void onFailure(Exception exc) {
                    }
                });
                StorageReference reference2 = SellingBookAdapter.this.mref.getReference("BookImages/");
                reference2.child(SellingBookAdapter.this.bookID + "P2.jpeg").delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    public void onSuccess(Void voidR) {
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    public void onFailure(Exception exc) {
                    }
                });
                dialogInterface.dismiss();
                Toast.makeText(SellingBookAdapter.this.context, "Book Deleted...", 0).show();
                Intent intent = new Intent(SellingBookAdapter.this.context, SellingItems.class);
                intent.putExtra("bookKey", SellingBookAdapter.this.bookID);
                SellingBookAdapter.this.context.startActivity(intent);
            }
        }).setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create();
    }
}
