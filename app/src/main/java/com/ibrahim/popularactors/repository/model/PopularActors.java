package com.ibrahim.popularactors.repository.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularActors implements Parcelable {
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_results")
    @Expose
    private int totalResults;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("results")
    @Expose
    private List<Actor> actors = null;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setResults(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.totalResults);
        dest.writeInt(this.totalPages);
        dest.writeTypedList(this.actors);
    }

    public PopularActors() {
    }

    protected PopularActors(Parcel in) {
        this.page = in.readInt();
        this.totalResults = in.readInt();
        this.totalPages = in.readInt();
        this.actors = in.createTypedArrayList(Actor.CREATOR);
    }

    public static final Parcelable.Creator<PopularActors> CREATOR = new Parcelable.Creator<PopularActors>() {
        @Override
        public PopularActors createFromParcel(Parcel source) {
            return new PopularActors(source);
        }

        @Override
        public PopularActors[] newArray(int size) {
            return new PopularActors[size];
        }
    };
}
