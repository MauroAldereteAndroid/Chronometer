package com.maurodev.chronometer.ui.principal

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class AlertDialogConfirm(val context: Context) {

    fun show() {
        val builder = AlertDialog.Builder(context)
        //set title for alert dialog
        builder.setTitle("CONFIRM")
        //set message for alert dialog
        builder.setMessage("Finalizar cronometro")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            Toast.makeText(context, "clicked yes", Toast.LENGTH_LONG).show()
        }
        //performing cancel action
        builder.setNeutralButton("Cancel") { dialogInterface, which ->
            Toast.makeText(context, "clicked cancel\n operation cancel", Toast.LENGTH_LONG).show()
        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            Toast.makeText(context, "clicked No", Toast.LENGTH_LONG).show()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}