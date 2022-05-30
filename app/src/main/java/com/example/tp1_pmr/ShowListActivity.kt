package com.example.tp1_pmr

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class ShowListActivity : AppCompatActivity(), View.OnClickListener {
    private var sharedPreferences: SharedPreferences? = null
    private var profile: Profile? = null
    private var listTD: ListTD? = null
    private var dataSet: MutableList<ItemTD>? = null

    private var recyclerList : RecyclerView? = null
    private var refBtnOK: Button? = null
    private var refEdtNewItem: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)

        val extras = intent.extras
        val pseudo = extras?.getString("pseudo")
        val listTdIndex = extras?.getInt("listTD index")

        // Gets listTD from the bundle
        sharedPreferences = getSharedPreferences("Profiles",0)
        val jsonProfile = sharedPreferences?.getString(pseudo,"DEFAULT")
        profile = Gson().fromJson(jsonProfile,Profile::class.java)
        listTD = profile?.getLists()?.get(listTdIndex!!)

        dataSet = listTD?.getItems()

        recyclerList = findViewById(R.id.list)
        recyclerList?.adapter = ItemTdAdapter(profile!!,dataSet!!)
        recyclerList?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerList?.setOnClickListener(this)

        refEdtNewItem = findViewById(R.id.newItem)
        refBtnOK = findViewById(R.id.btnOK)
        refBtnOK?.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id){
            R.id.btnOK -> {
                // Creates new itemTD and update the dataset
                val itemTD = ItemTD(refEdtNewItem?.text.toString())
                dataSet!!.add(itemTD)
                recyclerList?.adapter?.notifyItemInserted(dataSet!!.size-1)

                // Saves new user profile
                val profileGson = Gson().toJson(profile)
                sharedPreferences?.edit()?.apply(){
                    putString(profile?.getLogin(),profileGson)
                    apply()
                }
            }
        }
    }
}