package com.tsamarahanifa.finalproject.teams

import com.google.gson.Gson
import com.tsamarahanifa.finalproject.api.ApiRepository
import com.tsamarahanifa.finalproject.api.ApiService

class TeamPresenter(
    private val view: TeamView,
    private val apiRequest: ApiRepository,
    private val gson: Gson,
) {


    fun getTeamListData(league: String?) {
        view.showLoading()


        val request = apiRequest.doRequest(ApiService.getTeams(league))
        val data =
            gson.fromJson(
                apiRequest.doRequest(ApiService.getTeams(league)),
                TeamModelResponse::class.java
            )

//        view.showTeamListData(data.await().teams)
        view.hideLoading()

    }

    fun getTeamList(league: String?) = apiRequest.doRequest(ApiService.getTeams(league))
}