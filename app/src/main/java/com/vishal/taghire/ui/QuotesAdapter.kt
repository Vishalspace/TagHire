package com.vishal.taghire.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vishal.taghire.databinding.ScripcardBinding
import com.vishal.taghire.model.Currency

class QuotesAdapter : ListAdapter<Currency, QuotesAdapter.QuotesViewHolder>(QuotesDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        return QuotesViewHolder(ScripcardBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.binding.currency = CurrencyViewModel(getItem(position))
    }

    class QuotesViewHolder(val binding: ScripcardBinding) : RecyclerView.ViewHolder(binding.root)
}

class CurrencyViewModel(currency: Currency) {
    val expanded = ObservableBoolean(false)
    val baseAsset = ObservableField(currency.baseAsset)
    val lastPrice = ObservableField(currency.lastPrice)
    val openPrice = ObservableField(currency.openPrice)
    val highPrice = ObservableField(currency.highPrice)
    val lowPrice = ObservableField(currency.lowPrice)

    fun onClick(_view: View?) {
        expanded.set(!expanded.get())
    }
}

class QuotesDiffCallBack : DiffUtil.ItemCallback<Currency>() {
    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem.baseAsset == newItem.baseAsset
    }

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem.baseAsset == newItem.baseAsset && oldItem.lastPrice == newItem.lastPrice
    }
}