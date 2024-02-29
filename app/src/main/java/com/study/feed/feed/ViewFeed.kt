package com.study.feed.feed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.study.feed.R

class ViewFeed: AppCompatActivity() {

  val viewFeedList = ArrayList<ViewFeedVo>()

  private fun setData(){

    val viewFeedItemImageVo1 = ViewFeedItemImageVo("1")
    val viewFeedItemImageVo2 = ViewFeedItemImageVo("2")
    val viewFeedItemImageVo3 = ViewFeedItemImageVo("3")
    val viewFeedItemImageVo4 = ViewFeedItemImageVo("4")

    val viewFeedItemImageList1 = ArrayList<ViewFeedItemImageVo>()
    viewFeedItemImageList1.add(viewFeedItemImageVo1)
    viewFeedItemImageList1.add(viewFeedItemImageVo2)
    viewFeedItemImageList1.add(viewFeedItemImageVo3)
    val viewFeedVo1 = ViewFeedVo("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
      ,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
      ,viewFeedItemImageList1
      , "http:~~"
      , true)
    viewFeedList.add(viewFeedVo1)

    val viewFeedItemImageList2 = ArrayList<ViewFeedItemImageVo>()
    viewFeedItemImageList2.add(viewFeedItemImageVo2)
    viewFeedItemImageList2.add(viewFeedItemImageVo3)
    viewFeedItemImageList2.add(viewFeedItemImageVo1)
    val viewFeedVo2 = ViewFeedVo("동해물과 백두산이 마르고 닳도록\n하느님이 보우하사 우리나라 만세\n무궁화 삼천리 화려강산\n대한사람 대한으로 길이 보전하세"
      ,"동해물과 백두산이 마르고 닳도록\n" +
        "하느님이 보우하사 우리나라만세\n" +
        "무궁화 삼천리 화려강산 대한사람\n" +
        "대한으로 길이 보전하세"
      ,viewFeedItemImageList2
      , "http:~~"
      , false)
    viewFeedList.add(viewFeedVo2)

    val viewFeedItemImageList3 = ArrayList<ViewFeedItemImageVo>()
    viewFeedItemImageList3.add(viewFeedItemImageVo3)
    viewFeedItemImageList3.add(viewFeedItemImageVo1)
    viewFeedItemImageList3.add(viewFeedItemImageVo2)
    val viewFeedVo3 = ViewFeedVo("bbbbbbbbb","bbbbbbbbb",viewFeedItemImageList3, "http:~~", false)
    viewFeedList.add(viewFeedVo3)

    val viewFeedItemImageList4 = ArrayList<ViewFeedItemImageVo>()
    viewFeedItemImageList4.add(viewFeedItemImageVo4)
    viewFeedItemImageList4.add(viewFeedItemImageVo2)
    viewFeedItemImageList4.add(viewFeedItemImageVo1)
    val viewFeedVo4 = ViewFeedVo("bbbbbbbbb","bbbbbbbbb",viewFeedItemImageList4, "http:~~", false)
    viewFeedList.add(viewFeedVo4)

    val viewFeedVo5 = ViewFeedVo("bbbbbbbbb","bbbbbbbbb",viewFeedItemImageList4, "http:~~", false)
    viewFeedList.add(viewFeedVo5)
    val viewFeedVo6 = ViewFeedVo("bbbbbbbbb","bbbbbbbbb",viewFeedItemImageList4, "http:~~", false)
    viewFeedList.add(viewFeedVo6)
    val viewFeedVo7 = ViewFeedVo("bbbbbbbbb","bbbbbbbbb",viewFeedItemImageList4, "http:~~", false)
    viewFeedList.add(viewFeedVo7)

  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.view_feed)

    setData()

    val viewFeed = findViewById<RecyclerView>(R.id.viewFeed)
    val viewFeedAdapter = ViewFeedAdapter(this, viewFeedList)
    viewFeed.adapter = viewFeedAdapter


  }
}