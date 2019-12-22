package com.tjeit.a20191222_01_loginandsignupapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_black_list.*

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

        }

    }

    override fun setValues() {

    }

}
