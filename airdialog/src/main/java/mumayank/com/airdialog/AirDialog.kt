package mumayank.com.airdialog

import android.app.Activity
import android.support.v7.app.AlertDialog
import java.lang.ref.WeakReference


class AirDialog(
    activity: Activity,
    title: String = "",
    message: String = "",
    iconDrawableId: Int? = null,
    isCancelable: Boolean = true,
    airButton1: Button? = null,
    airButton2: Button? = null,
    airButton3: Button? = null,
    isDialogReallyWithoutAnyButtons: Boolean = false
) {

    class Button(val textOnButton: String, val onClick: () -> Unit)

    var alertDialog: AlertDialog? = null

    init {

        val activityWeakReference = WeakReference(activity)
        val alertDialogBuilder = AlertDialog.Builder(activity)

        if (title != "") {
            alertDialogBuilder.setTitle(title)
        }

        if (message != "") {
            alertDialogBuilder.setMessage(message)
        }

        if (iconDrawableId != null) {
            alertDialogBuilder.setIcon(iconDrawableId)
        }

        alertDialogBuilder.setCancelable(isCancelable)

        if (airButton1 != null) {
            alertDialogBuilder.setPositiveButton(airButton1.textOnButton) { _, _ ->
                if (activityWeakReference.get() != null) {
                    alertDialog?.dismiss()
                    airButton1.onClick.invoke()
                }
            }
        }

        if (airButton2 != null) {
            alertDialogBuilder.setNegativeButton(airButton2.textOnButton) { _, _ ->
                if (activityWeakReference.get() != null) {
                    alertDialog?.dismiss()
                    airButton2.onClick.invoke()
                }
            }
        }

        if (airButton3 != null) {
            alertDialogBuilder.setNeutralButton(airButton3.textOnButton) { _, _ ->
                if (activityWeakReference.get() != null) {
                    alertDialog?.dismiss()
                    airButton3.onClick.invoke()
                }
            }
        }

        if ( (airButton1 == null) && (airButton2 == null) && (airButton3 == null) ) {
            if (isDialogReallyWithoutAnyButtons == false) {
                val tempAirButton = AirDialog.Button("OK") {}
                alertDialogBuilder.setPositiveButton(tempAirButton.textOnButton) { _, _ ->
                    if (activityWeakReference.get() != null) {
                        alertDialog?.dismiss()
                        tempAirButton.onClick.invoke()
                    }
                }
            }
        }

        if (activity.isFinishing == false) {
            if (activityWeakReference.get() != null) {
                alertDialog = alertDialogBuilder.create()
                alertDialog?.show()
            }
        }
    }

    fun dismiss() {
        alertDialog?.dismiss()
    }
}