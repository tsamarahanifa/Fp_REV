package com.tsamarahanifa.finalproject.teams

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tsamarahanifa.finalproject.R

class TeamAdapter(private val context: Context, private val teams: List<TeamModel>, private val listener: (TeamModel) -> Unit) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewTyoe: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.team_item, parent, false))

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: ViewHolder, postition: Int) {
        holder.bindItem(teams[postition], listener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(team: TeamModel, listener: (TeamModel) -> Unit) {

            val tvTeamName = itemView.findViewById<TextView>(R.id.teamName)
            val ivTeamLogo = itemView.findViewById<ImageView>(R.id.teamLogo)
            tvTeamName.text = team.strTeam
            Picasso.get().load(team.strTeamBadge).into(ivTeamLogo)
            itemView.setOnClickListener {
                listener(team)
            }
        }
    }
}