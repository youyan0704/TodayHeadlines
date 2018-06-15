package com.youyan.android.headlines.ui.customView.loadingView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.shapes.Shape
import android.util.AttributeSet
import android.view.View
import android.support.v4.content.ContextCompat
import com.youyan.android.headlines.R

class ShapeView : View{

    private var mPaint: Paint = Paint()
    private lateinit var mPath: Path
    private var mCurrentShape = Shape.Circle

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs,defStyleAttr){

        mPaint.isAntiAlias = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        setMeasuredDimension(Math.min(width, height), Math.min(width, height))
    }

    override fun onDraw(canvas: Canvas) {

        when (mCurrentShape) {
            Shape.Circle -> {
                // 圆形
                val center = (width / 2).toFloat()
                mPaint.color = ContextCompat.getColor(context, R.color.material_deep_orange_500)
                canvas.drawCircle(center, center, center, mPaint)
            }
            Shape.Square -> {
                // 正方形
                mPaint.color = ContextCompat.getColor(context, R.color.material_green_400)
                canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), mPaint)
            }
            Shape.Triangle -> {
                // 三角  Path 画路线
                mPaint.color = ContextCompat.getColor(context, R.color.material_deep_purple_400)
                if (mPath == null) {
                    // 画路径
                    mPath = Path()
                    mPath.moveTo((width / 2).toFloat(), 0F)
                    mPath.lineTo(0F, (width / 2 * Math.sqrt(3.0)).toFloat())
                    mPath.lineTo(width.toFloat(), (width / 2 * Math.sqrt(3.0)).toFloat())
                    // path.lineTo(getWidth()/2,0);
                    mPath.close()
                }
                canvas.drawPath(mPath, mPaint)
            }
        }

        super.onDraw(canvas)
    }

    /**
     * 改变形状
     */
    fun exchange() {

        mCurrentShape = when (mCurrentShape) {
            Shape.Circle -> Shape.Square
            Shape.Square -> Shape.Triangle
            Shape.Triangle -> Shape.Circle
        }
        invalidate()
    }

    enum class Shape {
        Circle, Square, Triangle
    }

    fun getCurrentShape(): Shape {
        return mCurrentShape
    }
}