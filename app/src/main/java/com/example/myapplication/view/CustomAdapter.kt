package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Result
import com.squareup.picasso.Picasso


class CustomAdapter(private val mList: List<Result>, val mItemClickListener: ItemClickListener) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    // create new views//создание ViewHolder’ов
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design com.example.myapplication.view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a com.example.myapplication.view//заполнение ViewHolder’ов информацией
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //val itemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + mList.get(position)?.poster_path)
            .into(holder.imageView)

        // sets the text to the textview from our itemHolder class
        //holder.textView.text = Result.title

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        init {

          ItemView.setOnClickListener {
                mList?.get(position)?.id?.let  {
                    it -> mItemClickListener.onItemClick(it)}
                }
            }
        }
    }



