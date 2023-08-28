package com.dev.android.dbitsdk

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.webkit.CookieSyncManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dev.android.dbitsdk.databinding.ActivityServerErrorBinding

class StatusErrorActivity : AppCompatActivity() {
    lateinit var binding: ActivityServerErrorBinding
    lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServerErrorBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        webView = binding.webview
        val webSettings: WebSettings = webView.settings
        webSettings.setJavaScriptEnabled(true)
        webSettings.builtInZoomControls = false
        webSettings.domStorageEnabled = true
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT

        webView.loadUrl(decodeBase64(Util.backupSite))

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                CookieSyncManager.getInstance().startSync()
            }

            override fun onPageFinished(view: WebView, url: String) {
                CookieSyncManager.getInstance().stopSync()
                super.onPageFinished(view, url)

            }

        }
    }
    private fun decodeBase64(input: String): String = String(Base64.decode(input, Base64.DEFAULT), Charsets.UTF_8)
}


