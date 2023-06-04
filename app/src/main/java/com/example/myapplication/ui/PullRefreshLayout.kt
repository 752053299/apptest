package com.example.myapplication.ui

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.children
import com.example.myapplication.R

open class PullRefreshLayout @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr:Int = 0):
                                ViewGroup(context,attributeSet,defStyleAttr) {

    companion object {
        private const val TAG = "PullRefreshLayout"
    }

    private var mTargetView: View? = null
    private lateinit var mRefreshView: View


    /**
     * RefreshView的初始offset
     */
    private var mRefreshInitOffset: Int = 0

    /**
     * RefreshView当前offset
     */
    private var mRefreshCurrentOffset = 0


    /**
     * 刷新时RefreshView的offset
     */
    private var mRefreshEndOffset = 0


    /**
     * 是否自动根据RefreshView的高度计算RefreshView的初始位置
     */
    private val mAutoCalculateRefreshInitOffset = true

    /**
     * 下拉时TargetView的位置
     */
    private var mTargetCurrentOffset: Int = 0

    /**
     * TargetView(ListView或者ScrollView等)的初始位置
     */
    private var mTargetInitOffset = 0

    /**
     * 刷新时TargetView的位置
     */
    private var mTargetRefreshOffset: Int = 0

    init {
        mRefreshCurrentOffset = mRefreshInitOffset
        addRefreshView()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.i(TAG, "onMeasure: ")
        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val targetMeasureWidthSpec = MeasureSpec.makeMeasureSpec(widthSize - paddingLeft - paddingRight, MeasureSpec.EXACTLY)
        val targetMeasureHeightSpec = MeasureSpec.makeMeasureSpec(heightSize - paddingTop - paddingBottom, MeasureSpec.EXACTLY)
        measureChild(mRefreshView, widthMeasureSpec, heightMeasureSpec)

        //计算refreshView的初始位置
        if(mAutoCalculateRefreshInitOffset) {
            val refreshViewHeight = mRefreshView.measuredHeight
            mRefreshCurrentOffset = -refreshViewHeight
        }

        ensureTargetView()
        if (mTargetView == null) {
            Log.d(TAG, "onMeasure: mTargetView == null")
            setMeasuredDimension(widthSize,heightSize)
            return
        }
        mTargetView!!.measure(targetMeasureWidthSpec,targetMeasureHeightSpec)
        setMeasuredDimension(widthSize,heightSize)
    }

    /**
     * 给子类机会处理targetView
     */
    protected fun onEnsureTargetView(view: View) {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.i(TAG, "onLayout: ")
        val width = measuredWidth
        val height = measuredHeight
        if (childCount == 0) {
            return
        }
        ensureTargetView()
        //布局TargetView
        val childLeft = paddingLeft
        val childTop = paddingTop
        val childWidth = width - paddingLeft - paddingRight
        val childHeight = height - paddingTop - paddingBottom
        mTargetView?.apply {
            layout(childLeft,childTop + mTargetCurrentOffset, childWidth + childLeft,childTop + childHeight + mTargetCurrentOffset)
        }
        //布局RefreshView
        val refreshViewWidth = mRefreshView.measuredWidth
        val refreshViewHeight = mRefreshView.measuredHeight
        mRefreshView.layout(width/2 - refreshViewWidth/2, mRefreshCurrentOffset, width/2 + refreshViewWidth/2, refreshViewHeight + mRefreshCurrentOffset)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return super.onInterceptTouchEvent(ev)
    }

    




    private fun ensureTargetView() {
        if (mTargetView == null) {
            for (child in children) {
                if (child != mRefreshView) {
                    onEnsureTargetView(child)
                    mTargetView = child
                    break
                }
            }
        }

    }

    private fun addRefreshView() {
        mRefreshView = createRefreshView()
        addView(mRefreshView)
    }

    protected fun createRefreshView() :View{
        return RefreshView(context)
    }


    interface IRefreshView {
        fun stop()
        fun doRefresh()
        fun onPull(offset: Int)
    }


}


class RefreshView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr:Int = 0):
    LinearLayout(context,attributeSet,defStyleAttr), PullRefreshLayout.IRefreshView {


    init {
        inflate(context, R.layout.refresh_view,this)
        val lp = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        layoutParams = lp
    }

    override fun stop() {
    }

    override fun doRefresh() {
    }

    override fun onPull(offset: Int) {
    }

}