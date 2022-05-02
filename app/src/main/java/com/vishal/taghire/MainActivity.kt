package com.vishal.taghire

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.vishal.taghire.api.WazirApi
import com.vishal.taghire.databinding.ActivityMainBinding
import com.vishal.taghire.ui.QuotesAdapter
import com.vishal.taghire.utils.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var api: WazirApi
    private lateinit var binding: ActivityMainBinding
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private val compositeDisposable = CompositeDisposable()
    private val adapter = QuotesAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).component.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initUi(binding)
        callApi()
    }

    private fun initUi(binding: ActivityMainBinding) {
        swipeRefresh = findViewById(R.id.swipeRefreshLayout)
        binding.currencyrecycler.layoutManager = LinearLayoutManager(this)
        binding.currencyrecycler.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        refresh()
    }

    private fun refresh() {
        swipeRefresh.setOnRefreshListener {
            callApi()
        }
    }

    private fun callApi() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
        api.getQuotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                adapter.setData(result)
            }.addTo(compositeDisposable)
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

}


