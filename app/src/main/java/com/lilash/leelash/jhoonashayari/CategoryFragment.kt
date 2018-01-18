package com.lilash.leelash.jhoonashayari


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.shayari_category_item.view.*
import org.jetbrains.anko.toast

class CategoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sc = arrayListOf<ShayariCategory>()
        for (i in 1..11){
            sc.add(ShayariCategory("Shayari Category $i"))
        }

        rv_shayari_category.layoutManager = LinearLayoutManager(activity)
        rv_shayari_category.adapter = ShayariCategoryAdapter(sc)
    }
}

private class ShayariCategory(val shayari: String)

private class ShayariCategoryAdapter(private val shayari: List<ShayariCategory>): RecyclerView.Adapter<ShayariCategoryAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val shayari = view.tv_shayari_category as TextView
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shayari = shayari[position]
        holder.shayari.text = shayari.shayari
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context)
                .inflate(R.layout.shayari_category_item, parent, false)

        item.findViewById<LinearLayout>(R.id.ll_shayari_category_item_layout).setOnClickListener {
            parent.context.toast("Clicked!")
        }

        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return shayari.size
    }
}