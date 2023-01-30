package com.example.free_pre_android

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.free_pre_android.databinding.ActivityPreBinding

class PreActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityPreBinding
    var btnChangeHeightClicked = false

    //Int to dp - 나중에 시도해보기
    /*val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPreBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        //음성으로 알려 줄 필요 있지 않나..? : 한번 클릭하면 free로 갈 수 있다는 설명
        //눌렀을 때 글자를 읽어주는건 자동으로 되지만, 글자가 아닌 부분을 눌렀을 때 어떤 부분인지에 대한 설명은 따로 넣어야 할 듯
        //한번만 가능하도록 or 다시 클릭하면 들어가도록.. [회의]
        //https://www.geeksforgeeks.org/how-to-change-textview-size-programmatically-in-android/
        viewBinding.linearTop.setOnClickListener {
            /*val mParams = viewBinding.linearTop.layoutParams
            mParams.apply {
                height *=3
            }
            viewBinding.linearTop.layoutParams = mParams
        }*/

            //[수정] - 한번 클릭하면 내려가고, 다시 한번 더 클릭하면 올라가고?(아니면 두번 클릭하면 올라가게 하는 것도 나쁘지 않은 듯)
            //일단 내려가는 것까지 됨
            slideView(viewBinding.linearTop,viewBinding.linearTop.layoutParams.height,630)

        }
    }


    /*클릭할 때마다 뷰 높이 조절 */
    public fun slideView(view: View, currentHeight:Int, newHeight:Int){
        val slideAnimator = ValueAnimator.ofInt(currentHeight,newHeight).setDuration(500)

        slideAnimator.addUpdateListener {
            val value :Int
            value = it.getAnimatedValue() as Int
            view.layoutParams.height = value.toInt()
            view.requestLayout()
        }

        val animationSet:AnimatorSet = AnimatorSet()
        animationSet.setInterpolator(AccelerateDecelerateInterpolator())
        animationSet.play(slideAnimator)
        animationSet.start()
    }
}