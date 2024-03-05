package com.example.weather_app.presentation.features.location

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_app.core.Config
import com.example.weather_app.databinding.ItemPlaceBinding
import com.example.weather_app.presentation.model.place.PlaceUi
import com.example.weather_app.presentation.utils.Mapper

class PlaceAdapter(
    private val clickListener: ClickListener,
    private val sharedPref: SharedPreferences
) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>(), Mapper<List<PlaceUi>> {

    private val list = mutableListOf<PlaceUi>()

    class PlaceViewHolder(private val binding: ItemPlaceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(place: PlaceUi, clickListener: ClickListener, sharedPref: SharedPreferences) {
            val currentPlace = sharedPref.getString(Config.SHARED_PREFS_CURRENT_PLACE, "")
            binding.tvPlaceCityName.text = place.city
            binding.icIsCurrent.visibility = if (place.city == currentPlace) View.VISIBLE else View.GONE
            binding.cvPlaceItem.setOnClickListener { clickListener.onClick(place) }
            binding.cvPlaceItem.setOnLongClickListener {
                clickListener.onLongClick(place)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(list[position], clickListener, sharedPref)
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