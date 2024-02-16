package com.study.feed

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.study.feed.layout.Layout1
import com.study.feed.layout.Layout2

class MainActivity : AppCompatActivity() {

    companion object {
        var widthRate: Float = 0.0f
        var heightRate: Float = 0.0f

        fun Int.responsiveHeight(): Int = (this * heightRate).toInt()
        fun Int.responsiveWidth(): Int = (this * widthRate).toInt()
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
}