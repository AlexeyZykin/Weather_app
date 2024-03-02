package com.example.weather_app.presentation.dialog

import android.content.Context
import com.example.weather_app.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogManager {
    fun deleteAllPlaces(context: Context, listener: Listener){
        MaterialAlertDialogBuilder(context)
            .setTitle(context.getString(R.string.delete_all_places))
            .setPositiveButton(context.getString(R.string.delete)) { _, _ ->
                listener.onClick()
            }
            .setNegativeButton(context.getString(R.string.cancel_dialog), null)
            .show()
    }

    fun deletePlace(context: Context, listener: Listener) {
        MaterialAlertDialogBuilder(context)
            .setTitle(context.getString(R.string.delete_place))
            .setPositiveButton(context.getString(R.string.delete)) { _, _ ->
                listener.onClick()
            }
            .setNegativeButton(context.getString(R.string.cancel_dialog), null)
            .show()
    }

    interface Listener{
        fun onClick()
    }
}