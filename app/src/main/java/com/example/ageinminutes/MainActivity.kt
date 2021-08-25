package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
            tvSelectedDate.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate!!.time

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time
            val difference = currentDateInMinutes - selectedDateInMinutes

            val seconds = TimeUnit.MILLISECONDS.toSeconds(difference)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(difference)
            val hours = TimeUnit.MILLISECONDS.toHours(difference)
            val days = TimeUnit.MILLISECONDS.toDays(difference)

            tvSelectedDateInSeconds.text = seconds.toString()
            tvSelectedDateInMinutes.text = minutes.toString()
            tvSelectedDateInHours.text = hours.toString()
            tvSelectedDateInDays.text = days.toString()
        }
            , year
            ,month
            ,day)

        dpd.datePicker.maxDate = Date().time
        dpd.show()
    }
}