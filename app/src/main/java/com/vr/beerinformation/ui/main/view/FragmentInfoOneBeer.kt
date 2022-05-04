package com.vr.beerinformation.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.vr.beerinformation.R
import com.vr.beerinformation.data.model.Beer
import com.vr.beerinformation.ui.main.adapter.BtnBeerAdapter

class FragmentInfoOneBeer() : Fragment() {

    var beer=Beer()
    var ChekInternet : Boolean = true
    var toolbar:Toolbar?=null

    constructor(internet:Boolean?, beer: Beer) : this() {
        this.beer = beer
        ChekInternet = internet!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_info_one_beer, container, false)
        val image = view.findViewById<ImageView>(R.id.imageFragmentBeer)
        val toolbar =
        if (ChekInternet){
            Picasso.with(context)
                .load(beer.image_url)
                .into(image)
        }else{
            image.setImageResource(R.drawable.image_no_internet)
        }

        val date_text = view.findViewById<TextView>(R.id.date_text)
        date_text.setText(beer.first_brewed)
        val Info = view.findViewById<TextView>(R.id.all_info_text)
        Info.setText(beer.description)
        return view
    }
}