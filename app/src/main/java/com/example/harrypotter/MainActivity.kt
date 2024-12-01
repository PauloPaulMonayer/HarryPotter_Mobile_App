import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.harrypotter.R
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // מצביעים לאלמנטים ב-XML
        val btnSelectDate = findViewById<Button>(R.id.btn_select_date)
        val rgTicketType = findViewById<RadioGroup>(R.id.rg_ticket_type)
        val spTheatre = findViewById<Spinner>(R.id.sp_theatre)
        val btnGetTickets = findViewById<Button>(R.id.btn_get_tickets)
        val tvDescription = findViewById<TextView>(R.id.tv_description)

        // 1. תכונת בחירת תאריך
        btnSelectDate.setOnClickListener {
            showDatePickerDialog()
        }

        // 2. מילוי ספינר באולמות קולנוע
        val theatres = arrayOf("Theatre 1", "Theatre 2", "Theatre 3", "VIP Theatre")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, theatres)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spTheatre.adapter = adapter

        // 3. לחיצה על כפתור קבלת כרטיסים
        btnGetTickets.setOnClickListener {
            val selectedTicketType = when (rgTicketType.checkedRadioButtonId) {
                R.id.rb_adult -> "Adult Ticket"
                R.id.rb_child -> "Child Ticket"
                else -> "No ticket type selected"
            }
            val selectedTheatre = spTheatre.selectedItem.toString()

            if (selectedTicketType == "No ticket type selected") {
                Toast.makeText(this, "Please select a ticket type", Toast.LENGTH_SHORT).show()
            } else {
                val message = "Ticket Type: $selectedTicketType\nTheatre: $selectedTheatre"
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                Toast.makeText(this, "Selected date: $date", Toast.LENGTH_SHORT).show()
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}
