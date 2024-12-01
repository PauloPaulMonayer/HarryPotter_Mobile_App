package com.example.harrypotter

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class TicketsActivity : AppCompatActivity() {

    private lateinit var quantitySpinner: Spinner
    private lateinit var ticketTypeGroup: RadioGroup
    private lateinit var theatreSpinner: Spinner
    private lateinit var dateButton: Button
    private lateinit var totalPriceText: TextView
    private lateinit var getTicketsButton: Button


    companion object {
        val ticketsList = mutableListOf<String>()
    }

    private val quantities = listOf(1, 2, 3, 4, 5)
    private val theatres = listOf("Theatre 1", "Theatre 2", "Theatre 3")
    private val adultPrice = 15
    private val childPrice = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)

        // חיבור אלמנטים
        quantitySpinner = findViewById(R.id.sp_quantity)
        ticketTypeGroup = findViewById(R.id.rg_ticket_type)
        theatreSpinner = findViewById(R.id.sp_theatre)
        dateButton = findViewById(R.id.btn_select_date)
        totalPriceText = findViewById(R.id.tv_total_price)
        getTicketsButton = findViewById(R.id.btn_get_tickets)


        quantitySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, quantities)
        theatreSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, theatres)


        dateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    dateButton.text = "$dayOfMonth/${month + 1}/$year"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


        quantitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calculatePrice()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        ticketTypeGroup.setOnCheckedChangeListener { _, _ -> calculatePrice() }


        getTicketsButton.setOnClickListener {
            val selectedQuantity = quantities[quantitySpinner.selectedItemPosition]
            val selectedType = if (ticketTypeGroup.checkedRadioButtonId == R.id.rb_adult) "Adult" else "Child"
            val selectedTheatre = theatreSpinner.selectedItem.toString()
            val selectedDate = dateButton.text.toString()

            if (selectedDate.isBlank() || selectedTheatre.isBlank()) {
                Toast.makeText(this, "Please select a date and theatre.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val ticketDetails =
                "Type: $selectedType, Quantity: $selectedQuantity, Theatre: $selectedTheatre, Date: $selectedDate"
            ticketsList.add(ticketDetails)


            showPreviewDialog(ticketDetails)
        }
    }


    private fun calculatePrice() {
        val quantity = quantities[quantitySpinner.selectedItemPosition]
        val ticketTypePrice = when (ticketTypeGroup.checkedRadioButtonId) {
            R.id.rb_adult -> adultPrice
            R.id.rb_child -> childPrice
            else -> 0
        }
        val totalPrice = quantity * ticketTypePrice
        totalPriceText.text = "Total Price: $$totalPrice"
    }


    private fun showPreviewDialog(ticketDetails: String) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_ticket_preview)

        val tvDetails = dialog.findViewById<TextView>(R.id.tv_ticket_details)
        tvDetails.text = ticketDetails

        val btnConfirm = dialog.findViewById<Button>(R.id.btn_confirm)
        val btnCancel = dialog.findViewById<Button>(R.id.btn_cancel)

        btnConfirm.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this, "Ticket confirmed!", Toast.LENGTH_SHORT).show()


            val intent = Intent(this, TicketsViewActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out)
        }

        btnCancel.setOnClickListener { dialog.dismiss() }


        dialog.window?.attributes?.windowAnimations = R.style.DialogBounceAnimation
        dialog.show()
    }


    private fun animateButton(button: Button) {
        val moveAnimation = AnimationUtils.loadAnimation(this, R.anim.move_up_down)
        val fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_out)

        button.startAnimation(moveAnimation)
        button.startAnimation(fadeAnimation)
    }
}
