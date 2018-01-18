package com.lilash.leelash.jhoonashayari


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_status.*
import kotlinx.android.synthetic.main.shayari_status_item.view.*


class StatusFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val ss = arrayListOf<ShayariStatus>()
        for (i in 1..20){
            ss.add(ShayariStatus("Sample shayari submitted $i"))
        }

        rv_shayari_status.layoutManager = LinearLayoutManager(activity)
        rv_shayari_status.adapter = ShayariStatusAdapter(ss)
    }
}

private class ShayariStatus(val shayari: String)

private class ShayariStatusAdapter(private val shayari: List<ShayariStatus>): RecyclerView.Adapter<ShayariStatusAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val shayari = view.tv_shayari_status as TextView
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shayari = shayari[position]
        holder.shayari.text = shayari.shayari
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context)
                .inflate(R.layout.shayari_status_item, parent, false)

        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return shayari.size
    }
}