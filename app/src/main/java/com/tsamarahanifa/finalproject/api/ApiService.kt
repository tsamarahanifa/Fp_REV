package com.tsamarahanifa.finalproject.api

object ApiService {
    private const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"

    fun getTeams(league: String?): String {
        return "${BASE_URL}search_all_teams.php?l=$league"
    }

    fun getLeague(): String {
        return "${BASE_URL}all_leagues.php"
    }

}