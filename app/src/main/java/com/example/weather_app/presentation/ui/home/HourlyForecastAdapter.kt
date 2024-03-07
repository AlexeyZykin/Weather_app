package com.example.weather_app.presentation.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.weather_app.R
import com.example.weather_app.presentation.model.weather.ForecastItemUi
import com.example.weather_app.presentation.utils.DateTypeConverter
import com.example.weather_app.presentation.utils.Mapper
import com.example.weather_app.presentation.utils.iconRes

class HourlyForecastAdapter(
    private val clickListener: ClickListener) :
    RecyclerView.Adapter<HourlyForecastAdapter.ForecastViewHolder>(), Mapper<List<ForecastItemUi>> {
    private val list = mutableListOf<ForecastItemUi>()


    class ForecastViewHolder(view: View) : ViewHolder(view) {
        private val tvTime = view.findViewById<TextView>(R.id.tvHourlyForecastTime)
        private val icon = view.findViewById<ImageView>(R.id.icHourlyForecastWeather)
        private val tvTemp = view.findViewById<TextView>(R.id.tvHourlyForecastTemp)
        private val card = view.findViewById<CardView>(R.id.cvHourlyForecast)

        fun bind(forecastItem: ForecastItemUi, clickListener: ClickListener) {
            tvTime.text = DateTypeConverter
                .convertUnixToDateString(forecastItem.dt, DateTypeConverter.HOUR_DATE_FORMAT)
            val iconRes = forecastItem.weatherType.iconRes(forecastItem.partOfDay)
            Glide.with(icon.context).load(iconRes).centerCrop().into(icon)
            tvTemp.text = forecastItem.mainInfo.temp.toString() + "°c"
            card.setOnClickListener { clickListener.onClickHourlyForecast(forecastItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_hourly_forecast,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }


    override fun map(source: List<ForecastItemUi>) {
        val diff = DiffUtil(list, source)
        val result = androidx.recyclerview.widget.DiffUtil.calculateDiff(diff)
        list.clear()
        list.addAll(source)
        result.dispatchUpdatesTo(this)
    }

    interface ClickListener {
        fun onClickHourlyForecast(forecastItem: ForecastItemUi)
    }

    class DiffUtil(
        private val oldList: List<ForecastItemUi>,
        private val newList: List<ForecastItemUi>
    ) : androidx.recyclerview.widget.DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size


        override fun getNewListSize() = newList.size


        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].dt == newList[newItemPosition].dt
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}