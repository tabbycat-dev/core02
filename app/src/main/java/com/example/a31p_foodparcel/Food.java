package com.example.a31p_foodparcel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;
// TODO #2 turn Food into a parcelable object

public class Food implements Parcelable {
    private String name;
    private String locationURL="";
    private String keyword="";
    private String date;
    private boolean share=false;
    private String authorEmail = "";
    private Float rating;
    public Food(String name){
        this.name = name;
    }
    public Food(String name, String locationURL, String keyword, String date, boolean share, String authorEmail, Float rating) {
        this.name = name;
        this.locationURL = locationURL;
        this.keyword = keyword;
        this.date = date;
        this.share = share;
        this.authorEmail = authorEmail;
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
    public static final Creator <Food> CREATOR = new Creator<Food>(){
        @Override
        public Food createFromParcel(Parcel parcel) {
            return new Food(parcel);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
    //Construct the object from the Parcel
    private Food(Parcel parcel){
        name = parcel.readString();

    }
    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Float getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationURL() {
        return locationURL;
    }

    public void setLocationURL(String locationURL) {
        this.locationURL = locationURL;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setShare(Boolean share) {
        this.share = share;
    }

    public Boolean getShare() {
        return share;
    }

    public String getDate() {
        return date;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
