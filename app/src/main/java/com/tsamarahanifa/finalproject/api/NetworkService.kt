package com.tsamarahanifa.finalproject.api

import android.os.AsyncTask
import com.tsamarahanifa.finalproject.teams.TeamModel

class NetworkService
     : AsyncTask<Unit, Unit, List<TeamModel>>() {
        override fun doInBackground(vararg params: Unit?): List<TeamModel> {
            TODO("Not yet implemented")
        }

        override fun onPostExecute(result: List<TeamModel>?) {
            super.onPostExecute(result)

        }
    }
