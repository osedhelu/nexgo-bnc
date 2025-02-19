package com.disglobal.bnc.ui

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.nexgo.common.ByteUtils
import com.nexgo.common.LogUtils
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.SdkResult
import com.nexgo.oaf.apiv3.device.pinpad.OnPinPadInputListener
import com.nexgo.oaf.apiv3.device.pinpad.PinAlgorithmModeEnum
import com.nexgo.oaf.apiv3.device.pinpad.PinKeyboardModeEnum
import com.nexgo.oaf.apiv3.device.pinpad.PinPad
import com.nexgo.oaf.apiv3.device.pinpad.PinPadKeyCode
import com.nexgo.oaf.apiv3.device.reader.CardInfoEntity
import com.nexgo.oaf.apiv3.device.reader.CardReader
import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
import com.nexgo.oaf.apiv3.device.reader.OnCardInfoListener
import com.nexgo.oaf.apiv3.emv.AidEntity
import com.nexgo.oaf.apiv3.emv.AmexTransDataEntity
import com.nexgo.oaf.apiv3.emv.CandidateAppInfoEntity
import com.nexgo.oaf.apiv3.emv.CapkEntity
import com.nexgo.oaf.apiv3.emv.DynamicReaderLimitEntity
import com.nexgo.oaf.apiv3.emv.EmvDataSourceEnum
import com.nexgo.oaf.apiv3.emv.EmvEntryModeEnum
import com.nexgo.oaf.apiv3.emv.EmvHandler2
import com.nexgo.oaf.apiv3.emv.EmvOnlineResultEntity
import com.nexgo.oaf.apiv3.emv.EmvProcessFlowEnum
import com.nexgo.oaf.apiv3.emv.EmvProcessResultEntity
import com.nexgo.oaf.apiv3.emv.EmvTransConfigurationEntity
import com.nexgo.oaf.apiv3.emv.OnEmvProcessListener2
import com.nexgo.oaf.apiv3.emv.PromptEnum
import com.nexgo.oaf.apiv3.emv.UnionPayTransDataEntity
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Locale
import java.util.Map

@Composable
fun EmvActivity2Screen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val deviceEngine = (context.applicationContext as NexgoApplication).deviceEngine
    val emvHandler2 = deviceEngine.getEmvHandler2("app2")
    var cardNo by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var mExistSlot by remember { mutableStateOf<CardSlotTypeEnum?>(null) }
    var isExpressPaySeePhoneTapCardAgain by remember { mutableStateOf(false) }

    //enable below lines to capture the EMV logs
    emvHandler2.emvDebugLog(true)
    LogUtils.setDebugEnable(true)

    val emvUtils = remember { EmvUtils(context) }

    fun initEmvAid() {
        emvHandler2.delAllAid()
        if (emvHandler2.aidListNum <= 0) {
            val aidEntityList = emvUtils.aidList
            if (aidEntityList == null) {
                Log.d("nexgo", "initAID failed")
                return
            }

            val i = emvHandler2.setAidParaList(aidEntityList)
            Log.d("nexgo", "setAidParaList $i")
        } else {
            Log.d("nexgo", "setAidParaList already load aid")
        }
    }

    fun initEmvCapk() {
        emvHandler2.delAllCapk()
        val capkNum = emvHandler2.capkListNum
        Log.d("nexgo", "capk_num $capkNum")
        if (capkNum <= 0) {
            val capkEntityList = emvUtils.capkList
            if (capkEntityList == null) {
                Log.d("nexgo", "initCAPK failed")
                return
            }
            val j = emvHandler2.setCAPKList(capkEntityList)
            Log.d("nexgo", "setCAPKList $j")
        } else {
            Log.d("nexgo", "setCAPKList already load capk")
        }
    }

    fun startEmvTest() {
        val cardReader = deviceEngine.cardReader
        val slotTypes = HashSet<CardSlotTypeEnum>()
        slotTypes.add(CardSlotTypeEnum.ICC1)
        slotTypes.add(CardSlotTypeEnum.RF)
        cardReader.searchCard(slotTypes, 60, object : OnCardInfoListener {
            override fun onCardInfo(retCode: Int, cardInfo: CardInfoEntity?) {
                if (retCode == SdkResult.Success && cardInfo != null) {
                    mExistSlot = cardInfo.cardExistslot
                    val transData = EmvTransConfigurationEntity()
                    transData.transAmount = amount
                    transData.emvTransType = 0x00.toByte()
                    transData.countryCode = "840"
                    transData.currencyCode = "840"
                    transData.termId = "00000001"
                    transData.merId = "000000000000001"
                    transData.transDate = SimpleDateFormat("yyMMdd", Locale.getDefault()).format(Date())
                    transData.transTime = SimpleDateFormat("hhmmss", Locale.getDefault()).format(Date())
                    transData.traceNo = "00000000"
                    transData.emvProcessFlowEnum = EmvProcessFlowEnum.EMV_PROCESS_FLOW_STANDARD
                    transData.emvEntryModeEnum =
                        if (cardInfo.cardExistslot == CardSlotTypeEnum.RF) EmvEntryModeEnum.EMV_ENTRY_MODE_CONTACTLESS else EmvEntryModeEnum.EMV_ENTRY_MODE_CONTACT

                    val unionPayTransDataEntity = UnionPayTransDataEntity()
                    unionPayTransDataEntity.qpbocForGlobal = true
                    unionPayTransDataEntity.supportCDCVM = true
                    transData.unionPayTransDataEntity = unionPayTransDataEntity

                    Log.d("nexgo", "start emv")
                    emvHandler2.emvProcess(transData, object : OnEmvProcessListener2 {
                        override fun onSelApp(
                            appNameList: MutableList<String>?,
                            appInfoList: MutableList<CandidateAppInfoEntity>?,
                            isFirstSelect: Boolean
                        ) {
                            TODO("Not yet implemented")
                        }

                        override fun onTransInitBeforeGPO() {
                            TODO("Not yet implemented")
                        }

                        override fun onContactlessTapCardAgain() {
                            TODO("Not yet implemented")
                        }

                        override fun onConfirmCardNo(cardInfo: CardInfoEntity?) {
                            TODO("Not yet implemented")
                        }

                        override fun onCardHolderInputPin(isOnlinePin: Boolean, leftTimes: Int) {
                            TODO("Not yet implemented")
                        }

                        override fun onRemoveCard() {
                            TODO("Not yet implemented")
                        }

                        override fun onPrompt(promptEnum: PromptEnum?) {
                            TODO("Not yet implemented")
                        }

                        override fun onOnlineProc() {
                            TODO("Not yet implemented")
                        }

                        override fun onFinish(retCode: Int, entity: EmvProcessResultEntity?) {
                            TODO("Not yet implemented")
                        }

                        override fun onSwipeIncorrect() {
                            TODO("Not yet implemented")
                        }

                        override fun onMultipleCards() {
                            TODO("Not yet implemented")
                        }
                    })
                } else {
                    Toast.makeText(context, "search card failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onSwipeIncorrect() {
                TODO("Not yet implemented")
            }

            override fun onMultipleCards() {
                TODO("Not yet implemented")
            }
        })
        Toast.makeText(context, "please insert or tap card", Toast.LENGTH_SHORT).show()
    }

    fun inputAmount() {
        val et = EditText(context)
        et.inputType = InputType.TYPE_CLASS_NUMBER
        AlertDialog.Builder(context)
            .setTitle("Input Amount")
            .setView(et)
            .setPositiveButton("Confirm") { dialog, which ->
                val input = et.text.toString()
                if (input.isEmpty() || !TextUtils.isDigitsOnly(input)) {
                    Toast.makeText(context, "Format Error$input", Toast.LENGTH_LONG).show()
                    return@setPositiveButton
                } else {
                    amount = leftPad(input, 12, '0')
                    Log.d("nexgo", "amount = $amount")
                    startEmvTest()
                }
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(onClick = { initEmvAid() }) {
            Text("Add AID")
        }
        Button(onClick = { initEmvCapk() }) {
            Text("Add CAPK")
        }
        Button(onClick = {
            Log.d("nexgo", "getAidListNum " + emvHandler2.aidListNum)
            Log.d("nexgo", "getCapkListNum " + emvHandler2.capkListNum)
        }) {
            Text("Check AID Num")
        }
        Button(onClick = {
            Toast.makeText(context, "Please check Logcat", Toast.LENGTH_SHORT).show()
            val aidEntities = emvHandler2.aidList
            if (aidEntities == null) {
                Log.d("nexgo", "AID = null")
            } else {
                for (aidEntity in aidEntities) {
                    Log.d("nexgo", "AID = " + Gson().toJson(aidEntity))
                }
            }

            val capkEntities = emvHandler2.capkList
            if (capkEntities == null) {
                Log.d("nexgo", "capkEntities = null")
            } else {
                for (capkEntity in capkEntities) {
                    Log.d("nexgo", "AID = " + Gson().toJson(capkEntity))
                }
            }
        }) {
            Text("Check AID Detail")
        }
        Button(onClick = { inputAmount() }) {
            Text("Start EMV")
        }
        Button(onClick = { emvHandler2.emvProcessCancel() }) {
            Text("Cancel EMV")
        }
    }
}

private fun leftPad(input: String, size: Int, padChar: Char): String {
    return input.padStart(size, padChar)
}

@Preview(showBackground = true)
@Composable
fun EmvActivity2Preview() {
    EmvActivity2Screen()
}
