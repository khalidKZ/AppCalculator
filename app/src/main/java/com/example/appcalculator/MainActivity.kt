package com.example.appcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvBtn:TextView? = null
     var lastNumeric:Boolean = false
     var lastDot:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      tvBtn = findViewById(R.id.tvBtn)

    }
//    خاص بي الارقام
    fun onDigit(view:View){
        tvBtn?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }
//    خاص بي الحذف
    fun onClear(view: View){
        tvBtn?.text = ""
    }
    fun onEqual(view: View){
        if (lastNumeric){
            var tvValue = tvBtn?.text.toString()
            var prefix = ""
            try {

                if (tvValue.startsWith("-")){
                    prefix = "-"
//               substring تتخلص من اول قيمة في التكست فيو
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){

 //                دالة سبلت تقسم النص بين علامة سالب او العلامة المكتوبة داخل القوسين
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvBtn?.text =removeZeroAfterDot( (one.toDouble() - two.toDouble()).toString())

                }else if (tvValue.contains("+")){

                    //                دالة سبلت تقسم النص بين علامة سالب او العلامة المكتوبة داخل القوسين
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvBtn?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                }else if (tvValue.contains("/")){

                    //                دالة سبلت تقسم النص بين علامة سالب او العلامة المكتوبة داخل القوسين
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvBtn?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                }else if (tvValue.contains("*")){

                    //                دالة سبلت تقسم النص بين علامة سالب او العلامة المكتوبة داخل القوسين
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvBtn?.text =removeZeroAfterDot( (one.toDouble() * two.toDouble()).toString())
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }
//    خاصة بي حذف الدوت و الصفر بعد تنفيذ العملية الحسابية
    fun removeZeroAfterDot(result : String):String{
     var value = result
        if (result.contains(".0")){
            value = result.substring(0,result.length -2)
        }
        return value
    }
//     خاص ب زر الدوت والهدف منه يمنعني من اني اكرر الدوت ورا بعض
    fun onDecimalPoint(view: View){
        if (lastNumeric && !lastDot){
            tvBtn?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
//    تمنع من تكرار عمليتين حسابية مثل ++ -- **
    fun onOperator(view: View){
        tvBtn?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())){
                tvBtn?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }
//    تمنع من كتابت سالب قبل الرقم
    private fun isOperatorAdded(value:String):Boolean{
        return if (value.startsWith("-")){
            false
        }else{
                      value.contains("-")
                    ||value.contains("+")
                    ||value.contains("/")
                    ||value.contains("*")
        }
    }
}