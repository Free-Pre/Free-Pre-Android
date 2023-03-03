package com.example.free_pre_android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityProductsBinding

class ProductsActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBindingRun()
    }

    fun viewBindingRun(){
        viewBinding.run {
            textSanitaryPad.setOnClickListener {
                startActivity(Intent(this@ProductsActivity,SanitaryPadActivity::class.java))
            }
            textTampon.setOnClickListener {
                startActivity(Intent(this@ProductsActivity,TamponActivity::class.java))
            }
            textMenstrualCup.setOnClickListener {
                startActivity(Intent(this@ProductsActivity,MenstrualCupActivity::class.java))
            }
            textPeriodUnderwear.setOnClickListener {
                startActivity(Intent(this@ProductsActivity,PeriodUnderwearActivity::class.java))
            }
        }
    }
}