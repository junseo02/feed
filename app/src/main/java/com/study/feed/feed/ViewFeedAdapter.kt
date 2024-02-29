package com.study.feed.feed

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.Image
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.study.feed.MainActivity.Companion.responsiveHeight
import com.study.feed.MainActivity.Companion.responsiveWidth
import com.study.feed.R
import com.study.feed.layout.Layout1

class ViewFeedAdapter(val context: Context, private val viewFeedList: ArrayList<ViewFeedVo>):RecyclerView.Adapter<ViewFeedAdapter.ViewHolder>() {

  inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val textView = view.findViewById<TextView>(R.id.viewFeedItemText)
    private val viewFeedItemImage = view.findViewById<ViewPager2>(R.id.viewFeedItemImage)
    private val linearLayout = view.findViewById<LinearLayout>(R.id.indicator)
    private val viewFeedItemExport = view.findViewById<ImageButton>(R.id.viewFeedItemExport)
    private val viewFeedItemHeart = view.findViewById<ImageButton>(R.id.viewFeedItemHeart)

    private val viewFeedItem = view.findViewById<ConstraintLayout>(R.id.viewFeedItem)

    fun bind(viewFeedVo: ViewFeedVo, context: Context) {
      linearLayout?.removeAllViews()

      val viewFeedItemLayoutParams = viewFeedItem.layoutParams as RecyclerView.LayoutParams
      viewFeedItemLayoutParams.topMargin = 8.responsiveHeight()
      viewFeedItem.layoutParams = viewFeedItemLayoutParams
      viewFeedItem.setPadding(14.responsiveWidth(),15.responsiveHeight(),14.responsiveWidth(),12.responsiveHeight())

      val viewFeedItemImageLayoutParams = viewFeedItemImage.layoutParams
      viewFeedItemImageLayoutParams.height = 152.responsiveHeight()
      viewFeedItemImage.layoutParams = viewFeedItemImageLayoutParams

      // 이미지 slide
      val viewFeedItemImageAdapter = ViewFeedItemImageAdapter(context, viewFeedVo.viewFeedItemImageList)
      viewFeedItemImage?.offscreenPageLimit = viewFeedVo.viewFeedItemImageList.size
      viewFeedItemImage?.adapter = viewFeedItemImageAdapter
      viewFeedItemImage?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
          super.onPageSelected(position)
          currentIndicator(position)
        }
      })

      // 이미지 indicator
      for (i:Int in 1..viewFeedVo.viewFeedItemImageList.size) {
        val imageView: ImageView = ImageView(context)
        imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ellipse_4))
        val params = LinearLayout.LayoutParams(5.responsiveWidth(), 5.responsiveWidth())
        params.setMargins(2.responsiveWidth(),0,2.responsiveWidth(),0)
        imageView.layoutParams = params
        linearLayout?.addView(imageView)
      }
      currentIndicator(0)

      // heart
      val viewFeedItemHeartLayoutParams = viewFeedItemHeart.layoutParams as ConstraintLayout.LayoutParams
      viewFeedItemHeartLayoutParams.width = 30.responsiveWidth()
      viewFeedItemHeartLayoutParams.height = 30.responsiveWidth()
      viewFeedItemHeart.layoutParams = viewFeedItemHeartLayoutParams

      heartColor(viewFeedVo.viewFeedItemHeart)
      viewFeedItemHeart?.setOnClickListener {
        viewFeedVo.viewFeedItemHeart = !viewFeedVo.viewFeedItemHeart
        heartColor(viewFeedVo.viewFeedItemHeart)
      }


      // export
      val viewFeedItemExportLayoutParams = viewFeedItemExport.layoutParams as ConstraintLayout.LayoutParams
      viewFeedItemExportLayoutParams.width = 30.responsiveWidth()
      viewFeedItemExportLayoutParams.height = 30.responsiveWidth()
      viewFeedItemExport.layoutParams = viewFeedItemExportLayoutParams

      viewFeedItemExport?.setOnClickListener {
        val intent = Intent(Intent.ACTION_SEND_MULTIPLE)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_HTML_TEXT, viewFeedVo.viewFeedItemExport)
        ContextCompat.startActivity(context ,Intent.createChooser(intent, "공유"), null)
      }

      // text
      if (viewFeedVo.originalText.length > 35) {
        viewFeedVo.viewText = viewFeedVo.originalText.substring(0 until 35) + " ... 더보기"
        val moreText: SpannableString = SpannableString(viewFeedVo.viewText)
        // ...까지 같은 색상
        moreText.setSpan(ForegroundColorSpan(Color.parseColor("#656565")), 0, viewFeedVo.viewText.length-4 , Spanned.SPAN_INTERMEDIATE)
        // 더보기 색상
        moreText.setSpan(ForegroundColorSpan(Color.parseColor("#888888")), viewFeedVo.viewText.length-3, viewFeedVo.viewText.length , Spanned.SPAN_INTERMEDIATE)
        textView?.text = moreText

        textView?.setOnClickListener {
          if (textView.text.indexOf("더보기") != -1) {
            textView.text = viewFeedVo.originalText
            textView.maxLines = 100
          } else {
            val intent = Intent(context, Post::class.java)

            intent.putExtra("originalText", viewFeedVo.originalText)
            intent.putExtra("viewText", viewFeedVo.viewText)

            val itemImageList:ArrayList<String> = ArrayList()
            for (i:Int in 0 until viewFeedVo.viewFeedItemImageList.size) {
              itemImageList.add(viewFeedVo.viewFeedItemImageList[i].image)
            }
            intent.putExtra("viewFeedItemImageList", itemImageList)

            intent.putExtra("viewFeedItemExport", viewFeedVo.viewFeedItemExport)
            intent.putExtra("viewFeedItemHeart", viewFeedVo.viewFeedItemHeart)


            ContextCompat.startActivity(context, intent, null)
          }
        }
      } else {
        textView?.text = viewFeedVo.viewText

        textView?.setOnClickListener {
          val intent = Intent(context, Post::class.java)

          intent.putExtra("originalText", viewFeedVo.originalText)
          intent.putExtra("viewText", viewFeedVo.viewText)

          val itemImageList:ArrayList<String> = ArrayList()
          for (i:Int in 0 until viewFeedVo.viewFeedItemImageList.size) {
            itemImageList.add(viewFeedVo.viewFeedItemImageList[i].image)
          }
          intent.putExtra("viewFeedItemImageList", itemImageList)

          intent.putExtra("viewFeedItemExport", viewFeedVo.viewFeedItemExport)
          intent.putExtra("viewFeedItemHeart", viewFeedVo.viewFeedItemHeart)


          ContextCompat.startActivity(context, intent, null)

        }
      }
    }

    fun currentIndicator(position: Int) {
      val child = linearLayout?.childCount
      for (i:Int in 0 until child!!) {
        val imageView:ImageView = linearLayout?.getChildAt(i) as ImageView
        if (i == position) {
          imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ellipse_4))
          imageView.setColorFilter(ContextCompat.getColor(context, R.color.darkGray), PorterDuff.Mode.SRC_IN)
        } else {
          imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ellipse_4))
          imageView.setColorFilter(ContextCompat.getColor(context, R.color.gray2Background), PorterDuff.Mode.SRC_IN)
        }
      }
    }

    fun heartColor(boolean: Boolean) {
      if (boolean) {
        viewFeedItemHeart?.setColorFilter(ContextCompat.getColor(context, R.color.red), PorterDuff.Mode.SRC_IN)
      } else {
        viewFeedItemHeart?.setColorFilter(ContextCompat.getColor(context, R.color.black), PorterDuff.Mode.SRC_IN)
      }
    }

  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewFeedAdapter.ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.view_feed_item, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewFeedAdapter.ViewHolder, position: Int) {
    holder.bind(viewFeedList[position], context)
  }

  override fun getItemCount(): Int {
    return viewFeedList.size
  }
}