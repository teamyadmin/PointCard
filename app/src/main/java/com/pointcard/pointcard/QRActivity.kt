package com.pointcard.pointcard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import android.widget.Button


class QRActivity : AppCompatActivity() {

    private var qrReaderView: DecoratedBarcodeView? = null
    private var startButton: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)

        qrReaderView = findViewById(R.id.decoratedBarcodeView)
        startCapture()

    }

    private fun startCapture() {
        startButton?.isEnabled = false

        //工事中
    }
}
