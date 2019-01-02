package mumayank.com.airdialoglibraryproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import mumayank.com.airdialog.AirDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            AirDialog(this, "Hello", false).show(
                "wha",
                AirDialog.Button("OK") {
                    Toast.makeText(this, "OK pressed", Toast.LENGTH_SHORT).show()
                },
                AirDialog.Button("CANCEL") {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
                }
            )
        }

        button2.setOnClickListener {
            AirDialog(this, "Some").showNoButtons("Please wait")
        }

        button3.setOnClickListener {
            val airDialog = AirDialog(this, isCancelable = false).showProgress("Some message", airButton3 = AirDialog.Button("Cancel") {

            })
        }

        button4.setOnClickListener {
            AirDialog(this, "Choose:", false, android.R.drawable.ic_notification_clear_all).showOptions(arrayOf("One", "Two", "Three"),
                airButton3 = AirDialog.Button("Cancel") {

                },
                onOptionsCallback = object: AirDialog.ShowOptionsCallback {
                override fun onOptionSelected(selectedOptionIndex: Int) {
                    Toast.makeText(this@MainActivity, selectedOptionIndex.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onCancelled() {
                    Toast.makeText(this@MainActivity, "Cancelled", Toast.LENGTH_SHORT).show()
                }

            }, defaultSelectedOptionIndex = 1)
        }
    }
}
