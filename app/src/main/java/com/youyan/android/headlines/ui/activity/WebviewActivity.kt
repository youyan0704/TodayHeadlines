package com.youyan.android.headlines.ui.activity

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.webkit.*
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.youyan.android.headlines.R
import com.youyan.android.headlines.utils.LoggerUtil
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

//        QMUIStatusBarHelper.setStatusBarLightMode(this)

        webContent.settings.javaScriptEnabled
        webContent.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webContent.settings.setAppCacheEnabled(true)
        webContent.settings.allowFileAccess = true
        webContent.settings.setAppCachePath(applicationContext.cacheDir.absolutePath)
        webContent.settings.loadsImagesAutomatically = true
        webContent.settings.setSupportZoom(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webContent.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }


//        window.requestFeature(Window.FEATURE_PROGRESS)
        webContent.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                progressBar.progress = newProgress * 1000
            }
        }

        webContent.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.visibility = View.VISIBLE

            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }

            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                super.onReceivedSslError(view, handler, error)
                LoggerUtil.i("sslError",error.toString())
                handler!!.proceed()

            }

        }

        LoggerUtil.i("url",intent.getStringExtra("url"))
        webContent.loadUrl(intent.getStringExtra("url"))

    }
}
