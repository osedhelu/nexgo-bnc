package com.disglobal.bnc.ui.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.disglobal.bnc.ui.theme.primary

@Composable
fun CustomKeyboard() {
//    var inputVal = mutableStateOf("")
    val state = remember {
        mutableStateOf("")
    }
    CustomKeyboard(num = state, onClick = {
        println("Clicked")
    })
}

@Composable
fun CustomKeyboard(
    num: MutableState<String>, onClick: () -> Unit
) {
    var onNum by remember { num }
    Column(
        modifier = Modifier
            .fillMaxSize(.76f)
            .padding(8.dp)
    ) {
        val sizeButton = 1f

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberButton(
                number = "1", onClick = {
                    onNum += "1"
                }, modifier = Modifier.weight(sizeButton)
            )
            NumberButton(number = "2", onClick = {
                onNum += "2"
            }, modifier = Modifier.weight(sizeButton))
            NumberButton(number = "3", onClick = {
                onNum += "3"
            }, modifier = Modifier.weight(sizeButton))

        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberButton(number = "4", onClick = {
                onNum += "4"
            }, modifier = Modifier.weight(sizeButton))
            NumberButton(number = "5", onClick = {
                onNum += "5"
            }, modifier = Modifier.weight(sizeButton))
            NumberButton(number = "6", onClick = {
                onNum += "6"
            }, modifier = Modifier.weight(sizeButton))

        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberButton(number = "7", onClick = {
                onNum += "7"
            }, modifier = Modifier.weight(sizeButton))
            NumberButton(number = "8", onClick = {
                onNum += "8"
            }, modifier = Modifier.weight(sizeButton))
            NumberButton(number = "9", onClick = {
                onNum += "9"
            }, modifier = Modifier.weight(sizeButton))
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberButton(number = "⌫", primaryBt = true, onClick = {
                onNum = if (onNum.length > 1) {
                    onNum.substring(0, onNum.length - 1)
                } else {
                    "0"
                }
            }, modifier = Modifier.weight(sizeButton))
            NumberButton(number = "0", onClick = {
                onNum += "0"
            }, modifier = Modifier.weight(sizeButton))

            NumberButton(
                number = "⏎",
                primaryBt = true,
                onClick = onClick,
                modifier = Modifier.weight(sizeButton)
            )


        }
    }
}

@Composable
private fun NumberButton(
    number: String, onClick: () -> Unit, modifier: Modifier = Modifier,
    primaryBt: Boolean = false
) {
    OutlinedButton(
        onClick = {
            onClick()
        },
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (primaryBt) primary else Color.White,
        ),
        border = ButtonDefaults.outlinedButtonBorder.copy(
            brush = SolidColor(MaterialTheme.colorScheme.primary),

            )

    ) {
        Text(
            text = number, fontSize = 23.sp, color = if (primaryBt) Color.White else Color.Black,
        )

    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 900)
@Composable
fun PreviewCustomKeyboard() {
    val num = remember { mutableStateOf("123") }
    val onNum by remember { num }
    Text(text = onNum)
    CustomKeyboard()
}