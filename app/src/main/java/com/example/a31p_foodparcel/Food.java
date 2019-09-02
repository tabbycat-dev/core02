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
    private Date date;
    private Boolean share=false;
    private String authorEmail = "";
    private String rating="";
    public Food(String name){
        this.name = name;
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
    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
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

    public Date getDate() {
        return date;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
