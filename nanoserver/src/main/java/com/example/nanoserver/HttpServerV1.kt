package com.example.nanoserver

import android.util.Log
import com.google.gson.Gson
import fi.iki.elonen.NanoHTTPD

/**
 * http服务
 */
class HttpServerV1(hostName: String, port: Int) :NanoHTTPD(hostName, port){
    private val TAG = "http_server"
    private var count = 0
    private var mGson: Gson = Gson()

    override fun serve(session: IHTTPSession?): Response {
        Log.i(TAG, "session.uri = ${session?.uri} ,method = ${session?.method} ," +
                "header = ${session?.headers}, + params=${session?.parameters}")

        if (session?.method == Method.GET){
            if (session.uri == HTTP_URI_HELLO) {
                return responseJsonString(200,"hello my friend","success")
            }
        }
        return responseJsonString(404,"","request not support!")
    }

//    private fun dealWith(session: IHTTPSession?) : Response {
//
//    }
    private fun <T> responseJsonString(code: Int,data: T,msg: String) :Response{
        val response = Responser(code, data, msg)
        return newFixedLengthResponse(mGson.toJson(response))
    }

}