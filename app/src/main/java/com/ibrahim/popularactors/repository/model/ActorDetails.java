package com.ibrahim.popularactors.repository.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActorDetails implements Parcelable {
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("known_for_department")
    @Expose
    private String knownForDepartment;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private int gender;
    @SerializedName("biography")
    @Expose
    private String biography;
    @SerializedName("popularity")
    @Expose
    private double popularity;
    @SerializedName("place_of_birth")
    @Expose
    private String placeOfBirth;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;
    @SerializedName("adult")
    @Expose
    private boolean adult;
    @SerializedName("imdb_id")
    @Expose
    private String imdbId;


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public void setKnownForDepartment(String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.birthday);
        dest.writeString(this.knownForDepartment);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.gender);
        dest.writeString(this.biography);
        dest.writeDouble(this.popularity);
        dest.writeString(this.placeOfBirth);
        dest.writeString(this.profilePath);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.imdbId);
    }

    public ActorDetails() {
    }

    protected ActorDetails(Parcel in) {
        this.birthday = in.readString();
        this.knownForDepartment = in.readString();
        this.id = in.readInt();
        this.name = in.readString();
        this.gender = in.readInt();
        this.biography = in.readString();
        this.popularity = in.readDouble();
        this.placeOfBirth = in.readString();
        this.profilePath = in.readString();
        this.adult = in.readByte() != 0;
        this.imdbId = in.readString();
    }

    public static final Parcelable.Creator<ActorDetails> CREATOR = new Parcelable.Creator<ActorDetails>() {
        @Override
        public ActorDetails createFromParcel(Parcel source) {
            return new ActorDetails(source);
        }

        @Override
        public ActorDetails[] newArray(int size) {
            return new ActorDetails[size];
        }
    };
}
