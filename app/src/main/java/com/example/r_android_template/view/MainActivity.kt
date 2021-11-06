package com.example.r_android_template.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.r_android_template.R
import kotlinx.android.synthetic.main.activity_main.*
import models.jsonToModel
import utils.EstateAdapter

class MainActivity : AppCompatActivity(), EstateAdapter.OnItemClickListener {
    val estateList = jsonToModel()
    var clickedItemId = 0
    private lateinit var estateAdapter: EstateAdapter



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        estateAdapter = EstateAdapter(estateList, this, this)

        rvEstateList.adapter = estateAdapter
        rvEstateList.layoutManager = LinearLayoutManager(this)

        val pref = getPreferences(Context.MODE_PRIVATE)
        clickedItemId = pref.getInt("Id", 0)

        btShowEstateNumber.setOnClickListener{
            Toast.makeText(this, estateList[clickedItemId-1].estateNo, Toast.LENGTH_SHORT).show()
        }
    }

    fun onSave() {
        val pref = getPreferences(Context.MODE_PRIVATE)
        val editor = pref.edit()

        editor.putInt("Id", clickedItemId)
        editor.apply()
    }

    override fun onItemClick(position: Int) {

        clickedItemId = estateList[position].id
        onSave()


    }

}