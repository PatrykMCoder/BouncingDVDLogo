package com.pmprogramms.bouncingdvdlogo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.ImageView
import java.util.*

class MainActivity : AppCompatActivity() {
    private var screenWidth: Int = 0
    private var screenHeight: Int = 0

    private var imageWidth: Int = 0
    private var imageHeight: Int = 0

    private var speedMoveX = 10
    private var speedMoveY = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getScreenData()

        val image = findViewById<ImageView>(R.id.image)
        imageWidth = image.maxWidth
        imageHeight = image.maxHeight
        setColor(image)

        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
              moveImage(image)
            }
        }, 0,  100)

    }

    private fun moveImage(image: ImageView) {
        image.x = image.x + speedMoveX
        image.y = image.y + speedMoveY

        if (image.x >= screenWidth - imageWidth || image.x <= 0) {
            speedMoveX = -speedMoveX
            setColor(image)
        }

        if (image.y  >= screenHeight.toFloat() - imageHeight || image.y <= 0) {
            speedMoveY = -speedMoveY
            setColor(image)
        }

    }

    private fun setColor(image: ImageView) {
        val random = Random()
        val r = (random.nextFloat())
        val g = (random.nextFloat())
        val b = (random.nextFloat())

        image.setColorFilter(Color.argb(100f, r,g, b))
    }

    private fun getScreenData() {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        screenWidth = displayMetrics.widthPixels
        screenHeight = displayMetrics.heightPixels
    }
}