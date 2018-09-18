package com.ibrahim.popularactors.repository.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Actor implements Parcelable {
    @SerializedName("popularity")
    @Expose
    private double popularity;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("adult")
    @Expose
    private boolean adult;

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.popularity);
        dest.writeInt(this.id);
        dest.writeString(this.profilePath);
        dest.writeString(this.name);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
    }

    public Actor() {
    }

    protected Actor(Parcel in) {
        this.popularity = in.readDouble();
        this.id = in.readInt();
        this.profilePath = in.readString();
        this.name = in.readString();
        this.adult = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Actor> CREATOR = new Parcelable.Creator<Actor>() {
        @Override
        public Actor createFromParcel(Parcel source) {
            return new Actor(source);
        }

        @Override
        public Actor[] newArray(int size) {
            return new Actor[size];
        }
    };
}
