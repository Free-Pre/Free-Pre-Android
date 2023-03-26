package com.example.free_pre_android

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.adapter.SymptomAdapter
import com.example.free_pre_android.data.SymptomCheckDTO
import com.example.free_pre_android.data.SymptomGetDTO
import com.example.free_pre_android.data.SymptomModifyDTO
import com.example.free_pre_android.databinding.ActivitySymptomBinding
import com.example.free_pre_android.model.GlobalVariable
import com.example.free_pre_android.model.SymptomData
import com.example.free_pre_android.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SymptomActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySymptomBinding
    private lateinit var symptomAdapter: SymptomAdapter
    private lateinit var dataList: ArrayList<SymptomData>


    private var email = ""
    private var date = ""
    private var vomit: Boolean = false
    private var headache: Boolean = false
    private var backache: Boolean = false
    private var constipation: Boolean = false
    private var giddiness: Boolean = false
    private var tiredness: Boolean = false
    private var fainting: Boolean = false
    private var sensitivity: Boolean = false
    private var acne: Boolean = false
    private var muscular_pain: Boolean = false

    private var clicked: Boolean = false
    public var selectedSymptom = arrayListOf<SymptomData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySymptomBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //viewBindingRun()

        //사용자 이메일 가져오기
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("Email", Activity.MODE_PRIVATE)
        email = sharedPreferences.getString("emailKey", "there's no email")
            .toString()               //로그인 되어있는 유저의 이메일
        Log.d("symptomTest", email)

        //사용자가 선택한 날짜 가져오기
        val sharedPreferencesDate: SharedPreferences =
            this.getSharedPreferences("selected_date", Activity.MODE_PRIVATE)
        date = sharedPreferencesDate.getString("selectDate", "No date selected.")
            .toString()               //선택한 날짜
        Log.d("symptomTest", date)


        //viewBindingRun()보다 코드 간결 - 이거 사용하자.
        /*
        val textViewList = listOf<TextView>(
            viewBinding.textVomit,
            viewBinding.textHeadache,
            viewBinding.textBackache,
            viewBinding.textConstipation,
            viewBinding.textGiddiness,
            viewBinding.textTiredness,
            viewBinding.textFainting,
            viewBinding.textSensitivity,
            viewBinding.textAcne,
            viewBinding.textMuscularPain)
        val isSelectedArray = BooleanArray(textViewList.size)      //텍스트의 선택여부 저장
        Log.d("symptomTest","선택전: ${isSelectedArray[0]}")

        for ((index, textView) in textViewList.withIndex()) {
            Log.d("symptomTest","vomit은: ${vomit}")
            textView.setOnClickListener {
                isSelectedArray[index] = !isSelectedArray[index]
                //textView.setBackgroundColor(if (isSelectedArray[index]) {resources.getColor(R.color.primary_light)} else resources.getColor(R.color.primary_dark))
                textView.setBackgroundResource(if (isSelectedArray[index]) {R.drawable.style_symptom_content_dark } else R.drawable.style_symptom_content)
                textView.setTextColor(if (isSelectedArray[index]) resources.getColor(R.color.primary_dark) else resources.getColor(R.color.primary_light))
                //textView.setBackgroundResource(R.drawable.style_symptom_content)
                vomit = isSelectedArray[0]
                headache = isSelectedArray[1]
                backache = isSelectedArray[2]
                constipation = isSelectedArray[3]
                giddiness = isSelectedArray[4]
                tiredness = isSelectedArray[5]
                fainting = isSelectedArray[6]
                sensitivity = isSelectedArray[7]
                acne = isSelectedArray[8]
                muscular_pain = isSelectedArray[9]
                Log.d("symptomTest","isSelectedArray의 ${index}번째 원소는: ${isSelectedArray[index]}")
            }
        }

        viewBinding.btnSave.setOnClickListener {
            // 액티비티1에서 변수를 전달하는 코드
            val intent = Intent(this@SymptomActivity, FreeActivity::class.java)
            //intent.putExtra("boolean", true)
            startActivity(intent)
            (applicationContext as GlobalVariable).boolean = true           //전역변수

            SymptomCheck()                                                  //입력한 증상 서버에 보내기
        }*/


        viewBindingRun()
        GetSymptoms(email,date)     //기록 된 증상 가져오기.

    }


    fun viewBindingRun() {

        viewBinding.run {
            textVomit.setOnClickListener {
                vomit = !vomit
                checkDeco(vomit, textVomit)
            }
            textHeadache.setOnClickListener {
                headache = !headache
                checkDeco(headache, textHeadache)
            }
            textBackache.setOnClickListener {
                backache = !backache
                checkDeco(backache, textBackache)
            }
            textConstipation.setOnClickListener {
                constipation = !constipation
                checkDeco(constipation, textConstipation)
            }
            textGiddiness.setOnClickListener {
                giddiness = !giddiness
                checkDeco(giddiness, textGiddiness)
            }
            textTiredness.setOnClickListener {
                tiredness = !tiredness
                checkDeco(tiredness, textTiredness)
            }
            textFainting.setOnClickListener {
                fainting = !fainting
                checkDeco(fainting, textFainting)
            }
            textSensitivity.setOnClickListener {
                sensitivity = !sensitivity
                checkDeco(sensitivity, textSensitivity)
            }
            textAcne.setOnClickListener {
                acne = !acne
                checkDeco(acne, textAcne)
            }
            textMuscularPain.setOnClickListener {
                muscular_pain = !muscular_pain
                checkDeco(muscular_pain, textMuscularPain)
            }

        }
    }

    //증상 선택시 데코
    fun checkDeco(b: Boolean, symptom: TextView) {
        if (b) {
            symptom.setBackgroundResource(R.drawable.style_symptom_content_dark)
            symptom.setTextColor(Color.parseColor("#1A2A46"))
        } else {
            symptom.setBackgroundResource(R.drawable.style_symptom_content)
            symptom.setTextColor(Color.parseColor("#FDE3F4"))
        }
    }

    //email : 사용자 이메일
    //data : 사용자가 누른 날짜
    //증상 입력 서버 연결
    fun SymptomCheck() {
        val dataInfo = SymptomCheckDTO(
            email,
            date,
            vomit,
            headache,
            backache,
            constipation,
            giddiness,
            tiredness,
            fainting,
            sensitivity,
            acne,
            muscular_pain
        )
        // API 호출
        RetrofitBuilder.symptomApi.symptomCheck(dataInfo).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                //Log.d("Symptom11",response.body().toString())    //어차피 null 값이라..
                if (response.isSuccessful) {
                    // 응답이 성공적으로 왔을 때 처리할 내용
                    Log.d("SymptomTest", response.body().toString())      //void니까 null값!!
                    Log.d("SymptomTest", "연결성공")
                } else {
                    // 응답이 실패한 경우
                    // 에러 메시지 출력 등의 처리 수행
                    Log.d("SymptomTest", "연결미스")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // 네트워크 오류 등으로 인해 요청이 실패한 경우
                // 에러 메시지 출력 등의 처리 수행
                Log.e("SymptomTest", t.message.toString())
            }
        })
    }

    //email : 사용자 이메일
    //data : 사용자가 누른 날짜 (선택한 날짜)
    //증상 수정
    fun SymptomModify(email:String, date:String) {
        val dataInfo = SymptomModifyDTO(
            vomit,
            headache,
            backache,
            constipation,
            giddiness,
            tiredness,
            fainting,
            sensitivity,
            acne,
            muscular_pain
        )
        // API 호출
        RetrofitBuilder.symptomApi.symptomModify(email,date,dataInfo).enqueue(object : Callback<SymptomModifyDTO> {
            override fun onResponse(call: Call<SymptomModifyDTO>, response: Response<SymptomModifyDTO>) {
                //Log.d("Symptom11",response.body().toString())
                if (response.isSuccessful) {
                    // 응답이 성공적으로 왔을 때 처리할 내용
                    Log.d("SymptomModify", response.body().toString())
                    Log.d("SymptomModify", "연결성공")
                } else {
                    // 응답이 실패한 경우
                    // 에러 메시지 출력 등의 처리 수행
                    Log.d("SymptomModify", "연결미스")
                    Log.d("SymptomModify", response.toString())
                }
            }

            override fun onFailure(call: Call<SymptomModifyDTO>, t: Throwable) {
                // 네트워크 오류 등으로 인해 요청이 실패한 경우
                // 에러 메시지 출력 등의 처리 수행
                Log.e("SymptomModify", t.message.toString())
            }
        })
    }


    //증상 정보 가져오기
    fun GetSymptoms(
        email: String,
        date: String,
    ) {
        // API 호출
        RetrofitBuilder.symptomApi.symptomGet(email, date)
            .enqueue(object : Callback<SymptomGetDTO> {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onResponse(
                    call: Call<SymptomGetDTO>,
                    response: Response<SymptomGetDTO>
                ) {
                    Log.d("GetSymptomsActivity", response.body().toString())

                    if (response.isSuccessful) {
                        // 응답이 성공적으로 왔을 때 처리할 내용
                        val resultSymptom: SymptomGetDTO? = response.body()
                        resultSymptom?.let {
                            Log.d("GetSymptomsActivity", "연결성공")


                            if(resultSymptom.result == null){
                                Toast.makeText(this@SymptomActivity,"There are no recorded symptoms.",Toast.LENGTH_SHORT).show() //기록되어있는 증상이 없습니다.
                                viewBinding.btnSave.setOnClickListener {
                                    //증상을 체크한 것이 있으면
                                    if (vomit || headache || backache || constipation || giddiness || tiredness || fainting || sensitivity || acne || muscular_pain) {
                                        Log.d("BtnSave", "기존에 저장된 정보X -> 증상 체크O")

                                        // FreeActivity의 CalendarFragment로 이동
                                        val intent = Intent(this@SymptomActivity, FreeActivity::class.java)
                                        startActivity(intent)
                                        (applicationContext as GlobalVariable).boolean = true           //전역변수

                                        //입력한 증상 서버로 보내기
                                        SymptomCheck()

                                    } else { //증상 체크한 것이 하나도 없다면
                                        //btnSave.isClickable = false
                                        Toast.makeText(this@SymptomActivity, "No symptom selected.", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }else{//result != null, 저장되어있던 정보가 있다면 정보 가져오기(기존 정보 반영) -> BooleanArray로 수정하기
                                //기존 정보 띄워주기
                                vomit = resultSymptom.result.vomit
                                checkDeco(vomit, viewBinding.textVomit)
                                headache = resultSymptom.result.headache
                                checkDeco(headache, viewBinding.textHeadache)
                                backache = resultSymptom.result.backache
                                checkDeco(backache, viewBinding.textBackache)
                                constipation=resultSymptom.result.constipation
                                checkDeco(constipation, viewBinding.textConstipation)
                                giddiness = resultSymptom.result.giddiness
                                checkDeco(giddiness, viewBinding.textGiddiness)
                                tiredness= resultSymptom.result.tiredness
                                checkDeco(tiredness, viewBinding.textTiredness)
                                fainting = resultSymptom.result.fainting
                                checkDeco(fainting, viewBinding.textFainting)
                                sensitivity = resultSymptom.result.sensitivity
                                checkDeco(sensitivity, viewBinding.textSensitivity)
                                acne= resultSymptom.result.acne
                                checkDeco(acne, viewBinding.textAcne)
                                muscular_pain=resultSymptom.result.muscular_pain
                                checkDeco(muscular_pain, viewBinding.textMuscularPain)

                                //증상 수정 후 save 누르면 증상 수정됨
                                viewBinding.btnSave.setOnClickListener {
                                    //증상을 체크한 것이 있으면
                                    if (vomit || headache || backache || constipation || giddiness || tiredness || fainting || sensitivity || acne || muscular_pain) {
                                        Log.d("BtnSave", "기존에 증상 정도O-> 증상 변경O.")

                                        // FreeActivity의 CalendarFragment로 이동
                                        val intent = Intent(this@SymptomActivity, FreeActivity::class.java)
                                        startActivity(intent)
                                        (applicationContext as GlobalVariable).boolean = true           //전역변수

                                        //수정한 증상 서버로 보내기
                                        SymptomModify(email,date)

                                    } else { //증상 체크한 것이 하나도 없다면
                                        //원래 있던 증상 다 취소하고 싶을 때 (선택한거 다 취소하고 싶을 때)
                                        //btnSave.isClickable = false
                                        /*val intent = Intent(this@SymptomActivity, FreeActivity::class.java)
                                        startActivity(intent)
                                        (applicationContext as GlobalVariable).boolean = true
                                        SymptomModify(email,date)*/
                                        Toast.makeText(this@SymptomActivity, "No symptom selected.", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }

                        }
                    } else {
                        // 응답이 실패한 경우
                        // 에러 메시지 출력 등의 처리 수행
                        Log.d("GetSymptomsActivity", "연결미스")
                    }
                }

                override fun onFailure(call: Call<SymptomGetDTO>, t: Throwable) {
                    // 네트워크 오류 등으로 인해 요청이 실패한 경우
                    // 에러 메시지 출력 등의 처리 수행
                    Log.e("GetSymptomsActivity", t.message.toString())
                }
            })
    }

}







