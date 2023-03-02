package com.example.free_pre_android

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.data.preSignUpDTO
import com.example.free_pre_android.databinding.ActivityFirstPeriodBinding
import com.example.free_pre_android.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstPeriodActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFirstPeriodBinding
    var email=""
    var nickname=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFirstPeriodBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val sharedPreferences: SharedPreferences = getSharedPreferences("Email", Activity.MODE_PRIVATE)
        email = sharedPreferences.getString("emailKey","there's no email").toString()
        nickname=intent.getStringExtra("nickname").toString()

        clickBtn()
    }

    fun clickBtn(){
        viewBinding.btnYes.setOnClickListener {
            if(signUp(true)){
                val intent = Intent(this,RecentPeriodActivity::class.java)
                Log.d("FIRST_PERIOD", "$nickname YES")
                startActivity(intent)
            }
            else{
                Log.e("FIRST_PERIOD","FREE 회원가입 오류")
            }

        }
        viewBinding.btnNo.setOnClickListener {

            //회원가입 api 연결
            if(signUp(false)){
                val intent = Intent(this,PreActivity::class.java)
                Log.d("FIRST_PERIOD", "$nickname NO")
                startActivity(intent)
            }
            else{
                Log.e("FIRST_PERIOD","PRE 회원가입 오류")
            }
        }
    }

    fun signUp(first_period:Boolean):Boolean{
        var result=false
        RetrofitBuilder.loginApi.preSignUp(preSignUpDTO(email,nickname,first_period)).enqueue(object :Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    result=true
                    Toast.makeText(this@FirstPeriodActivity,"Sign Up was completed", Toast.LENGTH_SHORT).show()
                }
                Log.d("FIRST_PERIOD",response.message().toString())
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("FIRST_PERIOD",t.message.toString())
            }
        })
        return result
    }
}
