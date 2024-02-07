package com.study.feed

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.study.feed.layout.Layout1

class MainActivity : AppCompatActivity() {

    companion object {
        var widthRate: Float = 0.0f
        var heightRate: Float = 0.0f

//        fun View.margin(left: Float, top: Float, right: Float, bottom: Float) {
//            layoutParams<ViewGroup.MarginLayoutParams> {
//                left.run { leftMargin = context }
//            }
//        }

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
        println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        println(deviceWidth)
        println(widthRate)
        println(deviceDensity)
        println(returnPx)
        println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        return returnPx
    }
}