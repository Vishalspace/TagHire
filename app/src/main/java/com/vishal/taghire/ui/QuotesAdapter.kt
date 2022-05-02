package com.vishal.taghire.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vishal.taghire.databinding.ScripcardBinding
import com.vishal.taghire.model.Currency

class QuotesAdapter : RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder>(){
    private val currencies = mutableListOf<Currency>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        return QuotesViewHolder(ScripcardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.binding.tvbaseAsset.text = currencies[position].baseAsset
        holder.binding.tvlast.text = currencies[position].lastPrice.toString()
//        holder.binding.tvbid.text = currencies[position].bidPrice.toString()
//        holder.binding.tvask.text = currencies[position].askPrice.toString()
//        holder.binding.tvvolume.text = currencies[position].volume.toString()
//        holder.binding.tvopen.text = currencies[position].openPrice.toString()
//        holder.binding.tvlow.text = currencies[position].lowPrice.toString()
//        holder.binding.tvhigh.text = currencies[position].highPrice.toString()

//        val isExpanded = position === mExpandedPosition
//        holder.details.setVisibility(if (isExpanded) View.VISIBLE else View.GONE)
//        holder.itemView.isActivated = isExpanded
//
//        if (isExpanded) previousExpandedPosition = position
//
//        holder.itemView.setOnClickListener {
//            mExpandedPosition = if (isExpanded) -1 else position
//            notifyItemChanged(previousExpandedPosition)
//            notifyItemChanged(position)
//        }
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    fun setData(newCurrencies: List<Currency>) {
        currencies.clear()
        currencies.addAll(newCurrencies)
        notifyDataSetChanged()
    }

    class QuotesViewHolder(val binding :ScripcardBinding): RecyclerView.ViewHolder(binding.root)
}