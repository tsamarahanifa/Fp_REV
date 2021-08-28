package com.tsamarahanifa.finalproject.leagues

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tsamarahanifa.finalproject.R

class LeagueAdapter(private val context: Context, private val leagues: List<LeagueModel>, private val listener: (LeagueModel) -> Unit) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewTyoe: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.league_item, parent, false))

    override fun getItemCount() = leagues.size

    override fun onBindViewHolder(holder: ViewHolder, postition: Int) {
        holder.bindItem(leagues[postition], listener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(league: LeagueModel, listener: (LeagueModel) -> Unit) {

            val tvLeagueName = itemView.findViewById<TextView>(R.id.tvLeagueName)
            val tvSport = itemView.findViewById<TextView>(R.id.tvSport)
            tvLeagueName.text = league.strLeague
            tvSport.text = league.strLeague
            itemView.setOnClickListener {
                listener(league)
            }
        }
    }
}