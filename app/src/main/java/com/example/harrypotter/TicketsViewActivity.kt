package com.example.harrypotter

import android.os.Build
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.widget.TextView
import androidx.annotation.RequiresApi

class TicketsViewActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets_view)

        val ticketsContainer: LinearLayout = findViewById(R.id.ll_tickets_container)
        val ticketsList = TicketsActivity.ticketsList

        // הוספת כרטיסים עם עיצוב CardView
        for (ticket in ticketsList) {
            val cardView = CardView(this)
            cardView.setCardBackgroundColor(getColor(R.color.secondary_light_text))
            cardView.radius = 16f
            cardView.elevation = 8f
            cardView.setContentPadding(16, 16, 16, 16)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 0, 0, 24) // ריווח בין כרטיסים
            cardView.layoutParams = layoutParams

            // תוכן הכרטיס
            val ticketText = TextView(this)
            ticketText.text = ticket
            ticketText.textSize = 18f
            ticketText.setTextColor(getColor(R.color.primary_light_text))
            ticketText.setPadding(16, 16, 16, 16)

            cardView.addView(ticketText)
            ticketsContainer.addView(cardView)
        }
    }
}
