package com.youyan.android.headlines.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.youyan.android.headlines.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        QMUIStatusBarHelper.translucent(this)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        /*splash.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE or
                                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY*/
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
