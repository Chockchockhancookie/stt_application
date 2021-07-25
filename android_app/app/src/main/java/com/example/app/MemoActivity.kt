package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.adapters.RoomAdapter
import com.example.app.datas.Room
import kotlinx.android.synthetic.main.activity_memo.*
import java.time.LocalDate


class MemoActivity : BaseActivity() {
    val mRoomList = ArrayList<Room>()
    lateinit var mRoomAdapter: RoomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)
        setupEvents()
        setValues()

        add.setOnClickListener {
            val intent = Intent(this, VoiceActivity::class.java)
            startActivity(intent)
        }
    }
    override fun setupEvents() {

    }
    override fun setValues() {
        mRoomList.add(Room("2021-7-23", "이틀전"))
        mRoomList.add(Room("2021-7-24", "어제"))
        mRoomList.add(Room("2021-7-25", "오늘"))

        mRoomAdapter = RoomAdapter(mContext, R.layout.memo_list_item, mRoomList)
        roomListView.adapter = mRoomAdapter
    }
}