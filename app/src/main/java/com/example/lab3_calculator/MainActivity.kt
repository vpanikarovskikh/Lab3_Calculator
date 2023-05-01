package com.example.lab3_calculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lab3_calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var numberForPlusMinus = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button0.setOnClickListener {
            setTextFields("0")
            setMiddleFields("0")}
        binding.button1.setOnClickListener {
            setTextFields("1")
            setMiddleFields("1") }
        binding.button2.setOnClickListener {
            setTextFields("2")
            setMiddleFields("2") }
        binding.button3.setOnClickListener {
            setTextFields("3")
            setMiddleFields("3")}
        binding.button4.setOnClickListener {
            setTextFields("4")
            setMiddleFields("4")}
        binding.button5.setOnClickListener {
            setTextFields("5")
            setMiddleFields("5")}
        binding.button6.setOnClickListener {
            setTextFields("6")
            setMiddleFields("6") }
        binding.button7.setOnClickListener {
            setTextFields("7")
            setMiddleFields("7")}
        binding.button8.setOnClickListener {
            setTextFields("8")
            setMiddleFields("8")}
        binding.button9.setOnClickListener {
            setTextFields("9")
            setMiddleFields("9")}
        binding.buttonPoint.setOnClickListener {
            setTextFields(".")
            setMiddleFields(".")}

        binding.buttonPlus.setOnClickListener {
            changeColorMinusBack()
            changeColorMultiplyBack()
            changeColorDevideBack()
            onClick()
            changeColorPlusInp()
            setMiddleFields("+")}
        binding.buttonMinus.setOnClickListener {
            changeColorPlusBack()
            changeColorDevideBack()
            changeColorMultiplyBack()
            onClick()
            changeColorMinusInp()
            setMiddleFields("-")}
        binding.buttonMultiply.setOnClickListener {
            changeColorPlusBack()
            changeColorMinusBack()
            changeColorDevideBack()
            onClick()
            changeColorMultiplyInp()
            setMiddleFields("*")}
        binding.buttonDevide.setOnClickListener {
            changeColorPlusBack()
            changeColorMinusBack()
            changeColorMultiplyBack()
            onClick()
            changeColorDevideInp()
            setMiddleFields("/")}
        binding.buttonPlusminus.setOnClickListener {
            if (!(toArray(binding.middleField.text.toString()).last().isDigit())) {
                binding.middleField.append("-")
                numberForPlusMinus += 1
            }
            else {
                val beforeEdit = binding.resultField.text.toString().toDouble()
                val beforeEditLong = beforeEdit.toLong()
                if (beforeEdit == beforeEditLong.toDouble())
                    binding.resultField.text = (beforeEditLong * (-1)).toString()
                else
                    binding.resultField.text = (beforeEdit * (-1)).toString()
                binding.middleField.text = (binding.middleField.text.toString().toDouble() * (-1)).toString()
            }
        }
        binding.buttonPercent.setOnClickListener {
            val rFi = binding.resultField.text.toString().toDouble() / (100).toFloat()
            val mFi = binding.middleField.text.toString().toDouble() / (100).toFloat()
            binding.resultField.text = rFi.toFloat().toString()
            binding.middleField.text = mFi.toFloat().toString()
        }

        binding.buttonAc.setOnClickListener {
            changeColorPlusBack()
            changeColorMinusBack()
            changeColorMultiplyBack()
            changeColorDevideBack()
            binding.buttonAc.text = "AC"
            binding.resultField.text = "0"
            binding.middleField.text = ""
        }
        binding.buttonEquals.setOnClickListener {
            onClick()
        }
    }

    private fun onClick() {
        changeColorPlusBack()
        changeColorMinusBack()
        changeColorMultiplyBack()
        changeColorDevideBack()
        try {
            val ex = ExpressionBuilder(binding.middleField.text.toString()).build()
            val result = ex.evaluate()
            val longRes = result.toLong()
            if (result == longRes.toDouble()){
                binding.resultField.text = ""
                binding.middleField.text = ""
                binding.resultField.text = longRes.toString()
                binding.middleField.text = longRes.toString()
            }
            else {
                binding.resultField.text = result.toFloat().toString()
                binding.middleField.text = result.toFloat().toString()
            }
        }
        catch (e: Exception) {
            Log.d("Ошибка", "Сообщение: ${e.message}")
            if (e.message == "Division by zero!") {
                binding.middleField.text = ""
                binding.resultField.text = "Ошибка"
            }
        }
    }


    fun setMiddleFields(str: String) {
        binding.middleField.append(str)
    }
    fun setTextFields(str: String) {
        if (numberForPlusMinus == 1) {
            numberForPlusMinus = 0
            binding.resultField.text = ""
            binding.resultField.append("-")
            binding.resultField.append(str)
        } else if (binding.resultField.text.toString() == "0") {
            binding.resultField.text = ""
            binding.resultField.append(str)
        } else if (!(toArray(binding.middleField.text.toString()).last().isDigit()) &&
            toArray(binding.middleField.text.toString()).last() !== '.') {
            binding.resultField.text = ""
            binding.resultField.height = 250
            binding.resultField.append(str)
        }
        else {
            binding.resultField.append(str)
            println(toArray(binding.middleField.text.toString()))
        }

        if (binding.resultField.text.toString() !== "0")
            binding.buttonAc.text = "C"

    }

//    changing buttons' colors
    private fun changeColorPlusInp() {
        binding.buttonPlus.setBackgroundColor(resources.getColor(R.color.white))
        binding.buttonPlus.setTextColor(resources.getColor(R.color.orange))
    }
    private fun changeColorPlusBack() {
        binding.buttonPlus.setBackgroundColor(resources.getColor(R.color.orange))
        binding.buttonPlus.setTextColor(resources.getColor(R.color.white))
    }
    private fun changeColorMinusInp() {
        binding.buttonMinus.setBackgroundColor(resources.getColor(R.color.white))
        binding.buttonMinus.setTextColor(resources.getColor(R.color.orange))
    }
    private fun changeColorMinusBack() {
        binding.buttonMinus.setBackgroundColor(resources.getColor(R.color.orange))
        binding.buttonMinus.setTextColor(resources.getColor(R.color.white))
    }
    private fun changeColorMultiplyInp() {
        binding.buttonMultiply.setBackgroundColor(resources.getColor(R.color.white))
        binding.buttonMultiply.setTextColor(resources.getColor(R.color.orange))
    }
    private fun changeColorMultiplyBack() {
        binding.buttonMultiply.setBackgroundColor(resources.getColor(R.color.orange))
        binding.buttonMultiply.setTextColor(resources.getColor(R.color.white))
    }
    private fun changeColorDevideInp() {
        binding.buttonDevide.setBackgroundColor(resources.getColor(R.color.white))
        binding.buttonDevide.setTextColor(resources.getColor(R.color.orange))
    }
    private fun changeColorDevideBack() {
        binding.buttonDevide.setBackgroundColor(resources.getColor(R.color.orange))
        binding.buttonDevide.setTextColor(resources.getColor(R.color.white))
    }

//    string to array
    fun toArray(str: String): CharArray {
        return str.toCharArray()
    }
}