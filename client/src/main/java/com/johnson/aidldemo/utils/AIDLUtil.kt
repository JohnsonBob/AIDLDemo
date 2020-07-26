package com.johnson.aidldemo.utils

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import androidx.annotation.NonNull
import com.johnson.aidldemo.IService

/**
 * @ClassName AIDLUtil
 * @Description TODO
 * @Author Johnson
 * @Date 2020-07-26 16:23
 */
class AIDLUtil {
    private var iService: IService? = null
    private val TAG = "AIDLUtil"

    private object Holder {
        val aidlUtil = AIDLUtil()
    }

    companion object{
        fun getInstance() = Holder.aidlUtil
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(componentName: ComponentName?) {
            iService = null
            Log.i(TAG, "解除绑定成功")
        }

        override fun onServiceConnected(componentName: ComponentName?, iBinder: IBinder?) {
            iService = IService.Stub.asInterface(iBinder)
            Log.i(TAG, "绑定成功")
        }
    }

    fun bindService(context: Context) {
        val intent = Intent("com.johnson.aidldemo")
        intent.setPackage("com.johnson.aidldemo.server")
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    fun unbindService(context: Context) {
        iService ?: return
        Log.i(TAG, "取消绑定")
        context.unbindService(serviceConnection)
        iService = null
    }

    fun request(@NonNull func: String, @NonNull params: String): String? {
        if (null == iService) {
            Log.i(TAG, "iService为null")
            return null
        }
        Log.i(TAG, "发起AIDL请求")
        try {
            return iService?.getData(func, params)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

        return null
    }


}