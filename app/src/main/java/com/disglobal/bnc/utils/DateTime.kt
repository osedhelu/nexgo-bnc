package com.disglobal.bnc.utils


import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class iFechahora(
    val hora: String = "",
    val fecha: String = "",
    val week: String? = "",
    val fechaString: String = "",
    val dd: String? = "",
    val LLLL: String? = "",
    val yyyy: String? = ""
)

fun getDatetime(): iFechahora {
    val calendar = Calendar.getInstance()
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH) + 1
    val year = calendar.get(Calendar.YEAR) + 0
    val formattedDate = String.format("%02d%02d%04d", day, month, year)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val week = DateTimeFormatter.ofPattern("EEEE")
        val dd = DateTimeFormatter.ofPattern("dd")
        val LLLL = DateTimeFormatter.ofPattern("LLLL")
        val yyyy = DateTimeFormatter.ofPattern("yyyy")
        val hora = DateTimeFormatter.ofPattern("hh:mm a")
        val fecha = DateTimeFormatter.ofPattern("dd/M/yyyy")

        val current = LocalDateTime.now()
        return iFechahora(
            hora = current.format(hora),
            fecha = current.format(fecha),
            week = current.format(week),
            dd = current.format(dd),
            LLLL = current.format(LLLL),
            yyyy = current.format(yyyy),
            fechaString = formattedDate
        )
    } else {
        val week = SimpleDateFormat("EEEE")
        val dd = SimpleDateFormat("dd")
        val LLLL = SimpleDateFormat("LLLL")
        val yyyy = SimpleDateFormat("yyyy")
        val hora = SimpleDateFormat("hh:mm a")
        val formatterfecha = SimpleDateFormat("dd/M/yyyy")

        return iFechahora(
            hora = hora.format(Date()),
            fecha = formatterfecha.format(Date()),
            week = week.format(Date()),
            dd = dd.format(Date()),
            LLLL = LLLL.format(Date()),
            yyyy = yyyy.format(Date()),
            fechaString = formattedDate
        )

    }
}

fun parseDateString(dateString: String): iFechahora {
    val dateFormat1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
    val dateFormat2 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS", Locale.getDefault())

    val date = try {
        dateFormat1.parse(dateString)
    } catch (e: Exception) {
        dateFormat2.parse(dateString)
    }

    val calendar = Calendar.getInstance()
    calendar.time = date

    val hora = "${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}"
    val fecha = "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${
        calendar.get(Calendar.YEAR)
    }"
    val week = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
    val dd = calendar.getDisplayName(Calendar.DAY_OF_MONTH, Calendar.LONG, Locale.getDefault())
    val LLLL = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
    val yyyy = calendar.get(Calendar.YEAR).toString()

    return iFechahora(hora, fecha, week, dd, LLLL, yyyy)
}

fun getDateToString(dateString: String?): iFechahora {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS", Locale.getDefault())
    val date: Date = if (dateString == null || dateString == "null") {
        Date()
    } else {
        try {
            dateFormat.parse(dateString)
        } catch (e: Exception) {
            val fallbackDateFormat =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
            fallbackDateFormat.parse(dateString)
        } as Date
    }

    val week = SimpleDateFormat("EEEE", Locale.getDefault())
    val dd = SimpleDateFormat("dd", Locale.getDefault())
    val LLLL = SimpleDateFormat("LLLL", Locale.getDefault())
    val yyyy = SimpleDateFormat("yyyy", Locale.getDefault())
    val hora = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val formatterfecha = SimpleDateFormat("dd/M/yyyy", Locale.getDefault())

    return iFechahora(
        hora = hora.format(date),
        fecha = formatterfecha.format(date),
        week = week.format(date),
        dd = dd.format(date),
        LLLL = LLLL.format(date),
        yyyy = yyyy.format(date),
    )
}

fun getDateUTC(): Long {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    return calendar.timeInMillis
}

//fun obtenerFechaActual(): String {
//    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS")
//    val fechaActual = Date()
//    return dateFormat.format(fechaActual)
//}