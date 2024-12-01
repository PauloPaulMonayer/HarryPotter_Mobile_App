package com.example.harrypotter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        // רשימת התמונות (מזהי Drawable)
        val imageList = listOf(
            R.drawable.harry1,
            R.drawable.harry2,
            R.drawable.harry3,
            R.drawable.harry4,
            R.drawable.harry5,
            R.drawable.harry6,
            R.drawable.harry7,
            R.drawable.harry8,
            R.drawable.harry9,
            R.drawable.harry10,
            R.drawable.harry11,
            R.drawable.harry12,
            R.drawable.harry14,
            R.drawable.harry15,
            R.drawable.harry16,
            R.drawable.harry17,
            R.drawable.harry18,
            R.drawable.harry19,
        )

        // הגדרת ה-ViewPager2 וה-Adapter
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = GalleryPagerAdapter(imageList)
    }
}
