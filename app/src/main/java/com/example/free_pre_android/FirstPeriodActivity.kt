package com.example.free_pre_android

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.data.SignUpDTO
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
            signUp(true)
        }
        viewBinding.btnNo.setOnClickListener {
            signUp(false)
        }
    }

    fun signUp(first_period:Boolean){
        RetrofitBuilder.loginApi.signUp(SignUpDTO(email,nickname,first_period)).enqueue(object :Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    Toast.makeText(this@FirstPeriodActivity,"Sign Up was completed", Toast.LENGTH_SHORT).show()
                    if(first_period){//FREE
                        val intent =Intent(this@FirstPeriodActivity,RecentPeriodActivity::class.java)
                        Log.d("FIRST_PERIOD", "$nickname YES")
                        startActivity(intent)
                    }
                    else{//PRE
                        val intent =Intent(this@FirstPeriodActivity,PreActivity::class.java)
                        Log.d("FIRST_PERIOD", "$nickname NO")
                        startActivity(intent)
                    }
                }
                else{
                    //에러 처리
                    Log.e("FIRST_PERIOD","response fail")
                }
                Log.d("FISRT_PERIOD",response.message().toString())
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                //에러 처리
                Log.e("FIRST_PERIOD",t.message.toString())
            }
        })
    }
}
