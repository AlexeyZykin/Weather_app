package com.example.weather_app.presentation.features.location

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_app.R
import com.example.weather_app.presentation.model.place.AutocompletePlaceUi
import com.example.weather_app.presentation.model.place.PlaceUi
import com.example.weather_app.presentation.utils.Mapper


class AutocompletePlacesAdapter :
    RecyclerView.Adapter<AutocompletePlacesAdapter.AutocompletePlacesViewHolder>(),
    Mapper<List<PlaceUi>> {
        private val list = mutableListOf<PlaceUi>()


    class AutocompletePlacesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cityName = view.findViewById<TextView>(R.id.tvAutoCompleteCity)

        fun bind(place: PlaceUi) {
            cityName.text = place.city
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AutocompletePlacesViewHolder {
        return AutocompletePlacesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_autocomplete_place,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: AutocompletePlacesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun map(source: List<PlaceUi>) {
        val diff = DiffUtil(list, source)
        val result = androidx.recyclerview.widget.DiffUtil.calculateDiff(diff)
        list.clear()
        list.addAll(source)
        result.dispatchUpdatesTo(this)
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