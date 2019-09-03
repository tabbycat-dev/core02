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
    private Float rating = 0f;
    private int image= 0;

    public Food(int image,String name, String date, String authorEmail){
        this.name = name;
        this.image = image;
        this.date = date;
        this.authorEmail = authorEmail;
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

    public void updateFood(String name, String locationURL, String keyword, String date, boolean share,
                           String authorEmail, Float rating) {

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
        //PAY ATTENTION TO ORDER
        parcel.writeString(name);
        parcel.writeString(locationURL);
        parcel.writeString(keyword);
        parcel.writeString(date);
        parcel.writeInt(share ? 1 : 0);
        parcel.writeString(authorEmail);
        parcel.writeFloat(rating);
        parcel.writeInt(image);
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
    //MUST BE IN THE SAME ORDER WITH writeToParcel
    private Food(Parcel parcel){
        name = parcel.readString();
        locationURL = parcel.readString();
        keyword = parcel.readString();
        date = parcel.readString();
        share = parcel.readInt()==1; //1 is true, 0 is false
        authorEmail = parcel.readString();
        rating = parcel.readFloat();
        image = parcel.readInt();
    }

    public boolean getShare() { return share; }

    public String getDate() { return date; }

    public String getAuthorEmail() { return authorEmail; }

    public String getKeyword() { return keyword; }

    public Float getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public String getLocationURL() { return locationURL; }

    public int getImage() { return image; }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
