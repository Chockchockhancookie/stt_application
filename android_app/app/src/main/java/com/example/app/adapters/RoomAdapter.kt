package com.example.app.adapters

import android.content.Context
import android.content.LocusId
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.app.R
import com.example.app.datas.Room

class RoomAdapter (
    val mContext: Context,
    val resId: Int,
    val mList: List<Room>
) : ArrayAdapter<Room>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.memo_list_item, null)
        }

        val row = tempRow!!
        val data = mList[position]
        val price = row.findViewById<TextView>(R.id.priceTxt)
        val address = row.findViewById<TextView>(R.id.addressTxt)

        price.text = "${data.memo_date}"
        address.text = "${data.memo_title}"

        return row
    }
}







//class Room(
//    // 클래스의 생성자에서 변수들을 나열해서 클래스가 가져야하는 정보 항목들로 설정
//    val deposit: Int,
//    val monthlyRent: Int,
//    val address: String,
//    val floor: Int,
//    val description: String )
//{
