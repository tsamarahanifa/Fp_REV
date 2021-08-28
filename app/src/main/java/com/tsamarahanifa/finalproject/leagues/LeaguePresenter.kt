package com.tsamarahanifa.finalproject.leagues

import com.google.gson.Gson
import com.tsamarahanifa.finalproject.api.ApiRepository
import com.tsamarahanifa.finalproject.api.ApiService

class LeaguePresenter(
    private val view: LeagueView,
    private val apiRequest: ApiRepository,
    private val gson: Gson,
) {

    fun getLeagueList() = apiRequest.doRequest(ApiService.getLeague())
}