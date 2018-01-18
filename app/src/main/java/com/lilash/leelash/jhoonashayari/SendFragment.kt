package com.lilash.leelash.jhoonashayari


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_send.*


class SendFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_send, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Utilities.scaleDownWidgets(arrayOf(et_send_shayari))

        et_send_shayari.setOnFocusChangeListener { v, hasFocus ->
            Utilities.focusChangeListener(v, hasFocus)
        }
    }
}
