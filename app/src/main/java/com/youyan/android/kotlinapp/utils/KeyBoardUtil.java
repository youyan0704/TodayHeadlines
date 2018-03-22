package com.youyan.android.kotlinapp.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by android on 3/21/18.
 * 屏幕类
 */

public class KeyBoardUtil {

    private KeyBoardUtil(){
        throw new UnsupportedOperationException("KeyBoardUtil cannot be instantiated");
    }

    /**
     * 打开软键盘
     */
    public static void openKeybord(EditText mEditText, Context mContext){
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
    /**
     * 关闭软键盘
     */
    public static void closeKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
}
