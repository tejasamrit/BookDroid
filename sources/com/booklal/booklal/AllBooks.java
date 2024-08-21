package com.booklal.booklal;

public class AllBooks {
    private String BookAuthor;
    private String BookDescription;
    private String BookID;
    private String BookName;
    private String BookPublication;
    private String BookSellingPrice;
    String ImgURL;
    private String pDistance;

    public String getBookSellingPrice() {
        return this.BookSellingPrice;
    }

    public String getBookID() {
        return this.BookID;
    }

    public String getBookPublication() {
        return this.BookPublication;
    }

    public String getpDistance() {
        return this.pDistance;
    }

    public String getImgURL() {
        return this.ImgURL;
    }

    public AllBooks(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.BookName = str;
        this.BookDescription = str2;
        this.BookID = str3;
        this.ImgURL = str4;
        this.pDistance = str5;
        this.BookPublication = str6;
        this.BookSellingPrice = str7;
        this.BookAuthor = str8;
    }

    public String getNameOfBook() {
        return this.BookName;
    }

    public String getDescriptionOfBook() {
        return this.BookDescription;
    }

    public String getBookAuthor() {
        return this.BookAuthor;
    }

    public void setBookAuthor(String str) {
        this.BookAuthor = str;
    }
}
