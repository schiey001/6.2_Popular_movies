package com.example.popularmovies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.R
import com.example.popularmovies.model.Movie
import com.squareup.picasso.Picasso

class MoviesAdapter(private val context: Context, private val movies: ArrayList<Movie>, private val clickListener: (Movie) -> Unit) : RecyclerView.Adapter<MoviesAdapter.ViewHolder?>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie, clickListener: (Movie) -> Unit) {

            val movieRank: TextView = itemView.findViewById(R.id.tvRanking)
            val movieImage: ImageView = itemView.findViewById(R.id.imageMovie)

            movieRank.text = movie.rank
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/"+movie.imageURL)
                .resize(500, 500)
                .into(movieImage)

            itemView.setOnClickListener { clickListener(movie) }
        }
    }

    /**
     * Creates and returns a ViewHolder object.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.list_item_grid_movie, parent, false)
        return ViewHolder(view)
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return movies.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], clickListener)
    }

}