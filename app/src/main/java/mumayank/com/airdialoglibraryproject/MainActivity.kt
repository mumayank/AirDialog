package mumayank.com.airdialoglibraryproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mumayank.com.airdialog.AirDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showDialog()
    }

    private fun showDialog() {
        val a = AirDialog(
            this,
            "T",
            "M",
            R.drawable.notification_icon_background,
            false,
            AirDialog.Button("OK") {
                showDialog()
            },
            AirDialog.Button("Cancel") {
                Toast.makeText(this, "This is a toast", Toast.LENGTH_SHORT).show()
            },
            AirDialog.Button("Maybe") {
                Toast.makeText(this, "This is a maybe button", Toast.LENGTH_SHORT).show()
            }
        )
    }
}
