package mumayank.com.airdialog

import android.app.Activity
import android.support.v7.app.AlertDialog
import java.lang.ref.WeakReference

class AirDialog {

    class Button(val textOnButton: String, val onClick: () -> Unit)

    companion object {

        private var alertDialog: AlertDialog? = null

        fun show(
            activity: Activity,
            title: String = "",
            message: String = "",
            iconDrawableId: Int? = null,
            isCancelable: Boolean = true,
            airButton1: Button = Button("OK") {},
            airButton2: Button? = null,
            airButton3: Button? = null
        ) {
            val activityWeakReference = WeakReference(activity)

            alertDialog = AlertDialog.Builder(activity).create()

            if (title != "") {
                alertDialog?.setTitle(title)
            }

            if (message != "") {
                alertDialog?.setMessage(message)
            }

            if (iconDrawableId != null) {
                alertDialog?.setIcon(iconDrawableId)
            }

            alertDialog?.setCancelable(isCancelable)

            alertDialog?.setButton(AlertDialog.BUTTON_POSITIVE, airButton1.textOnButton) { _, i ->
                if (activityWeakReference.get() != null) {
                    airButton1.onClick.invoke()
                    alertDialog?.dismiss()
                }
            }

            if (airButton2 != null) {
                alertDialog?.setButton(AlertDialog.BUTTON_NEGATIVE, airButton2.textOnButton) { _, i ->
                    if (activityWeakReference.get() != null) {
                        airButton2.onClick.invoke()
                        alertDialog?.dismiss()
                    }
                }
            }

            if (airButton3 != null) {
                alertDialog?.setButton(AlertDialog.BUTTON_POSITIVE, airButton3.textOnButton) { _, i ->
                    if (activityWeakReference.get() != null) {
                        airButton3.onClick.invoke()
                        alertDialog?.dismiss()
                    }
                }
            }

            if (activity.isFinishing == false) {
                alertDialog?.show()
            }
        }
    }
}