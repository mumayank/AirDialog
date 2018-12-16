package mumayank.com.airdialog

import android.app.Activity
import android.support.v7.app.AlertDialog
import java.lang.ref.WeakReference
import android.os.Build
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton


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
            airButton2: Button? = null
        ) {
            val activityWeakReference = WeakReference(activity)

            val alert = activity.alert{}

            if (title != "") {
                alert.title = title
            }

            if (message != "") {
                alert.message = message
            }

            if (iconDrawableId != null) {
                alert.iconResource = iconDrawableId
            }

            alert.isCancelable = isCancelable

            alert.positiveButton(airButton1.textOnButton) {
                if (activityWeakReference.get() != null) {
                    airButton1.onClick.invoke()
                }
            }

            if (airButton2 != null) {
                alert.negativeButton(airButton2.textOnButton) {
                    if (activityWeakReference.get() != null) {
                        airButton2.onClick.invoke()
                    }
                }
            }

            if (activity.isFinishing == false) {
                alert.show()
            }
        }
    }
}