package com.youyan.android.headlines.ui.customView.loadingView

import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.animation.ObjectAnimator
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.animation.AccelerateInterpolator
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.youyan.android.headlines.R


class LoadingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    private var mShapeView: ShapeView? = null           // 上面的形状
    private var mShadowView: View? = null               // 中间的阴影
    private var mTranslationDistance = 0
    // 动画执行的时间
    private val ANIMATOR_DURATION: Long = 500
    // 是否停止动画
    private var mIsStopAnimator = false

    init {
        mTranslationDistance = dip2px(80)
        initLayout()
    }

    private fun dip2px(dip: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip.toFloat(), resources.displayMetrics).toInt()
    }

    /**
     * 初始化加载布局
     */
    private fun initLayout() {

        val view = LayoutInflater.from(context).inflate(R.layout.layout_loading, null)
//        View.inflate(context, R.layout.layout_loading, this)

        mShapeView = view.findViewById(R.id.shape_view)
        mShadowView = view.findViewById(R.id.shadow_view)

        post {

            startFallAnimator()
        }

    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun startFallAnimator() {
        if (mIsStopAnimator) {
            return
        }

        val translationAnimator = ObjectAnimator.ofFloat(mShapeView,"translationY",0f, mTranslationDistance.toFloat())
        // 配合中间阴影缩小
        val scaleAnimator = ObjectAnimator.ofFloat(mShadowView, "scaleX", 1f, 0.3f)
        val animatorSet = AnimatorSet()
        animatorSet.duration = ANIMATOR_DURATION
        // 下落的速度应该是越来越快
        animatorSet.interpolator = AccelerateInterpolator()
        animatorSet.playTogether(translationAnimator, scaleAnimator)
        animatorSet.start()
        // 下落完之后就上抛了，监听动画执行完毕
        // 是一种思想，在 Adapter 中的 BannerView 写过
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // 改变形状
                mShapeView!!.exchange()
                // 下落完之后就上抛了
                startUpAnimator()
                // 开始旋转
            }
        })
    }


    @SuppressLint("ObjectAnimatorBinding")
    private fun startUpAnimator() {
        if (mIsStopAnimator) {
            return
        }
        // 动画作用在谁的身上
        // 下落位移动画
        val translationAnimator = ObjectAnimator.ofFloat(mShapeView, "translationY", mTranslationDistance.toFloat(), 0f)
        // 配合中间阴影缩小
        val scaleAnimator = ObjectAnimator.ofFloat(mShadowView, "scaleX", 0.3f, 1f)

        val animatorSet = AnimatorSet()
        animatorSet.duration = ANIMATOR_DURATION
        animatorSet.interpolator = DecelerateInterpolator()
        animatorSet.playTogether(translationAnimator, scaleAnimator)
        // 先执行 translationAnimator 接着执行 scaleAnimator
        // 上抛完之后就下落了，监听动画执行完毕
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // 上抛完之后就下落了
                startFallAnimator()
            }

            override fun onAnimationStart(animation: Animator) {
                // 开始旋转
                startRotationAnimator()
            }
        })
        // 执行 -> 监听的 onAnimationStart 方法
        animatorSet.start()
    }

    /**
     * 上抛的时候需要旋转
     */
    @SuppressLint("ObjectAnimatorBinding")
    private fun startRotationAnimator() {
        val rotationAnimator = when (mShapeView!!.getCurrentShape()) {
            ShapeView.Shape.Circle, ShapeView.Shape.Square -> {
                ObjectAnimator.ofFloat(mShapeView, "rotation", 0f, 180f)
            }
            ShapeView.Shape.Triangle -> {
                ObjectAnimator.ofFloat(mShapeView, "rotation", 0f, -120f)
            }
        }
        rotationAnimator.duration = ANIMATOR_DURATION
        rotationAnimator.interpolator = DecelerateInterpolator()
        rotationAnimator.start()
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(View.INVISIBLE)
        // 清理动画
        mShapeView!!.clearAnimation()
        mShadowView!!.clearAnimation()
        // 把LoadingView从父布局移除
        val parent = parent as ViewGroup
        parent.removeView(this)            // 从父布局移除
        removeAllViews()                        // 移除自己所有的View
        mIsStopAnimator = true
    }
}