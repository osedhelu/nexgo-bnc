package com.disglobal.bnc.ui.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.disglobal.bnc.ui.theme.MyApplicationTheme

class EmvActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    EmvScreen()
                }
            }
        }


    }

}

@Composable
fun EmvScreen() {
    var amount by remember { mutableStateOf("") }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        EmvScreen()
    }
}
