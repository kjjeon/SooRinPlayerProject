package com.alticast.soorinplayerproject.view.consumption

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.alticast.soorinplayerproject.R
import com.alticast.soorinplayerproject.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_consumption.*

class ConsumptionActivity : BaseActivity() {

    private lateinit var viewModel: ConsumptionViewModel
    private val consumptionRecyclerAdapter: ConsumptionRecyclerAdapter  by lazy { ConsumptionRecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumption)

        viewModel = ViewModelProviders.of(this).get(ConsumptionViewModel::class.java)

        consumptionRecyclerAdapter.setItems(mutableListOf("play", "pause","resume" , "stop", "release"))
        consumptionRecyclerAdapter.itemSelected()
            .subscribe {   viewModel.doKeyHandle(it)  }
            .drop()

        consumption_recycler_view.apply {
            adapter = consumptionRecyclerAdapter
            layoutManager = LinearLayoutManager(this@ConsumptionActivity)
            setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stop()
    }

    companion object {
        private const val TAG = "ConsumptionActivity"
    }
}
