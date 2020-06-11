package com.example.popularmovies.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.popularmovies.R
import com.example.popularmovies.adapter.MoviesAdapter
import com.example.popularmovies.model.Movie
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_recyclerview.*
import org.json.JSONException

const val MOVIE = "MOVIE"

class MainActivity : AppCompatActivity() {

    private val movies = arrayListOf<Movie>()
    private val moviesAdapter = MoviesAdapter(this, movies) { movieItem : Movie -> movieItemClicked(movieItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        MovieRecyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        MovieRecyclerView.adapter = moviesAdapter

        btnSubmit.setOnClickListener{ getMoviesFromAPI(etYear.text.toString()) }
    }

    private fun getMoviesFromAPI(year: String){
        movies.clear()
        moviesAdapter.notifyDataSetChanged()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(applicationContext)
        val url = "https://api.themoviedb.org/3/discover/movie?api_key=aa12e2dbfdc286cb0fd89c7530f97355&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&year=$year"

        val jsonArrayRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                Log.i("Logging", url)
                val results = response.getJSONArray("results")
                Log.i("Logging", results.toString())
                var ranking: Int = 0
                for (i in 0 until results.length()) {
                    try {
                        var jsonObject = results.getJSONObject(i)
                        ranking += 1
                        var imageURL = jsonObject.getString("poster_path")
                        var backdropImageURL = jsonObject.getString("backdrop_path")
                        var title = jsonObject.getString("title")
                        var voteAverage = jsonObject.getString("vote_average")
                        var overview = jsonObject.getString("overview")
                        var releaseDate = jsonObject.getString("release_date")

                        movies.add(
                            Movie(
                                ranking.toString(), title, imageURL, backdropImageURL, overview, voteAverage, releaseDate
                            )
                        )
                    } catch (e: JSONException) {

                    }
                }
                moviesAdapter.notifyDataSetChanged()
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
                Log.e("Logging", error.toString())
            }
        )
        queue.add(jsonArrayRequest)
    }

    private fun movieItemClicked(movieItem : Movie) {
        Log.i("Logging", movieItem.toString())

        val resultIntent = Intent(this, DetailActivity::class.java)
        resultIntent.putExtra(MOVIE, movieItem)
        startActivity(resultIntent)
    }
}