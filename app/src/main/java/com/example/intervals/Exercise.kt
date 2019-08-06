package com.example.intervals

import android.os.Parcel
import android.os.Parcelable

class Exercise(
    val name: String?, var exercise_time: Int = 0, var break_time: Int = 0,
    val chest: Int = 0, val back: Int = 0, val legs: Int = 0,
    val abs: Int = 0, val biceps: Int = 0, val triceps: Int = 0,
    val shoulders: Int = 0, val cardio: Int = 0) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(exercise_time)
        parcel.writeInt(break_time)
        parcel.writeInt(chest)
        parcel.writeInt(back)
        parcel.writeInt(legs)
        parcel.writeInt(abs)
        parcel.writeInt(biceps)
        parcel.writeInt(triceps)
        parcel.writeInt(shoulders)
        parcel.writeInt(cardio)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Exercise> {
        override fun createFromParcel(parcel: Parcel): Exercise {
            return Exercise(parcel)
        }

        override fun newArray(size: Int): Array<Exercise?> {
            return arrayOfNulls(size)
        }
    }

}
