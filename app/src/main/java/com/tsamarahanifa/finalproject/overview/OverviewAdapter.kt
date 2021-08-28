package com.tsamarahanifa.finalproject.overview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tsamarahanifa.finalproject.R
import com.tsamarahanifa.finalproject.teams.TeamModel

class OverviewAdapter(private val context: Context, private val team: TeamModel) : RecyclerView.Adapter<OverviewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewTyoe: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_overview, parent, false))

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ViewHolder, postition: Int) {
        holder.bindItem(team)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(team: TeamModel) {

            var desc = itemView.findViewById<TextView>(R.id.tvTeamDescription)
            var stadium = itemView.findViewById<TextView>(R.id.tvStadiumDescription)

            desc.text = team.strDescriptionEN
            stadium.text = team.strStadiumDescription
        }
    }
}