package com.study.feed.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.study.feed.R

class ViewFeedItemImageAdapter(val context: Context, val viewFeedItemImageList: ArrayList<ViewFeedItemImageVo>):RecyclerView.Adapter<ViewFeedItemImageAdapter.ViewHolder>() {

  inner class ViewHolder(view: View):RecyclerView.ViewHolder(view!!) {
    val image = view?.findViewById<ImageView>(R.id.slideImage)
    fun bind(viewFeedItemImageVo: ViewFeedItemImageVo, context: Context) {
      image?.setImageResource(getImage(viewFeedItemImageVo))
    }
  }

  fun getImage(viewFeedItemImageVo: ViewFeedItemImageVo) : Int {
    return when(viewFeedItemImageVo.image) {
      "1" -> R.drawable.image1
      "2" -> R.drawable.image2
      "3" -> R.drawable.image3
      "4" -> R.drawable.image4
      else -> R.color.black
    }
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewFeedItemImageAdapter.ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.view_feed_item_image, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewFeedItemImageAdapter.ViewHolder, position: Int) {
    holder.bind(viewFeedItemImageList[position], context)
  }

  override fun getItemCount(): Int {
    return viewFeedItemImageList.size
  }
}