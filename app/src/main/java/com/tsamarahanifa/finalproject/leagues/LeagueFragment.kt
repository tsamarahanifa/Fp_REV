package com.tsamarahanifa.finalproject.leagues

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
import com.tsamarahanifa.finalproject.R
import com.tsamarahanifa.finalproject.api.ApiRepository
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.internals.AnkoInternals

class LeagueFragment : Fragment(), LeagueView, AdapterView.OnItemSelectedListener, AnkoLogger {


    private lateinit var progressBar: ProgressBar
    private lateinit var rvAdapter: LeagueAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: LeaguePresenter

    private val leagueList = mutableListOf<LeagueModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rvAdapter = LeagueAdapter(activity!!.applicationContext, leagueList) {
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_league, container, false)
        progressBar = view.findViewById(R.id.progressBarLeague)

        recyclerView = view.findViewById(R.id.rv_league)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = rvAdapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = LeaguePresenter(this, request, gson)

        //first  call
        NetworkRequestAsync().execute()
        return view
    }

    override fun showLeagues(leagues: List<LeagueModel>?) {
        leagueList.clear()
        leagues?.let { leagueList.addAll(it) }
        rvAdapter.notifyDataSetChanged()
    }

    override fun showLeague(league: LeagueModel?) {}

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

    inner class NetworkRequestAsync : AsyncTask<String, Unit, List<LeagueModel>>() {
        override fun onPreExecute() {
            super.onPreExecute()
            showLoading()
        }

        override fun doInBackground(vararg params: String?): List<LeagueModel> {
            val request = presenter.getLeagueList()
            val data = Gson().fromJson(request, LeagueModelResponse::class.java)
            return data.leagues
        }

        override fun onPostExecute(result: List<LeagueModel>?) {
            super.onPostExecute(result)
            showLeagues(result ?: emptyList())
            hideLoading()
        }
    }
}