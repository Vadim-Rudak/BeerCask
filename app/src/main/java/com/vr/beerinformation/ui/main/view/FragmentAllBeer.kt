package com.vr.beerinformation.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vr.beerinformation.R
import com.vr.beerinformation.ui.main.adapter.ItemDecoration
import com.vr.beerinformation.ui.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class FragmentAllBeer : Fragment() {

    private val viewModel by activityViewModel<MainViewModel>()

    override fun onStart() {
        super.onStart()
        viewModel.title.postValue(resources.getString(R.string.name_in_splash))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_all_beer, container, false) as RecyclerView
        view.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = viewModel.adapter
            addItemDecoration(ItemDecoration(requireContext()))
        }
        return view
    }
}