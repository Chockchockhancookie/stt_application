package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.datas.Room
import kotlinx.android.synthetic.main.activity_room_detail.*

class RoomDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_detail)

        setValues()
        setupEvents()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        val room = intent.getSerializableExtra("roomInfo") as Room
        priceTxt.text = room.memo_title
        descTxt.text = room.memo_date
        addressTxt.text = room.memo_context

    }
}