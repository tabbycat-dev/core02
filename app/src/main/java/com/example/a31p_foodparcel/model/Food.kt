package com.example.a31p_foodparcel.model

import android.os.Parcel
import android.os.Parcelable

@Parcelize
data class Food(
        var image: Int?,
        var name: String?,
        var date: String?,
        var cusine: String?,
        var rating: Float = 0f)
    : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readFloat()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        image?.let { parcel.writeInt(it) }
        parcel.writeString(name)
        parcel.writeString(date)
        parcel.writeString(cusine)
        parcel.writeFloat(rating)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Food> {
        override fun createFromParcel(parcel: Parcel): Food {
            return Food(parcel)
        }

        override fun newArray(size: Int): Array<Food?> {
            return arrayOfNulls(size)
        }
    }
}

annotation class Parcelize


