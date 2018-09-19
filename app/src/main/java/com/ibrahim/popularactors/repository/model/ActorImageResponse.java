package com.ibrahim.popularactors.repository.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActorImageResponse implements Parcelable {
    @SerializedName("profiles")
    @Expose
    private List<ActorImage> actorImageList = null;
    @SerializedName("id")
    @Expose
    private int id;

    public List<ActorImage> getActorImageList() {
        return actorImageList;
    }

    public void setActorImageList(List<ActorImage> actorImageList) {
        this.actorImageList = actorImageList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.actorImageList);
        dest.writeInt(this.id);
    }

    public ActorImageResponse() {
    }

    protected ActorImageResponse(Parcel in) {
        this.actorImageList = in.createTypedArrayList(ActorImage.CREATOR);
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<ActorImageResponse> CREATOR = new Parcelable.Creator<ActorImageResponse>() {
        @Override
        public ActorImageResponse createFromParcel(Parcel source) {
            return new ActorImageResponse(source);
        }

        @Override
        public ActorImageResponse[] newArray(int size) {
            return new ActorImageResponse[size];
        }
    };
}
