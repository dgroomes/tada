package us.mn.dgtc.tada.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import us.mn.dgtc.tada.R

class HomeActivity : Activity() {

    val artOptions = ArtOptions("Random")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val paintOptions = arrayOf("Random", "At touch point")
        val spinner = findViewById(R.id.paint_options) as Spinner
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, paintOptions)
        spinner.adapter = adapter

        val listener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = parent?.getItemAtPosition(position) as String
                artOptions.paint = item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do nothing
            }
        }
        spinner.setOnItemSelectedListener(listener)
    }

    @Suppress("UNUSED_PARAMETER")
    fun go(view: View) {
        val intent = Intent(this, CountDownTimerArtActivity::class.java)
        startActivity(intent)
    }
}
