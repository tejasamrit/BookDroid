package com.booklal.booklal;

import android.app.AlertDialog;
import android.content.Context;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.github.pgreze.reactions.ReactionPopup;
import com.github.pgreze.reactions.ReactionsConfigBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.util.ExponentialBackoff;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import kotlin.jvm.functions.Function1;

public class MessagesAdapter extends RecyclerView.Adapter {
    final int ITEM_RECEIVE = 2;
    final int ITEM_SENT = 1;
    Context context;
    ArrayList<Message> messages;
    String receiverRoom;
    String senderRoom;

    public MessagesAdapter(Context context2, ArrayList<Message> arrayList, String str, String str2) {
        this.context = context2;
        this.messages = arrayList;
        this.senderRoom = str;
        this.receiverRoom = str2;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new SentViewHolder(LayoutInflater.from(this.context).inflate(C0699R.layout.item_sent, viewGroup, false));
        }
        return new ReceiverViewHolder(LayoutInflater.from(this.context).inflate(C0699R.layout.item_recieve, viewGroup, false));
    }

    public int getItemViewType(int i) {
        return FirebaseAuth.getInstance().getUid().equals(this.messages.get(i).getSenderId()) ? 1 : 2;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        final Message message = this.messages.get(i);
        int[] iArr = {C0699R.C0701drawable.like, C0699R.C0701drawable.face, C0699R.C0701drawable.love, C0699R.C0701drawable.accept, C0699R.C0701drawable.deal, C0699R.C0701drawable.hand};
        final ReactionPopup reactionPopup = new ReactionPopup(this.context, new ReactionsConfigBuilder(this.context).withReactions(iArr).build(), new Function1(viewHolder2, iArr, message) {
            public final /* synthetic */ RecyclerView.ViewHolder f$1;
            public final /* synthetic */ int[] f$2;
            public final /* synthetic */ Message f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final Object invoke(Object obj) {
                return MessagesAdapter.this.lambda$onBindViewHolder$0$MessagesAdapter(this.f$1, this.f$2, this.f$3, (Integer) obj);
            }
        });
        if (viewHolder.getClass() == SentViewHolder.class) {
            SentViewHolder sentViewHolder = (SentViewHolder) viewHolder2;
            long timestamp = message.getTimestamp();
            long time = new Date().getTime();
            long j = time - timestamp;
            long j2 = j / ExponentialBackoff.DEFAULT_BACKOFF_MAX_DELAY_MS;
            long j3 = j / 3600000;
            String str = message.getMessage() + "\n";
            String format = new SimpleDateFormat("dd").format(new Date(timestamp));
            int parseInt = format != null ? Integer.parseInt(format) : 0;
            String format2 = new SimpleDateFormat("dd").format(new Date(time));
            if ((format2 != null ? Integer.parseInt(format2) : 0) != parseInt) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                if (j3 < 36) {
                    String str2 = "yesterday," + simpleDateFormat.format(new Date(timestamp));
                    SpannableString spannableString = new SpannableString(str + str2);
                    spannableString.setSpan(new ForegroundColorSpan(-7829368), str.length(), str.length() + str2.length(), 33);
                    spannableString.setSpan(new RelativeSizeSpan(0.7f), str.length(), str.length() + str2.length(), 33);
                    sentViewHolder.sendMessage.setText(spannableString);
                } else {
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                    String str3 = simpleDateFormat.format(new Date(timestamp)) + "  " + simpleDateFormat2.format(new Date(timestamp));
                    SpannableString spannableString2 = new SpannableString(str + str3);
                    spannableString2.setSpan(new ForegroundColorSpan(-7829368), str.length(), str.length() + str3.length(), 33);
                    spannableString2.setSpan(new RelativeSizeSpan(0.7f), str.length(), str.length() + str3.length(), 33);
                    sentViewHolder.sendMessage.setText(spannableString2);
                }
            } else {
                if (str.length() < 25) {
                    str = message.getMessage() + "  ";
                }
                String format3 = new SimpleDateFormat("hh:mm a").format(new Date(timestamp));
                SpannableString spannableString3 = new SpannableString(str + format3);
                spannableString3.setSpan(new ForegroundColorSpan(-7829368), str.length(), str.length() + format3.length(), 33);
                spannableString3.setSpan(new RelativeSizeSpan(0.7f), str.length(), str.length() + format3.length(), 33);
                sentViewHolder.sendMessage.setText(spannableString3);
            }
            if (message.getFeeling() < 0 || message.getFeeling() >= 6) {
                sentViewHolder.sendFeeling.setVisibility(8);
            } else {
                sentViewHolder.sendFeeling.setImageResource(iArr[message.getFeeling()]);
                sentViewHolder.sendFeeling.setVisibility(0);
            }
            sentViewHolder.sendMessage.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    View inflate = LayoutInflater.from(MessagesAdapter.this.context).inflate(C0699R.layout.delete_dialog, (ViewGroup) null);
                    final AlertDialog create = new AlertDialog.Builder(MessagesAdapter.this.context).setTitle("Delete Message").setView(inflate).create();
                    ((TextView) inflate.findViewById(C0699R.C0702id.delete_for_everyone)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            message.setMessage("This message is removed.");
                            message.setFeeling(-1);
                            FirebaseDatabase.getInstance().getReference().child("chats").child(MessagesAdapter.this.senderRoom).child("messages").child(message.getMessageId()).setValue(message);
                            FirebaseDatabase.getInstance().getReference().child("chats").child(MessagesAdapter.this.receiverRoom).child("messages").child(message.getMessageId()).setValue(message);
                            create.dismiss();
                        }
                    });
                    ((TextView) inflate.findViewById(C0699R.C0702id.delete_for_me)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            FirebaseDatabase.getInstance().getReference().child("chats").child(MessagesAdapter.this.senderRoom).child("messages").child(message.getMessageId()).setValue((Object) null);
                            create.dismiss();
                        }
                    });
                    ((TextView) inflate.findViewById(C0699R.C0702id.cancel_delete_message_action)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            create.dismiss();
                        }
                    });
                    create.show();
                    return false;
                }
            });
            return;
        }
        ReceiverViewHolder receiverViewHolder = (ReceiverViewHolder) viewHolder2;
        long timestamp2 = message.getTimestamp();
        long time2 = new Date().getTime();
        long j4 = time2 - timestamp2;
        long j5 = j4 / ExponentialBackoff.DEFAULT_BACKOFF_MAX_DELAY_MS;
        long j6 = j4 / 3600000;
        String str4 = message.getMessage() + "\n";
        String format4 = new SimpleDateFormat("dd").format(new Date(timestamp2));
        int parseInt2 = format4 != null ? Integer.parseInt(format4) : 0;
        String format5 = new SimpleDateFormat("dd").format(new Date(time2));
        if (parseInt2 != (format5 != null ? Integer.parseInt(format5) : 0)) {
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("hh:mm a");
            if (j6 < 36) {
                String str5 = "yesterday, " + simpleDateFormat3.format(new Date(timestamp2));
                SpannableString spannableString4 = new SpannableString(str4 + str5);
                spannableString4.setSpan(new ForegroundColorSpan(-7829368), str4.length(), str4.length() + str5.length(), 33);
                spannableString4.setSpan(new RelativeSizeSpan(0.7f), str4.length(), str4.length() + str5.length(), 33);
                receiverViewHolder.receiveMessage.setText(spannableString4);
            } else {
                SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("dd/MM/yyyy");
                String str6 = simpleDateFormat3.format(new Date(timestamp2)) + "  " + simpleDateFormat4.format(new Date(timestamp2));
                SpannableString spannableString5 = new SpannableString(str4 + str6);
                spannableString5.setSpan(new ForegroundColorSpan(-7829368), str4.length(), str4.length() + str6.length(), 33);
                spannableString5.setSpan(new RelativeSizeSpan(0.7f), str4.length(), str4.length() + str6.length(), 33);
                receiverViewHolder.receiveMessage.setText(spannableString5);
            }
        } else {
            if (str4.length() < 25) {
                str4 = message.getMessage() + "  ";
            }
            String format6 = new SimpleDateFormat("hh:mm a").format(new Date(timestamp2));
            SpannableString spannableString6 = new SpannableString(str4 + format6);
            spannableString6.setSpan(new ForegroundColorSpan(-7829368), str4.length(), str4.length() + format6.length(), 33);
            spannableString6.setSpan(new RelativeSizeSpan(0.7f), str4.length(), str4.length() + format6.length(), 33);
            receiverViewHolder.receiveMessage.setText(spannableString6);
        }
        if (message.getFeeling() < 0 || message.getFeeling() >= 6) {
            receiverViewHolder.receiveFeeling.setVisibility(8);
        } else {
            receiverViewHolder.receiveFeeling.setImageResource(iArr[message.getFeeling()]);
            receiverViewHolder.receiveFeeling.setVisibility(0);
        }
        receiverViewHolder.receiveMessage.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                reactionPopup.onTouch(view, motionEvent);
                return false;
            }
        });
        receiverViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                View inflate = LayoutInflater.from(MessagesAdapter.this.context).inflate(C0699R.layout.delete_dialog, (ViewGroup) null);
                final AlertDialog create = new AlertDialog.Builder(MessagesAdapter.this.context).setTitle("Delete Message").setView(inflate).create();
                ((TextView) inflate.findViewById(C0699R.C0702id.delete_for_everyone)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        create.dismiss();
                    }
                });
                ((TextView) inflate.findViewById(C0699R.C0702id.delete_for_me)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        FirebaseDatabase.getInstance().getReference().child("chats").child(MessagesAdapter.this.senderRoom).child("messages").child(message.getMessageId()).setValue((Object) null);
                        create.dismiss();
                    }
                });
                ((TextView) inflate.findViewById(C0699R.C0702id.cancel_delete_message_action)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        create.dismiss();
                    }
                });
                create.show();
                return false;
            }
        });
    }

    public /* synthetic */ Boolean lambda$onBindViewHolder$0$MessagesAdapter(RecyclerView.ViewHolder viewHolder, int[] iArr, Message message, Integer num) {
        if (viewHolder.getClass() == SentViewHolder.class) {
            SentViewHolder sentViewHolder = (SentViewHolder) viewHolder;
            if (num != null && num.intValue() < 6) {
                sentViewHolder.sendFeeling.setImageResource(iArr[num.intValue()]);
                sentViewHolder.sendFeeling.setVisibility(0);
            }
        } else {
            ReceiverViewHolder receiverViewHolder = (ReceiverViewHolder) viewHolder;
            if (num != null && num.intValue() > -1 && num.intValue() < 6) {
                receiverViewHolder.receiveFeeling.setImageResource(iArr[num.intValue()]);
                receiverViewHolder.receiveFeeling.setVisibility(0);
            }
        }
        if (num.intValue() < 6) {
            message.setFeeling(num.intValue());
        } else {
            message.setFeeling(-1);
        }
        FirebaseDatabase.getInstance().getReference().child("chats").child(this.senderRoom).child("messages").child(message.getMessageId()).setValue(message);
        FirebaseDatabase.getInstance().getReference().child("chats").child(this.receiverRoom).child("messages").child(message.getMessageId()).setValue(message);
        return true;
    }

    public int getItemCount() {
        return this.messages.size();
    }

    public class SentViewHolder extends RecyclerView.ViewHolder {
        ImageView sendFeeling;
        TextView sendMessage;

        public SentViewHolder(View view) {
            super(view);
            this.sendFeeling = (ImageView) view.findViewById(C0699R.C0702id.sent_feeling);
            this.sendMessage = (TextView) view.findViewById(C0699R.C0702id.sent_message);
        }
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {
        ImageView receiveFeeling;
        TextView receiveMessage;

        public ReceiverViewHolder(View view) {
            super(view);
            this.receiveFeeling = (ImageView) view.findViewById(C0699R.C0702id.recieve_feeling);
            this.receiveMessage = (TextView) view.findViewById(C0699R.C0702id.recieve_message);
        }
    }
}
