package com.example.survcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import com.example.survcalc.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    lateinit var option : Spinner
    lateinit var result1: TextView
    lateinit var result2: TextView
    lateinit var mass: TextView
    lateinit var img: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            if (binding.textInput.text.isEmpty())
                Toast.makeText(applicationContext, "Поле с выхлопом пустое", Toast.LENGTH_LONG).show()
            else
            calculatingBbs()
        }

        option = findViewById(R.id.sp_mass) as Spinner
        result1 = findViewById(R.id.result_text1) as TextView
        result2 = findViewById(R.id.result_text2) as TextView
        mass = findViewById(R.id.massText) as TextView
        img = findViewById(R.id.img) as ImageView

        val options = arrayOf("0.2","0.23","0.25","0.28","0.3","0.32","0.36","0.4","0.43","0.45","0.48","0.5")

        val editText:EditText = findViewById(R.id.textInput) as EditText
        val button:Button = findViewById(R.id.button) as Button



        option.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, Id: Long) {
                mass.text = options.get(position)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                mass.text = "Выбери массу шара"
            }

        }
    }

    private fun calculatingBbs() {

        val speed = binding.textInput.text.toString().toDouble()
        val mass = binding.spMass.selectedItem.toString().toDouble()

        var Pow = 0.5 * speed * speed * mass / 1000
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        val roundoff = df.format(Pow)

        var Mps = sqrt(Pow*2*1000/0.2)
        val roundoff2 = (Mps*1000.0).roundToInt() / 1000.0



        binding.resultText1.text = "$roundoff Дж"

        binding.resultText2.text = "$roundoff2 м/с"
        if (Mps>149)
            binding.img.setImageResource(R.drawable.boink)
        else
            binding.img.setImageResource(R.drawable.harosh)
    }


}

