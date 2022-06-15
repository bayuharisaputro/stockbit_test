package com.example.stockbit_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.iv_back
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        initEvent()
    }

    private fun initView() {
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val spanTxt = SpannableString(txt_register.text)
        spanTxt.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.green_bibit)), 17, 33, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        txt_register.text = spanTxt

    }

    private fun initEvent() {
        var isError: Boolean = false

        iv_back.setOnClickListener {
            onBackPressed()
        }

        et_username.addTextChangedListener(object :TextWatcher{
           override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

           override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               if(p0?.isNotEmpty() == true) {
                   tl_username.isErrorEnabled = false
               }
           }

           override fun afterTextChanged(p0: Editable?) {}

       })

        et_password.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0?.isNotEmpty() == true) {
                    tl_password.isErrorEnabled = false
                    isError = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {}

        })


        btn_login.setOnClickListener {
            if(et_username.text?.isEmpty() == true) {
                isError = true
                tl_username.isErrorEnabled = true
                tl_username.error = "Please fill username or Email First"
            }

            if(et_password.text?.isEmpty() == true) {
                isError = true
                tl_password.isErrorEnabled = true
                tl_password.error = "Please fill password"
            }

            if (!isError) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun initData() {}
}