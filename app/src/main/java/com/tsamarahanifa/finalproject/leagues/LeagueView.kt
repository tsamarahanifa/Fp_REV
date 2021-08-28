package com.tsamarahanifa.finalproject.leagues

import com.tsamarahanifa.finalproject.BaseView

interface LeagueView : BaseView {
    fun showLeagues(teams: List<LeagueModel>?)
    fun showLeague(team: LeagueModel?)
}