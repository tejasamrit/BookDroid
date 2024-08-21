package com.booklal.booklal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.annotation.Nullable;

public class ChatActivity extends AppCompatActivity {
    String NOTIFICATION_MESSAGE;
    MessagesAdapter adapter;
    private ImageView backBtnToolbar;
    String bookInfo;
    RecyclerView chatRecyclerView;
    int count = 0;
    FirebaseDatabase database;
    EditText messageBox;
    ImageView messageSendButton;
    ArrayList<Message> messages;
    FirebaseFirestore mstore;
    String name;
    String receiverRoom;
    String receiverUid;
    String senderRoom;
    String sender_name;
    String strLastMsgTime;
    private TextView toolBarTitle;
    private Toolbar toolbar;
    int unicode = 10004;
    /* access modifiers changed from: private */
    public Boolean verified;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0699R.layout.activity_chat);
        this.messages = new ArrayList<>();
        this.chatRecyclerView = (RecyclerView) findViewById(C0699R.C0702id.chat_recyclerView);
        this.messageSendButton = (ImageView) findViewById(C0699R.C0702id.sendBtn);
        this.messageBox = (EditText) findViewById(C0699R.C0702id.messageBox);
        this.backBtnToolbar = (ImageView) findViewById(C0699R.C0702id.toolbar_back_icon_other);
        this.name = getIntent().getStringExtra("name");
        this.receiverUid = getIntent().getStringExtra("uid");
        this.bookInfo = getIntent().getStringExtra("bookinfo");
        final String uid = FirebaseAuth.getInstance().getUid();
        FirebaseFirestore instance = FirebaseFirestore.getInstance();
        this.mstore = instance;
        instance.collection("users").document(uid).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                ChatActivity.this.sender_name = documentSnapshot.getString("displayname");
            }
        });
        if (this.bookInfo == null) {
            this.mstore.collection("users").document(this.receiverUid).addSnapshotListener((Activity) this, (EventListener<DocumentSnapshot>) new EventListener<DocumentSnapshot>() {
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException firebaseFirestoreException) {
                    Boolean unused = ChatActivity.this.verified = documentSnapshot.getBoolean("isVerified");
                }
            });
            Boolean bool = this.verified;
            if (bool != null && bool.booleanValue()) {
                this.name += getEmojiByUnicode(this.unicode);
            }
        }
        final DatabaseReference child = FirebaseDatabase.getInstance().getReference().child("USERS").child(this.receiverUid).child(uid);
        final DatabaseReference child2 = FirebaseDatabase.getInstance().getReference().child("USERS").child(uid).child(this.receiverUid);
        this.toolbar = (Toolbar) findViewById(C0699R.C0702id.custom_toolbar_other_noimage);
        this.toolBarTitle = (TextView) findViewById(C0699R.C0702id.toolbar_title_other_noimage);
        setSupportActionBar(this.toolbar);
        String str = this.name;
        if (str != null) {
            this.toolBarTitle.setText(str);
        } else {
            this.toolBarTitle.setText("Error in Chat!");
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.senderRoom = uid + this.receiverUid;
        String str2 = this.receiverUid + uid;
        this.receiverRoom = str2;
        this.adapter = new MessagesAdapter(this, this.messages, this.senderRoom, str2);
        this.chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.chatRecyclerView.setAdapter(this.adapter);
        FirebaseDatabase instance2 = FirebaseDatabase.getInstance();
        this.database = instance2;
        instance2.getReference().child("chats").child(this.senderRoom).child("messages").addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                ChatActivity.this.messages.clear();
                int i = 0;
                for (DataSnapshot next : dataSnapshot.getChildren()) {
                    Message message = (Message) next.getValue(Message.class);
                    message.setMessageId(next.getKey());
                    ChatActivity.this.messages.add(message);
                    i++;
                }
                ChatActivity.this.adapter.notifyDataSetChanged();
                if (ChatActivity.this.messages.size() > 0 && ChatActivity.this.messages.size() != ChatActivity.this.count) {
                    ChatActivity.this.count = i;
                    ChatActivity.this.chatRecyclerView.scrollToPosition(ChatActivity.this.messages.size() - 1);
                }
            }
        });
        child.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    child2.addListenerForSingleValueEvent(new ValueEventListener() {
                        public void onCancelled(DatabaseError databaseError) {
                        }

                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() != null) {
                                child2.child("MsgSeen").setValue(true);
                            }
                        }
                    });
                }
            }
        });
        this.messageSendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String obj = ChatActivity.this.messageBox.getText().toString();
                if (ChatActivity.this.messages != null && ChatActivity.this.messages.size() > 0) {
                    ChatActivity.this.chatRecyclerView.scrollToPosition(ChatActivity.this.messages.size() - 1);
                }
                if (obj != null && obj.length() >= 1) {
                    Boolean bool = false;
                    int i = 0;
                    while (true) {
                        if (i < obj.length()) {
                            if (obj.charAt(i) != ' ' && obj.charAt(i) != 10) {
                                bool = true;
                                break;
                            }
                            i++;
                        } else {
                            break;
                        }
                    }
                    if (!bool.booleanValue()) {
                        Toast.makeText(ChatActivity.this, "Message is empty.", 0).show();
                        return;
                    }
                    ChatActivity.this.NOTIFICATION_MESSAGE = obj;
                    if (ChatActivity.this.bookInfo != null) {
                        obj = ChatActivity.this.bookInfo + obj;
                    }
                    ChatActivity.this.bookInfo = "";
                    Date date = new Date();
                    Boolean bool2 = new Boolean("True");
                    Boolean bool3 = new Boolean("False");
                    final Message message = new Message(obj, uid, date.getTime());
                    ChatActivity.this.messageBox.setText("");
                    final String key = ChatActivity.this.database.getReference().push().getKey();
                    HashMap hashMap = new HashMap();
                    hashMap.put("lastMsg", message.getMessage());
                    hashMap.put("lastMsgTime", Long.valueOf(date.getTime()));
                    hashMap.put("lastMsgSeen", Boolean.valueOf(bool2.booleanValue()));
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("lastMsg", message.getMessage());
                    hashMap2.put("lastMsgTime", Long.valueOf(date.getTime()));
                    hashMap2.put("lastMsgSeen", Boolean.valueOf(bool3.booleanValue()));
                    ChatActivity.this.strLastMsgTime = date.getTime() + "";
                    ChatActivity.this.database.getReference().child("chats").child(ChatActivity.this.senderRoom).updateChildren(hashMap);
                    ChatActivity.this.database.getReference().child("chats").child(ChatActivity.this.receiverRoom).updateChildren(hashMap2);
                    ChatActivity.this.database.getReference().child("chats").child(ChatActivity.this.senderRoom).child("messages").child(key).setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                        public void onSuccess(Void voidR) {
                            ChatActivity.this.database.getReference().child("chats").child(ChatActivity.this.receiverRoom).child("messages").child(key).setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                                public void onSuccess(Void voidR) {
                                    child.addListenerForSingleValueEvent(new ValueEventListener() {
                                        public void onCancelled(DatabaseError databaseError) {
                                        }

                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.getValue() == null) {
                                                HashMap hashMap = new HashMap();
                                                hashMap.put("uid", uid);
                                                hashMap.put("name", ChatActivity.this.name);
                                                hashMap.put("phoneNumber", "IDK");
                                                hashMap.put("profileImage", "IDK");
                                                hashMap.put("stringTimeStamp", ChatActivity.this.strLastMsgTime);
                                                child.setValue(hashMap);
                                                child.child("MsgSeen").setValue(false);
                                                return;
                                            }
                                            child.child("MsgSeen").setValue(false);
                                            child.child("stringTimeStamp").setValue(ChatActivity.this.strLastMsgTime);
                                        }
                                    });
                                    child2.addListenerForSingleValueEvent(new ValueEventListener() {
                                        public void onCancelled(DatabaseError databaseError) {
                                        }

                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.getValue() == null) {
                                                HashMap hashMap = new HashMap();
                                                hashMap.put("uid", ChatActivity.this.receiverUid);
                                                hashMap.put("name", "IDK");
                                                hashMap.put("phoneNumber", "IDK");
                                                hashMap.put("profileImage", "IDK");
                                                hashMap.put("stringTimeStamp", ChatActivity.this.strLastMsgTime);
                                                child2.setValue(hashMap);
                                                child2.child("MsgSeen").setValue(true);
                                                return;
                                            }
                                            child2.child("MsgSeen").setValue(true);
                                            child2.child("stringTimeStamp").setValue(ChatActivity.this.strLastMsgTime);
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            }
        });
        this.toolBarTitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ChatActivity.this.receiverUid != null) {
                    Intent intent = new Intent(ChatActivity.this, PublicProfileActivity.class);
                    intent.putExtra("uid", ChatActivity.this.receiverUid);
                    ChatActivity.this.startActivity(intent);
                    return;
                }
                Toast.makeText(ChatActivity.this, "User does't exists.", 0).show();
            }
        });
        this.backBtnToolbar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                child2.addListenerForSingleValueEvent(new ValueEventListener() {
                    public void onCancelled(DatabaseError databaseError) {
                    }

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            child2.child("MsgSeen").setValue(true);
                        }
                    }
                });
                ChatActivity.this.onBackPressed();
                ChatActivity.this.finish();
            }
        });
    }

    public String getEmojiByUnicode(int i) {
        return new String(Character.toChars(i));
    }
}
