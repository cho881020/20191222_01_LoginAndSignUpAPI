package com.tjeit.a20191222_01_loginandsignupapi.utils

import android.content.Context
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ConnectServer {

    interface JsonResponseHandler {
        fun onResponse(json: JSONObject)
    }

    companion object {

        //        우리가 접속할 서버의 호스트 주소 (BASE_URL) => 강사의 맥북에 접속 주소
        val BASE_URL = "http://192.168.0.17:5000"

        //        회원가입 요청 함수
        fun putRequestSignUp(
            context: Context,
            inputId: String,
            inputPw: String,
            inputName: String,
            inputPhone: String,
            handler: JsonResponseHandler?
        ) {

//            이 앱이 클라이언트 역할 을 할 수 있게 해주는 클래스 객체화
            val client = OkHttpClient()
//            서버의 기능중 어느 주소로 갈지 명시 => 주소 : url
            val url = "${BASE_URL}/auth"

//            서버로 들고갈 파라미터들을 미리 작성.
//            PUT 메쏘드 이므로 폼데이터에 파라미터들을 실어주자.
//            okhttp에서는 formData를 formBody라는 이름으로 사용.

            val formData = FormBody.Builder()
                .add("login_id", inputId)
                .add("password", inputPw)
                .add("name", inputName)
                .add("phone", inputPhone)
                .build() // 모두 추가했으면 폼바디를 완성. build()

//            실제로 날아갈 요청을 작성. => 화면을 넘어갈때 Intent를 만드는것과 비슷한 개념
            val request = Request.Builder()
                .url(url)
                .put(formData)
                .build() // 리퀘스트에 필요한 데이터를 다 넣었으면 build()로 완성

//            만든 요청을 실제로 실행 (클라이언트) => startActivity와 비슷한 개념
//            요청에 대한 응답 처리 코드 => 액티비티가 실행하도록 연결

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    서버 요청에 실패하면 왜 실패했는지를 로그캣으로 찍자
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
//                    서버 요청에 성공하면 응답 내용(=>String으로 받아서)을 JSON으로 가공
                    val body = response.body()!!.string()
                    val json = JSONObject(body)
                    handler?.onResponse(json)

                }

            })
        }

        fun postRequestLogin(context:Context, id:String, pw:String, handler: JsonResponseHandler?) {

            val client = OkHttpClient()
            val url = "${BASE_URL}/auth"

            val formData = FormBody.Builder()
                .add("login_id", id)
                .add("password", pw)
                .build()

            val request = Request.Builder()
                .url(url)
                .post(formData)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()!!.string()
                    val json = JSONObject(body)
                    handler?.onResponse(json)
                }

            })


        }


        fun getRequestBlackList(context: Context, handler:JsonResponseHandler?) {

            val client = OkHttpClient()

//            GET 방식 호출은 파라미터를 query에 담는다. => 주소창에 같이 적어주는 방식
//            url을 만들때 애초에 파라미터도 같이 첨부해야함.

            val urlBuilder = HttpUrl.parse("${BASE_URL}/black_list")!!.newBuilder()
//            urlBuilder.addEncodedQueryParameter("파라미터이름", "필요변수")

//              URL빌더 를 통해 가공된 URL을 실제 String 타입의 url로 변경.

            val url = urlBuilder.build().toString()

//            요청을 날리는 리퀘스트를 생성
            // API가 헤더를 요구한다면, 리퀘스트 생성시에 첨부.
//            GET방식은 제일 기본적인 리퀘스트. => post / put 등과 다르게 메쏘드를 지정하지 않는다. => 바로 build() 로 마무리
            val request = Request.Builder()
                .url(url)
                .header("X-Http-Token", ContextUtil.getUserToken(context))
                .build()

//            클라이언트를 이용해 서버에 실제 요청

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()!!.string()
                    val json = JSONObject(body)
                    handler?.onResponse(json)
                }

            })


        }


        fun postRequestBlackList(context:Context, title:String, phoneNum:String, content:String, handler: JsonResponseHandler?) {

            val client = OkHttpClient()
            val url = "${BASE_URL}/black_list"

            val formData = FormBody.Builder()
                .add("title", title)
                .add("phone_num", phoneNum)
                .add("content", content)
                .build()

            val request = Request.Builder()
                .url(url)
                .post(formData)
                .header("X-Http-Token", ContextUtil.getUserToken(context))
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()!!.string()
                    val json = JSONObject(body)
                    handler?.onResponse(json)
                }

            })


        }

    }



}







