package com.lilash.leelash.jhoonashayari

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        Utilities.fullScreen(this)

        replaceFragment(CategoryFragment(), "category_fragment")

        setupBottomNavigation()


    }

    private fun setupBottomNavigation(){
        val home = AHBottomNavigationItem(R.string.home, R.drawable.ic_home, R.color.md_cyan_A700 )
        val submitShayari = AHBottomNavigationItem(R.string.submit_shayari, R.drawable.ic_submit_shayari, R.color.md_light_blue_A700)
        val status = AHBottomNavigationItem(R.string.status, R.drawable.ic_status, R.color.md_blue_A700)

        navigation_bar.addItem(home)
        navigation_bar.addItem(submitShayari)
        navigation_bar.addItem(status)

        navigation_bar.defaultBackgroundColor = Color.parseColor("#80FEFEFE")

        navigation_bar.accentColor = Color.parseColor("#1976D2")
        navigation_bar.inactiveColor = Color.parseColor("#747474")

        navigation_bar.isForceTint = true

        navigation_bar.isTranslucentNavigationEnabled = true

        navigation_bar.titleState = AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE

        navigation_bar.isColored = true

        navigation_bar.setOnTabSelectedListener { position, wasSelected ->
            when(position){
                0 -> replaceFragment(CategoryFragment(), "category_fragment")
                1 -> replaceFragment(SendFragment(), "send_fragment")
                2 -> replaceFragment(StatusFragment(), "status_fragment")
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment, tag: String){
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragment, tag)
                .commit()
    }
}
