package com.example.tinynetworklibdemo.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tinynetworklib.network.core.request.Request
import com.example.tinynetworklib.network.core.response.IResponse
import com.example.tinynetworklib.network.provider.ICall
import com.example.tinynetworklib.network.provider.ICallback
import com.example.tinynetworklibdemo.App
import com.example.tinynetworklibdemo.R

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var okButton: Button
    private lateinit var cancelButton: Button

    private var call: ICall? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()

        okButton.setOnClickListener {
            val text = editText.text.toString()

            progressBar.visibility = View.VISIBLE
            textView.text = ""

            call = App.networkProvider.call(Request(text), callback)
        }

        cancelButton.setOnClickListener {
            call?.apply {
                cancel()
                progressBar.visibility = View.GONE
            }
            call = null
        }
    }

    private val callback = object : ICallback {
        override fun onSuccess(response: IResponse) {
            runOnUiThread {
                textView.text = String(response.body)
                progressBar.visibility = View.GONE
            }
        }

        override fun onError(e: Exception) {
            runOnUiThread {
                textView.text = e.message
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun bindViews() {
        editText = findViewById(R.id.edit_text)
        textView = findViewById(R.id.text_view)
        progressBar = findViewById(R.id.progress_bar)
        okButton = findViewById(R.id.ok_button)
        cancelButton = findViewById(R.id.cancel_button)
    }

}
