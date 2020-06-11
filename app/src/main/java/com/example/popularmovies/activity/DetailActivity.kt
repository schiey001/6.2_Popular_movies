package com.example.popularmovies.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.popularmovies.R
import com.example.popularmovies.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

const val ADD_MOVIE_REQUEST_CODE = 100

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val bundle: Bundle? = intent.extras

        bundle?.apply {
            //Parcelable Data
            val movie: Movie? = getParcelable("MOVIE")
            if (movie != null) {
                detailTitle.text = movie.title
                detailDate.text = movie.releaseDate
                detailOverview.text = movie.overview
                detailRating.text = movie.rating
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500/"+movie.imageURL)
                    .resize(500, 500)
                    .into(imageMovie)
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500/"+movie.backdropImageURL)
                    .resize(500, 500)
                    .into(backdropImage)
            }
        }
    }

    private fun initViews() {
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        Log.i("Logging", "OnACTIVITYRESULT")
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK) {
//            when (requestCode) {
//                ADD_MOVIE_REQUEST_CODE -> {
//                    val movie = data!!.getParcelableExtra<Movie>(MOVIE)
//                    detailTitle.text = movie.title
//                }
//            }
//        }
//    }
}