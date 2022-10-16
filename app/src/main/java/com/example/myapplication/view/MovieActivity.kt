package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.*
import com.example.myapplication.databinding.ActivityMovieBinding
import com.example.myapplication.viewmodel.MoviesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMovieBinding
    private val mViewModel: MoviesViewModel = MoviesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        mViewModel.getMovies()
        //Log.i("123","234")

////////////


        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = GridLayoutManager(this, 2)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of com.example.myapplication.view
        for (i in 1..50) {
            data.add(ItemsViewModel(androidx.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha, "Item " + i))
        }



     /////////////////////////////////API

        val apiInterface = ApiInterface.create().getMovies("b0688661789a70b864deef6539037e1b")

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue( object : Callback<movies>, CustomAdapter.ItemClickListener {
            override fun onResponse(call: Call<movies>?, response: Response<movies>?) {

                val adapter = CustomAdapter(response!!.body()!!.results, this)
                recyclerview.adapter = adapter

                //Log.i("checkkk", "successssss ${call?.toString()} ${response?.body()?.results}" )

            }

            override fun onFailure(call: Call<movies>?, t: Throwable?) {

            }


            override fun onItemClick(id: Int) {
                val intent = Intent(this@MovieActivity, MoviesDetailsActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)

            }
        })

    }



/////////////////////////////////////////
   override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }
}
