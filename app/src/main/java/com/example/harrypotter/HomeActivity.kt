package com.example.harrypotter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.harrypotter.GalleryActivity
import com.example.harrypotter.InfoActivity
import com.example.harrypotter.TicketsActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val ticketsList = arrayListOf(
            "Ticket 1: Harry Potter - 10:00 AM",
            "Ticket 2: Harry Potter - 12:00 PM",
            "Ticket 3: Harry Potter - 3:00 PM"
        )


        // הפניות לכפתורים
        val infoButton: Button = findViewById(R.id.infoButton)
        val galleryButton: Button = findViewById(R.id.galleryButton)
        val ticketsButton: Button = findViewById(R.id.ticketsButton)
        val viewTicketsButton: Button = findViewById(R.id.MyticketsButton)

        // מעבר לעמוד המידע
        infoButton.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }

        // מעבר לעמוד הגלריה
        galleryButton.setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
        }

        // מעבר לעמוד כרטיסים
        ticketsButton.setOnClickListener {
            val intent = Intent(this, TicketsActivity::class.java)
            startActivity(intent)
        }

        // מעבר לעמוד צפייה בכרטיסים
        viewTicketsButton.setOnClickListener {
            val intent = Intent(this, TicketsViewActivity::class.java)
            intent.putStringArrayListExtra("tickets", ticketsList) // העברת רשימת הכרטיסים
            startActivity(intent)
        }
    }
}
