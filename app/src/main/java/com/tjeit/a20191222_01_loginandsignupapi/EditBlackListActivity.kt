package com.tjeit.a20191222_01_loginandsignupapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tjeit.a20191222_01_loginandsignupapi.utils.ConnectServer
import kotlinx.android.synthetic.main.activity_edit_black_list.*
import org.json.JSONObject

class EditBlackListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_black_list)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        postBtn.setOnClickListener {

//            사용자가 입력한 값을 저장
            val title = titleEdt.text.toString()
            val phone = phoneNumEdt.text.toString()
            val content = contentEdt.text.toString()

//            서버에 게시글 등록을 요청하는 기능을 추가해야함

//            실제로 서버에 게시글 등록 API를 호출하는 코드
            ConnectServer.postRequestBlackList(mContext, title, phone, content, object : ConnectServer.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
//                    서버의 응답을 확인
                    Log.d("게시글등록응답", json.toString())

                    val code = json.getInt("code")

                    if (code == 200) {
                        runOnUiThread {
                            Toast.makeText(mContext, "게시글이 등록되었습니다.", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                    else {
                        val message = json.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }

                    }

                }

            })


        }

    }

    override fun setValues() {

    }

}
