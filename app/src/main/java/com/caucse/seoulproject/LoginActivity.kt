package com.caucse.seoulproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.caucse.seoulproject.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        var loginButton = this.naverLogin
        naverLogin.setOnClickListener { view ->
            var intent = Intent(this, MainActivity::class.java)
            // TODO : 이런식으로 intent.putExtra(key, value) 식으로 로그인 정보 전달
            intent.putExtra("userName", "부현식")
            intent.putExtra("userEmail", "bhs9194@nate.com")
            startActivity(intent)
            finish()
        }
    }
}
