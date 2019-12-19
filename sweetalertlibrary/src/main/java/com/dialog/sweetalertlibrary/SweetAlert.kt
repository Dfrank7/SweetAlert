package com.dialog.sweetalertlibrary

import android.app.Activity
import android.graphics.drawable.AnimatedVectorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import kotlinx.android.synthetic.main.alert_activity.view.*

class SweetAlert {

    internal lateinit var avd : AnimatedVectorDrawableCompat
    internal lateinit var avd2 : AnimatedVectorDrawable

    fun showSuccessAlert(activity: Activity, title : String, message : String, clickListener: OnClickListener ){
        var viewGroup = activity.findViewById<ViewGroup>(android.R.id.content)

        var dialogView = LayoutInflater.from(activity).inflate(R.layout.alert_activity, viewGroup, false)
        var drawable = dialogView.iconview.drawable

        var builder = AlertDialog.Builder(activity, R.style.CustomAlertDialog)
        builder.setView(dialogView)
        builder.setCancelable(false)
        var dialog = builder.create()
        dialogView.title.text = title
        dialogView.message.text =message
        if (drawable is AnimatedVectorDrawableCompat) {
            avd = drawable
            avd.start()
        }else if (drawable is AnimatedVectorDrawable){
            avd2 = drawable
            avd2.start()
        }
        var buttonOk = dialogView.findViewById<Button>(R.id.buttonOk)
        buttonOk.setOnClickListener{
            clickListener.setOkayButton(it)
            it.buttonOk.text = ""
            dialog.dismiss()
        }
        dialogView.buttonCancel.setOnClickListener {
            clickListener.setCancelButton(it)
            dialog.dismiss()
        }
        dialog.show()

    }

    fun showFailureAlert(activity: Activity, title: String, message: String, clickListener: OnClickListener) {
        var viewGroup = activity.findViewById<ViewGroup>(android.R.id.content)

        var dialogView =
            LayoutInflater.from(activity).inflate(R.layout.alert_activity, viewGroup, false)

        var builder = AlertDialog.Builder(activity, R.style.CustomAlertDialog)
        builder.setView(dialogView)
        builder.setCancelable(false)
        var dialog = builder.create()
        dialogView.message.text = message
        dialogView.title.text = title
        dialogView.iconview.setImageResource(R.drawable.ic_cancel_black_24dp)
        dialogView.layoutColor.background = activity.getDrawable(R.color.colorRed)
        var buttonOk = dialogView.findViewById<Button>(R.id.buttonOk)
        buttonOk.background = activity.getDrawable(R.drawable.button_background_failed)
        buttonOk.setOnClickListener {
            dialog.dismiss()
        }
        dialogView.buttonCancel.background = activity.getDrawable(R.drawable.button_background_failed)
        dialogView.buttonCancel.setOnClickListener {
            clickListener.setCancelButton(it)
            dialog.dismiss()
        }
        dialog.show()

    }
}