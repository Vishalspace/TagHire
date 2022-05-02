package com.vishal.taghire.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.vishal.taghire.databinding.ScripcardBinding
import com.vishal.taghire.model.Currency

class QuotesAdapter : RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder>(){
    private val currencies = mutableListOf<Currency>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        return QuotesViewHolder(ScripcardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.binding.currency = CurrencyViewModel(currencies[position])
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    fun setData(newCurrencies: List<Currency>) {
        currencies.clear()
        currencies.addAll(newCurrencies)
        notifyDataSetChanged()
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