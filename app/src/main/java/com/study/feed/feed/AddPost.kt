package com.study.feed.feed

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.recyclerview.widget.RecyclerView
import com.study.feed.MainActivity.Companion.responsiveHeight
import com.study.feed.MainActivity.Companion.responsiveWidth
import com.study.feed.R

class AddPost: AppCompatActivity() {

  private val addImageList = ArrayList<Uri>()

  private fun setData() {

    val addPost = findViewById<ConstraintLayout>(R.id.addPost)
    val addPostLayoutParams = addPost.layoutParams as ConstraintLayout.LayoutParams
    addPostLayoutParams.setMargins(20.responsiveWidth(), 0, 20.responsiveWidth(), 0)
    addPost.layoutParams = addPostLayoutParams

    val addPostGroup = findViewById<ConstraintLayout>(R.id.addPostGroup)
    val addPostGroupLayoutParams = addPostGroup.layoutParams as ConstraintLayout.LayoutParams
    addPostGroupLayoutParams.topMargin = 15.responsiveHeight()
    addPostGroup.layoutParams = addPostGroupLayoutParams

    val addPostImageButton = findViewById<ImageButton>(R.id.addPostImageButton)
    val addPostImageButtonLayoutParams = addPostImageButton.layoutParams as ConstraintLayout.LayoutParams
    addPostImageButtonLayoutParams.width = 44.responsiveWidth()
    addPostImageButtonLayoutParams.height = 44.responsiveWidth()
    addPostImageButton.layoutParams = addPostImageButtonLayoutParams

    val addPostImageGroup = findViewById<RecyclerView>(R.id.addPostImageGroup)
    val addPostImageGroupLayoutParams = addPostImageGroup.layoutParams as ConstraintLayout.LayoutParams
    addPostImageGroupLayoutParams.width = 276.responsiveWidth()
    addPostImageGroupLayoutParams.height = 44.responsiveWidth()
    addPostImageGroup.layoutParams = addPostImageGroupLayoutParams

    val addPostTitleTextView = findViewById<TextView>(R.id.addPostTitleTextView)
    val addPostTitleTextViewLayoutParams = addPostTitleTextView.layoutParams as ConstraintLayout.LayoutParams
    addPostTitleTextViewLayoutParams.topMargin = 25.responsiveHeight()
    addPostTitleTextView.layoutParams = addPostTitleTextViewLayoutParams

    val addPostTitleEditText = findViewById<EditText>(R.id.addPostTitleEditText)
    val addPostTitleEditTextLayoutParams = addPostTitleEditText.layoutParams as ConstraintLayout.LayoutParams
    addPostTitleEditTextLayoutParams.height = 37.responsiveHeight()
    addPostTitleEditText.layoutParams = addPostTitleEditTextLayoutParams

    val addPostContentTextView = findViewById<TextView>(R.id.addPostContentTextView)
    val addPostContentTextViewLayoutParams = addPostContentTextView.layoutParams as ConstraintLayout.LayoutParams
    addPostContentTextViewLayoutParams.topMargin = 11.responsiveHeight()
    addPostContentTextView.layoutParams = addPostContentTextViewLayoutParams

    val addPostContentEditText = findViewById<EditText>(R.id.addPostContentEditText)
    val addPostContentEditTextLayoutParams = addPostContentEditText.layoutParams as ConstraintLayout.LayoutParams
    addPostContentEditTextLayoutParams.height = 171.responsiveHeight()
    addPostContentEditText.layoutParams = addPostContentEditTextLayoutParams

    val addPostButton = findViewById<Button>(R.id.addPostButton)
    val addPostButtonLayoutParams = addPostButton.layoutParams as ConstraintLayout.LayoutParams
    addPostButtonLayoutParams.height = 50.responsiveHeight()
    addPostButton.layoutParams = addPostButtonLayoutParams

  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.add_post)

    setData()

    val addPostImageButton : ImageButton = findViewById(R.id.addPostImageButton)
    addPostImageButton.setOnClickListener{

      val intent = Intent(Intent.ACTION_PICK)
      intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
      intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
      intent.action = Intent.ACTION_GET_CONTENT
      activityResultLauncher.launch(intent)

    }

    val addPostImageGroup = findViewById<RecyclerView>(R.id.addPostImageGroup)
    val addPostImageGroupAdapter = AddPostAdapter(this, addImageList, object:AddPostAdapter.OnItemClickListener {
      override fun onClick(position: Int) {
        addImageList.removeAt(position)
        addPostImageGroup.adapter?.notifyItemRemoved(position)
        addPostImageGroup.adapter?.notifyItemRangeChanged(position, addImageList.size)

      }
    })
    addPostImageGroup.adapter = addPostImageGroupAdapter

    val addPostButton = findViewById<Button>(R.id.addPostButton)
    addPostButton.setOnClickListener {

      val titleText = findViewById<EditText>(R.id.addPostTitleEditText)
      val contentText = findViewById<EditText>(R.id.addPostContentEditText)

      val addPostVo = AddPostVo(titleText.text.toString(), contentText.text.toString(), addImageList)
      // addPostVo를 저장
    }

  }

  private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
    if (result.resultCode == Activity.RESULT_OK) {
      val addPostImageGroup = findViewById<RecyclerView>(R.id.addPostImageGroup)
      val intent = result.data
      if (intent?.clipData != null) {
        val count = intent.clipData!!.itemCount

        for (i in 0 until count) {
          val uri = intent.clipData!!.getItemAt(i).uri
          addImageList.add(uri)
          addPostImageGroup.adapter?.notifyItemInserted(addImageList.size)
        }

      } else {
        addImageList.add(intent?.data!!)
        addPostImageGroup.adapter?.notifyItemInserted(addImageList.size)
      }
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    activityResultLauncher.unregister()
  }


}