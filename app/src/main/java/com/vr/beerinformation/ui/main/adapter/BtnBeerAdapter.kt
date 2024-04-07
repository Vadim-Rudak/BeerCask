package com.vr.beerinformation.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
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
    private var beerList:List<Beer> = listOf()
    var chekInternet : Boolean?= true

    @SuppressLint("NotifyDataSetChanged")
    fun setBeer(listBeer: List<Beer>) {
        this.beerList = listBeer
        notifyDataSetChanged()
    }

    interface Listener {
        fun clicked(beer: Beer)
    }

    override fun getItemCount() = beerList.size


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
        val idBeer = cardView.findViewById<TextView>(R.id.text_id_beer)
        val nameBeer = cardView.findViewById<TextView>(R.id.text_name_beer)
        val image = cardView.findViewById<ImageView>(R.id.image_beer)

        idBeer.text = position.toString()
        nameBeer.text = beerList[position].name
        if (chekInternet!!){
            Picasso.get().load(beerList[position].image_url).into(image)
        }else{
            image.setImageResource(R.drawable.image_no_internet)
        }
        cardView.setOnClickListener {
            if (listener != null) {
                listener!!.clicked(beerList[position])
            }
        }
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}