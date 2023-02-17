package com.example.free_pre_android

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityCameraBinding

class CameraActivity : BaseActivity() {
    private lateinit var viewBinding: ActivityCameraBinding

    companion object{
        private val CLOUD_VISION_API_KEY: String = "AIzaSyBHdJZ1o4uGmQ5PE6j9zUGIGXtJIYy7650"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        //여기서 바로 카메라 실행되도록 해야함.
        requirePermissions(arrayOf(Manifest.permission.CAMERA),10)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==10){
            if(resultCode== Activity.RESULT_OK){
                Log.d("camera","촬영 성공")
            }else{
                Log.d("camera","촬영 실패")
            }
        }
    }

    override fun permissionGranted(requestCode: Int) {
        val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,99)
    }

    override fun permissionDenied(requestCode: Int) {
        Toast.makeText(baseContext,"카메라 권한 거부됨",Toast.LENGTH_SHORT).show()
    }

}