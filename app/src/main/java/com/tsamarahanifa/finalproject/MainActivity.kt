package com.tsamarahanifa.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tsamarahanifa.finalproject.leagues.LeagueFragment
import com.tsamarahanifa.finalproject.teams.TeamsFragment
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottomNavigation)

        setUpBottomNav()
        replaceFragment(LeagueFragment())

        Realm.init(this)
    }

    private fun setUpBottomNav() {
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_match -> {
                    replaceFragment(LeagueFragment())
                    true
                }
                R.id.bottom_teams -> {
                    replaceFragment(TeamsFragment())
                    true
                }
                R.id.bottom_favourties -> {
                    replaceFragment(FavoritesFragment())
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack("#", 0)
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrame, fragment)
            .addToBackStack("#")
            .commit()
    }

}