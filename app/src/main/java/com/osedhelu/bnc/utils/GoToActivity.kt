package com.osedhelu.bnc.utils


import android.app.Activity
import android.content.Context
import android.content.Intent
import com.osedhelu.bnc.ui.MainActivity

enum class iNameActivity {
    MAIN,
}

fun GoToActivity(nameactivity: iNameActivity, context: Context, finish: Boolean = false) {
    val activity = (context as Activity)
    when (nameactivity) {
        iNameActivity.MAIN -> {
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("Activity", "Main")
            context.startActivity(intent)
        }

    }
    if (finish) {
        activity?.finish()
    }
}