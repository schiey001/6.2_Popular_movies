package com.example.popularmovies.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(

    var rank: String,
    var title: String,
    var imageURL: String,
    var backdropImageURL: String,
    var overview: String,
    var rating: String,
    var releaseDate: String

) : Parcelable {

}