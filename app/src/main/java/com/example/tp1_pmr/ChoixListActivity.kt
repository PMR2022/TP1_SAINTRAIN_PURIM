package com.example.tp1_pmr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChoixListActivity : AppCompatActivity(), View.OnClickListener {
    val dataSet = mutableListOf("Liste 1","Liste 2","Liste 3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choix_list)

        val list = findViewById<RecyclerView>(R.id.list)
        list.adapter = ListAdapter(dataSet)
        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onClick(view: View) {
        when (view.id){
            R.id.btnOK -> {
                //...
            }
        }
    }
}