package com.study.feed.layout

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.study.feed.MainActivity.Companion.responsiveHeight
import com.study.feed.MainActivity.Companion.responsiveWidth
import com.study.feed.R

class Layout1: AppCompatActivity() {

  private lateinit var imageRecyclerView: RecyclerView
  private lateinit var imageAdapter: ImageAdapter
  private var imageData = ArrayList<ImageVo>()

  private fun setSize() {

    val layout1 = findViewById<ConstraintLayout>(R.id.layout1)
    val layout1LayoutParams = layout1.layoutParams as FrameLayout.LayoutParams
    layout1LayoutParams.setMargins(0, 48.responsiveHeight(),0,0)
    layout1.layoutParams = layout1LayoutParams

    val title1 = findViewById<ConstraintLayout>(R.id.title1)
    val title1LayoutParams = title1.layoutParams as ConstraintLayout.LayoutParams
    title1LayoutParams.setMargins(16.responsiveWidth(),0, 16.responsiveWidth(),0)
    title1.layoutParams = title1LayoutParams

    // 가로 새로 비율 1:1
    val image1 = findViewById<ImageView>(R.id.image1)
    image1.layoutParams.width = 160.responsiveWidth()
    val image2 = findViewById<ImageView>(R.id.image2)
    image2.layoutParams.width = 160.responsiveWidth()
    val image3 = findViewById<ImageView>(R.id.image3)
    image3.layoutParams.width = 160.responsiveWidth()
    val image4 = findViewById<ImageView>(R.id.image4)
    image4.layoutParams.width = 160.responsiveWidth()

    val title2 = findViewById<ConstraintLayout>(R.id.title2)
    val title2LayoutParams = title2.layoutParams as ConstraintLayout.LayoutParams
    title2LayoutParams.setMargins(0, 16.responsiveHeight(),0,0)
    title2.layoutParams = title2LayoutParams

    val title2Text = findViewById<TextView>(R.id.title2Text)
    title2Text.setPadding(16.responsiveWidth(), 0, 16.responsiveWidth(), 0)

    val imageBox2 = findViewById<ConstraintLayout>(R.id.imageBox2)
    imageBox2.setPadding(0, 16.responsiveHeight(), 0, 16.responsiveHeight())

    val imageSlide = findViewById<RecyclerView>(R.id.imageSlide)
    val imageSlideLayoutParams = imageSlide.layoutParams as ConstraintLayout.LayoutParams
    imageSlideLayoutParams.setMargins(16.responsiveWidth(), 0, 0, 0)
    imageSlide.layoutParams = imageSlideLayoutParams

  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.layout1)

    setSize()

    val imageVo1 = ImageVo("image", "1")
    imageData.add(imageVo1)
    val imageVo2 = ImageVo("image", "2")
    imageData.add(imageVo2)
    val imageVo3 = ImageVo("image", "3")
    imageData.add(imageVo3)
    val imageVo4 = ImageVo("image", "1")
    imageData.add(imageVo4)
    val imageVo5 = ImageVo("image", "2")
    imageData.add(imageVo5)
    val imageVo6 = ImageVo("image", "3")
    imageData.add(imageVo6)
    val imageVo7 = ImageVo("image", "1")
    imageData.add(imageVo7)
    val imageVo8 = ImageVo("image", "2")
    imageData.add(imageVo8)
    val imageVo9 = ImageVo("image", "3")
    imageData.add(imageVo9)

    imageRecyclerView = findViewById(R.id.imageSlide)

    val horizontalImageItemDecoration = HorizontalImageItemDecoration()
    imageRecyclerView.addItemDecoration(horizontalImageItemDecoration)

    imageAdapter = ImageAdapter(this, imageData)
    imageRecyclerView.adapter = imageAdapter




  }


}