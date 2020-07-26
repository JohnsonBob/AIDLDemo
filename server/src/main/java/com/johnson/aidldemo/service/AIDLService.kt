package com.johnson.aidldemo.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.johnson.aidldemo.IService
import org.json.JSONException
import org.json.JSONObject

/**
 * @ClassName AIDLService
 * @Description TODO
 * @Author Johnson
 * @Date 2020-07-26 16:07
 */
class AIDLService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return stub
    }

    private val stub = object : IService.Stub() {
        override fun getData(func: String?, params: String?): String {
            Log.i("AIDLService", "接收到请求")
            Log.i("AIDLService", "func：$func；params：$params")
            val jsonObject = JSONObject()
            when (func) {
                "char" -> try {
                    jsonObject.put("name", "Johnson")
                    jsonObject.put("sex", "man")
                    val millis = System.currentTimeMillis()
                    jsonObject.put("time", millis)
                    jsonObject.put("params", params)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

                else -> {
                    jsonObject.put("type", "this $func")
                    jsonObject.put("params", params)
                }
            }

            return jsonObject.toString()
        }

    }
}
