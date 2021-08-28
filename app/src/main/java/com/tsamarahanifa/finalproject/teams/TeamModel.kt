package com.tsamarahanifa.finalproject.teams

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TeamModel (
    @SerializedName("strStadiumDescription")
    var strStadiumDescription: String? = null,

    @SerializedName("strTeamFanart1")
    var strTeamFanart1: String? = null,

    @SerializedName("idLeague")
    var idLeague: String? = null,

    @SerializedName("strTeamLogo")
    var strTeamLogo: String? = null,

    @SerializedName("strStadiumLocation")
    var strStadiumLocation: String? = null,

    @SerializedName("strCountry")
    var strCountry: String? = null,

    @SerializedName("strTeamBanner")
    var strTeamBanner: String? = null,

    @SerializedName("strStadiumThumb")
    var strStadiumThumb: String? = null,

    @SerializedName("intFormedYear")
    var intFormedYear: String? = null,

    @PrimaryKey
    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String? = null,

    @SerializedName("strTeam")
    var strTeam: String? = null,

    @SerializedName("strStadium")
    var strStadium: String? = null,

    @SerializedName("strTeamBadge")
    var strTeamBadge: String? = null,

    @SerializedName("strLeague")
    var strLeague: String? = null,

    @SerializedName("strManager")
    var strManager: String? = null

        ): RealmObject(), Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(strStadiumDescription)
        parcel.writeString(strTeamFanart1)
        parcel.writeString(idLeague)
        parcel.writeString(strTeamLogo)
        parcel.writeString(strStadiumLocation)
        parcel.writeString(strCountry)
        parcel.writeString(strTeamBanner)
        parcel.writeString(strStadiumThumb)
        parcel.writeString(intFormedYear)
        parcel.writeString(idTeam)
        parcel.writeString(strDescriptionEN)
        parcel.writeString(strTeam)
        parcel.writeString(strStadium)
        parcel.writeString(strTeamBadge)
        parcel.writeString(strLeague)
        parcel.writeString(strManager)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TeamModel> {
        override fun createFromParcel(parcel: Parcel): TeamModel {
            return TeamModel(parcel)
        }

        override fun newArray(size: Int): Array<TeamModel?> {
            return arrayOfNulls(size)
        }
    }
}

data class TeamResponse(
    @SerializedName("teams")
    val teams: List<TeamModel>
)
