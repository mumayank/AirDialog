package mumayank.com.airdialog

import android.app.Activity
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import java.lang.ref.WeakReference
import android.os.Build


class AirDialog {

    class Button(val textOnButton: String, val onClick: () -> Unit)

    companion object {

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
            val alertDialog = AlertDialog.Builder(activity).create()

            if (title != "") {
                alertDialog.setTitle(title)
            }

            if (message != "") {
                alertDialog.setMessage(message)
            }

            if (iconDrawableId != null) {
                alertDialog.setIcon(iconDrawableId)
            }

            alertDialog.setCancelable(isCancelable)

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, airButton1.textOnButton) { dialog, which ->
                if (activityWeakReference.get() != null) {
                    airButton1.onClick.invoke()
                }
            }

            if (airButton2 != null) {
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, airButton2.textOnButton) { dialog, which ->
                    if (activityWeakReference.get() != null) {
                        airButton2.onClick.invoke()
                    }
                }
            }

            if (airButton3 != null) {
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, airButton3.textOnButton) { dialog, which ->
                    if (activityWeakReference.get() != null) {
                        airButton3.onClick.invoke()
                    }
                }
            }

            if (activity.isFinishing == false) {
                alertDialog.show()
            }
        }
    }
}