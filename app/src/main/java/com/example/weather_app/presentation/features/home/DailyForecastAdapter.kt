package com.example.weather_app.presentation.features.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather_app.R
import com.example.weather_app.presentation.model.ForecastItemUi
import com.example.weather_app.presentation.utils.DateTypeConverter
import com.example.weather_app.presentation.utils.iconRes

class DailyForecastAdapter(
    private val clickListener: ClickListener
) :
    RecyclerView.Adapter<DailyForecastAdapter.ForecastViewHolder>(), Mapper<List<ForecastItemUi>> {
    private val list = mutableListOf<ForecastItemUi>()


    class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvDayOfWeek = view.findViewById<TextView>(R.id.tvDayOfWeek)
        private val icon = view.findViewById<ImageView>(R.id.icDailyForecastWeather)
        private val tvTemp = view.findViewById<TextView>(R.id.tvDailyForecastTempDay)
        private val card = view.findViewById<CardView>(R.id.cvDailyForecast)

        fun bind(forecastItem: ForecastItemUi, clickListener: ClickListener) {
            val context = tvDayOfWeek.context
            if (DateTypeConverter.convertUnixToDate(forecastItem.dt) == DateTypeConverter.currentDate())
                tvDayOfWeek.text = context.getString(R.string.today)
            else
                tvDayOfWeek.text = DateTypeConverter.convertUnixToDayOfWeek(forecastItem.dt)
            val iconRes = forecastItem.weatherType.iconRes(forecastItem.partOfDay)
            Glide.with(context).load(iconRes).centerCrop().into(icon)
            tvTemp.text = forecastItem.mainInfo.temp.toString() + "°c"
            card.setOnClickListener { clickListener.onClickDailyForecast(forecastItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_daily_forecast,
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
        fun onClickDailyForecast(forecastItem: ForecastItemUi)
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