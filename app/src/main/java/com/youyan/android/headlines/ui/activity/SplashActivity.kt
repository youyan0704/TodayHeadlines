package com.youyan.android.headlines.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.youyan.android.headlines.R
import io.reactivex.Observable
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        QMUIStatusBarHelper.translucent(this)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onStart() {
        super.onStart()
        Observable.timer(1500,TimeUnit.MILLISECONDS)
                .doOnNext{
                    startActivity<MainActivity>()
                    finish()
                }
                .subscribe()

    }

}
