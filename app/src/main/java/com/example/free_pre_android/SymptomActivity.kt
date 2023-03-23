package com.example.free_pre_android

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.free_pre_android.adapter.SymptomAdapter
import com.example.free_pre_android.databinding.ActivitySymptomBinding
import com.example.free_pre_android.model.GlobalVariable
import com.example.free_pre_android.model.SymptomData


class SymptomActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySymptomBinding
    private lateinit var symptomAdapter: SymptomAdapter
    private lateinit var dataList: ArrayList<SymptomData>

    //private lateinit var selectedDataList : ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySymptomBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


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

            //val textselectedSymptom = symptomAdapter.selectedSymptom[0].symptom
            //Log.d("SelectedSymptom","${textselectedSymptom}")


            //selectedDataList = ArrayList()
            for(i in symptomAdapter.selectedSymptom.indices){
                Log.d("SelectedSymptom","${symptomAdapter.selectedSymptom[i].symptom}")
                //selectedDataList[i] = symptomAdapter.selectedSymptom[i].symptom
            }
            //val symptom0 = symptomAdapter.selectedSymptom[0].symptom
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
            //Log.d("SymptomTest","${symptomAdapter.selectedSymptom[2].symptom}")


            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[0].symptom}")
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[1].symptom}")      //만약 값이 없다면 아예 나오지 않는다.
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[2].symptom}")
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[3].symptom}")
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[4].symptom}")
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[5].symptom}")
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[6].symptom}")
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[7].symptom}")
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[8].symptom}")
            Log.d("SelectedSymptom2","${symptomAdapter.selectedSymptom[9].symptom}")


            // 액티비티1에서 변수를 전달하는 코드
            val intent = Intent(this@SymptomActivity, FreeActivity::class.java)
            //intent.putExtra("boolean", true)
            startActivity(intent)
            (applicationContext as GlobalVariable).boolean = true           //전역변수

            //PostSymptom("yjs000616","2020-03-01","vomit","","","","","","","","acne","")

        }

    }


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


    private fun PostSymptom(
        /*email:String,
        date:String,
        vomit:String,
        headache:String,
        backache:String,
        constipation:String,
        giddiness:String,
        tiredness:String,
        fainting:String,
        sensitivity:String,
        acne:String,
        muscular_pain:String*/){
        //getsharedPreference
        //val email
        //val date

        /*
        val postSymptom = SymptomCheckDTO(

        )

        RetrofitBuilder.symptomApi.symptomCheck(selectedSymptom).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {//연결 성공한 경우에만 처리
                    Log.d("PostSymptom","연결성공")     //response.body().toString()

                }
                else{
                    Log.d("PostSymptom","연결미스")    //response.code().toString()

                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("PostSymptom", "연결실패")      //t.message.toString()
            }
        })*/
    }


    override fun onDestroy() {
        super.onDestroy()
        viewBinding != null
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        //activivy의 상태를 저장하는 코드
    }


}
