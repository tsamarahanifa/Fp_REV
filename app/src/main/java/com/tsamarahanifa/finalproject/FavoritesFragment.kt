package com.tsamarahanifa.finalproject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tsamarahanifa.finalproject.teams.TeamAdapter
import com.tsamarahanifa.finalproject.teams.TeamModel
import io.realm.Realm
import org.jetbrains.anko.internals.AnkoInternals

class FavoritesFragment : Fragment() {


    private lateinit var realm: Realm
    private lateinit var v: View
    private lateinit var adapter: TeamAdapter
    private val teams = mutableListOf<TeamModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm = Realm.getDefaultInstance()
        adapter = TeamAdapter(activity!!.applicationContext, teams) {
            context!!.startActivity(intentFor<DetailActivity>("TEAM" to it))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_favorites, container, false)
        var pb = v.findViewById<ProgressBar>(R.id.pbCommon_)
        pb.visibility = View.GONE
        setupRecyclerView()
        getFavorites()
        return v
    }

    private fun getFavorites() {
        var tvNoFav = v.findViewById<TextView>(R.id.tvNoFav)
        val favorites = realm.where(TeamModel::class.java).findAll()
        if (favorites.isNotEmpty()) {
            teams.clear()
            teams.addAll(favorites)
            adapter.notifyDataSetChanged()
            tvNoFav.visibility = View.GONE
        } else {
            tvNoFav.visibility = View.VISIBLE
        }
    }

    inline fun <reified T: Any> Fragment.intentFor(vararg params: Pair<String, Any?>): Intent =
        AnkoInternals.createIntent(activity!!, T::class.java, params)

    private fun setupRecyclerView() {
        var rv = v.findViewById<RecyclerView>(R.id.rvFavorites)
        rv.layoutManager = LinearLayoutManager(activity)
        rv.adapter = adapter
    }
}