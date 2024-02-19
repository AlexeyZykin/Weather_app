package com.example.weather_app.presentation.features.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weather_app.R
import com.example.weather_app.data.model.ForecastItemEntity
import com.example.weather_app.data.model.ForecastWeatherEntity
import com.example.weather_app.presentation.utils.DateTypeConverter
import com.squareup.picasso.Picasso

class DailyForecastAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<DailyForecastAdapter.ForecastViewHolder>(), Mapper<ForecastWeatherEntity> {

    private val list = mutableListOf<ForecastItemEntity>()

    class ForecastViewHolder(view: View) : ViewHolder(view) {
        private val tvDate = view.findViewById<TextView>(R.id.tvDate)
        private val icon = view.findViewById<ImageView>(R.id.icWeather)
        private val tvTemp = view.findViewById<TextView>(R.id.tvTempDay)

        fun bind(forecastItem: ForecastItemEntity, clickListener: ClickListener) {
            tvDate.text = DateTypeConverter.convertUnixToDayOfWeek(forecastItem.dt)
            Picasso.get()
                .load("https://openweathermap.org/img/wn/${forecastItem.weather.icon}@2x.png")
                .into(icon)
            tvTemp.text = forecastItem.mainInfo.temp.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_forecast,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }


    override fun map(source: ForecastWeatherEntity) {
        val diff = DiffUtil(list, source.forecastList)
        val result = androidx.recyclerview.widget.DiffUtil.calculateDiff(diff)
        list.clear()
        list.addAll(source.forecastList)
        result.dispatchUpdatesTo(this)
    }

    interface ClickListener {
        fun onClick(forecastItem: ForecastItemEntity)
    }

    class DiffUtil(
        private val oldList: List<ForecastItemEntity>,
        private val newList: List<ForecastItemEntity>
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