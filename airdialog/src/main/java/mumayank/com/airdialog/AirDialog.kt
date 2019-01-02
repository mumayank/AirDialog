package mumayank.com.airdialog

import android.app.Activity
import android.support.v7.app.AlertDialog
import android.widget.Button
import java.lang.ref.WeakReference

class AirDialog(
    val activity: Activity,
    val title: String = "",
    val isCancelable: Boolean = true,
    val iconDrawableId: Int? = null
) {
    class Button(val textOnButton: String, val onClick: () -> Unit)

    private var alertDialog: AlertDialog? = null

    fun dismiss() {
        alertDialog?.dismiss()
    }

    fun show(
        message: String = "",
        airButton1: Button? = null,
        airButton2: Button? = null,
        airButton3: Button? = null
    ) {
        val activityWeakReference = WeakReference<Activity>(activity)
        val alertDialogBuilder = AlertDialog.Builder(activity)

        setTitle(alertDialogBuilder)
        setMessage(alertDialogBuilder, message)
        setIcon(alertDialogBuilder)
        setCancelable(alertDialogBuilder)
        setButton1(alertDialogBuilder, activityWeakReference, airButton1)
        setButton2(alertDialogBuilder, activityWeakReference, airButton2)
        setButton3(alertDialogBuilder, activityWeakReference, airButton3)
        setAtLeastOneButtonIfNoButtonsSpecified(alertDialogBuilder, activityWeakReference, airButton1, airButton2, airButton3)










        if (activity.isFinishing == false) {
            if (activityWeakReference.get() != null) {
                alertDialog = alertDialogBuilder.create()
                alertDialog?.show()
            }
        }
    }

    private fun setTitle(alertDialogBuilder: AlertDialog.Builder) {
        if (title != "") {
            alertDialogBuilder.setTitle(title)
        }
    }

    private fun setMessage(alertDialogBuilder: AlertDialog.Builder, message: String) {
        if (message != "") {
            alertDialogBuilder.setMessage(message)
        }
    }

    private fun setIcon(alertDialogBuilder: AlertDialog.Builder) {
        if (iconDrawableId != null) {
            alertDialogBuilder.setIcon(iconDrawableId)
        }
    }

    private fun setCancelable(alertDialogBuilder: AlertDialog.Builder) {
        alertDialogBuilder.setCancelable(isCancelable)
    }

    private fun setButton1(alertDialogBuilder: AlertDialog.Builder, activityWeakReference: WeakReference<Activity>, airButton1: Button?) {
        if (airButton1 != null) {
            alertDialogBuilder.setPositiveButton(airButton1.textOnButton) { _, _ ->
                if (activityWeakReference.get() != null) {
                    alertDialog?.dismiss()
                    airButton1.onClick.invoke()
                }
            }
        }
    }

    private fun setButton2(alertDialogBuilder: AlertDialog.Builder, activityWeakReference: WeakReference<Activity>, airButton2: Button?) {
        if (airButton2 != null) {
            alertDialogBuilder.setNegativeButton(airButton2.textOnButton) { _, _ ->
                if (activityWeakReference.get() != null) {
                    alertDialog?.dismiss()
                    airButton2.onClick.invoke()
                }
            }
        }
    }

    private fun setButton3(alertDialogBuilder: AlertDialog.Builder, activityWeakReference: WeakReference<Activity>, airButton3: Button?) {
        if (airButton3 != null) {
            alertDialogBuilder.setNeutralButton(airButton3.textOnButton) { _, _ ->
                if (activityWeakReference.get() != null) {
                    alertDialog?.dismiss()
                    airButton3.onClick.invoke()
                }
            }
        }
    }

    private fun setAtLeastOneButtonIfNoButtonsSpecified(alertDialogBuilder: AlertDialog.Builder, activityWeakReference: WeakReference<Activity>, airButton1: Button?, airButton2: Button?, airButton3: Button?) {
        if ( (airButton1 == null) && (airButton2 == null) && (airButton3 == null) ) {
            val tempAirButton = AirDialog.Button("OK") {}
            alertDialogBuilder.setPositiveButton(tempAirButton.textOnButton) { _, _ ->
                if (activityWeakReference.get() != null) {
                    alertDialog?.dismiss()
                    tempAirButton.onClick.invoke()
                }
            }
        }
    }



}