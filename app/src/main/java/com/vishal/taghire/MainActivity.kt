package com.vishal.taghire

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.vishal.taghire.api.WazirApi
import com.vishal.taghire.databinding.ActivityMainBinding
import com.vishal.taghire.ui.QuotesAdapter
import com.vishal.taghire.utils.addTo
import com.vishal.taghire.utils.convertLongToTime
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var api: WazirApi
    private lateinit var binding: ActivityMainBinding
    private val compositeDisposable = CompositeDisposable()
    private val adapter = QuotesAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).component.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initUi(binding)
    }

    private fun initUi(binding: ActivityMainBinding) {
        binding.tvat.text = "Fetching data..."
        binding.currencyrecycler.layoutManager = LinearLayoutManager(this)
        binding.currencyrecycler.adapter = adapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            callApi()
        }
    }

    override fun onResume() {
        super.onResume()
        startAutoRefresh()
    }

    private fun startAutoRefresh() {
        Observable.interval(0, 4, TimeUnit.SECONDS, Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnEach {
                if (!binding.swipeRefreshLayout.isRefreshing) callApi()
            }
            .subscribe()
            .addTo(compositeDisposable)
    }

    private fun callApi() {
        api.getQuotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate { binding.swipeRefreshLayout.isRefreshing = false }
            .subscribe { result ->
                adapter.submitList(result)
                binding.tvat.text = convertLongToTime(result.first().at)
            }.addTo(compositeDisposable)
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

}


