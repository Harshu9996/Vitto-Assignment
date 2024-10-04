package com.example.vittoassignment.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vittoassignment.R
import com.example.vittoassignment.util.Category

class TagsRecyclerViewAdapter(private val tagList:List<Category>,val onTagChanged:(HomeScreenEvents)->Unit):RecyclerView.Adapter<TagsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tag_recyclerview_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return tagList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tag.text = tagList[position].toString().lowercase()
        holder.tag.setOnClickListener {
            onTagChanged(HomeScreenEvents.ChangTag(tagList[position]))
        }
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val tag = itemView.findViewById<TextView>(R.id.tag_title)

    }
}