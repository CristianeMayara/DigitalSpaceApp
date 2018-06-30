package com.cristiane.joyjetapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cristiane on 27/06/2018.
 */

public class Article implements Parcelable {

    private int id;
    private String title;
    private String summary;
    private String text;
    private int category;
    private int imageId;
    private boolean isFavorite;

    public Article(int id, String title, String summary, String text, int imageId, int category, boolean isFavorite) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.text = text;
        this.category = category;
        this.imageId = imageId;
        this.isFavorite = isFavorite;
    }

    public void setId(int value) {
        this.id = value;
    }

    public int getId() {
        return this.id;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getTitle() {
        return this.title;
    }

    public void setSummary(String value) {
        this.summary = value;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setText(String value) {
        this.text = value;
    }

    public String getText() {
        return this.text;
    }

    public void setCategory(int value) {
        this.category = value;
    }

    public int getCategory() {
        return this.category;
    }

    public void setImageId(int value) {
        this.imageId = value;
    }

    public int getImageId() {
        return this.imageId;
    }

    public void setFavorite(boolean value) {
        this.isFavorite = value;
    }

    public boolean isFavorite() {
        return this.isFavorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.summary);
        dest.writeString(this.text);
        dest.writeInt(this.category);
        dest.writeInt(this.imageId);
        dest.writeByte(this.isFavorite ? (byte) 1 : (byte) 0);
    }

    protected Article(Parcel in) {
        this.id = (int) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.summary = in.readString();
        this.text = in.readString();
        this.category = in.readInt();
        this.imageId = in.readInt();
        this.isFavorite = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
