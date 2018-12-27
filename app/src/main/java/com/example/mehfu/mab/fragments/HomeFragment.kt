package com.example.mehfu.mab.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mehfu.mab.R
import com.example.mehfu.mab.adapters.SportsAdapter
import com.example.mehfu.mab.db.DbManager
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewSports.layoutManager = LinearLayoutManager(this!!.activity)



        Toast.makeText(activity, DbManager(activity).getAllSports()?.size.toString(), Toast.LENGTH_LONG).show()

        recyclerViewSports.adapter = SportsAdapter(context, DbManager(context).getAllSports())
    }
}