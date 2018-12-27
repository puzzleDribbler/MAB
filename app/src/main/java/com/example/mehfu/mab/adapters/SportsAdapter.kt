package com.example.mehfu.mab.adapters

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mehfu.mab.R
import com.example.mehfu.mab.fragments.SportsDetailFragments
import com.example.mehfu.mab.models.Sport
import kotlinx.android.synthetic.main.recyclerview_sports.view.*
import com.example.mehfu.mab.java.ImageConverter

class SportsAdapter(val context: Context?, val sports: List<Sport>?) : RecyclerView.Adapter<SportsAdapter.SportsViewHolder>(){


    companion object {
        const val KEY_SPORTS = "key_sports"
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SportsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_sports, p0, false)
        return SportsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sports?.size!!
    }

    override fun onBindViewHolder(sportsViewHolder : SportsViewHolder, position : Int) {

        val sports = sports?.get(position)

        sportsViewHolder.imageViewThumbnail.setImageBitmap(ImageConverter.stringToBitmap(sports?.image))
        sportsViewHolder.textViewTitle.text = sports?.name
        sportsViewHolder.textViewDesc.text = sports?.name
        sportsViewHolder.textViewDuration.text =  sports?.duration.toString() + "minutes played"


        if (sports?.isNonBall!!) {
            sportsViewHolder.textViewNonBall.visibility = View.VISIBLE
            sportsViewHolder.textViewBall.visibility = View.VISIBLE
        }else{
            sportsViewHolder.textViewNonBall.visibility = View.VISIBLE
            sportsViewHolder.textViewBall.visibility = View.VISIBLE
        }

    }


    inner class SportsViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val imageViewThumbnail = view.imageViewThumbnail
        val textViewTitle = view.textViewName
        val textViewDesc = view.textViewDesc
        val textViewDuration = view.textViewDuration
        val textViewBall = view.textViewBall
        val textViewNonBall = view.textViewNonBall



        init {

            view.textViewNonBall.visibility = View.INVISIBLE
            view.textViewBall.visibility = View.INVISIBLE

            view.setOnClickListener {
                val food = sports?.get(adapterPosition)

                val fragment = SportsDetailFragments()

                val bundle = Bundle()
                bundle.putSerializable(KEY_SPORTS, food)

                fragment.arguments = bundle

                (context as FragmentActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content_area, fragment)
                    .commit()
            }
        }
    }
}




















