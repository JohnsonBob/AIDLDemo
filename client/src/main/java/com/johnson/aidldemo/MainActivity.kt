package com.johnson.aidldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.johnson.aidldemo.utils.AIDLUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListener()
    }

    private fun setListener(){
        bindBtn.setOnClickListener{
            AIDLUtil.getInstance().bindService(applicationContext)
            resultTv.append("绑定服务\n")
        }

        unbindBtn.setOnClickListener{
            AIDLUtil.getInstance().unbindService(applicationContext)
            resultTv.append("解除绑定\n")
        }

        sendRequestBtn.setOnClickListener{
            val request = AIDLUtil.getInstance().request("char", "test")
            if (!request.isNullOrEmpty()) {
                resultTv.append("接收到返回数据: ")
                resultTv.append(request)
                resultTv.append("\n")
            }
        }
    }
}
