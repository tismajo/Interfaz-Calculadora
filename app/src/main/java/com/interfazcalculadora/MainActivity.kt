package com.interfazcalculadora

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var display: EditText
    private lateinit var calculator: ScientificCalculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)
        calculator = ScientificCalculator()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gridLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupButtons()
    }

    private fun setupButtons() {
        val buttonIds = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.buttonDot,
            R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide,
            R.id.buttonOpenP, R.id.buttonCloseP, R.id.buttonClear, R.id.buttonEqual
        )

        buttonIds.forEach { buttonId ->
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener { handleButtonClick(buttonId) }
        }
    }

    private fun handleButtonClick(buttonId: Int) {
        when (buttonId) {
            R.id.buttonClear -> display.text.clear()
            R.id.buttonEqual -> calculateResult()
            else -> {
                val button = findViewById<Button>(buttonId)
                display.append(button.text)
            }
        }
    }

    private fun calculateResult() {
        try {
            val expression = display.text.toString()
            val result = calculator.evaluate(expression)
            display.setText(result.toString())
        } catch (e: Exception) {
            display.setText("Error")
        }
    }
}
