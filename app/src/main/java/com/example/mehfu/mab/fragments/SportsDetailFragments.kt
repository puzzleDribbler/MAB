package com.example.mehfu.mab.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mehfu.mab.R
import com.example.mehfu.mab.adapters.SportsAdapter
import com.example.mehfu.mab.models.Sport


class SportsDetailFragments: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sports = arguments?.getSerializable(SportsAdapter.KEY_SPORTS) as Sport

        Toast.makeText(activity, sports.name, Toast.LENGTH_LONG).show()
    }
}