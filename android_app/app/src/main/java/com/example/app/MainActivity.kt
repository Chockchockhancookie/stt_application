package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_tmp.setOnClickListener {
            val intent = Intent(this, MemoActivity::class.java)
            startActivity(intent)
        }


        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.3:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var loginService = retrofit.create(LoginService::class.java)

        button.setOnClickListener {
            var textId = editText.text.toString()
            var textPw = editText2.text.toString()

            loginService.requestLogin(textId, textPw).enqueue(object : Callback<Login> {
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    var login = response.body() // code, msg
                    var dialog = AlertDialog.Builder(this@MainActivity)
                    dialog.setTitle("성공!")
                    dialog.setMessage("cod= " + login?.code + " msg= " + login?.msg)    // null 값이 있을 수도 있을 때 ?를 사용!
                    dialog.show()

                }   //웹통신 성공. 응답값을 받아옴

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    var dialog = AlertDialog.Builder(this@MainActivity)
                    dialog.setTitle("실패!")
                    dialog.setMessage("통신에 실패했습니다.")
                    dialog.show()
                }   //웹통신 실패. 실행되는 코드

            })
//            var dialog = AlertDialog.Builder(this)
//            dialog.setTitle("알람!")
//            dialog.setMessage("id= " + textId + " pw= " + textPw)
//            dialog.show()
        }


    }
}

