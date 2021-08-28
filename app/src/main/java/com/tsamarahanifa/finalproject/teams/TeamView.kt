package com.tsamarahanifa.finalproject.teams

import com.tsamarahanifa.finalproject.BaseView

interface TeamView : BaseView {
    fun showTeams(teams: List<TeamModel>?)
    fun showTeam(team: TeamModel?)
}