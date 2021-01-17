package com.android.weatherapp.renderforest.ui.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.weatherapp.renderforest.clean.R
import com.android.weatherapp.renderforest.clean.databinding.ItemForcastBinding
import com.android.weatherapp.renderforest.domain.model.DailyItem
import kotlin.properties.Delegates

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var mWeatherList: List<DailyItem> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = DataBindingUtil.inflate<ItemForcastBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_forcast, parent, false
        )
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = if (mWeatherList.isNullOrEmpty()) 0 else mWeatherList.size

    inner class HomeViewHolder(private val itemForcastBinding: ItemForcastBinding) :
        RecyclerView.ViewHolder(itemForcastBinding.root) {

        fun onBind(item: DailyItem) {
            itemForcastBinding.model = item
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(mWeatherList[position])
    }
}