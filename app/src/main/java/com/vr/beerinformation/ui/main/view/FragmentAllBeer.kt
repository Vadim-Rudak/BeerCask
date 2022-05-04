package com.vr.beerinformation.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vr.beerinformation.ui.main.adapter.BtnBeerAdapter
import com.vr.beerinformation.R

class FragmentAllBeer () : Fragment() {
    var view: RecyclerView? = null
    var adapter = BtnBeerAdapter()
    var toolbar : Toolbar?=null

    constructor(toolbar: Toolbar?,adapter: BtnBeerAdapter) : this() {
        this.adapter = adapter
        this.toolbar=toolbar
    }

    override fun onStart() {
        super.onStart()
        toolbar?.title = "InfoBeer"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_all_beer, container, false) as RecyclerView?
        val layoutManager = LinearLayoutManager(activity as MainActivity)
        view?.layoutManager = layoutManager

        view?.adapter = adapter
        adapter.setListener(object : BtnBeerAdapter.Listener {
            override fun Clicked(pos_beer: Int) {
                val fragmentInfoOneBeer = FragmentInfoOneBeer(adapter.ChekInternet,adapter.beerList[pos_beer])
                toolbar?.title = adapter.beerList[pos_beer].name
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fr_place, fragmentInfoOneBeer)
                    ?.addToBackStack(null)
                    ?.commit()
            }
        })
        return view
    }

}