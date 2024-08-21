package com.booklal.booklal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.util.ExponentialBackoff;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {
    Context context;
    ArrayList<User> users;

    public UsersAdapter(Context context2, ArrayList<User> arrayList) {
        this.context = context2;
        this.users = arrayList;
    }

    public UsersViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new UsersViewHolder(LayoutInflater.from(this.context).inflate(C0699R.layout.sample_conversation, viewGroup, false));
    }

    public void onBindViewHolder(final UsersViewHolder usersViewHolder, int i) {
        final User user = this.users.get(i);
        String uid = FirebaseAuth.getInstance().getUid();
        final String str = uid + user.getUid();
        FirebaseDatabase.getInstance().getReference().child("chats").child(str).addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String str = (String) dataSnapshot.child("lastMsg").getValue(String.class);
                    int i = 0;
                    if (str != null) {
                        String str2 = "";
                        for (int i2 = 0; i2 < str.length(); i2++) {
                            if (str.charAt(i2) != 10) {
                                str2 = str2 + str.charAt(i2);
                            }
                        }
                        str = str2;
                    }
                    if (str != null && str.length() > 25) {
                        str = str.substring(0, 24) + "...";
                    }
                    if (dataSnapshot.child("lastMsgTime") != null) {
                        long longValue = ((Long) dataSnapshot.child("lastMsgTime").getValue(Long.class)).longValue();
                        long time = new Date().getTime();
                        long j = time - longValue;
                        long j2 = j / ExponentialBackoff.DEFAULT_BACKOFF_MAX_DELAY_MS;
                        long j3 = j / 3600000;
                        String format = new SimpleDateFormat("dd").format(new Date(longValue));
                        int parseInt = format != null ? Integer.parseInt(format) : 0;
                        String format2 = new SimpleDateFormat("dd").format(new Date(time));
                        if (format2 != null) {
                            i = Integer.parseInt(format2);
                        }
                        if (parseInt == i) {
                            usersViewHolder.msgTime.setText(new SimpleDateFormat("hh:mm a").format(new Date(longValue)));
                        } else if (j3 < 36) {
                            usersViewHolder.msgTime.setText("Yesterday");
                        } else {
                            usersViewHolder.msgTime.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(longValue)));
                        }
                    }
                    usersViewHolder.lastMsg.setText(str);
                    return;
                }
                usersViewHolder.lastMsg.setText("Tap to chat");
            }
        });
        FirebaseDatabase.getInstance().getReference().child("USERS").child(uid).child(user.getUid()).child("MsgSeen").addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && !((Boolean) dataSnapshot.getValue(Boolean.class)).booleanValue()) {
                    usersViewHolder.selectChat.setBackgroundColor(Color.parseColor("#E3F2FD"));
                }
            }
        });
        if (user.getName() != null && user.getName().length() > 20) {
            user.getName().substring(0, 19);
            usersViewHolder.username.setText(user.getName());
        } else if (user.getName() != null) {
            usersViewHolder.username.setText(user.getName());
        }
        ((RequestBuilder) Glide.with(this.context).load(user.getProfileImage()).placeholder((int) C0699R.C0701drawable.layout_demo_account_circle)).into(usersViewHolder.chat_profile_image);
        usersViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(UsersAdapter.this.context, ChatActivity.class);
                intent.putExtra("name", user.getName());
                intent.putExtra("uid", user.getUid());
                UsersAdapter.this.context.startActivity(intent);
            }
        });
        usersViewHolder.selectChat.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                UsersAdapter.this.AskOption(str, user.getUid()).show();
                return true;
            }
        });
    }

    public int getItemCount() {
        return this.users.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {
        ImageView chat_profile_image;
        TextView lastMsg;
        TextView msgTime;
        ConstraintLayout selectChat;
        TextView username;

        public UsersViewHolder(View view) {
            super(view);
            this.lastMsg = (TextView) view.findViewById(C0699R.C0702id.chat_lastMsg);
            this.username = (TextView) view.findViewById(C0699R.C0702id.chat_username);
            this.chat_profile_image = (ImageView) view.findViewById(C0699R.C0702id.chat_profile);
            this.msgTime = (TextView) view.findViewById(C0699R.C0702id.chat_msgTime);
            this.selectChat = (ConstraintLayout) view.findViewById(C0699R.C0702id.select_chat);
        }
    }

    /* access modifiers changed from: private */
    public AlertDialog AskOption(final String str, final String str2) {
        return new AlertDialog.Builder(this.context).setTitle((CharSequence) "Delete Chat").setMessage((CharSequence) "Do you want to Delete?").setIcon((int) C0699R.C0701drawable.delete_32dp).setPositiveButton((CharSequence) "Delete", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseDatabase.getInstance().getReference().child("chats").child(str).removeValue();
                FirebaseDatabase.getInstance().getReference().child("USERS").child(FirebaseAuth.getInstance().getUid()).child(str2).removeValue();
                dialogInterface.dismiss();
                Toast.makeText(UsersAdapter.this.context, "Chat Deleted.", 0).show();
            }
        }).setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create();
    }
}
