package com.study.feed.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.study.feed.R

class PostAdapter(val context: Context, private val imageList: ArrayList<String>):RecyclerView.Adapter<PostAdapter.ViewHolder>() {

  inner class ViewHolder(view: View):RecyclerView.ViewHolder(view!!) {
    val imageView = view?.findViewById<ImageView>(R.id.postImage)

    fun bind(image: String, context: Context) {
      imageView?.setImageResource(getImage(image))
    }
    fun getImage(image: String) : Int {
      return when(image) {
        "1" -> R.drawable.image1
        "2" -> R.drawable.image2
        "3" -> R.drawable.image3
        "4" -> R.drawable.image4
        else -> R.color.black
      }
    }

  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.post_image, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
    return holder.bind(imageList[position], context)
  }

  override fun getItemCount(): Int {
    return imageList.size
  }
}