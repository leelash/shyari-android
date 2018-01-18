package com.lilash.leelash.jhoonashayari

import android.animation.LayoutTransition
import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.schibsted.spain.parallaxlayerlayout.SensorTranslationUpdater

import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {


    private var normalLogin = true
    private var authenticationUrl = "normalUrl"
    private lateinit var sensorTranslationUpdater: SensorTranslationUpdater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Utilities.fullScreen(this)

        setContentView(R.layout.activity_login)

        et_username.setText(intent.getStringExtra("username"))
        et_password.setText(intent.getStringExtra("password"))


        // android:animateLayoutChanges
        (parallax as ViewGroup).layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        // Setting up the parallax view
        sensorTranslationUpdater = SensorTranslationUpdater(this)
        parallax.setTranslationUpdater(sensorTranslationUpdater)

        // Initial scaling down the widgets.
        Utilities.scaleDownWidgets(arrayOf(et_username, et_password))

        // Setup onFocusChangeListeners on editText
        setupEditTextFocusChangeListeners()



        btn_admin_login.setOnClickListener {
            if(normalLogin){
                normalLogin = !normalLogin
                authenticationUrl = "adminUrl"
                btn_admin_login.text = getString(R.string.login_as_normal_user)
                btn_login.text = getString(R.string.login_as_admin)
                buttonSpringAnimation()

            }else{
                normalLogin = !normalLogin
                authenticationUrl = "normalUrl"
                btn_admin_login.text = getString(R.string.login_as_admin)
                btn_login.text = getString(R.string.login)
                buttonSpringAnimation()
            }
        }

        btn_do_signup.setOnClickListener {
            startActivity<SignupActivity>()
            finish()
        }

        btn_login.setOnClickListener {
            if (normalLogin){
                startActivity<UserActivity>()
                finish()
            } else {
                startActivity<AdminActivity>()
                finish()
            }
        }
    }

    private fun setupEditTextFocusChangeListeners(){
        et_username.setOnFocusChangeListener { v, hasFocus ->
            Utilities.focusChangeListener(v, hasFocus)
        }

        et_password.setOnFocusChangeListener { v, hasFocus ->
            Utilities.focusChangeListener(v, hasFocus)
        }
    }

    private fun buttonSpringAnimation(){
        val springAnimation = SpringAnimation(btn_login, DynamicAnimation.TRANSLATION_X, 0f)
        springAnimation.setStartVelocity(10000f)
        springAnimation.spring.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
        springAnimation.spring.stiffness = SpringForce.STIFFNESS_LOW
        springAnimation.start()
    }

    override fun onResume() {
        super.onResume()
        sensorTranslationUpdater.registerSensorManager()
    }

    override fun onPause() {
        sensorTranslationUpdater.unregisterSensorManager()
        super.onPause()
    }

}
