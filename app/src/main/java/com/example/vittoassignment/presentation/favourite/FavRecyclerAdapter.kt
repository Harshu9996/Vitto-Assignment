package com.example.vittoassignment.presentation.favourite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vittoassignment.R
import com.example.vittoassignment.domain.News
import com.example.vittoassignment.presentation.favourite.FavRecyclerAdapter.*

class FavRecyclerAdapter(val context:Context): RecyclerView.Adapter<ViewHolder>() {

    var list = listOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fav_recycler_view_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currItem = list[position]
        holder.title.text = currItem.title
        holder.description.text = currItem.description
        holder.author.text = currItem.author
        Glide.with(context).load(currItem.imageUrl).into(holder.image)
    }

    fun updateList(newsList:List<News>){
        list = newsList
        notifyDataSetChanged()

    }


    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

        val title = itemView.findViewById<TextView>(R.id.fav_recycler_title)
        val description = itemView.findViewById<TextView>(R.id.fav_recycler_description)
        val author = itemView.findViewById<TextView>(R.id.fav_recycler_author)
        val image = itemView.findViewById<ImageView>(R.id.fav_recycler_image)



    }

}