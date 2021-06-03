package com.example.transconnect.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.transconnect.DataAdapter
import com.example.transconnect.DataCallback
import com.example.transconnect.R
import com.example.transconnect.data.Bus
import com.example.transconnect.data.BusViewModel
import com.example.transconnect.data.Helper.TYPE_BUS
import com.example.transconnect.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_bus.*

class BusFragment : Fragment(),
    DataCallback{

    private lateinit var viewModel: BusViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bus, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProvider(
                    it,
                    ViewModelProvider.NewInstanceFactory()
            )[BusViewModel::class.java]
        }

        val listMovie = viewModel.getBus()
        setupRecyclerView(listMovie)
    }

    private fun setupRecyclerView(data: List<Bus>) {
        rv_bus.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = DataAdapter(this@BusFragment)
        }.also {
            it.adapter.let { adapter ->
                when (adapter) {
                    is DataAdapter -> {
                        adapter.setData(data)
                    }
                }
            }
        }
    }


    override fun onItemClicked(data: Bus) {
        startActivity(
                Intent(
                        context,
                        DetailActivity::class.java
                )
                        .putExtra(DetailActivity.EXTRA_DATA, data.bKoridor)
                        .putExtra(DetailActivity.EXTRA_TYPE, TYPE_BUS)
        )
    }
}