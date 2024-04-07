package com.vr.beerinformation.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.vr.beerinformation.R
import com.vr.beerinformation.data.model.Beer

class FragmentInfoOneBeer() : Fragment() {

    var beer=Beer()
    private var checkInternet : Boolean = true

    constructor(internet:Boolean?, beer: Beer) : this() {
        this.beer = beer
        checkInternet = internet!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_info_one_beer, container, false)
        val image = view.findViewById<ImageView>(R.id.imageFragmentBeer)
        val dateText = view.findViewById<TextView>(R.id.date_text)
        val textInfo = view.findViewById<TextView>(R.id.all_info_text)

        if (checkInternet){
            Picasso.get()
                .load(beer.image_url)
                .into(image)
        }else{
            image.setImageResource(R.drawable.image_no_internet)
        }
        dateText.text = beer.first_brewed
        textInfo.text = beer.description
        return view
    }
}