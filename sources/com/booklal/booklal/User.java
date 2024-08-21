package com.booklal.booklal;

import java.util.Comparator;

public class User {
    public static Comparator<User> recentChatsComparator = new Comparator<User>() {
        public int compare(User user, User user2) {
            String stringTimeStamp = user.getStringTimeStamp();
            return (int) (Long.valueOf(Long.parseLong(user2.getStringTimeStamp())).longValue() - Long.valueOf(Long.parseLong(stringTimeStamp)).longValue());
        }
    };
    private Boolean MsgSeen;
    private String name;
    private String phoneNumber;
    private String profileImage;
    private String stringTimeStamp;
    private String uid;

    public Boolean getMsgSeen() {
        return this.MsgSeen;
    }

    public void setMsgSeen(Boolean bool) {
        this.MsgSeen = bool;
    }

    public User() {
    }

    public User(String str, String str2, String str3, String str4, String str5) {
        this.uid = str;
        this.name = str2;
        this.phoneNumber = str3;
        this.profileImage = str4;
        this.stringTimeStamp = str5;
    }

    public User(String str, String str2, String str3, String str4, String str5, Boolean bool) {
        this.uid = str;
        this.name = str2;
        this.phoneNumber = str3;
        this.profileImage = str4;
        this.stringTimeStamp = str5;
        this.MsgSeen = bool;
    }

    public String getUid() {
        return this.uid;
    }

    public String getStringTimeStamp() {
        return this.stringTimeStamp;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public void setStringTimeStamp(String str) {
        this.stringTimeStamp = str;
    }

    public String getProfileImage() {
        return this.profileImage;
    }

    public void setProfileImage(String str) {
        this.profileImage = str;
    }
}
