package com.lilash.leelash.jhoonashayari

import android.app.Activity
import android.os.Build
import android.preference.PreferenceManager
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import org.jetbrains.anko.startActivity

/**
 * Created by knightshade on 1/14/18.
 */
class Utilities{
    companion object {
        fun fullScreen(activityReference : Activity){
            if(Build.VERSION.SDK_INT >= 21){
                activityReference.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            activityReference.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN)

        }

        fun isFirstRun(activityReference: Activity){
            val prefs = PreferenceManager.getDefaultSharedPreferences(activityReference)
            val isFirstRun = prefs.getBoolean("isFirstRun", true)

            if(isFirstRun){
                prefs.edit().putBoolean("isFirstRun", false).apply()
            } else {
                launchLoginScreen(activityReference)
            }
        }

        fun launchLoginScreen(activityReference: Activity){
            activityReference.startActivity<LoginActivity>()
            activityReference.finish()
        }

        fun scaleDownWidgets( views: Array<EditText>){
            views.asSequence()
                    .map{ it.animate().setDuration(0).scaleX(0.85f).scaleY(0.85f).start()}
        }

        fun focusChangeListener(view: View, hasFocus: Boolean){
            if(hasFocus){
                view.animate().setDuration(100).scaleX(1f).scaleY(1f).start()
            } else {
                view.animate().setDuration(100).scaleX(0.85f).scaleY(0.85f).start()
            }
        }
    }
}