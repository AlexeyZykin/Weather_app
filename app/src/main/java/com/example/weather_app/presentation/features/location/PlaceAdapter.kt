package com.example.weather_app.presentation.features.location

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_app.R
import com.example.weather_app.presentation.model.place.PlaceUi
import com.example.weather_app.presentation.utils.Mapper

class PlaceAdapter(private val clickListener: ClickListener) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>(), Mapper<List<PlaceUi>> {

    private val list = mutableListOf<PlaceUi>()


    class PlaceViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val tvCity = view.findViewById<TextView>(R.id.tvPlaceCityName)
        private val cvPlace = view.findViewById<CardView>(R.id.cvPlaceItem)
        fun bind(place: PlaceUi, clickListener: ClickListener) {
            tvCity.text = place.city
            cvPlace.setOnLongClickListener {
                clickListener.onLongClick(place)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        return PlaceViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_place,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }

    override fun map(source: List<PlaceUi>) {
        val diff = DiffUtil(list, source)
        val result = androidx.recyclerview.widget.DiffUtil.calculateDiff(diff)
        list.clear()
        list.addAll(source)
        result.dispatchUpdatesTo(this)
    }

    interface ClickListener {
        fun onClick(place: PlaceUi)
        fun onLongClick(place: PlaceUi)
    }

    class DiffUtil(
        private val oldList: List<PlaceUi>,
        private val newList: List<PlaceUi>
    ) : androidx.recyclerview.widget.DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size


        override fun getNewListSize() = newList.size

        //todo("change field")
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].placeIdStr == newList[newItemPosition].placeIdStr
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}