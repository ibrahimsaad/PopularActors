package com.ibrahim.popularactors.repository.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActorImage implements Parcelable {
    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("vote_count")
    @Expose
    private int voteCount;
    @SerializedName("vote_average")
    @Expose
    private double voteAverage;
    @SerializedName("file_path")
    @Expose
    private String filePath;
    @SerializedName("aspect_ratio")
    @Expose
    private double aspectRatio;


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeInt(this.voteCount);
        dest.writeDouble(this.voteAverage);
        dest.writeString(this.filePath);
        dest.writeDouble(this.aspectRatio);
    }

    public ActorImage() {
    }

    protected ActorImage(Parcel in) {
        this.width = in.readInt();
        this.height = in.readInt();
        this.voteCount = in.readInt();
        this.voteAverage = in.readDouble();
        this.filePath = in.readString();
        this.aspectRatio = in.readDouble();
    }

    public static final Parcelable.Creator<ActorImage> CREATOR = new Parcelable.Creator<ActorImage>() {
        @Override
        public ActorImage createFromParcel(Parcel source) {
            return new ActorImage(source);
        }

        @Override
        public ActorImage[] newArray(int size) {
            return new ActorImage[size];
        }
    };
}
