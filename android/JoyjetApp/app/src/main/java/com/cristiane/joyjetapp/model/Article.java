package com.cristiane.joyjetapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristiane on 27/06/2018.
 */

public class Article implements Parcelable {

    private String title;
    private String description;
    private List<String> galery;
    private int category;
    private boolean isFavorite;

    public Article(String title, String description, List<String> galery, int category, boolean isFavorite) {
        this.title = title;
        this.description = description;
        this.galery = galery;
        this.category = category;
        this.isFavorite = isFavorite;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCategory(int value) {
        this.category = value;
    }

    public int getCategory() {
        return this.category;
    }

    public List<String> getGalery() {
        return galery;
    }

    public void setGalery(List<String> galery) {
        this.galery = galery;
    }

    public void setFavorite(boolean value) {
        this.isFavorite = value;
    }

    public boolean isFavorite() {
        return this.isFavorite;
    }

    public boolean equals(Article obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if ((this.title.equals(obj.title)) &&
                (isGaleryEquals(obj.galery)) &&
                (this.category == obj.category) &&
                (this.isFavorite == obj.isFavorite) &&
                (this.description.equals(obj.description)))
            return true;
        return false;
    }

    private boolean isGaleryEquals(List<String> galery) {
        if (galery.size() != this.galery.size())
            return false;

        for (int i = 0; i < galery.size(); i++) {
            if (!galery.get(i).equals(this.galery.get(i)))
                return false;
        }
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeList(this.galery);
        dest.writeInt(this.category);
        dest.writeByte(this.isFavorite ? (byte) 1 : (byte) 0);
    }

    protected Article(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.galery = new ArrayList<>();
        in.readList(this.galery, String.class.getClassLoader());
        this.category = in.readInt();
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
