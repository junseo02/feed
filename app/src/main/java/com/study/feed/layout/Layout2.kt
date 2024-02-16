package com.study.feed.layout

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.study.feed.MainActivity.Companion.responsiveHeight
import com.study.feed.MainActivity.Companion.responsiveWidth
import com.study.feed.R

class Layout2: AppCompatActivity() {

  private fun setSize() {

    val layout2Back = findViewById<ConstraintLayout>(R.id.layout2Back)
    val layout2BackLayoutParams = layout2Back.layoutParams as FrameLayout.LayoutParams
    layout2BackLayoutParams.setMargins(0, layout2BackLayoutParams.topMargin.responsiveHeight(), 0, 0)

    val layout2Group = findViewById<ConstraintLayout>(R.id.layout2Group)
    val layout2GroupLayoutParams = layout2Group.layoutParams as ConstraintLayout.LayoutParams
    layout2GroupLayoutParams.setMargins(layout2GroupLayoutParams.leftMargin.responsiveWidth(), 0, layout2GroupLayoutParams.rightMargin.responsiveWidth(), 0)

    val layout2banner = findViewById<ImageView>(R.id.layout2banner)
    layout2banner.layoutParams.height = layout2banner.layoutParams.height.responsiveHeight()

    val layout2hSeries = findViewById<ConstraintLayout>(R.id.layout2hSeries)
    val layout2hSeriesLayoutParams = layout2hSeries.layoutParams as ConstraintLayout.LayoutParams
    layout2hSeriesLayoutParams.setMargins(0, layout2hSeriesLayoutParams.topMargin.responsiveHeight(),0,0)

    val layout2HButton2 = findViewById<AppCompatButton>(R.id.layout2HButton2)
    val layout2HButton2LayoutParams = layout2HButton2.layoutParams as ConstraintLayout.LayoutParams
    layout2HButton2LayoutParams.setMargins(layout2HButton2LayoutParams.leftMargin.responsiveWidth(),0,layout2HButton2LayoutParams.rightMargin.responsiveWidth(),0)
    layout2HButton2.layoutParams = layout2HButton2LayoutParams

    val layout2HButtonGroup2 = findViewById<ConstraintLayout>(R.id.layout2HButtonGroup2)
    val layout2HButtonGroup2LayoutParams = layout2HButtonGroup2.layoutParams as ConstraintLayout.LayoutParams
    layout2HButtonGroup2LayoutParams.setMargins(0, layout2HButtonGroup2LayoutParams.topMargin.responsiveHeight(),0,0)

    val layout2HButton5 = findViewById<AppCompatButton>(R.id.layout2HButton5)
    val layout2HButton5LayoutParams = layout2HButton5.layoutParams as ConstraintLayout.LayoutParams
    layout2HButton5LayoutParams.setMargins(layout2HButton5LayoutParams.leftMargin.responsiveWidth(), 0, layout2HButton5LayoutParams.rightMargin.responsiveWidth(),0)
    layout2HButton5.layoutParams = layout2HButton5LayoutParams

    val layout2My = findViewById<ConstraintLayout>(R.id.layout2My)
    val layout2MyLayoutParams = layout2My.layoutParams as ConstraintLayout.LayoutParams
    layout2MyLayoutParams.setMargins(0, layout2MyLayoutParams.topMargin.responsiveHeight(),0,0)

    val layout2MyReservation = findViewById<ConstraintLayout>(R.id.layout2MyReservation)
    layout2MyReservation.setPadding(layout2MyReservation.paddingLeft.responsiveWidth(),layout2MyReservation.paddingTop.responsiveHeight(),layout2MyReservation.paddingRight.responsiveWidth(),layout2MyReservation.paddingBottom.responsiveHeight())

    val layout2Community = findViewById<ConstraintLayout>(R.id.layout2Community)
    val layout2CommunityLayoutParams = layout2Community.layoutParams as ConstraintLayout.LayoutParams
    layout2CommunityLayoutParams.setMargins(0, layout2CommunityLayoutParams.topMargin.responsiveHeight(),0,0)
    layout2Community.layoutParams = layout2CommunityLayoutParams

  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.layout2)


    // 나의예약 리사이클뷰
    val myReservationList = ArrayList<MyReservationVo>()

    val myReservationVo1 = MyReservationVo("11.23","필라테스 수업 - A룸","오후 12:00")
    myReservationList.add(myReservationVo1)
    val myReservationVo2 = MyReservationVo("11.30","골프 - 1타석","오후 13:00")
    myReservationList.add(myReservationVo2)

    val myReservation : RecyclerView = findViewById(R.id.myReservation)

    val myReservationDecoration = MyReservationDecoration();
    myReservation.addItemDecoration(myReservationDecoration)

    val myReservationAdapter: MyReservationAdapter = MyReservationAdapter(this, myReservationList)
    myReservation.adapter = myReservationAdapter


    // 커뮤니티 리사이클뷰
    val communityList = ArrayList<CommunityVo>()

    val communityVo1 = CommunityVo("골프 트레이닝 센터")
    communityList.add(communityVo1)
    val communityVo2 = CommunityVo("게스트하우스")
    communityList.add(communityVo2)
    val communityVo3 = CommunityVo("헬스")
    communityList.add(communityVo3)

    val communityVo4 = CommunityVo("수영장")
    communityList.add(communityVo4)

    val community: RecyclerView = findViewById(R.id.community)

    val communityDecoration = CommunityDecoration()
    community.addItemDecoration(communityDecoration)

    val communityAdapter: CommunityAdapter = CommunityAdapter(this, communityList)
    community.adapter = communityAdapter

    setSize()
  }

}