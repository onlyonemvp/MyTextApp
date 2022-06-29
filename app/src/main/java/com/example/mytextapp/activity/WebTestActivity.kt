package com.example.mytextapp.activity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.mytextapp.R

class WebTestActivity : AppCompatActivity() {
    private var webView: WebView? = null
    private var btnAdd :Button? =null
    private var btnSub :Button? =null
    private var btnWeb :Button? =null
    private var seekbar :SeekBar? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_test)
        webView = findViewById(R.id.web)
        btnAdd =findViewById(R.id.add)
        btnSub  = findViewById(R.id.sub)
        btnWeb  = findViewById(R.id.th)
        seekbar= findViewById(R.id.seekbar)
        init()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        var settings =webView?.settings
        settings?.javaScriptEnabled =true
        settings?.loadWithOverviewMode =true
        webView?.loadUrl("http://192.168.50.212:5501/test/pic/index.html")

        btnAdd?.setOnClickListener {
            webView?.loadUrl("javascript:add()")
        }
        btnSub?.setOnClickListener {
            webView?.loadUrl("javascript:sub()")
        }
        btnWeb?.setOnClickListener {
//            webView?.loadUrl("javascript:renderPage(10,['#ff0000', '#0000ff'],200)")
            webView?.loadUrl("javascript:progress(30,['#ff0000'])")
        }

        seekbar?.setOnSeekBarChangeListener(object :OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                webView?.loadUrl("javascript:progress(${p0?.progress},['#ff0000', '#0000ff'])")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })



    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @Composable
    fun webview(){
        AndroidView(factory = {context ->
            var webview = WebView(context)
            webView?.settings?.javaScriptEnabled = true
            webView?.settings?.javaScriptCanOpenWindowsAutomatically = true
            webView?.settings?.domStorageEnabled = true
            webView?.settings?.loadsImagesAutomatically = true
            webView?.settings?.mediaPlaybackRequiresUserGesture = false
            webView?.webViewClient = WebViewClient()
            webview.loadUrl("http://192.168.50.212:5501/test/pic/index.html")

            webview
        })

        AndroidView(factory = {context ->
            var seekBarView = SeekBar(context)
            seekBarView.maxWidth = 200
            seekBarView
        })
    }

}