package com.tsamarahanifa.finalproject.teams

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.tsamarahanifa.finalproject.DetailActivity
import com.tsamarahanifa.finalproject.R
import com.tsamarahanifa.finalproject.api.ApiRepository
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.internals.AnkoInternals

class TeamsFragment : Fragment(), TeamView, AdapterView.OnItemSelectedListener, AnkoLogger {


    private lateinit var progressBar: ProgressBar
    private lateinit var rvAdapter: TeamAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: TeamPresenter

    private val teamList = mutableListOf<TeamModel>()
    private var currentLeague = "English Premier League"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rvAdapter = TeamAdapter(activity!!.applicationContext, teamList) {
            // intent to teamDetail
            context!!.startActivity(intentFor<DetailActivity>("TEAM" to it))
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_teams, container, false)
        progressBar = view.findViewById(R.id.progressbarTeams)

        recyclerView = view.findViewById(R.id.rvTeams)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = rvAdapter
        
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)

        //first  call
        NetworkRequestAsync().execute(currentLeague)
        return view
    }

    override fun showTeams(teams: List<TeamModel>?) {
        info { "showTeams" }
        teamList.clear()
        teams?.let { teamList.addAll(it) }
        rvAdapter.notifyDataSetChanged()
    }

    override fun showTeam(team: TeamModel?) {}

    override fun showLoading() {
        info { "shwLading" }
        progressBar.isEnabled = true
        progressBar.visibility = View.VISIBLE
        progressBar.progress = 50
    }

    override fun hideLoading() {
        info { "hideLoading" }
        progressBar.isEnabled = false
        progressBar.visibility = View.GONE
        progressBar.progress = 0
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }


    override fun onNothingSelected(p0: AdapterView<*>?) {}

    inline fun <reified T: Any> Fragment.intentFor(vararg params: Pair<String, Any?>): Intent =
        AnkoInternals.createIntent(activity!!, T::class.java, params)

    inner class NetworkRequestAsync : AsyncTask<String, Unit, List<TeamModel>>() {
        override fun onPreExecute() {
            super.onPreExecute()
            showLoading()
        }

        override fun doInBackground(vararg params: String?): List<TeamModel> {
            val leagueName = params[0] ?: return emptyList()
            val request = presenter.getTeamList(leagueName)
            val data = Gson().fromJson(request, TeamModelResponse::class.java)
            return data.teams
        }

        override fun onPostExecute(result: List<TeamModel>?) {
            super.onPostExecute(result)
            showTeams(result ?: emptyList())
            hideLoading()
        }
    }
}