package com.tsamarahanifa.finalproject.leagues

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class LeagueModel (
    @SerializedName("idLeague")
    var idLeague: String? = null,

    @SerializedName("strLeague")
    var strLeague: String? = null,

    @SerializedName("strSport")
    var strSport: String? = null,

    @SerializedName("strLeagueAlternate")
    var strLeagueAlternate: String? = null,
        ): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idLeague)
        parcel.writeString(strLeague)
        parcel.writeString(strSport)
        parcel.writeString(strLeagueAlternate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LeagueModel> {
        override fun createFromParcel(parcel: Parcel): LeagueModel {
            return LeagueModel(parcel)
        }

        override fun newArray(size: Int): Array<LeagueModel?> {
            return arrayOfNulls(size)
        }
    }
}

data class LeagueResponse(
    @SerializedName("leagues")
    val teams: List<LeagueModel>
)
