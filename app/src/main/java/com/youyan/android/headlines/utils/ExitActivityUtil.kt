package com.youyan.android.headlines.utils

import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent

/**
 * Created by android on 3/22/18.
 * 退出app
 */

class ExitActivityUtil : AppCompatActivity() {

    private var exitTime: Long = 0
    /*
     * 重写onKeyDown方法
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            //2s之内按返回键就会退出
            if (System.currentTimeMillis() - exitTime > 2000) {
                ToastUtil.showShort(applicationContext, "再按一次退出程序")
                exitTime = System.currentTimeMillis()
            } else {
                finish()
                System.exit(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
