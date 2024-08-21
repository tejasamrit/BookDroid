package com.booklal.booklal;

public class PublicProfileSellingBooks {
    private String BookDescription;
    private String BookID;
    private String BookName;
    private String BookPrice;
    private String ImageURL;

    public PublicProfileSellingBooks(String str, String str2, String str3, String str4, String str5) {
        this.BookName = str;
        this.BookDescription = str2;
        this.BookID = str3;
        this.ImageURL = str4;
        this.BookPrice = str5;
    }

    public String getBookName() {
        return this.BookName;
    }

    public String getBookDescription() {
        return this.BookDescription;
    }

    public String getBookPrice() {
        return this.BookPrice;
    }

    public String getImageURL() {
        return this.ImageURL;
    }

    public String getBookID() {
        return this.BookID;
    }
}
