package com.example.pabji.applectorrss.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Item implements Parcelable {
    private String title;
    private String link;
    private String description;
    private String content;
    private String imageUrl;
    private String pubDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content.trim();
    }

    public String getPubDate(){
        return pubDate;
    }

    public Date getFormatedDate() {
        DateFormat dateFormat = new SimpleDateFormat();
        Date date = new Date();
        try {
            date = dateFormat.parse(pubDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.link);
        dest.writeString(this.description);
        dest.writeString(this.content);
        dest.writeString(this.imageUrl);
        dest.writeString(this.pubDate);
    }

    public Item (){
        title = "";
        link = "";
        description = "";
        content = "";
        imageUrl = "";
        pubDate = "";
    }

    protected Item(Parcel in) {
        this.title = in.readString();
        this.link = in.readString();
        this.description = in.readString();
        this.content = in.readString();
        this.imageUrl = in.readString();
        this.pubDate = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
