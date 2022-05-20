package com.example.tp1_pmr

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(
    private val dataSet: List<String>
) : RecyclerView.Adapter<ListAdapter.ItemViewHolder>(), View.OnClickListener {

    override fun getItemCount(): Int = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = TextView(parent.context)
        itemView.setOnClickListener(this)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun onClick(view: View) {
        val list = view.parent as RecyclerView
        val itemIndex = list.getChildLayoutPosition(view)

        val context = view.context
        val bundle = Bundle().apply {
            putInt("item index", itemIndex);
        }
        val showListIntent = Intent(context, ShowListActivity::class.java).apply {
            putExtras(bundle)
        }
        context.startActivity(showListIntent)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView as? TextView

        fun bind(text: String) {
           textView?.text = text
        }
    }
}