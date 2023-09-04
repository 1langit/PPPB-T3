package com.arfdn.kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var txtView: TextView
    private var operand1 = ""
    private var operand2 = ""
    private var operatr = ""
    private var equalsClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtView = findViewById(R.id.txtField)
    }

    fun onDigitClick(view: View) {
        val digit = (view as Button).text.toString()
        if (operatr.isEmpty()) {
            if (equalsClicked) {
                onClearClick(view)
                equalsClicked = false
            }
            operand1 += digit
            updateResultText(operand1)
        } else {
            operand2 += digit
            updateResultText("$operand1 $operatr $operand2")
        }
    }

    fun onOperatorClick(view: View) {
        if (!operand1.isEmpty()) {
            if (!operand2.isEmpty()) {
                onEqualsClick(view)
            }
            if (operand1 != "error") {
                operatr = (view as Button).text.toString()
                updateResultText("$operand1 $operatr")
            }
        }
    }

    fun onEqualsClick(view: View) {
        if (!operand2.isEmpty()) {
            val result = when (operatr) {
                "+" -> operand1.toInt() + operand2.toInt()
                "-" -> operand1.toInt() - operand2.toInt()
                "*" -> operand1.toInt() * operand2.toInt()
                "/" -> {
                    if (operand2.toInt() == 0) {
                        "error"
                    } else {
                        operand1.toInt() / operand2.toInt()
                    }
                }
                else -> operand1
            }
            updateResultText(result.toString())
            operand1 = result.toString()
            operand2 = ""
            operatr = ""
            equalsClicked = true
        }
    }

    fun onClearClick(view: View) {
        operand1 = ""
        operand2 = ""
        operatr = ""
        updateResultText("")
    }

    private fun updateResultText(text: String) {
        txtView.text = text
    }
}