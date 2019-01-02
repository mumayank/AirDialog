package mumayank.com.airdialog

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.support.v7.app.AlertDialog
import java.lang.ref.WeakReference

class AirDialog(
    val activity: Activity,
    val title: String = "",
    val isCancelable: Boolean = true,
    val iconDrawableId: Int? = null
) {
    class Button(val textOnButton: String, val onClick: () -> Unit)

    private var dialog: Dialog? = null

    fun dismiss() {
        dialog?.dismiss()
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
        setMessage(alertDialogBuilder, message = message)
        setIcon(alertDialogBuilder)
        setCancelable(alertDialogBuilder)
        setButton1(alertDialogBuilder, activityWeakReference = activityWeakReference, airButton1 = airButton1)
        setButton2(alertDialogBuilder, activityWeakReference = activityWeakReference, airButton2 = airButton2)
        setButton3(alertDialogBuilder, activityWeakReference = activityWeakReference, airButton3 = airButton3)
        setAtLeastOneButtonIfNoButtonsSpecified(alertDialogBuilder, activityWeakReference, airButton1, airButton2, airButton3)
        showDialog(alertDialogBuilder, activityWeakReference = activityWeakReference)
    }

    fun showNoButtons(
        message: String = ""
    ) {
        val activityWeakReference = WeakReference<Activity>(activity)
        val alertDialogBuilder = AlertDialog.Builder(activity)
        setTitle(alertDialogBuilder)
        setMessage(alertDialogBuilder, message = message)
        setIcon(alertDialogBuilder)
        setCancelable(alertDialogBuilder)
        showDialog(alertDialogBuilder, activityWeakReference = activityWeakReference)
    }

    interface ShowOptionsCallback {
        fun onOptionSelected(selectedOptionIndex: Int)
        fun onCancelled()
    }

    fun showOptions(
        charSequenceArray: Array<CharSequence>,
        airButton1: Button? = null,
        airButton2: Button? = null,
        airButton3: Button? = null,
        onOptionsCallback: ShowOptionsCallback,
        defaultSelectedOptionIndex: Int = 0
    ) {
        val activityWeakReference = WeakReference<Activity>(activity)
        val alertDialogBuilder = AlertDialog.Builder(activity)
        setTitle(alertDialogBuilder)
        alertDialogBuilder.setSingleChoiceItems(charSequenceArray, defaultSelectedOptionIndex) { d, selectedOptionIndex ->
            onOptionsCallback.onOptionSelected(selectedOptionIndex)
            d.dismiss()
        }
        alertDialogBuilder.setOnCancelListener {
            onOptionsCallback.onCancelled()
        }
        setIcon(alertDialogBuilder)
        setCancelable(alertDialogBuilder)
        setButton1(alertDialogBuilder, activityWeakReference = activityWeakReference, airButton1 = airButton1)
        setButton2(alertDialogBuilder, activityWeakReference = activityWeakReference, airButton2 = airButton2)
        setButton3(alertDialogBuilder, activityWeakReference = activityWeakReference, airButton3 = airButton3)
        showDialog(alertDialogBuilder, activityWeakReference = activityWeakReference)
    }

    fun showProgress(
        message: String = "",
        airButton1: Button? = null,
        airButton2: Button? = null,
        airButton3: Button? = null
    ) {
        val activityWeakReference = WeakReference<Activity>(activity)
        val progressDialog = ProgressDialog(activity)
        setTitle(progressDialog = progressDialog)
        setMessage(progressDialog = progressDialog, message = message)
        setIcon(progressDialog = progressDialog)
        setCancelable(progressDialog = progressDialog)
        setButton1(progressDialog = progressDialog, activityWeakReference = activityWeakReference, airButton1 = airButton1)
        setButton2(progressDialog = progressDialog, activityWeakReference = activityWeakReference, airButton2 = airButton2)
        setButton3(progressDialog = progressDialog, activityWeakReference = activityWeakReference, airButton3 = airButton3)
        showDialog(progressDialog = progressDialog, activityWeakReference = activityWeakReference)
    }

    private fun showDialog(alertDialogBuilder: AlertDialog.Builder? = null, progressDialog: ProgressDialog? = null, activityWeakReference: WeakReference<Activity>) {
        if (activity.isFinishing == false) {
            if (activityWeakReference.get() != null) {
                if (alertDialogBuilder != null) {
                    dialog = alertDialogBuilder.create()
                    dialog?.show()
                } else if (progressDialog != null) {
                    dialog = progressDialog
                }
                dialog?.show()
            }
        }
    }

    private fun setTitle(alertDialogBuilder: AlertDialog.Builder? = null, progressDialog: ProgressDialog? = null) {
        if (title != "") {
            alertDialogBuilder?.setTitle(title)
            progressDialog?.setTitle(title)
        }
    }

    private fun setMessage(alertDialogBuilder: AlertDialog.Builder? = null, progressDialog: ProgressDialog? = null, message: String) {
        if (message != "") {
            alertDialogBuilder?.setMessage(message)
            progressDialog?.setMessage(message)
        }
    }

    private fun setIcon(alertDialogBuilder: AlertDialog.Builder? = null, progressDialog: ProgressDialog? = null) {
        if (iconDrawableId != null) {
            alertDialogBuilder?.setIcon(iconDrawableId)
            progressDialog?.setIcon(iconDrawableId)
        }
    }

    private fun setCancelable(alertDialogBuilder: AlertDialog.Builder? = null, progressDialog: ProgressDialog? = null) {
        alertDialogBuilder?.setCancelable(isCancelable)
        progressDialog?.setCancelable(isCancelable)
    }

    private fun setButton1(alertDialogBuilder: AlertDialog.Builder? = null, progressDialog: ProgressDialog? = null, activityWeakReference: WeakReference<Activity>, airButton1: Button?) {
        if (airButton1 != null) {
            alertDialogBuilder?.setPositiveButton(airButton1.textOnButton) { _, _ ->
                if (activityWeakReference.get() != null) {
                    dialog?.dismiss()
                    airButton1.onClick.invoke()
                }
            }
            progressDialog?.setButton(Dialog.BUTTON_POSITIVE, airButton1.textOnButton) { _, _ ->
                if (activityWeakReference.get() != null) {
                    dialog?.dismiss()
                    airButton1.onClick.invoke()
                }
            }
        }
    }

    private fun setButton2(alertDialogBuilder: AlertDialog.Builder? = null, progressDialog: ProgressDialog? = null, activityWeakReference: WeakReference<Activity>, airButton2: Button?) {
        if (airButton2 != null) {
            alertDialogBuilder?.setNegativeButton(airButton2.textOnButton) { _, _ ->
                if (activityWeakReference.get() != null) {
                    dialog?.dismiss()
                    airButton2.onClick.invoke()
                }
            }
            progressDialog?.setButton(Dialog.BUTTON_NEGATIVE, airButton2.textOnButton) { _, _ ->
                if (activityWeakReference.get() != null) {
                    dialog?.dismiss()
                    airButton2.onClick.invoke()
                }
            }
        }
    }

    private fun setButton3(alertDialogBuilder: AlertDialog.Builder? = null, progressDialog: ProgressDialog? = null, activityWeakReference: WeakReference<Activity>, airButton3: Button?) {
        if (airButton3 != null) {
            alertDialogBuilder?.setNeutralButton(airButton3.textOnButton) { _, _ ->
                if (activityWeakReference.get() != null) {
                    dialog?.dismiss()
                    airButton3.onClick.invoke()
                }
            }
            progressDialog?.setButton(Dialog.BUTTON_NEUTRAL, airButton3.textOnButton) { _, _ ->
                if (activityWeakReference.get() != null) {
                    dialog?.dismiss()
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
                    dialog?.dismiss()
                    tempAirButton.onClick.invoke()
                }
            }
        }
    }



}