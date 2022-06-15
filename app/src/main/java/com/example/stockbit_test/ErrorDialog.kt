package com.example.stockbit_test

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class ErrorDialog(context: Context) : Dialog(context) {

    private var btnOk: TextView
    private var tvErrorCode: TextView
    private var tvErrorMessage: TextView

    init {
        setContentView(R.layout.layout_error_dialog)

        val window: Window? = this.window
        window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        this.setCancelable(false)
        this.setCanceledOnTouchOutside(false)

        btnOk = findViewById(R.id.btn_ok)
        tvErrorCode = findViewById(R.id.txt_error_code)
        tvErrorMessage = findViewById(R.id.txt_error_message)

        btnOk.setOnClickListener {
            dismiss()
        }
    }

    fun setDialogData(errorCode : String, errorMessage : String) {
        tvErrorCode.text = errorCode
        tvErrorMessage.text = errorMessage

    }
}