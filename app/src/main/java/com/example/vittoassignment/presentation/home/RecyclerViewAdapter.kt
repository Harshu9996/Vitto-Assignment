package com.example.vittoassignment.presentation.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vittoassignment.R
import com.example.vittoassignment.domain.News

class RecyclerViewAdapter(val context:Context, val onFavouriteCLick:(HomeScreenEvents)->Unit): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    val TAG = "RecyclerViewAdapter"
    var list: List<News> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_news_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currItem = list[position]
        holder.title.text = currItem.title
        holder.decsription.text = currItem.description
        holder.author.text = currItem.author
        if (currItem.isFavourite){
            holder.favourite.setImageResource(R.drawable.baseline_favorite_24)
        }
        holder.favourite.setOnClickListener {
            onFavouriteCLick(HomeScreenEvents.AddFavouriteNews(currItem))
            Log.d(TAG, "onBindViewHolder: Clicked at position $position")
        }

        Glide.with(context)
            .load(currItem.imageUrl)
            .into(holder.newsImage)

    }

    fun updateList(newsList:List<News>){
        list = newsList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){


        val title = itemView.findViewById<TextView>(R.id.recycler_title)
        val decsription = itemView.findViewById<TextView>(R.id.recycler_description)
        val newsImage = itemView.findViewById<ImageView>(R.id.recycler_image)
        val favourite = itemView.findViewById<ImageButton>(R.id.recycler_fav)
        val author = itemView.findViewById<TextView>(R.id.recycler_author)


    }

}