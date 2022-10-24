package com.vr.beerinformation.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vr.beerinformation.R
import com.vr.beerinformation.data.model.Beer

class BtnBeerAdapter : RecyclerView.Adapter<BtnBeerAdapter.ViewHolder>() {
    private var listener: Listener? = null
    var beerList = mutableListOf<Beer>()
    var ChekInternet : Boolean?= true

    fun setBeer(movies: List<Beer>) {
        this.beerList = movies.toMutableList()
        notifyDataSetChanged()
    }

    interface Listener {
        fun Clicked(pos_beer: Int)
    }

    override fun getItemCount(): Int = beerList.size


    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_button_beer, parent, false) as CardView
        return ViewHolder(cv)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.cardView
        val name_b = cardView.findViewById<View>(R.id.text_name_beer) as TextView
        name_b.text = beerList[position].name
        val id_b = cardView.findViewById<View>(R.id.text_id_beer) as TextView
        id_b.text = position.toString()
        val image = cardView.findViewById<View>(R.id.image_beer) as ImageView
        if (ChekInternet!!){
            Picasso.with(cardView.context)
                .load(beerList[position].image_url)
                .into(image)
        }else{
            image.setImageResource(R.drawable.image_no_internet)
        }
        cardView.setOnClickListener {
            if (listener != null) {
                listener!!.Clicked(id_b.text.toString().toInt())
            }
        }
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}