package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.ApiInterface
import com.example.myapplication.data.MoviesDetails
import com.example.myapplication.databinding.ActivityMoviesDetailsBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesDetailsActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMoviesDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMoviesDetailsBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        val id = intent.getIntExtra("id", 0)


        /////////////////////////////////API

        //val apiInterface = ApiInterface.create().getMovies("b0688661789a70b864deef6539037e1b")

        //apiInterface.enqueue( Callback<List<Movie>>())
        val apiInterface =
            ApiInterface.create().getMoviesDetails(id, "b0688661789a70b864deef6539037e1b")

        apiInterface.enqueue(object : Callback<MoviesDetails> {
            override fun onResponse(
                call: Call<MoviesDetails>?,
                response: Response<MoviesDetails>?
            ) {

                val title = bindingClass.moviesDetailsTitle
                title.text = response!!.body()?.title
                val releaseDate = bindingClass.activityMoviesReleaseDateEnter
                releaseDate.text = response!!.body()?.release_date
                val overview = bindingClass.textView4
                overview.text = response!!.body()?.overview
                val score = bindingClass.activityMoviesScoreEnter
                score.text = response!!.body()?.vote_average.toString()

                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500" + response.body()?.backdrop_path)
                    .into(bindingClass.imageInFirstCardView)


                //Log.i("checkkk", "successssss ${call?.toString()} ${response?.body()?.results}" )
            }

            override fun onFailure(call: Call<MoviesDetails>?, t: Throwable?) {

            }

        })
    }}
