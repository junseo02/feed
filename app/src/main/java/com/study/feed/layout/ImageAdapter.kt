package com.study.feed.layout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.study.feed.MainActivity
import com.study.feed.R

class ImageAdapter(val context: Context, private var ImageData: ArrayList<ImageVo>):RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
  inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
    var image = view?.findViewById<ImageView>(R.id.imageSlideImage)
    var layoutParams = image?.layoutParams

    fun bind(imageVo: ImageVo, context: Context) {
      image?.setImageResource(getImage(imageVo))
      layoutParams?.width = (MainActivity.widthRate * 120).toInt();
      layoutParams?.height = (MainActivity.heightRate * 60).toInt();

      image?.layoutParams = layoutParams
    }
  }

  fun getImage(imageVo: ImageVo) : Int {
    return when(imageVo.image) {
      "1" -> R.drawable.image1
      "2" -> R.drawable.image2
      "3" -> R.drawable.image3
      "4" -> R.drawable.image4
      else -> R.color.black
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.image_slide_item, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {
    holder.bind(ImageData[position], context)
  }

  override fun getItemCount(): Int {
    return ImageData.size
  }
}