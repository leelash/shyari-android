package com.lilash.leelash.jhoonashayari

import android.animation.LayoutTransition
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.schibsted.spain.parallaxlayerlayout.SensorTranslationUpdater
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.startActivity
import java.util.*


class SignupActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var sensorTranslationUpdater: SensorTranslationUpdater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Utilities.fullScreen(this)
        setContentView(R.layout.activity_signup)

        // Set adapter to autocompletetextview
        actv_country.setAdapter(ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resources.getStringArray(R.array.countries_array)))

        // android:animateLayoutChanges
        (signup_parallax as ViewGroup).layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        // Setting up the parallax view
        sensorTranslationUpdater = SensorTranslationUpdater(this)
        signup_parallax.setTranslationUpdater(sensorTranslationUpdater)

        Utilities.scaleDownWidgets(arrayOf(et_name,
                et_email,
                et_signup_password,
                et_dob,
                actv_country,
                et_phone))

        setupEditTextonFocusChangeListeners()

        btn_signup.setOnClickListener {
            startActivity<LoginActivity>("username" to et_phone.text.toString(),
                    "password" to et_signup_password.text.toString())
            finish()
        }
    }

    override fun onDateSet(view: DatePickerDialog, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        et_dob.setText("$dayOfMonth - $monthOfYear - $year", TextView.BufferType.EDITABLE)
        actv_country.requestFocus()
    }

    private fun setupEditTextonFocusChangeListeners(){
        et_name.setOnFocusChangeListener { v, hasFocus ->
            Utilities.focusChangeListener(v, hasFocus)
        }

        et_email.setOnFocusChangeListener { v, hasFocus ->
            Utilities.focusChangeListener(v, hasFocus)
        }

        et_signup_password.setOnFocusChangeListener { v, hasFocus ->
            Utilities.focusChangeListener(v, hasFocus)
        }

        et_dob.setOnFocusChangeListener { v, hasFocus ->
            Utilities.focusChangeListener(v, hasFocus)
            if(et_dob.text.toString().isEmpty()){
                val c = Calendar.getInstance()
                val dpd = DatePickerDialog.newInstance(
                        this,
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)
                )
                dpd.version = DatePickerDialog.Version.VERSION_2
                dpd.show(fragmentManager, "Select Date of birth")
            }
        }

        actv_country.setOnFocusChangeListener { v, hasFocus ->
            Utilities.focusChangeListener(v, hasFocus)
        }

        et_phone.setOnFocusChangeListener { v, hasFocus ->
            Utilities.focusChangeListener(v, hasFocus)
        }
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






































