package com.study.feed.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.study.feed.MainActivity.Companion.responsiveHeight
import com.study.feed.MainActivity.Companion.responsiveWidth
import com.study.feed.R

class CommentAdapter(
    val context: Context
  , private val commentList: ArrayList<CommentParentVo>
  , private val listener:CommentAdapter.OnItemClickListener
):RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

  interface OnItemClickListener {
    fun onClick(commentParentVo: CommentParentVo)
  }

  inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val userId = view.findViewById<TextView>(R.id.userId)
    private val parentComment = view.findViewById<TextView>(R.id.parentComment)
    private val childCommentList = view.findViewById<RecyclerView>(R.id.childCommentList)
    private val commentItem = view.findViewById<ConstraintLayout>(R.id.commentItem)

    fun bind(commentParentVo: CommentParentVo, context: Context) {
      userId?.text = commentParentVo.userId
      parentComment?.text = commentParentVo.commentText

      val userIdLayoutParams = userId.layoutParams as ConstraintLayout.LayoutParams
      userIdLayoutParams.height = 20.responsiveHeight()
      userId.layoutParams = userIdLayoutParams

      val parentCommentLayoutParams = parentComment.layoutParams as ConstraintLayout.LayoutParams
      parentCommentLayoutParams.height = 32.responsiveHeight()
      parentComment.layoutParams = parentCommentLayoutParams

      if (commentParentVo.childList.size > 0) {
        val commentChildAdapter = CommentChildAdapter(context, commentParentVo.childList, object:CommentChildAdapter.OnItemClickListener{
          override fun onClick(commentChildVo: CommentChildVo) {
            listener.onClick(commentParentVo)
          }
        })
        childCommentList?.adapter = commentChildAdapter
        val childCommentListLayoutParams = childCommentList.layoutParams as ConstraintLayout.LayoutParams
        childCommentListLayoutParams.leftMargin = 40.responsiveWidth()
        childCommentList.layoutParams = childCommentListLayoutParams

      } else {

        val id:Int = View.generateViewId()

        val addChildCommentImage = ImageView(context)
        addChildCommentImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.arrow))
        addChildCommentImage.id = id
        val addChildCommentImageLayoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        addChildCommentImageLayoutParams.topToBottom = R.id.parentComment
        addChildCommentImageLayoutParams.height = 20.responsiveHeight()
        addChildCommentImage.layoutParams = addChildCommentImageLayoutParams

        commentItem?.addView(addChildCommentImage)

        val addChildComment = TextView(context)
        val layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.topToBottom = R.id.parentComment
        layoutParams.leftToRight = addChildCommentImage.id
        layoutParams.height = 20.responsiveHeight()
        addChildComment.layoutParams = layoutParams
        addChildComment.text = "답글 달기"

        addChildComment.setOnClickListener {
          listener.onClick(commentParentVo)
        }

        commentItem?.addView(addChildComment)

      }
    }

  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.comment, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: CommentAdapter.ViewHolder, position: Int) {
    holder.bind(commentList[position], context)
  }

  override fun getItemCount(): Int {
    return commentList.size
  }
}