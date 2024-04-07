package com.vr.beerinformation.ui.main.adapter

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecoration(val context: Context) : RecyclerView.ItemDecoration()  {

    private var leftItem:Int = 8
    private var rightItem:Int = 8
    private var topItem:Int = 4
    private var bottomItem:Int = 4

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val itemPosition = parent.getChildAdapterPosition(view)
        outRect.apply {
            top = getSize(if (itemPosition == 0){ 12 }else{ topItem })
            bottom = getSize(bottomItem)
            left = getSize(leftItem)
            right = getSize(rightItem)
        }
    }

    private fun getSize(i:Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, i.toFloat(), context.resources.displayMetrics).toInt()
}