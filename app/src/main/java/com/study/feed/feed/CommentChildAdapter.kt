package com.study.feed.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.study.feed.MainActivity.Companion.responsiveHeight
import com.study.feed.R

class CommentChildAdapter(
  val context: Context
  , private val commentChildList: ArrayList<CommentChildVo>
  , private val listener: OnItemClickListener
):RecyclerView.Adapter<CommentChildAdapter.ViewHolder>() {

  interface OnItemClickListener {
    fun onClick(commentChildVo: CommentChildVo)
  }

  inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val childUserId = view.findViewById<TextView>(R.id.childUserId)
    private val childComment = view.findViewById<TextView>(R.id.childComment)
    private val commentChildItem = view.findViewById<ConstraintLayout>(R.id.commentChildItem)

    fun bind(commentChildVo: CommentChildVo, context: Context) {
      childUserId?.text = commentChildVo.userId
      childComment?.text = commentChildVo.commentChildText

      val childUserIdLayoutParams = childUserId.layoutParams as ConstraintLayout.LayoutParams
      childUserIdLayoutParams.height = 20.responsiveHeight()
      childUserId.layoutParams = childUserIdLayoutParams

      val childCommentLayoutParams = childComment.layoutParams as ConstraintLayout.LayoutParams
      childCommentLayoutParams.height = 32.responsiveHeight()
      childComment.layoutParams = childCommentLayoutParams

      if (commentChildVo == commentChildList[commentChildList.lastIndex]) {

        val id:Int = View.generateViewId()

        val addChildCommentImage = ImageView(context)
        addChildCommentImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.arrow))
        addChildCommentImage.id = id
        val addChildCommentImageLayoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        addChildCommentImageLayoutParams.topToBottom = R.id.childComment
        addChildCommentImageLayoutParams.height = 20.responsiveHeight()
        addChildCommentImage.layoutParams = addChildCommentImageLayoutParams

        commentChildItem?.addView(addChildCommentImage)

        val addChildComment = TextView(context)
        val layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.topToBottom = R.id.childComment
        layoutParams.leftToRight = addChildCommentImage.id
        layoutParams.height = 20.responsiveHeight()
        addChildComment.layoutParams = layoutParams
        addChildComment.text = "답글 달기"

        addChildComment.setOnClickListener {
          listener.onClick(commentChildVo)
        }

        commentChildItem?.addView(addChildComment)
      }

    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentChildAdapter.ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.comment_child, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: CommentChildAdapter.ViewHolder, position: Int) {
    holder.bind(commentChildList[position], context)
  }

  override fun getItemCount(): Int {
    return commentChildList.size
  }
}