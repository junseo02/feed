package com.study.feed.feed

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.study.feed.MainActivity.Companion.responsiveWidth
import com.study.feed.R

class AddPostAdapter(
    val context: Context
  , private val addImageList: ArrayList<Uri>
  , private val listener: AddPostAdapter.OnItemClickListener
):RecyclerView.Adapter<AddPostAdapter.ViewHolder>() {

  interface OnItemClickListener {
    fun onClick(position:Int)
  }

  inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val addPostImage = view.findViewById<ImageView>(R.id.addPostImage)
    private val layout = view.findViewById<ConstraintLayout>(R.id.addPostImageLayout)
    
    fun bind(context: Context, uri: Uri, position: Int) {
      addPostImage.setImageURI(uri)

      val layoutParams = layout.layoutParams as RecyclerView.LayoutParams
      layoutParams.leftMargin = 10.responsiveWidth()
      layout.layoutParams = layoutParams

      val addPostImageLayoutParams = addPostImage.layoutParams as ConstraintLayout.LayoutParams
      addPostImageLayoutParams.width = 44.responsiveWidth()
      addPostImageLayoutParams.height = 44.responsiveWidth()
      addPostImage.layoutParams = addPostImageLayoutParams

      addPostImage.setOnClickListener{
        listener.onClick(position)
      }

    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPostAdapter.ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.add_post_image, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: AddPostAdapter.ViewHolder, position: Int) {
    holder.bind(context, addImageList[position], position)
  }

  override fun getItemCount(): Int {
    return addImageList.size
  }
}