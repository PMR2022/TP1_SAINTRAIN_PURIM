package com.example.tp1_pmr.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp1_pmr.ListTD
import com.example.tp1_pmr.ListTdAdapter
import com.example.tp1_pmr.Profile
import com.example.tp1_pmr.R
import com.google.gson.Gson

class ChoixListActivity : AppCompatActivity(), View.OnClickListener {
    private var sharedPreferences: SharedPreferences? = null
    private var profile: Profile? = null
    private var dataSet: MutableList<ListTD>? = null

    private var recyclerList : RecyclerView? = null
    private var refBtnOK: Button? = null
    private var refEdtNewList: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choix_list)

        sharedPreferences = getSharedPreferences("Profiles",0)

        // Gets profile from the bundle
        val extras = intent.extras
        val pseudo = extras?.getString("pseudo")
        val jsonProfile = sharedPreferences?.getString(pseudo,"DEFAULT")
        profile = Gson().fromJson(jsonProfile, Profile::class.java)

        dataSet = profile?.getLists()

        recyclerList = findViewById(R.id.list)
        recyclerList?.adapter = ListTdAdapter(profile!!,dataSet!!)
        recyclerList?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        refEdtNewList = findViewById(R.id.newList)
        refBtnOK = findViewById(R.id.btnOK)
        refBtnOK?.setOnClickListener(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_preferences -> {
                val extras = intent.extras
                val pseudo = extras?.getString("pseudo")
                val bundle = Bundle().apply {
                    putString("pseudo", pseudo)
                }
                val settingsIntent = Intent(this, SettingsActivity::class.java).apply {
                    putExtras(bundle)
                }
                startActivity(settingsIntent)
            }
        }
        return true
    }

    override fun onClick(view: View) {
        when (view.id){
            R.id.btnOK -> {
                //Creates new listTD and update the dataset
                val listTD = ListTD(refEdtNewList?.text.toString())
                dataSet!!.add(listTD)
                recyclerList?.adapter?.notifyItemInserted(dataSet!!.size -1)

                // Saves new user profile
                val profileGson = Gson().toJson(profile)
                sharedPreferences?.edit()?.apply {
                    putString(profile?.getLogin(), profileGson)
                    apply()
                }
            }
        }
    }
}