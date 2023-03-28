package com.example.free_pre_android

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.example.free_pre_android.base.BaseActivity
import com.example.free_pre_android.databinding.ActivityCameraBinding
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions


class CameraActivity : BaseActivity() {

    val PERM_CAMERA=100//카메라 권한 처리
    val REQ_CAMERA=101//카메라 촬영 요청

    var result:String=""
    //private var mediaPlayer: MediaPlayer? = null
    private lateinit var viewBinding: ActivityCameraBinding

    companion object{
        private val CLOUD_VISION_API_KEY: String = "AIzaSyBHdJZ1o4uGmQ5PE6j9zUGIGXtJIYy7650"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        //여기서 바로 카메라 실행되도록 해야함.
        requirePermissions(arrayOf(Manifest.permission.CAMERA), PERM_CAMERA)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK){
            when(requestCode){
                REQ_CAMERA->{
                    if(data?.extras?.get("data")!=null){
                        val bitmap=data?.extras?.get("data")as Bitmap
                        viewBinding.imagePreView.setImageBitmap(bitmap)
                        val settedImage:InputImage=setImage(bitmap) //bitmap을 InputImage로 변환
                        callCloudVision(settedImage)//google vision Api 호출
                    }
                }
            }
        }
    }


    override fun permissionGranted(requestCode: Int) {
        when(requestCode){
            PERM_CAMERA->openCamera()
        }
    }

    override fun permissionDenied(requestCode: Int) {
        when(requestCode){
            PERM_CAMERA->{
                Toast.makeText(baseContext,"권한을 승인해야 카메라를 사용할 수 있습니다.",Toast.LENGTH_SHORT).show()

            }
        }
    }
    fun openCamera(){
        //playShutterSound()
        val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,REQ_CAMERA)
    }

    fun setImage(bitmap: Bitmap):InputImage{
        val image = InputImage.fromBitmap(bitmap, 0)
        Log.d("camera","이미지 변환")
        return image
    }

    fun callCloudVision(image: InputImage){
        val reconizer:TextRecognizer=TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())
        val result=reconizer.process(image)
            .addOnSuccessListener { visionText->//텍스트 인식 작업 성공
                Log.d("camera","텍스트 인식 작업 성공")
                Log.d("camera",visionText.toString())
                for (block in visionText.textBlocks) {
                    val boundingBox = block.boundingBox
                    val cornerPoints = block.cornerPoints
                    val text = block.text
                    Log.d("camera",text)
                    result=result+text+"\n"
                    for (line in block.lines) {
                        // ...
                        for (element in line.elements) {
                            // ...
                        }
                    }
                }
                viewBinding.txtResult.text=result
        }
            .addOnFailureListener { e->//텍스트 인식 작업 실패
                Log.e("camera","텍스트 인식 작업 실패")
                //...
            }
    }



    /*
    fun playShutterSound() {
        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.shutter_sound)
            mediaPlayer?.start()
            mediaPlayer?.setOnCompletionListener { mp -> mp.release() }
        } catch (e: Exception) {
            Log.e("CamerSound", "Failed to play shutter sound", e)
        }
    }*/

}