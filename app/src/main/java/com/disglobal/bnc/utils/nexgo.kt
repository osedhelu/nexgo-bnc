package com.disglobal.bnc.utils


import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.disglobal.bnc.R
import com.disglobal.bnc.config.SERIAL_DEFAULT
import com.disglobal.bnc.config.iValueCardTransacion
import com.disglobal.bnc.data.local.database.TransCount
import com.disglobal.bnc.data.local.database.TransactionTableDB
import com.disglobal.bnc.DigipayApi.domain.entities.GetInfoAffiliatesResp
import com.disglobal.bnc.nexgobnc
import com.nexgo.common.ByteUtils
import com.nexgo.common.LogUtils
import com.nexgo.oaf.apiv3.SdkResult
import com.nexgo.oaf.apiv3.card.mifare.AuthEntity
import com.nexgo.oaf.apiv3.card.mifare.BlockEntity
import com.nexgo.oaf.apiv3.card.mifare.M1CardOperTypeEnum
import com.nexgo.oaf.apiv3.card.mifare.M1KeyTypeEnum
import com.nexgo.oaf.apiv3.device.printer.AlignEnum
import com.nexgo.oaf.apiv3.device.printer.GrayLevelEnum
import com.nexgo.oaf.apiv3.device.printer.Printer
import com.nexgo.oaf.apiv3.device.reader.CardInfoEntity
import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
import com.nexgo.oaf.apiv3.device.reader.OnCardInfoListener
import java.text.DecimalFormat
import java.util.Locale


fun getSerialPost(context: Context): String {
    return try {
        val deviceEngine = (context.applicationContext as nexgobnc).deviceEngine
        val deviceInfo = deviceEngine!!.deviceInfo
        deviceInfo.sn
    } catch (e: Exception) {
        SERIAL_DEFAULT
    }

}

fun PrintFacture(context: Context) {
    try {
        val deviceEngine = (context.applicationContext as nexgobnc).deviceEngine
        val printer: Printer? = deviceEngine!!.printer
        val FONT_SIZE_SMALL = 16
        val FONT_SIZE_NORMAL = 24
        val FONT_SIZE_BIG = 30

        printer?.setTypeface(Typeface.DEFAULT)
        LogUtils.setDebugEnable(true)
        val forma = DecimalFormat("#,##0.00")
        val data = getDatetime()
        printer!!.initPrinter() //init printer
        printer!!.setTypeface(Typeface.DEFAULT) //change print type
        printer!!.setLetterSpacing(3) //change the line space between each line
        printer!!.setGray(GrayLevelEnum.LEVEL_2) //change print gray
        printer!!.appendPrnStr(
            "texttttt", FONT_SIZE_NORMAL, AlignEnum.CENTER, false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.rif)}: xxxxxxxxxxxxxxxxx",
            FONT_SIZE_NORMAL,
            AlignEnum.CENTER,
            false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.fecha)}:", "xxxxxxfecha", FONT_SIZE_NORMAL, false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.hora)}:", "horaxxxxxxxx", FONT_SIZE_NORMAL, false
        )
        printer!!.appendPrnStr(
            "", "", FONT_SIZE_NORMAL, false
        )

        printer!!.appendPrnStr(
            context.getString(R.string.app_namey), "", FONT_SIZE_NORMAL, false
        )
        printer!!.appendPrnStr(
            context.getString(R.string.cantidad), "TOTAL RESP", FONT_SIZE_NORMAL, false
        )
        printer!!.appendPrnStr(
            context.getString(R.string.monto)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            "Bs. 000000000000",
            FONT_SIZE_NORMAL,
            false
        )

        printer!!.appendPrnStr(
            "", "", FONT_SIZE_BIG, false
        )


        printer!!.startPrint(true) { retCode ->
            if (retCode == -1005) {
                Toast.makeText(
                    context, context.getString(R.string.no_papel_imprimir), Toast.LENGTH_SHORT
                ).show()
            }
            if (retCode == 0) {
                Toast.makeText(
                    context, "Imprimiendo", Toast.LENGTH_SHORT
                ).show()
            }

        }
    } catch (e: Exception) {

    }
}

fun PrintFacturaDetails(context: Context, details: iValueCardTransacion) {
    val commerce = GetInfoAffiliatesResp.getCommerce(context)
    try {
        var deviceEngine = (context.applicationContext as nexgobnc).deviceEngine
        var printer: Printer? = deviceEngine!!.printer
        val FONT_SIZE_SMALL = 16
        val FONT_SIZE_NORMAL = 24
        val FONT_SIZE_BIG = 30
        LogUtils.setDebugEnable(true)
        val forma = DecimalFormat("#,##0.00")
        val date = getDatetime()
        printer!!.initPrinter() //init printer
        printer!!.setTypeface(Typeface.DEFAULT) //change print type
        printer!!.setLetterSpacing(0) //change the line space between each line
        printer!!.setGray(GrayLevelEnum.LEVEL_2) //change print gray
        printer!!.appendPrnStr(
            "${commerce?.name}", FONT_SIZE_NORMAL, AlignEnum.CENTER, false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.rif)}: ${commerce?.taxId}",
            FONT_SIZE_NORMAL,
            AlignEnum.CENTER,
            false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.fecha)}:", date.fecha, FONT_SIZE_NORMAL, false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.hora)}:", date.hora, FONT_SIZE_NORMAL, false
        )
        printer!!.appendPrnStr(
            "", "", FONT_SIZE_NORMAL, false
        )

        printer!!.appendPrnStr(
            context.getString(R.string.app_namey), "", FONT_SIZE_NORMAL, false
        )
        printer!!.appendPrnStr(
            context.getString(R.string.text_telefono_receptor),
            details.telefono,
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            context.getString(R.string.nameBank), details.banco, FONT_SIZE_NORMAL, false
        )
        printer!!.appendPrnStr(
            context.getString(R.string.referencia), details.referencia, FONT_SIZE_NORMAL, false
        )
        printer!!.appendPrnStr(
            context.getString(R.string.monto)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            "Bs. ${forma.format(details.monto)}",
            FONT_SIZE_NORMAL,
            false
        )

        printer!!.appendPrnStr(
            "", "", FONT_SIZE_BIG, false
        )


        printer!!.startPrint(true) { retCode ->
            if (retCode == -1005) {
                Toast.makeText(
                    context, context.getString(R.string.no_papel_imprimir), Toast.LENGTH_SHORT
                ).show()
            }
            if (retCode == 0) {
                Toast.makeText(
                    context, "Imprimiendo", Toast.LENGTH_SHORT
                ).show()
            }

        }
    } catch (e: Exception) {

    }


}

fun PrintFactureTransaction(context: Context, resp: TransactionTableDB) {
    val commerce = GetInfoAffiliatesResp.getCommerce(context)
    try {
        var deviceEngine = (context.applicationContext as nexgobnc).deviceEngine
        var printer: Printer? = deviceEngine!!.printer
        val FONT_SIZE_SMALL = 16
        val FONT_SIZE_NORMAL = 24
        val FONT_SIZE_BIG = 30

        printer?.setTypeface(Typeface.DEFAULT)
        LogUtils.setDebugEnable(true)
        val date = getDatetime()
        printer!!.initPrinter() //init printer
        printer!!.setTypeface(Typeface.DEFAULT) //change print type
        printer!!.setLetterSpacing(3) //change the line space between each line
        printer!!.setGray(GrayLevelEnum.LEVEL_2) //change print gray
        printer!!.appendPrnStr(
            "${commerce?.let { it.name }}", FONT_SIZE_NORMAL, AlignEnum.CENTER, false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.rif)}: ${commerce?.let { it.taxId }}",
            FONT_SIZE_NORMAL,
            AlignEnum.CENTER,
            false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.fecha)}:", date.fecha, FONT_SIZE_NORMAL, false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.hora)}:", date.hora, FONT_SIZE_NORMAL, false
        )
        printer!!.appendPrnStr(
            "", "", FONT_SIZE_NORMAL, false
        )

        printer!!.appendPrnStr(
            context.getString(R.string.app_namey), "", FONT_SIZE_NORMAL, false
        )
        printer!!.appendPrnStr(
            context.getString(R.string.phone)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            "${ConverString(resp.telefono)}",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            context.getString(R.string.cedula)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            "${ConverString(resp.cedula)}",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            context.getString(R.string.ref)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            "${resp.ref}",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            context.getString(R.string.status)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            if (resp.state === true) context.getString(R.string.EXITO) else context.getString(R.string.NOT_EXITO),
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            context.getString(R.string.monto)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            "Bs. ${FormatePriceUtilsDouble(resp.monto)}",
            FONT_SIZE_NORMAL,
            false
        )

        printer!!.appendPrnStr(
            "", "", FONT_SIZE_BIG, false
        )

        printer!!.startPrint(true) { retCode ->
            if (retCode == -1005) {
                Toast.makeText(
                    context, context.getString(R.string.no_papel_imprimir), Toast.LENGTH_SHORT
                ).show()
            }
            if (retCode == 0) {
                Toast.makeText(
                    context, "Imprimiendo", Toast.LENGTH_SHORT
                ).show()
            }

        }
    } catch (e: Exception) {

    }

}

fun PrintDetailsConsolidadoDiario(context: Context, details: TransCount) {
    val commerce = GetInfoAffiliatesResp.getCommerce(context)
    try {
        var deviceEngine = (context.applicationContext as nexgobnc).deviceEngine
        var printer: Printer? = deviceEngine!!.printer
        val FONT_SIZE_SMALL = 16
        val FONT_SIZE_NORMAL = 24
        val FONT_SIZE_BIG = 30
        LogUtils.setDebugEnable(true)
        val forma = DecimalFormat("#,##0.00")
        val date = getDatetime()
        printer!!.initPrinter() //init printer
        printer!!.setTypeface(Typeface.DEFAULT) //change print type
        printer!!.setLetterSpacing(0) //change the line space between each line
        printer!!.setGray(GrayLevelEnum.LEVEL_2) //change print gray
        printer!!.appendPrnStr(
            "${commerce?.name}", FONT_SIZE_NORMAL, AlignEnum.CENTER, false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.rif)}: ${commerce?.taxId}",
            FONT_SIZE_NORMAL,
            AlignEnum.CENTER,
            false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.fecha)}:", date.fecha, FONT_SIZE_NORMAL, false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.hora)}:", date.hora, FONT_SIZE_NORMAL, false
        )
        printer!!.appendPrnStr(
            "", "", FONT_SIZE_NORMAL, false
        )

        printer!!.appendPrnStr(
            context.getString(R.string.app_namey), "", FONT_SIZE_NORMAL, false
        )
//        =================================== Vuelto
        printer!!.appendPrnStr(
            context.getString(R.string.vuelto), "", FONT_SIZE_NORMAL, false
        )
        // printer!!.appendPrnStr(
        //     context.getString(R.string.cantidad),
        //     details.vuelto_total.toString(),
        //     FONT_SIZE_NORMAL,
        //     false
        // )
        // printer!!.appendPrnStr(
        //     context.getString(R.string.monto),
        //     details.vuelto_amount.toString(),
        //     FONT_SIZE_NORMAL,
        //     false
        // )


//        ======================================================== Comprobaciones
        printer!!.appendPrnStr(
            context.getString(R.string.comprobaciones), "", FONT_SIZE_NORMAL, false
        )
        // printer!!.appendPrnStr(
        //     context.getString(R.string.cantidad),
        //     details.comp_total.toString(),
        //     FONT_SIZE_NORMAL,
        //     false
        // )
        // printer!!.appendPrnStr(
        //     context.getString(R.string.monto),
        //     details.comp_amount.toString(),
        //     FONT_SIZE_NORMAL,
        //     false
        // )

        printer!!.appendPrnStr(
            "", "", FONT_SIZE_BIG, false
        )

        printer!!.startPrint(true) { retCode ->
            if (retCode == -1005) {
                Toast.makeText(
                    context, context.getString(R.string.no_papel_imprimir), Toast.LENGTH_SHORT
                ).show()
            }
            if (retCode == 0) {
                Toast.makeText(
                    context, "Imprimiendo", Toast.LENGTH_SHORT
                ).show()
            }

        }
    } catch (e: Exception) {

    }
}


fun PrintFactureTransactionValidarPago(
    context: Context, resp: TransactionTableDB, typeState: String
) {
    val commerce = GetInfoAffiliatesResp.getCommerce(context)
    try {
        val deviceEngine = (context.applicationContext as nexgobnc).deviceEngine
        val printer: Printer? = deviceEngine!!.printer
        val FONT_SIZE_SMALL = 16
        val FONT_SIZE_NORMAL = 24
        val FONT_SIZE_BIG = 30

        printer?.setTypeface(Typeface.DEFAULT)
        LogUtils.setDebugEnable(true)
        val forma = DecimalFormat("#,##0.00")
        val date = getDatetime()
        printer!!.initPrinter() //init printer
        printer!!.setTypeface(Typeface.DEFAULT) //change print type
        printer!!.setLetterSpacing(3) //change the line space between each line
        printer!!.setGray(GrayLevelEnum.LEVEL_2) //change print gray
        printer!!.appendPrnStr(
            "${commerce?.let { it.name }}", FONT_SIZE_NORMAL, AlignEnum.CENTER, false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.rif)}: ${commerce?.let { it.taxId }}",
            FONT_SIZE_NORMAL,
            AlignEnum.CENTER,
            false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.fecha)}:", date.fecha, FONT_SIZE_NORMAL, false
        )

        printer!!.appendPrnStr(
            "", "", FONT_SIZE_NORMAL, false
        )

        printer!!.appendPrnStr(
            "${typeState}", "", FONT_SIZE_NORMAL, false
        )
        printer!!.appendPrnStr(
            context.getString(R.string.phone)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            "${ConverString(resp.telefono)}",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            context.getString(R.string.nameBank), resp.nameBanco, FONT_SIZE_NORMAL, false
        )
        printer!!.appendPrnStr(
            context.getString(R.string.ref)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            "${resp.ref}",
            FONT_SIZE_NORMAL,
            false
        )

        printer!!.appendPrnStr(
            context.getString(R.string.monto)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            "Bs. ${forma.format(resp.monto)}",
            FONT_SIZE_NORMAL,
            false
        )

        printer!!.appendPrnStr(
            "", "", FONT_SIZE_BIG, false
        )

        printer!!.startPrint(true) { retCode ->
            if (retCode == -1005) {
                Toast.makeText(
                    context, context.getString(R.string.no_papel_imprimir), Toast.LENGTH_SHORT
                ).show()
            }
            if (retCode == 0) {
                Toast.makeText(
                    context, "Imprimiendo", Toast.LENGTH_SHORT
                ).show()
            }

        }
    } catch (e: Exception) {

    }

}

fun NFCardNexgo(context: Context, callback: (serialCARD: String) -> Unit) {
    try {
        //step 1 search card
        val activity = (context as Activity)
        val deviceEngine = (context.applicationContext as nexgobnc).deviceEngine
        val cardReader = deviceEngine?.cardReader
        val slotTypes = HashSet<CardSlotTypeEnum>()
        slotTypes.add(CardSlotTypeEnum.RF)

        cardReader?.searchCard(slotTypes, 60, object : OnCardInfoListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onCardInfo(retCode: Int, cardInfo: CardInfoEntity?) {
                val sbCode = java.lang.StringBuilder()
                sbCode.append("$retCode")
                if (cardInfo != null) {
                    //step 2 ,judge if rf card type is Mifare card
                    if (cardInfo.cardExistslot == CardSlotTypeEnum.RF) {
                        var ret = -1
                        val m1CardHandler = deviceEngine!!.m1CardHandler
                        val sbDataCard = java.lang.StringBuilder()
                        var blok1Data: String = ""
                        //step 3 , read UID
                        val uid = m1CardHandler.readUid()

                        //step 4, block authority
                        val authEntity = AuthEntity()
                        authEntity.setUid(uid)
                        authEntity.setKeyType(M1KeyTypeEnum.KEYTYPE_A) //keytype A or B
                        authEntity.setBlkNo(4)
                        authEntity.setPwd(ByteUtils.hexString2ByteArray("FFFFFFFFFFFF"))
                        ret = m1CardHandler.authority(authEntity)
                        if (ret != SdkResult.Success) {
                            return
                        }
                        val blockEntity4 = BlockEntity()
                        callback(uid)
//                    //Escritura datos bloque2 tarjeta Actualizar monto de tarjeta
//                    blockEntity4.setOperType(M1CardOperTypeEnum.INCREMENT)
//                    blockEntity4.setBlkNo(6)
//                    blockEntity4.blkData = ByteUtils.string2ASCIIByteArray(montoNew)
//                    ret = m1CardHandler.writeBlock(blockEntity4)
//                    if (ret != SdkResult.Success) {
//                        return
//                    }

                        //lectura datos bloque4 tarjeta user - PARA LEER BLOQUE
                        blockEntity4.setOperType(M1CardOperTypeEnum.INCREMENT)
                        blockEntity4.setBlkNo(6)
                        ret = m1CardHandler.readBlock(blockEntity4)
                        if (ret != SdkResult.Success) {
                            return
                        }
                        val blok2Data = ByteUtils.asciiByteArray2String(blockEntity4.blkData)

                        println(blok2Data)
//                    val decodeString = blockEntity4


//                    step 6 write block  PARA ESCRIBIR EN BLOQUE

//                    codificar una string usando el codificador Base64
//                    val hash = ByteUtils.b
//                    blockEntity4.setOperType(M1CardOperTypeEnum.INCREMENT)
//                    blockEntity4.setBlkNo(5)
//                    blockEntity4.blkData = hash
//                    println("aaaaaaa1 ${hash}")
//                    println("aaaaaaa2 ${String(hash)}")
//                    ret = m1CardHandler.writeBlock(blockEntity4)
//                    if (ret != SdkResult.Success) {
//                        return
//                    }
//


                    }
                }

            }


            override fun onSwipeIncorrect() {
                activity.runOnUiThread {
                    Toast.makeText(
                        context, "Error", Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onMultipleCards() {
                activity.runOnUiThread {
                    Toast.makeText(
                        context, "Error multicard", Toast.LENGTH_SHORT
                    ).show()
                }
            }


        })
    } catch (e: Exception) {
    }

}

fun ConverString(e: String, init: Int = 3, fin: Int = 2): String {
    try {
        return "${e.substring(0, init)}***${e.substring(e.length - fin)}"
    } catch (err: Exception) {
        return e
    }
}

//fun readCardEMV(ctx: Context) {
//    try {
//        val deviceEngine = (ctx.applicationContext as nexgobnc).deviceEngine
//        deviceEngine.ultralightEV1CardHandler
//
//    } catch (e: Exception) {
//        println("xxxxxx ${e.message}")
//    }
//
//}
