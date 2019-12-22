package com.tjeit.a20191222_01_loginandsignupapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tjeit.a20191222_01_loginandsignupapi.utils.ConnectServer
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

        signUpBtn.setOnClickListener {

//            사용자가 입력한 값을 별개로 저장
            val inputId = idEdt.text.toString()
            val inputPw = pwEdt.text.toString()
            val inputName = nameEdt.text.toString()
            val inputPhone = phoneEdt.text.toString()

            ConnectServer.putRequestSignUp(mContext, inputId, inputPw, inputName, inputPhone, object : ConnectServer.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                    Log.d("회원가입응답", json.toString())

                }

            })


        }

    }

    override fun setValues() {

    }

}
