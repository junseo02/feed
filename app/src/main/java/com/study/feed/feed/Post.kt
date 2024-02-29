package com.study.feed.feed

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.study.feed.MainActivity.Companion.responsiveHeight
import com.study.feed.MainActivity.Companion.responsiveWidth
import com.study.feed.R

class Post: AppCompatActivity() {

  private val commentParentList = ArrayList<CommentParentVo>()
  private fun setData() {

    val commentChildList1 = ArrayList<CommentChildVo>()
    val commentChildVo1 = CommentChildVo("#p001", "#c001", 1, "대댓글 첫번째!", "aaaa", "이에이", "2024-02-26")
    commentChildList1.add(commentChildVo1)
    val commentChildVo2 = CommentChildVo("#p001", "#c002", 2, "대댓글 두번째!", "bbb", "이비", "2024-02-29")
    commentChildList1.add(commentChildVo2)

    val commentParentVo1 = CommentParentVo("#p001", 1, "첫번째 댓글입니다.", "cho11", "초보자", "2024-02-25", commentChildList1)
    commentParentList.add(commentParentVo1)

    val commentChildList2 = ArrayList<CommentChildVo>()
    val commentParentVo2 = CommentParentVo("#p002", 2, "두번째 댓글", "gagaga", "가가가", "2024-02-27", commentChildList2)
    commentParentList.add(commentParentVo2)

    val postGroup = findViewById<ConstraintLayout>(R.id.postGroup)
    val postGroupLayoutParams = postGroup.layoutParams as LinearLayout.LayoutParams
    postGroupLayoutParams.setMargins(0, 8.responsiveHeight(), 0, 0)
    postGroup.layoutParams = postGroupLayoutParams
    postGroup.setPadding(14.responsiveWidth(), 15.responsiveHeight(), 14.responsiveWidth(), 12.responsiveHeight())

    val postImageSlide = findViewById<ViewPager2>(R.id.postImageSlide)
    val postImageSlideLayoutParams = postImageSlide.layoutParams as ConstraintLayout.LayoutParams
    postImageSlideLayoutParams.height = 152.responsiveHeight()
    postImageSlide.layoutParams = postImageSlideLayoutParams

    val postIndicator = findViewById<LinearLayout>(R.id.postIndicator)
    val postIndicatorLayoutParams = postIndicator.layoutParams as ConstraintLayout.LayoutParams
    postIndicatorLayoutParams.setMargins(0,0,0, 8.responsiveHeight())
    postIndicator.layoutParams = postIndicatorLayoutParams

    val postHeart = findViewById<ImageButton>(R.id.postHeart)
    val postHeartLayoutParams = postHeart.layoutParams as ConstraintLayout.LayoutParams
    postHeartLayoutParams.width = 30.responsiveWidth()
    postHeartLayoutParams.height = 30.responsiveWidth()
    postHeart.layoutParams = postHeartLayoutParams


    val postExport = findViewById<ImageButton>(R.id.postExport)
    val postExportLayoutParams = postExport.layoutParams as ConstraintLayout.LayoutParams
    postExportLayoutParams.width = 30.responsiveWidth()
    postExportLayoutParams.height = 30.responsiveWidth()
    postExport.layoutParams = postExportLayoutParams

    val commentRecyclerView = findViewById<RecyclerView>(R.id.commentRecyclerView)
    val commentRecyclerViewLayoutParams = commentRecyclerView.layoutParams as LinearLayout.LayoutParams
    commentRecyclerViewLayoutParams.setMargins(20.responsiveWidth(), 15.responsiveHeight(), 20.responsiveWidth(),0)
    commentRecyclerView.layoutParams = commentRecyclerViewLayoutParams
    commentRecyclerView.setPadding(0,15.responsiveHeight(),0,0)

    val postEditText = findViewById<EditText>(R.id.postEditText)
    val postEditTextLayoutParams = postEditText.layoutParams as ConstraintLayout.LayoutParams
    postEditTextLayoutParams.height = 48.responsiveHeight()
    postEditText.layoutParams = postEditTextLayoutParams

    val postButton = findViewById<Button>(R.id.postButton)
    val postButtonLayoutParams = postButton.layoutParams as ConstraintLayout.LayoutParams
    postButtonLayoutParams.setMargins(10.responsiveWidth(), 12.responsiveHeight(), 10.responsiveWidth(), 12.responsiveHeight())
    postButtonLayoutParams.width = 40.responsiveWidth()
    postButtonLayoutParams.height = 24.responsiveHeight()
    postButton.layoutParams = postButtonLayoutParams
    postButton.setPadding(0)

  }

  private fun Int.numberToCode(): String {
    return ("00$this").substring(("00$this").length-3, ("00$this").length)
  }

  private fun ImageButton.heartColor(heart:Boolean, context: Context) {
    if (heart) {
      this.setColorFilter(ContextCompat.getColor(context, R.color.red), PorterDuff.Mode.SRC_IN)
    } else {
      this.setColorFilter(ContextCompat.getColor(context, R.color.black), PorterDuff.Mode.SRC_IN)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.post)

    setData()

    // 이미지 슬라이드
    val postImageSlide = findViewById<ViewPager2>(R.id.postImageSlide)

    val postIndicator = findViewById<LinearLayout>(R.id.postIndicator)
    val context: Context = this

    val postAdapter = intent.getStringArrayListExtra("viewFeedItemImageList")?.let { PostAdapter(this, it) }
    postImageSlide.offscreenPageLimit = intent.getStringArrayListExtra("viewFeedItemImageList")?.size!!
    postImageSlide.adapter = postAdapter

    // 동그라미
    for (i: Int in 1 .. intent.getStringArrayListExtra("viewFeedItemImageList")?.size!!) {
      val imageView: ImageView = ImageView(this)
      imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ellipse_4))
      val params = LinearLayout.LayoutParams(5.responsiveWidth(), 5.responsiveWidth())
      params.setMargins(2.responsiveWidth(),0,2.responsiveWidth(),0)
      imageView.layoutParams = params
      postIndicator.addView(imageView)
    }

    postImageSlide.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
      override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        val child = intent.getStringArrayListExtra("viewFeedItemImageList")?.size!!
        for (i:Int in 0 until child) {
          val imageView: ImageView = postIndicator.getChildAt(i) as ImageView
          if (i == position) {
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ellipse_4))
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.darkGray), PorterDuff.Mode.SRC_IN)
          } else {
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ellipse_4))
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.gray2Background), PorterDuff.Mode.SRC_IN)
          }
        }
      }
    })

    // 하트
    val postHeart = findViewById<ImageButton>(R.id.postHeart)
    var heartBoolean = intent.getBooleanExtra("viewFeedItemHeart", false)
    postHeart.heartColor(heartBoolean, this)
    postHeart.setOnClickListener {
      heartBoolean = !heartBoolean
      postHeart.heartColor(heartBoolean, this)
    }

    // 공유
    val postExport = findViewById<ImageButton>(R.id.postExport)
    val export = intent.getStringExtra("viewFeedItemExport")
    postExport.setOnClickListener {
      val intent = Intent(Intent.ACTION_SEND_MULTIPLE)
      intent.type = "text/plain"
      intent.putExtra(Intent.EXTRA_HTML_TEXT, export)
      ContextCompat.startActivity(this ,Intent.createChooser(intent, "공유"), null)
    }

    // text
    val postText = findViewById<TextView>(R.id.postText)
    postText.text = intent.getStringExtra("originalText")

    // 댓글 입력
    val postEditText = findViewById<EditText>(R.id.postEditText)

    // 댓글 adapter
    val commentRecyclerView = findViewById<RecyclerView>(R.id.commentRecyclerView)
    val commentAdapter = CommentAdapter(this, commentParentList, object : CommentAdapter.OnItemClickListener {
      override fun onClick(commentParentVo: CommentParentVo) {
        postEditText.setText(commentParentVo.commentId)
      }

    })
    commentRecyclerView.adapter = commentAdapter

    val postButton = findViewById<Button>(R.id.postButton)

    postButton.setOnClickListener {

      if (postEditText.text.substring(0,2) == "#p") {
        // 대댓글
        val commentParentVo = commentParentList.find { commentParentVo -> commentParentVo.commentId == postEditText.text.substring(0,5) }
        val commentChildList = commentParentVo!!.childList

        val commentNumber:Int
        val childId: String
        if (commentParentVo.childList.size > 0) {
          commentNumber = commentParentVo.childList[commentParentVo.childList.lastIndex].commentChildId.substring(2, 5).toInt() + 1
          childId = "#c${commentNumber.numberToCode()}"
        } else {
          commentNumber = 1
          childId = "#c${commentNumber.numberToCode()}"
        }

        val commentChildVo = CommentChildVo(
            commentParentVo.commentId
          , childId
          , commentNumber
          , postEditText.text.toString().substring(6)
          , "joyBe"
          , "조이비"
          , "2024-02-28"
        )

        commentChildList.add(commentChildVo)
        commentParentVo.childList = commentChildList

        commentAdapter.notifyItemChanged(commentParentVo.commentSeq - 1)

      } else {
        // 댓글
        val commentNumber:Int = commentParentList[commentParentList.lastIndex].commentId.substring(2,5).toInt() + 1
        val parentId = "#p${commentNumber.numberToCode()}"

        val commentChildList = ArrayList<CommentChildVo>()
        val commentParentVo = CommentParentVo(
            parentId
          , commentNumber
          , postEditText.text.toString()
          , "na123"
          , "나일이"
          , "2024-02-28"
          , commentChildList)
        commentParentList.add(commentParentVo)
        commentAdapter.notifyItemInserted(commentParentList.size - 1)

      }

      // 키보드 내리기
      postEditText.text.clear()
      val inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      postEditText.clearFocus()
      inputMethodManager.hideSoftInputFromWindow(postEditText.windowToken, 0)
    }

  }

}