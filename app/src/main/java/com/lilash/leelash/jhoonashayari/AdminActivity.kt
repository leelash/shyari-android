package com.lilash.leelash.jhoonashayari

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.shayari_admin_item.view.*
import org.jetbrains.anko.toast

class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Utilities.fullScreen(this)

        setContentView(R.layout.activity_admin)

        val sl = arrayListOf<ApproveShayari>()
        for (i in 1..20){
            sl.add(ApproveShayari("Shayari $i"))
        }

        rv_admin.layoutManager = LinearLayoutManager(this)
        rv_admin.adapter = ApproveShayariAdapter(sl)
    }
}

private class ApproveShayari(val shayari: String)

private class ApproveShayariAdapter(private val shayari: List<ApproveShayari>): RecyclerView.Adapter<ApproveShayariAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val shayari = view.tv_admin_shayari as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context)
                .inflate(R.layout.shayari_admin_item, parent, false)

        item.findViewById<Button>(R.id.btn_approve).setOnClickListener {
            parent.context.toast("Approved")
        }

        item.findViewById<Button>(R.id.btn_reject).setOnClickListener {
            parent.context.toast("Rejected")
        }
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shayari = shayari[position]
        holder.shayari.text = shayari.shayari
    }

    override fun getItemCount(): Int {
        return shayari.size
    }
}













