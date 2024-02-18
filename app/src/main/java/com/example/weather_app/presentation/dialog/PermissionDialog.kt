package com.example.weather_app.presentation.dialog

import android.content.Context
import com.example.weather_app.R
import com.google.android.material.animation.AnimatableView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object PermissionDialog {
    fun locationSettingsDialog(context: Context, listener: Listener) {
        MaterialAlertDialogBuilder(context)
            .setMessage(context.getString(R.string.geo_permission_dialog_message))
            .setPositiveButton(context.getString(R.string.move_to_settings)) { _, _ ->
                listener.onCLick()
            }
            .setNegativeButton(context.getString(R.string.geo_permission_dialog_cancel), null)
            .show()
    }

    fun foregroundLocationDialog(context: Context, listener: Listener) {
        MaterialAlertDialogBuilder(context)
            .setTitle(R.string.permission_required)
            .setMessage(R.string.permission_foreground_location_message)
            .setPositiveButton(R.string.permission_foreground_location_positive) { _, _ ->
                listener.onCLick()
            }
            .setOnDismissListener{

            }
            .show()
    }

    interface Listener {
        fun onCLick()
    }
}