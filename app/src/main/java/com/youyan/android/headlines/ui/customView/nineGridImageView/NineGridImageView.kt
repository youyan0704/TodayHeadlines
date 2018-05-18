package com.youyan.android.headlines.ui.customView.nineGridImageView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.youyan.android.headlines.R
import java.util.ArrayList

open class NineGridImageView<T> @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
    : ViewGroup(context, attrs) {

    private var mRowCount: Int = 0          // 行数
    private var mColumnCount: Int = 0       // 列数

    private var mMaxSize: Int = 0           // 最大图片数
    private var mShowStyle: Int = 0         // 显示风格
    private var mGap: Int = 0               // 宫格间距
    private var mSingleImgSize: Int = 0     // 单张图片时的尺寸
    private var mGridSize: Int = 0          // 宫格大小,即图片大小

    private val mImageViewList = ArrayList<ImageView>()
    private var mImgDataList =  listOf<T>()

    private lateinit var mAdapter: NineGridImageViewAdapter<T>

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NineGridImageView)
        this.mGap = typedArray.getDimension(R.styleable.NineGridImageView_imgGap, 0f).toInt()
        this.mSingleImgSize = typedArray.getDimensionPixelSize(R.styleable.NineGridImageView_singleImgSize, -1)
        this.mShowStyle = typedArray.getInt(R.styleable.NineGridImageView_showStyle, STYLE_GRID)
        this.mMaxSize = typedArray.getInt(R.styleable.NineGridImageView_maxSize, 9)
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        val height: Int
        val totalWidth = width - paddingLeft - paddingRight
        if (mImgDataList.isNotEmpty()) {
            if (mImgDataList.size == 1 && mSingleImgSize != -1) {
                mGridSize = if (mSingleImgSize > totalWidth) totalWidth else mSingleImgSize
            } else {
                mImageViewList[0].scaleType = ImageView.ScaleType.CENTER_CROP
                mGridSize = (totalWidth - mGap * (mColumnCount - 1)) / mColumnCount
            }
            height = mGridSize * mRowCount + mGap * (mRowCount - 1) + paddingTop + paddingBottom
            setMeasuredDimension(width, height)
        } else {
            height = width
            setMeasuredDimension(width, height)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        layoutChildrenView()
    }

    /**
     * 布局 ImageView
     */
    private fun layoutChildrenView() {
        val childrenCount = mImgDataList.size
        for (i in 0 until childrenCount) {
            val childrenView = getChildAt(i) as ImageView
            mAdapter.onDisplayImage(context, childrenView, mImgDataList[i])
            val rowNum = i / mColumnCount
            val columnNum = i % mColumnCount
            val left = (mGridSize + mGap) * columnNum + paddingLeft
            val top = (mGridSize + mGap) * rowNum + paddingTop
            val right = left + mGridSize
            val bottom = top + mGridSize

            childrenView.layout(left, top, right, bottom)
        }
    }

    /**
     * 设置图片数据
     *
     * @param lists 图片数据集合
     */
    fun setImagesData(lists: List<T>?) {
        var lists = lists
        if (lists == null || lists.isEmpty()) {
            this.visibility = View.GONE
            return
        } else {
            this.setVisibility(View.VISIBLE)
        }

        if (mMaxSize > 0 && lists.size > mMaxSize) {
            lists = lists.subList(0, mMaxSize)
        }

        val gridParam = calculateGridParam(lists.size, mShowStyle)
        mRowCount = gridParam[0]
        mColumnCount = gridParam[1]
        val oldViewCount = mImgDataList.size
        val newViewCount = lists.size
        if (oldViewCount > newViewCount) {
            removeViews(newViewCount, oldViewCount - newViewCount)
        } else if (oldViewCount < newViewCount) {
            for (i in oldViewCount until newViewCount) {
                val iv = getImageView(i) ?: return
                addView(iv, generateDefaultLayoutParams())
            }
        }
        mImgDataList = lists
        requestLayout()
    }

    /**
     * 获得 ImageView
     * 保证了 ImageView 的重用
     *
     * @param position 位置
     */
    private fun getImageView(position: Int): ImageView? {
        return if (position < mImageViewList.size) {
            mImageViewList[position]
        } else {
            run {
                val imageView = mAdapter.generateImageView(context)
                mImageViewList.add(imageView)
                imageView.setOnClickListener { mAdapter.onItemImageClick(context, position, mImgDataList) }
                imageView
            }
        }
    }

    /**
     * 设置适配器
     *
     * @param adapter 适配器
     */
    fun setAdapter(adapter: NineGridImageViewAdapter<T>) {
        mAdapter = adapter
    }

    /**
     * 设置宫格间距
     *
     * @param gap 宫格间距 px
     */
    fun setGap(gap: Int) {
        mGap = gap
    }

    /**
     * 设置显示风格
     *
     * @param showStyle 显示风格
     */
    fun setShowStyle(showStyle: Int) {
        mShowStyle = showStyle
    }

    /**
     * 设置只有一张图片时的尺寸大小
     *
     * @param singleImgSize 单张图片的尺寸大小
     */
    fun setSingleImgSize(singleImgSize: Int) {
        mSingleImgSize = singleImgSize
    }

    /**
     * 设置最大图片数
     *
     * @param maxSize 最大图片数
     */
    fun setMaxSize(maxSize: Int) {
        mMaxSize = maxSize
    }

    companion object {

        const val STYLE_GRID = 0     // 宫格布局
        private const val STYLE_FILL = 1     // 全填充布局

        /**
         * 设置 宫格参数
         *
         * @param imagesSize 图片数量
         * @param showStyle  显示风格
         * @return 宫格参数 gridParam[0] 宫格行数 gridParam[1] 宫格列数
         */
        protected fun calculateGridParam(imagesSize: Int, showStyle: Int): IntArray {
            val gridParam = IntArray(2)
            when (showStyle) {
                STYLE_FILL -> when {
                    imagesSize < 3 -> {
                        gridParam[0] = 1
                        gridParam[1] = imagesSize
                    }
                    imagesSize <= 4 -> {
                        gridParam[0] = 2
                        gridParam[1] = 2
                    }
                    else -> {
                        gridParam[0] = imagesSize / 3 + if (imagesSize % 3 == 0) 0 else 1
                        gridParam[1] = 3
                    }
                }
                STYLE_GRID -> {
                    gridParam[0] = imagesSize / 3 + if (imagesSize % 3 == 0) 0 else 1
                    gridParam[1] = 3
                }
                else -> {
                    gridParam[0] = imagesSize / 3 + if (imagesSize % 3 == 0) 0 else 1
                    gridParam[1] = 3
                }
            }
            return gridParam
        }
    }
}