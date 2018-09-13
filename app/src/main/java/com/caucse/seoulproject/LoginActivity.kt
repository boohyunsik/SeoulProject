package com.caucse.seoulproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import kotlinx.android.synthetic.main.activity_login.*
import android.util.Log
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.nhn.android.naverlogin.data.OAuthLoginState
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Serializable
import java.net.HttpURLConnection
import java.net.URL


class LoginActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName
    private val OAUTH_CLIENT_ID = "ILMvRUaQNzCAYsTvxb59"
    private val OAUTH_CLIENT_SECRET = "tNuAP0CtD5"
    private val OAUTH_CLIENT_NAME = "Culin"
    private lateinit var mOAuthLoginInstance: OAuthLogin
    private lateinit var jsonParser: JsonParser
    private lateinit var jsonObject : JsonObject


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        initData()
        if(mOAuthLoginInstance.getState(this)==OAuthLoginState.OK) {
            Log.d("초기 로그인상태",mOAuthLoginInstance.getState(this@LoginActivity).toString())
            startCulin()
        }

        var loginButton = this.naverLogin
            loginButton.setOAuthLoginHandler(mOAuthLoginHandler)
    }

    fun startCulin(){
        Thread {
            val token = mOAuthLoginInstance.getAccessToken(this)// 네아로 접근 토큰 값";
            val header = "Bearer $token" // Bearer 다음에 공백 추가

            try {
                var apiURL = "https://openapi.naver.com/v1/nid/me"
                var url = URL(apiURL)
                var con = url.openConnection() as HttpURLConnection
                con.requestMethod = "GET"
                con.setRequestProperty("Authorization",header)
                Log.d(TAG,con.getRequestProperty("Authorization"))
                var responseCode = con.responseCode
                var br: BufferedReader?
                if (responseCode == 200) { // 정상 호출
                    br = BufferedReader(InputStreamReader(con.inputStream))
                } else {  // 에러 발생
                    br = BufferedReader(InputStreamReader(con.errorStream))
                }
                Log.d(TAG , "response code = $responseCode")

                jsonParser = JsonParser()
                var responseObject: JsonObject = jsonParser.parse(br.readLine()) as JsonObject
                jsonObject = responseObject.get("response") as JsonObject

                Log.d(TAG , "response = ${jsonObject}")
                Log.d(TAG , "id = ${jsonObject.get("id")}")
                Log.d(TAG , "nickname = ${jsonObject.get("nickname")}")
                Log.d(TAG, "profile_image = ${jsonObject.get("profile_image")}")
                Log.d(TAG , "age = ${jsonObject.get("age")}")
                Log.d(TAG , "gender = ${jsonObject.get("gender")}")
                Log.d(TAG , "email = ${jsonObject.get("email")}")
                Log.d(TAG , "name = ${jsonObject.get("name")}")
                Log.d(TAG , "birthday = ${jsonObject.get("birthday")}")

            } catch (e: Exception) {
                Log.d(TAG, "error = ${e.message}")
            }

        }.start()


        var intent = Intent(this, MainActivity::class.java)
        // TODO : 이런식으로 intent.putExtra(key, value) 식으로 로그인 정보 전달

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
                val accessToken = mOAuthLoginInstance.getAccessToken(this@LoginActivity)
                Log.d("로그인", "로그인성공")
                Log.d("성공토큰타입", accessToken)
                startCulin()
            } else {
                val accessToken = mOAuthLoginInstance.getAccessToken(this@LoginActivity)
                Log.d("로그인", "로그인실패")
                Log.d("실패토큰", accessToken)
            }
        }
    }


}