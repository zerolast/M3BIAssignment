package com.stupid.techie.m3biassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.stupid.techie.m3biassignment.R
import com.stupid.techie.m3biassignment.model.Joke
import androidx.recyclerview.widget.RecyclerView

class JokeListAdapter(private val listOfJokes: MutableList<Joke>) :
    RecyclerView.Adapter<JokeListAdapter.JokesListViewHolder>() {


   inner class JokesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewLabel: TextView = itemView.findViewById(R.id.textViewJokeLabel)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.joke_list_item, parent,false)
        return JokesListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfJokes.size
    }

    override fun onBindViewHolder(holder: JokesListViewHolder, position: Int) {

        holder.textViewLabel.text = listOfJokes[position].content
    }


}