package com.youyan.android.kotlinapp.utils

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView

import java.lang.reflect.Field

/**
 * Created by android on 10/19/17.
 */

object BottomNavigationViewHelper {

    @SuppressLint("RestrictedApi")
    fun disableShiftMode(navigationView: BottomNavigationView) {

        val menuView = navigationView.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false

            for (i in 0 until menuView.childCount) {
                val itemView = menuView.getChildAt(i) as BottomNavigationItemView
                itemView.setShiftingMode(false)
                itemView.setChecked(itemView.itemData.isChecked)
            }

        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }
}
