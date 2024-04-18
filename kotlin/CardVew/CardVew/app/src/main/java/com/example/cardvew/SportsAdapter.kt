package com.example.cardvew

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SportsAdapter(val sportsList:ArrayList<SportModel>)
    : RecyclerView.Adapter<SportsAdapter.MyViewHolder>() {


    inner class MyViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView){
        var sportImg: ImageView
        var sportName: TextView

        init {
            sportImg = itemView.findViewById(R.id.imageviewCard)
            sportName = itemView.findViewById(R.id.textView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_item_lauout, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return sportsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.sportImg.setImageResource(sportsList[position].sportImg)
        holder.sportName.setText(sportsList[position].sportName)
    }
}