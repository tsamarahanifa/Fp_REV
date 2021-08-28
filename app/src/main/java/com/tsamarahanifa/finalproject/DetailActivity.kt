package com.tsamarahanifa.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import com.tsamarahanifa.finalproject.overview.OverviewFragment
import com.tsamarahanifa.finalproject.player.PlayersFragment
import com.tsamarahanifa.finalproject.teams.TeamModel
import io.realm.Realm
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity() {
    private lateinit var team: TeamModel
    private lateinit var vpAdapter: TabLayoutAdapter
    private lateinit var realm: Realm
    private var isFavorite: Boolean = false
    private lateinit var menuItem: MenuItem

    lateinit var teamDetailLogo: ImageView
    lateinit var teamDetailName: TextView
    lateinit var teamDetailYear: TextView
    lateinit var teamDetailStadium: TextView
    lateinit var tabTeamDetail: TabLayout
    lateinit var viewPagerTeamDetail:ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        teamDetailLogo = findViewById(R.id.teamDetailLogo)
        teamDetailName =findViewById(R.id.teamDetailName)
        teamDetailYear=findViewById(R.id.teamDetailYear)
        teamDetailStadium =findViewById(R.id.teamDetailStadium)
        tabTeamDetail = findViewById(R.id.tabTeamDetail)
        viewPagerTeamDetail =findViewById(R.id.viewPagerTeamDetail)

        realm = Realm.getDefaultInstance()
        team = intent.getParcelableExtra<TeamModel>("TEAM") ?: return

        vpAdapter = TabLayoutAdapter(supportFragmentManager).apply {
            addFragment(OverviewFragment().apply { setTeam(team) }, "Detail")
            addFragment(PlayersFragment().apply {  }, "Players")
        }
        setUpToolbarItem()
        setupViewPager()
    }
    private fun setupViewPager() {
        viewPagerTeamDetail.adapter = vpAdapter
        tabTeamDetail.setupWithViewPager(viewPagerTeamDetail, true)
    }

    private fun setUpToolbarItem() {
        Picasso.get().load(team.strTeamBadge).into(teamDetailLogo)
        teamDetailName.text = team.strTeam
        teamDetailYear.text = "${team.intFormedYear}"
        teamDetailStadium.text = team.strStadium
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fav_menu, menu)
        menuItem = menu!!.getItem(0)
        getFavoriteState()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_favorite -> {
                if (!isFavorite) addToRealm() else removeFromRealm()

                isFavorite = !isFavorite
                setFavoriteState()
            }
        }
        return true
    }

    private fun addToRealm() {
        realm.beginTransaction()
        realm.copyToRealm(team)
        realm.commitTransaction()
        toast("${team.strTeam} is added to favorite")
    }

    private fun removeFromRealm() {
        realm.beginTransaction()
        val dTeam = realm.where(TeamModel::class.java).equalTo("idTeam", team.idTeam).findFirst()
        dTeam?.deleteFromRealm()
        realm.commitTransaction()
        toast("${team.strTeam} is removed from favorite")
    }

    private fun setFavoriteState() {
        if (isFavorite) {
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        } else {
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun getFavoriteState() {
        val favorite = realm.where(TeamModel::class.java).equalTo("idTeam", team.idTeam).findAll()
        isFavorite = !favorite.isEmpty()
        setFavoriteState()
    }
}
