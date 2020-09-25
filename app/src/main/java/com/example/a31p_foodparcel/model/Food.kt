package com.example.a31p_foodparcel.model

import android.os.Parcel
import android.os.Parcelable

// TODO #2 turn Food into a parcelable object
class Food : Parcelable {
    private var name: String?
    private var locationURL: String? = ""
    private var keyword: String? = ""
    private var date: String?
    private var share = false
    private var authorEmail: String? = ""
    private var rating: Float? = 0f
    private var image = 0

    constructor(image: Int, name: String?, date: String?, authorEmail: String?) {
        this.name = name
        this.image = image
        this.date = date
        this.authorEmail = authorEmail
    }

    constructor(name: String?, locationURL: String?, keyword: String?, date: String?, share: Boolean, authorEmail: String?, rating: Float?) {
        this.name = name
        this.locationURL = locationURL
        this.keyword = keyword
        this.date = date
        this.share = share
        this.authorEmail = authorEmail
        this.rating = rating
    }

    fun updateFood(name: String?, locationURL: String?, keyword: String?, date: String?, share: Boolean,
                   authorEmail: String?, rating: Float?) {
        this.name = name
        this.locationURL = locationURL
        this.keyword = keyword
        this.date = date
        this.share = share
        this.authorEmail = authorEmail
        this.rating = rating
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, i: Int) {
        //PAY ATTENTION TO ORDER
        parcel.writeString(name)
        parcel.writeString(locationURL)
        parcel.writeString(keyword)
        parcel.writeString(date)
        parcel.writeInt(if (share) 1 else 0)
        parcel.writeString(authorEmail)
        parcel.writeFloat(rating)
        parcel.writeInt(image)
    }

    //Construct the object from the Parcel
    //MUST BE IN THE SAME ORDER WITH writeToParcel
    private constructor(parcel: Parcel?) {
        name = parcel.readString()
        locationURL = parcel.readString()
        keyword = parcel.readString()
        date = parcel.readString()
        share = parcel.readInt() == 1 //1 is true, 0 is false
        authorEmail = parcel.readString()
        rating = parcel.readFloat()
        image = parcel.readInt()
    }

    constructor()

    fun getShare(): Boolean {
        return share
    }

    fun getDate(): String? {
        return date
    }

    fun getAuthorEmail(): String? {
        return authorEmail
    }

    fun getKeyword(): String? {
        return keyword
    }

    fun getRating(): Float? {
        return rating
    }

    fun getName(): String? {
        return name
    }

    fun getLocationURL(): String? {
        return locationURL
    }

    fun getImage(): Int {
        return image
    }

    override fun toString(): String {
        return super.toString()
    }

    companion object {
        val CREATOR: Parcelable.Creator<Food?>? = object : Parcelable.Creator<Food?> {
            override fun createFromParcel(parcel: Parcel?): Food? {
                return Food(parcel)
            }

            override fun newArray(size: Int): Array<Food?>? {
                return arrayOfNulls<Food?>(size)
            }
        }
    }

}