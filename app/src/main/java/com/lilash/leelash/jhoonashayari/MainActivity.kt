package com.lilash.leelash.jhoonashayari

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val layouts = intArrayOf(
            R.layout.welcome_slide_1,
            R.layout.welcome_slide_2,
            R.layout.welcome_slide_3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Utilities.isFirstRun(this)

        Utilities.fullScreen(this)

        setContentView(R.layout.activity_main)

        tab_layout.setupWithViewPager(view_pager)
        view_pager.adapter = MyViewPagerAdapter()


        btn_get_started.setOnClickListener {
            Utilities.launchLoginScreen(this)
        }
    }

    inner class MyViewPagerAdapter: PagerAdapter(){

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = layoutInflater.inflate(layouts[position], container, false)
            container.addView(view)
            return view
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return layouts.size
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }
}
