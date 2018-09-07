package com.caucse.seoulproject

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.caucse.seoulproject.R
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginDefine
import com.nhn.android.naverlogin.OAuthLoginHandler
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import android.databinding.adapters.TextViewBindingAdapter.setText
import android.util.Log


class LoginActivity : AppCompatActivity() {

    private val OAUTH_CLIENT_ID = "ILMvRUaQNzCAYsTvxb59"
    private val OAUTH_CLIENT_SECRET = "tNuAP0CtD5"
    private val OAUTH_CLIENT_NAME = "Culin"
    private lateinit var mOAuthLoginInstance: OAuthLogin
    private var loginOK: Boolean = false;
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initData()
    }

    override fun onResume() {
        super.onResume()
        var loginButton = this.naverLogin
        loginButton.setOAuthLoginHandler(mOAuthLoginHandler)
        mOAuthLoginInstance.getAccessToken()
    }
    fun startCulin(){
        var intent = Intent(this, MainActivity::class.java)
        // TODO : 이런식으로 intent.putExtra(key, value) 식으로 로그인 정보 전달
        intent.putExtra("userName", "부현식")
        intent.putExtra("userEmail", "bhs9194@nate.com")
        startActivity(intent)
        finish()
    }

    fun initData(){
        mOAuthLoginInstance = OAuthLogin.getInstance()
        mOAuthLoginInstance.init(this,OAUTH_CLIENT_ID,OAUTH_CLIENT_SECRET,OAUTH_CLIENT_NAME)
    }

    private val mOAuthLoginHandler = object : OAuthLoginHandler() {
        override fun run(success: Boolean) {
            if (success) {
                Log.d("로그인", "로그인성공")
                startCulin()
            } else {
                Log.d("로그인", "로그인실패")
            }
        }
    }


}