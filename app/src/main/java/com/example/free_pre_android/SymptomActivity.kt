package com.example.free_pre_android

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.adapter.SymptomAdapter
import com.example.free_pre_android.data.SymptomCheckDTO
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

        viewBindingRun()

        val sharedPreferences: SharedPreferences = this.getSharedPreferences("Email", Activity.MODE_PRIVATE)
        email = sharedPreferences.getString("emailKey","there's no email").toString()               //로그인 되어있는 유저의 이메일
        //date = viewBinding..currentDate.month.toString()                    //날짜도 getsharedPreference로..? - 선택한 날짜 보내기

        /*
        loadData()

        viewBinding.recyclerSymptom.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SymptomActivity)
            symptomAdapter = SymptomAdapter(dataList)
            adapter = symptomAdapter

        }

        /*
        viewBinding.btnSave.isEnabled = true

        symptomAdapter.setOnItemClickListener { response ->
            viewBinding.btnSave.isEnabled = symptomAdapter.getSelectedExpense() > 0        //선택된게 1개 이상있으면 btn isEnabled 는 true
        }*/

        viewBinding.btnSave.setOnClickListener {
            //Toast.makeText(this, "선택: ${symptomAdapter.getSelectedExpense()}개", Toast.LENGTH_SHORT).show()


            //Log.d("Symptom","${symptomAdapter.selectedSymptom.toString()}")
            Log.d("SelectedSymptom", symptomAdapter.selectedSymptom.toString())

            //selectedDataList = ArrayList()
            for(i in symptomAdapter.selectedSymptom.indices){
                //Log.d("SelectedSymptom","${symptomAdapter.selectedSymptom[i].symptom}")
                //selectedDataList[i] = symptomAdapter.selectedSymptom[i].symptom
            }

            /*val symptom0 = symptomAdapter.selectedSymptom[0].symptom
            //val symptom1 = symptomAdapter.selectedSymptom[1].symptom         //선택되지 않았으면 값이 저장되지 않는듯,,?
            //val symptom2 = symptomAdapter.selectedSymptom[2].symptom         //var로 두면 오류가 난다.   그러면 아예 다 저장하면 안되는데
            //val symptom3 = symptomAdapter.selectedSymptom[3].symptom
            //val symptom4 = symptomAdapter.selectedSymptom[4].symptom
            //val symptom5 = symptomAdapter.selectedSymptom[5].symptom
            //val symptom6 = symptomAdapter.selectedSymptom[6].symptom
            //val symptom7 = symptomAdapter.selectedSymptom[7].symptom
            //val symptom8 = symptomAdapter.selectedSymptom[8].symptom
            //val symptom9 = symptomAdapter.selectedSymptom[9].symptom
            //Log.d("SymptomTest","${symptomAdapter.selectedSymptom[1].symptom}")
            //Log.d("SymptomTest","${symptom2}")
            Log.d("SymptomTest","${symptomAdapter.selectedSymptom[2].symptom}")*/

            /*Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[0].symptom}")
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[1].symptom}")      //만약 값이 없다면 아예 나오지 않는다.
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[2].symptom}")
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[3].symptom}")
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[4].symptom}")
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[5].symptom}")
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[6].symptom}")
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[7].symptom}")
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[8].symptom}")
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[9].symptom}")*/

         */

    }


    fun viewBindingRun() {
        viewBinding.run {
            textVomit.setOnClickListener {
                vomit = true
                textVomit.setBackgroundResource(R.color.primary_light)
                textVomit.setTextColor(Color.parseColor("#1A2A46"))
            }
            textHeadache.setOnClickListener {
                headache = true
                textHeadache.setBackgroundResource(R.color.primary_light)
                textHeadache.setTextColor(Color.parseColor("#1A2A46"))
            }
            textBackache.setOnClickListener {
                backache = true
                textBackache.setBackgroundResource(R.color.primary_light)
                textBackache.setTextColor(Color.parseColor("#1A2A46"))
            }
            textConstipation.setOnClickListener {
                constipation = true
                textConstipation.setBackgroundResource(R.color.primary_light)
                textConstipation.setTextColor(Color.parseColor("#1A2A46"))
            }
            textGiddiness.setOnClickListener {
                giddiness = true
                textGiddiness.setBackgroundResource(R.color.primary_light)
                textGiddiness.setTextColor(Color.parseColor("#1A2A46"))
            }
            textTiredness.setOnClickListener {
                tiredness = true
                textTiredness.setBackgroundResource(R.color.primary_light)
                textTiredness.setTextColor(Color.parseColor("#1A2A46"))
            }
            textFainting.setOnClickListener {
                fainting = true
                textFainting.setBackgroundResource(R.color.primary_light)
                textFainting.setTextColor(Color.parseColor("#1A2A46"))
            }
            textSensitivity.setOnClickListener {
                sensitivity = true
                textSensitivity.setBackgroundResource(R.color.primary_light)
                textSensitivity.setTextColor(Color.parseColor("#1A2A46"))
            }
            textAcne.setOnClickListener {
                acne = true
                textAcne.setBackgroundResource(R.color.primary_light)
                textAcne.setTextColor(Color.parseColor("#1A2A46"))
            }
            textMuscularPain.setOnClickListener {
                muscular_pain = true
                textMuscularPain.setBackgroundResource(R.color.primary_light)
                textMuscularPain.setTextColor(Color.parseColor("#1A2A46"))
            }
            btnSave.setOnClickListener {
                // 액티비티1에서 변수를 전달하는 코드
                val intent = Intent(this@SymptomActivity, FreeActivity::class.java)
                //intent.putExtra("boolean", true)
                startActivity(intent)
                (applicationContext as GlobalVariable).boolean = true           //전역변수

                SymptomCheck()
            }
        }
    }
    /*
    fun setClick(viewBinding: ActivitySymptomBinding){
        if (selectedSymptom.contains(item)) {
            selectedSymptom.remove(item)
            changeBackground(viewBinding, R.color.primary_dark)
            changeTextColor(viewBinding,R.color.primary_light)
            changeDrawable(viewBinding,R.drawable.style_symptom_content)
        } else {
            selectedSymptom.add(item)
            changeBackground(viewBinding, R.color.primary_light)
            changeTextColor(viewBinding,R.color.primary_dark)
            changeDrawable(viewBinding,R.drawable.style_symptom_content_dark)
        }
    }*/





        //email : 사용자 이메일
        //data : 사용자가 누른 날짜
        //증상 입력
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


}


    /*
    private fun loadData(){
        dataList = ArrayList()
        //dataList.clear()                                              //이전에 dataList에 객체가 이미 생성되어 있다면, clear로 이전 요소 삭제 후 add로 데이터 추가함
        dataList.add(SymptomData("vomit"))
        dataList.add(SymptomData("headache"))
        dataList.add(SymptomData("backache"))
        dataList.add(SymptomData("constipation"))
        dataList.add(SymptomData("giddiness"))
        dataList.add(SymptomData("tiredness"))
        dataList.add(SymptomData("fainting"))
        dataList.add(SymptomData("sensitivity"))
        dataList.add(SymptomData("acne"))
        dataList.add(SymptomData("muscular pain"))
    }


    //email : 사용자 이메일
    //data : 사용자가 누른 날짜
    //증상 입력
    fun SymptomCheck(){
        val dataInfo = SymptomCheckDTO(email,date, vomit, headache, backache, constipation, giddiness, tiredness, fainting, sensitivity, acne, muscular_pain)
        // API 호출
        RetrofitBuilder.symptomApi.symptomCheck(dataInfo).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                //Log.d("Symptom11",response.body().toString())
                if (response.isSuccessful) {
                    // 응답이 성공적으로 왔을 때 처리할 내용
                        Log.d("calendarTest","연결성공")

                } else {
                    // 응답이 실패한 경우
                    // 에러 메시지 출력 등의 처리 수행
                    Log.d("calendarTest","연결미스")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // 네트워크 오류 등으로 인해 요청이 실패한 경우
                // 에러 메시지 출력 등의 처리 수행
                Log.e("calendarTest",t.message.toString())
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        viewBinding != null
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        //activivy의 상태를 저장하는 코드
    }*/





