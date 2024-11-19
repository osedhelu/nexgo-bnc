package com.disglobal.bnc.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import com.disglobal.bnc.ui.theme.primary
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun obtenerFechaActual(
    style: TextStyle = TextStyle(fontStyle = FontStyle.Normal), color: Color = primary
) {
    val currentDateTime = remember { mutableStateOf(Locale.getDefault()) }
    val CalendarGetInstance = remember { mutableStateOf(Calendar.getInstance().time) }

    LaunchedEffect(key1 = true) {
        while (true) {
            currentDateTime.value = Locale.getDefault()
            CalendarGetInstance.value = Calendar.getInstance().time
            delay(1000)
        }
    }
    val formatoFechaHora = SimpleDateFormat("HH:mm:ss", currentDateTime.value)


    val date = getDatetime()

    Text(
        text = "${date.week} ${date.dd} de ${date.LLLL} ${date.yyyy} ${
            formatoFechaHora.format(
                CalendarGetInstance.value
            )
        }", style = style, color = color
    )
}

fun obtenerFechaActual01(): String {
    val formatoFechaHora = SimpleDateFormat("dd/MM/yyyy | HH:mm:ss", Locale("es", "ES"))
    val fechaActual = Calendar.getInstance().time
    return formatoFechaHora.format(fechaActual)
}
