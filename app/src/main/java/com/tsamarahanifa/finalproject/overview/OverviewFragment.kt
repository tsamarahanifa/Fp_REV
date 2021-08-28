package com.tsamarahanifa.finalproject.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tsamarahanifa.finalproject.R
import com.tsamarahanifa.finalproject.teams.TeamModel

class OverviewFragment : Fragment() {
    private lateinit var team: TeamModel
    private lateinit var mView: View
    private lateinit var adapter: OverviewAdapter

    fun setTeam(team: TeamModel) {
        this.team = team
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = OverviewAdapter(activity!!.applicationContext, team)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_favorites, container, false)
        setupRecyclerView()
        return mView
    }

    private fun setupRecyclerView() {
        var recyclerView = mView.findViewById<RecyclerView>(R.id.rvFavorites)
        var progressBar = mView.findViewById<ProgressBar>(R.id.pbCommon_)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        progressBar.visibility = View.GONE
    }
}