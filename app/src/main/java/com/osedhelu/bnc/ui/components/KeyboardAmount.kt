package com.osedhelu.bnc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.osedhelu.bnc.ui.Screens.ShoppingScreen.PagoHelper
import com.osedhelu.bnc.ui.theme.primary

@Composable
fun CustomButton(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    backgroundColor: Color = Color.Gray,
) {
    Box(
        modifier = modifier
            .background(backgroundColor)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun NumericKeyboard(
    onNum: MutableState<String>, onEnter: (String) -> Unit, onBack: () -> Unit = {},

    ) {
    var num by remember { onNum }

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight
        val buttonWidth = (width * .19f).dp
        val heightButton = (height * .08f).dp
        val validAmount = PagoHelper.ValidAmount(num)
        val actualBackgroundColor = if (validAmount) primary else Color.LightGray
        val textColor = if (validAmount) Color.White else Color.White


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heightButton)
            ) {
                CustomButton(text = "1",
                    modifier = Modifier
                        .width(buttonWidth)
                        .fillMaxHeight()
                        .clickable { num += "1" })
                CustomButton(text = "2",
                    modifier = Modifier
                        .width(buttonWidth)
                        .fillMaxHeight()
                        .clickable { num += "2" })
                CustomButton(text = "3",
                    modifier = Modifier
                        .width(buttonWidth)
                        .fillMaxHeight()
                        .clickable { num += "3" })
                CustomButton(text = "⌫",
                    backgroundColor = primary,
                    modifier = Modifier
                        .width(buttonWidth)
                        .fillMaxHeight()
                        .clickable { num = num.dropLast(1) })
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(heightButton),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                CustomButton(text = "4",
                    modifier = Modifier
                        .width(buttonWidth)
                        .fillMaxHeight()
                        .clickable { num += "4" })
                CustomButton(text = "5",
                    modifier = Modifier
                        .width(buttonWidth)
                        .fillMaxHeight()
                        .clickable { num += "5" })
                CustomButton(text = "6",
                    modifier = Modifier
                        .width(buttonWidth)
                        .fillMaxHeight()
                        .clickable { num += "6" })
                CustomButton(text = "C",
                    backgroundColor = primary,
                    modifier = Modifier
                        .width(buttonWidth)
                        .fillMaxHeight()
                        .clickable { num = "" })
            }
            Row(
                Modifier
                    .height(heightButton * 2)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(Modifier.height(heightButton)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            CustomButton(text = "7",
                                modifier = Modifier
                                    .width(buttonWidth)
                                    .fillMaxHeight()
                                    .clickable { num += "7" })

                            CustomButton(text = "8",
                                modifier = Modifier
                                    .width(buttonWidth)
                                    .fillMaxHeight()
                                    .clickable { num += "8" })
                            CustomButton(text = "9",
                                modifier = Modifier
                                    .width(buttonWidth)
                                    .fillMaxHeight()
                                    .clickable { num += "9" })

                        }
                    }
                    Row(
                        Modifier.height(heightButton),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        CustomButton(
                            text = "↩", modifier = Modifier
                                .width(buttonWidth)
                                .fillMaxHeight()

                                .clickable { onBack() }, backgroundColor = primary
                        )
                        CustomButton(text = "0",
                            modifier = Modifier
                                .width(buttonWidth)
                                .fillMaxHeight()
                                .clickable { if (num.isNotEmpty()) num += "0" })
                        CustomButton(text = "00",
                            modifier = Modifier
                                .width(buttonWidth)
                                .fillMaxHeight()
                                .clickable { if (num.isNotEmpty()) num += "00" })
                    }
                }
                CustomButton(text = "⏎",
                    backgroundColor = actualBackgroundColor,
                    textColor = textColor,
                    modifier = Modifier
                        .width(buttonWidth)
                        .clickable { if (validAmount) onEnter(num) }
                        .fillMaxHeight())
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 600, heightDp = 400)
@Composable
fun PreviewNumericKeyboard() {
    val num = remember { mutableStateOf("123") }
    val onNum by remember { num }
    Text(text = onNum)
    NumericKeyboard(num, onEnter = { println(it) }, onBack = { println("back") })
}