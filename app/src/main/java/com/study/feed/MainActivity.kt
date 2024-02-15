package com.study.feed

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.study.feed.MainActivity.Companion.responsiveHeight
import com.study.feed.layout.Layout1
import com.study.feed.layout.Layout2

class MainActivity : AppCompatActivity() {

    companion object {
        var widthRate: Float = 0.0f
        var heightRate: Float = 0.0f

//        fun View.margin(left: Float, top: Float, right: Float, bottom: Float) {
//            layoutParams<ViewGroup.MarginLayoutParams> {
//                left.run { leftMargin = context }
//            }
//        }

//        fun Int.responsiveHeight(): Int = (this * heightRate * Resources.getSystem().displayMetrics.density).toInt()
//        fun Int.responsiveWidth(): Int = (this * widthRate * Resources.getSystem().displayMetrics.density).toInt()

        fun Int.responsiveHeight(): Int {
            println("==============HEIGHT START==============")
            println(this)
            println(heightRate)
//            println(Resources.getSystem().displayMetrics.density)
            println(this * heightRate)
            println((this * heightRate).toInt())
            println("============== HEIGHT END ==============")

            return (this * heightRate).toInt()
        }
        fun Int.responsiveWidth(): Int {
            println("==============WIDTH START==============")
            println(this)
            println(widthRate)
//            println(Resources.getSystem().displayMetrics.density)
            println(this * widthRate)
            println((this * widthRate).toInt())
            println("============== WIDTH END ==============")

            return (this * widthRate).toInt()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val display = this.applicationContext.resources.displayMetrics
        val deviceWidth = display.widthPixels
        val deviceHeight = display.heightPixels

        // height : 720
        widthRate = deviceWidth / 360.0f
        heightRate = deviceHeight / 640.0f

        println("====")
        println(deviceWidth)
        println(deviceHeight)
        println(widthRate)
        println(heightRate)
        println("====")

        val layoutButton1 : Button = findViewById(R.id.layoutButton1)
        layoutButton1.setOnClickListener {
            val intent = Intent(this, Layout1::class.java)
            startActivity(intent)
        }

        val layoutButton2 : Button = findViewById(R.id.layoutButton2)
        layoutButton2.setOnClickListener {
            val intent = Intent(this, Layout2::class.java)
            startActivity(intent)
        }
    }

    fun devicePx(px: Float, widthOrHeight: String): Float {
        val display = this.applicationContext.resources.displayMetrics
        val deviceWidth = display.widthPixels
        val deviceHeight = display.heightPixels
        val deviceDensity = display.densityDpi

        val widthRate = deviceWidth / 360f
        val heightRate = deviceHeight / 720f

        val returnPx = if (widthOrHeight == "w") {
            px * widthRate
        } else {
            px * heightRate
        }
        return returnPx
    }
}