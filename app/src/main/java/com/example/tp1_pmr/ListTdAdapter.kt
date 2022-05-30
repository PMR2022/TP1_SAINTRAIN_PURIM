package com.example.tp1_pmr

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp1_pmr.activities.ShowListActivity

class ListTdAdapter(
    private val profile:Profile, private val dataSet: List<ListTD>
) : RecyclerView.Adapter<ListTdAdapter.ItemViewHolder>(), View.OnClickListener {

    override fun getItemCount(): Int = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = TextView(parent.context)
        itemView.textSize = 16f

        itemView.setOnClickListener(this)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun onClick(view: View) {
        val recyclerList = view.parent as RecyclerView
        val listTdIndex = recyclerList.getChildLayoutPosition(view)

        // Bundles the list index and start showListActivity
        val bundle = Bundle().apply {
            putString("pseudo", profile.getLogin())
            putInt("listTD index", listTdIndex)
        }
        val showListIntent = Intent(view.context, ShowListActivity::class.java).apply {
            putExtras(bundle)
        }
        view.context.startActivity(showListIntent)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView as? TextView

        fun bind(list: ListTD) {
           textView?.text = list.getTitle()
        }
    }
}