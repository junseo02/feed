package com.study.feed.layout

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.study.feed.MainActivity

class CommunityDecoration: ItemDecoration() {

  override fun getItemOffsets(
    outRect: Rect,
    view: View,
    parent: RecyclerView,
    state: RecyclerView.State
  ) {
    super.getItemOffsets(outRect, view, parent, state)

    val position: Int = parent.getChildAdapterPosition(view)
    if (position > 0){
      outRect.apply {
        left = (MainActivity.widthRate * 8).toInt()
      }
    } else {
      outRect.apply {
        left = 0
        right = 0
        top = 0
        bottom = 0
      }
    }

  }

}