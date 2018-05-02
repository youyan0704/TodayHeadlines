package com.youyan.android.headlines.ui.activity

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import com.youyan.android.headlines.R
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.include_news_detail_tool_bar.*
import kotlinx.android.synthetic.main.include_news_detail_top.*
import org.jetbrains.anko.toast


class WebViewActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var loadingDialog : QMUITipDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        initView()
        initWebView()

    }

    private fun initView() {
        loadingDialog = QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("努力加载中...")
                .create()

        iv_back.setOnClickListener(this)
        write_comment.setOnClickListener(this)
        comment_count.setOnClickListener(this)
        love.setOnClickListener(this)
        share.setOnClickListener(this)
    }

    private fun initWebView() {
        webContent.settings.javaScriptEnabled = true
        webContent.settings.javaScriptCanOpenWindowsAutomatically = true
        webContent.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webContent.settings.setAppCacheEnabled(true)
        webContent.settings.allowFileAccess = true
        webContent.settings.setAppCachePath(applicationContext.cacheDir.absolutePath)
        webContent.settings.loadsImagesAutomatically = true
        webContent.settings.setSupportZoom(false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webContent.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        webContent.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
            }
        }

        webContent.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                news_detail_tool_bar.visibility = View.GONE
                loadingDialog.show()
                super.onPageStarted(view, url, favicon)

            }

            override fun onPageFinished(view: WebView?, url: String?) {
                news_detail_tool_bar.visibility = View.VISIBLE
                loadingDialog.dismiss()
                super.onPageFinished(view, url)
            }

        }

        webContent.loadUrl(intent.getStringExtra("url"))    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.iv_back -> {
                finish()
            }
            R.id.write_comment -> {
                toast("写评论")
            }
            R.id.comment_count -> {
                toast("评论数")
            }
            R.id.love -> {
                toast("收藏")
            }
            R.id.share -> {
                toast("分享")
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK && webContent.canGoBack()){
            webContent.goBack()
            return false
        }

        return super.onKeyDown(keyCode, event)
    }
}
