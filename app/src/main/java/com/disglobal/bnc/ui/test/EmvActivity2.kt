//package com.disglobal.bnc.ui.test
//
//import android.app.AlertDialog
//import android.content.Context
//import android.content.DialogInterface
//import android.os.Bundle
//import android.text.InputType
//import android.text.TextUtils
//import android.util.Log
//import android.view.View
//import android.widget.AdapterView
//import android.widget.EditText
//import android.widget.ListView
//import android.widget.SimpleAdapter
//import android.widget.TextView
//import android.widget.Toast
//import com.google.gson.Gson
//import com.nexgo.common.ByteUtils
//import com.nexgo.common.LogUtils
//import com.nexgo.oaf.apiv3.DeviceEngine
//import com.nexgo.oaf.apiv3.SdkResult
//import com.nexgo.oaf.apiv3.device.pinpad.OnPinPadInputListener
//import com.nexgo.oaf.apiv3.device.pinpad.PinAlgorithmModeEnum
//import com.nexgo.oaf.apiv3.device.pinpad.PinKeyboardModeEnum
//import com.nexgo.oaf.apiv3.device.pinpad.PinPadKeyCode
//import com.nexgo.oaf.apiv3.device.reader.CardInfoEntity
//import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
//import com.nexgo.oaf.apiv3.device.reader.OnCardInfoListener
//import com.nexgo.oaf.apiv3.emv.AmexTransDataEntity
//import com.nexgo.oaf.apiv3.emv.CandidateAppInfoEntity
//import com.nexgo.oaf.apiv3.emv.DynamicReaderLimitEntity
//import com.nexgo.oaf.apiv3.emv.EmvDataSourceEnum
//import com.nexgo.oaf.apiv3.emv.EmvEntryModeEnum
//import com.nexgo.oaf.apiv3.emv.EmvHandler2
//import com.nexgo.oaf.apiv3.emv.EmvOnlineResultEntity
//import com.nexgo.oaf.apiv3.emv.EmvProcessFlowEnum
//import com.nexgo.oaf.apiv3.emv.EmvProcessResultEntity
//import com.nexgo.oaf.apiv3.emv.EmvTransConfigurationEntity
//import com.nexgo.oaf.apiv3.emv.OnEmvProcessListener2
//import com.nexgo.oaf.apiv3.emv.PromptEnum
//import com.nexgo.oaf.apiv3.emv.UnionPayTransDataEntity
//import java.text.SimpleDateFormat
//import java.util.Date
//import java.util.Locale
//
//class EmvActivity2 : AppCompatActivity(), OnCardInfoListener, OnEmvProcessListener2,
//    OnPinPadInputListener {
//    private var deviceEngine: DeviceEngine? = null
//    private var emvHandler2: EmvHandler2? = null
//    private var cardNo: String? = null
//    private var pwdText: String? = null
//    private var pwdTv: TextView? = null
//    private var pwdAlertDialog: AlertDialog? = null
//    private var dv: View? = null
//    private var mContext: Context? = null
//    private var emvUtils: EmvUtils? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_emv2)
//        mContext = this
//        deviceEngine = (application as NexgoApplication).deviceEngine
//        emvHandler2 = deviceEngine!!.getEmvHandler2("app2")
//        dv = layoutInflater.inflate(R.layout.dialog_inputpin_layout, null)
//        pwdTv = dv?.findViewById<View>(R.id.input_pin) as TextView
//        pwdAlertDialog = AlertDialog.Builder(this).setView(dv).create()
//        pwdAlertDialog!!.setCanceledOnTouchOutside(false)
//
//        //enable below lines to capture the EMV logs
//        emvHandler2!!.emvDebugLog(true)
//        LogUtils.setDebugEnable(true)
//
//        emvUtils = EmvUtils.build(this@EmvActivity2)
//    }
//
//
//    private fun initEmvAid() {
//        emvHandler2!!.delAllAid()
//        if (emvHandler2!!.aidListNum <= 0) {
//            val aidEntityList = EmvUtils.aidList
//            if (aidEntityList == null) {
//                Log.d("nexgo", "initAID failed")
//                return
//            }
//
//            val i = emvHandler2!!.setAidParaList(aidEntityList)
//            Log.d("nexgo", "setAidParaList $i")
//        } else {
//            Log.d("nexgo", "setAidParaList " + "already load aid")
//        }
//    }
//
//
//    private fun initEmvCapk() {
//        emvHandler2!!.delAllCapk()
//        val capk_num = emvHandler2!!.capkListNum
//        Log.d("nexgo", "capk_num $capk_num")
//        if (capk_num <= 0) {
//            val capkEntityList = EmvUtils.capkList
//            if (capkEntityList == null) {
//                Log.d("nexgo", "initCAPK failed")
//                return
//            }
//            val j = emvHandler2!!.setCAPKList(capkEntityList)
//            Log.d("nexgo", "setCAPKList $j")
//        } else {
//            Log.d("nexgo", "setCAPKList " + "already load capk")
//        }
//    }
//
//
//    fun onClick(view: View) {
//        when (view.id) {
//            R.id.add_aid -> initEmvAid()
//            R.id.add_capk -> initEmvCapk()
//            R.id.start_emv -> inputAmount()
//            R.id.cancel_emv -> emvHandler2!!.emvProcessCancel()
//            R.id.checkaid -> {
//                Log.d("nexgo", "getAidListNum " + emvHandler2!!.aidListNum)
//                Log.d("nexgo", "getCapkListNum " + emvHandler2!!.capkListNum)
//            }
//
//            R.id.checkaiddetail -> {
//                Toast.makeText(this@EmvActivity2, "Please check Logcat", Toast.LENGTH_SHORT).show()
//                val aidEntities = emvHandler2!!.aidList
//                if (aidEntities == null) {
//                    Log.d("nexgo", "AID = null")
//                } else {
//                    for (aidEntity in aidEntities) {
//                        Log.d("nexgo", "AID = " + Gson().toJson(aidEntity))
//                    }
//                }
//
//                val capkEntities = emvHandler2!!.capkList
//                if (capkEntities == null) {
//                    Log.d("nexgo", "capkEntities = null")
//                } else {
//                    for (capkEntity in capkEntities) {
//                        Log.d("nexgo", "AID = " + Gson().toJson(capkEntity))
//                    }
//                }
//            }
//        }
//    }
//
//    private fun startEmvTest() {
//        val cardReader = deviceEngine!!.cardReader
//        val slotTypes = HashSet<CardSlotTypeEnum>()
//        slotTypes.add(CardSlotTypeEnum.ICC1)
//        slotTypes.add(CardSlotTypeEnum.RF)
//        cardReader.searchCard(slotTypes, 60, this)
//        Toast.makeText(this@EmvActivity2, "please insert or tap card", Toast.LENGTH_SHORT).show()
//    }
//
//    var amount: String? = null
//    private fun inputAmount() {
//        runOnUiThread {
//            val et = EditText(mContext)
//            et.inputType = InputType.TYPE_CLASS_NUMBER
//            AlertDialog.Builder(mContext!!).setTitle("Input Amount")
//                .setView(et)
//
//                .setPositiveButton("Confirm", DialogInterface.OnClickListener { dialog, which ->
//                    val input = et.text.toString()
//                    if ("" == input || !TextUtils.isDigitsOnly(input)) {
//                        Toast.makeText(applicationContext, "Format Error$input", Toast.LENGTH_LONG)
//                            .show()
//                        return@OnClickListener
//                    } else {
//                        amount = leftPad(input, 12, '0')
//                        Log.d("nexgo", "amount = $amount")
//                        startEmvTest()
//                    }
//                })
//                .setNegativeButton(
//                    "Cancel",
//                    DialogInterface.OnClickListener { dialog, which -> return@OnClickListener })
//                .show()
//        }
//    }
//
//    private var mExistSlot: CardSlotTypeEnum? = null
//    private val flag = true
//
//
//    override fun onCardInfo(retCode: Int, cardInfo: CardInfoEntity) {
//        if (retCode == SdkResult.Success && cardInfo != null) {
//            mExistSlot = cardInfo.cardExistslot
//            val transData = EmvTransConfigurationEntity()
//            transData.transAmount = amount
//            //            transData.setCashbackAmount("000000000100"); //if support cashback amount
//            transData.emvTransType = 0x00.toByte() //0x00-sale, 0x20-refund,0x09-sale with cashback
//            transData.countryCode = "840" //CountryCode
//            transData.currencyCode = "840" //CurrencyCode, 840 indicate USD dollar
//            transData.termId = "00000001"
//            transData.merId = "000000000000001"
//            transData.transDate =
//                SimpleDateFormat("yyMMdd", Locale.getDefault()).format(Date())
//            transData.transTime =
//                SimpleDateFormat("hhmmss", Locale.getDefault()).format(Date())
//            transData.traceNo = "00000000"
//
//            transData.emvProcessFlowEnum = EmvProcessFlowEnum.EMV_PROCESS_FLOW_STANDARD
//            if (cardInfo.cardExistslot == CardSlotTypeEnum.RF) {
//                transData.emvEntryModeEnum = EmvEntryModeEnum.EMV_ENTRY_MODE_CONTACTLESS
//            } else {
//                transData.emvEntryModeEnum = EmvEntryModeEnum.EMV_ENTRY_MODE_CONTACT
//            }
//
//
//            //set DRL for paypave , test cases :T5_12_01_DRL T5_12_02 T5_12_04 T5_12_05
//            //setPaywaveDrl();
//
//            //set expresspay DRL
//            //setExpressPayDrl();
//            if (isExpressPaySeePhoneTapCardAgain) {
//                val amexTransDataEntity = AmexTransDataEntity()
//                amexTransDataEntity.isExpressPaySeePhoneTapCardAgain = true
//            }
//
//            //for UPI
//            val unionPayTransDataEntity = UnionPayTransDataEntity()
//            unionPayTransDataEntity.setQpbocForGlobal(true)
//            unionPayTransDataEntity.isSupportCDCVM = true
//            //if support QPS, please enable below lines
//            //unionPayTransDataEntity.setSupportContactlessQps(true);
//            //unionPayTransDataEntity.setContactlessQpsLimit("000000030000");
//            transData.unionPayTransDataEntity = unionPayTransDataEntity
//
//
//            //if you want set contactless aid for first select, you can enable below lines. it is only used for contactless
//            //for example, the card have paypass and pure application(paypass priority is highest), but the local bank required use pure application,
//            // in this situation , you can use below method.
////            emvHandler2.contactlessSetAidFirstSelect((byte) 0x07, ByteUtils.hexString2ByteArray("a0000000041010"));
////            emvHandler2.contactlessSetAidFirstSelect((byte) 0x07, ByteUtils.hexString2ByteArray("a0000001524010"));
//            Log.d("nexgo", "start emv ")
//            emvHandler2!!.emvProcess(transData, this)
//        } else {
//            runOnUiThread {
//                Toast.makeText(
//                    this@EmvActivity2,
//                    "search card failed",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//    }
//
//    private fun setExpressPayDrl() {
//        val defaultDynamicReaderLimitEntity = DynamicReaderLimitEntity()
//        defaultDynamicReaderLimitEntity.appProgID = byteArrayOf(0xFF.toByte())
//        defaultDynamicReaderLimitEntity.readerCVMReqLimit =
//            ByteUtils.hexString2ByteArray("000000001000")
//        defaultDynamicReaderLimitEntity.readerContactlessTransLimit =
//            ByteUtils.hexString2ByteArray("000000001500")
//        defaultDynamicReaderLimitEntity.readerContactlessFloorLimit =
//            ByteUtils.hexString2ByteArray("000000001200")
//
//        val dynamicReaderLimitEntities: MutableList<DynamicReaderLimitEntity> = ArrayList()
//        var dynamicReaderLimitEntity = DynamicReaderLimitEntity()
//        dynamicReaderLimitEntity.appProgID = byteArrayOf(0x06.toByte())
//        dynamicReaderLimitEntity.readerCVMReqLimit = ByteUtils.hexString2ByteArray("000000000200")
//        dynamicReaderLimitEntity.readerContactlessTransLimit =
//            ByteUtils.hexString2ByteArray("000000000700")
//        dynamicReaderLimitEntity.readerContactlessFloorLimit =
//            ByteUtils.hexString2ByteArray("000000000400")
//        dynamicReaderLimitEntities.add(dynamicReaderLimitEntity)
//
//        dynamicReaderLimitEntity = DynamicReaderLimitEntity()
//        dynamicReaderLimitEntity.appProgID = byteArrayOf(0x0B.toByte())
//        dynamicReaderLimitEntity.readerCVMReqLimit =
//            ByteUtils.hexString2ByteArray("000000000200")
//        dynamicReaderLimitEntity.readerContactlessTransLimit =
//            ByteUtils.hexString2ByteArray("000000000300")
//        dynamicReaderLimitEntity.readerContactlessFloorLimit =
//            ByteUtils.hexString2ByteArray("000000000100")
//        dynamicReaderLimitEntities.add(dynamicReaderLimitEntity)
//
//        emvHandler2!!.setDynamicReaderLimitListForExpressPay(
//            defaultDynamicReaderLimitEntity,
//            dynamicReaderLimitEntities
//        )
//    }
//
//
//    private fun setPaywaveDrl() {
//        val dynamicReaderLimitEntity: MutableList<DynamicReaderLimitEntity> = ArrayList()
//
//        val entity = DynamicReaderLimitEntity()
//        entity.isDrlSupport = true
//        entity.appProgID = byteArrayOf(0x31, 0x02, 0x68, 0x26, 0x20) //get from 9f5a
//        entity.isAuthOfZeroCheck = true
//        entity.isStatusCheck = false
//        entity.isReaderCVMReqLimitCheck = true
//        entity.isReaderContactlessFloorLimitCheck = true
//        entity.isReaderContactlessTransLimitCheck = false
//        entity.readerCVMReqLimit = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x50, 0x01)
//        entity.readerContactlessFloorLimit = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x50, 0x00)
//        entity.readerContactlessTransLimit = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x60, 0x01)
//        dynamicReaderLimitEntity.add(entity)
//
//        val entity1 = DynamicReaderLimitEntity()
//        entity1.isDrlSupport = true
//        entity1.appProgID =
//            byteArrayOf(0x31, 0x02, 0x68, 0x26, 0x12, 0x00, 0x00, 0x03) //get from 9f5a
//        entity1.isStatusCheck = false
//        entity1.isAuthOfZeroCheck = true
//        entity1.isReaderCVMReqLimitCheck = true
//        entity1.isReaderContactlessFloorLimitCheck = true
//        entity1.isReaderContactlessTransLimitCheck = false
//        entity1.readerCVMReqLimit = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x50, 0x01)
//        entity1.readerContactlessFloorLimit = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x50, 0x00)
//        entity1.readerContactlessTransLimit = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x60, 0x01)
//        dynamicReaderLimitEntity.add(entity1)
//
//        val entity2 = DynamicReaderLimitEntity()
//        entity2.isDrlSupport = true
//        entity2.appProgID = byteArrayOf(0x31, 0x02, 0x68, 0x26, 0x12) //get from 9f5a
//        entity2.isAuthOfZeroCheck = true
//        entity2.isStatusCheck = false
//        entity2.isReaderCVMReqLimitCheck = true
//        entity2.isReaderContactlessFloorLimitCheck = true
//        entity2.isReaderContactlessTransLimitCheck = false
//        entity2.readerCVMReqLimit = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x50, 0x01)
//        entity2.readerContactlessFloorLimit = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x50, 0x00)
//        entity2.readerContactlessTransLimit = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x60, 0x01)
//        dynamicReaderLimitEntity.add(entity2)
//
//        val entity3 = DynamicReaderLimitEntity()
//        entity3.isDrlSupport = true
//        entity3.appProgID = byteArrayOf(0x31, 0x02, 0x68, 0x26, 0x00) //get from 9f5a
//        entity3.isAuthOfZeroCheck = true
//        entity3.isStatusCheck = false
//        entity3.isReaderCVMReqLimitCheck = true
//        entity3.isReaderContactlessFloorLimitCheck = true
//        entity3.isReaderContactlessTransLimitCheck = false
//        entity3.readerCVMReqLimit = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x50, 0x01)
//        entity3.readerContactlessFloorLimit = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x50, 0x00)
//        entity3.readerContactlessTransLimit = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x60, 0x01)
//        dynamicReaderLimitEntity.add(entity3)
//
//        emvHandler2!!.setDynamicReaderLimitListForPaywave(dynamicReaderLimitEntity)
//    }
//
//    override fun onSwipeIncorrect() {
//        runOnUiThread {
//            Toast.makeText(
//                this@EmvActivity2,
//                "please search card again",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }
//
//    override fun onMultipleCards() {
//        //cardReader.stopSearch(); //before next search card, please stopSearch first
//
//        runOnUiThread {
//            Toast.makeText(this@EmvActivity2, "please tap one card", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun leftPad(str: String?, size: Int, padChar: Char): String {
//        val padded = StringBuilder(str ?: "")
//        while (padded.length < size) {
//            padded.insert(0, padChar)
//        }
//        return padded.toString()
//    }
//
//
//    override fun onSelApp(
//        appNameList: List<String>,
//        appInfoList: List<CandidateAppInfoEntity>,
//        isFirstSelect: Boolean
//    ) {
//        Log.d("nexgo", "onAfterFinalSelectedApp->")
//
//        runOnUiThread {
//            val dv = layoutInflater.inflate(R.layout.dialog_app_list, null)
//            val alertDialog = AlertDialog.Builder(
//                mContext!!
//            ).setView(dv).create()
//            val lv = dv.findViewById<View>(R.id.aidlistView) as ListView
//            val listItem: MutableList<Map<String, String?>> = ArrayList()
//            for (i in appNameList.indices) {
//                val map: MutableMap<String, String?> = HashMap()
//                map["appIdx"] = (i + 1).toString() + ""
//                map["appName"] = appNameList[i]
//                listItem.add(map)
//            }
//            val adapter = SimpleAdapter(
//                mContext,
//                listItem,
//                R.layout.app_list_item,
//                arrayOf("appIdx", "appName"),
//                intArrayOf(R.id.tv_appIndex, R.id.tv_appName)
//            )
//            lv.adapter = adapter
//            lv.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
//                emvHandler2!!.onSetSelAppResponse(position + 1)
//                alertDialog.dismiss()
//                alertDialog.cancel()
//            }
//            alertDialog.setCanceledOnTouchOutside(false)
//            alertDialog.show()
//        }
//    }
//
//
//    private fun configPaywaveParameters() {
////        byte[] TTQ ;
////        byte[] kernelTTQ = emvHandler2.getTlv(ByteUtils.hexString2ByteArray("9F66"), EmvDataSourceEnum.FROM_KERNEL);
////        Log.d("nexgo",  "configPaywaveParameters, TTQ" + ByteUtils.byteArray2HexString(kernelTTQ));
////        //default TTQ value
////        TTQ = ByteUtils.hexString2ByteArray("36004000");
////        kernelTTQ[0] = TTQ[0];
////        kernelTTQ[2] = TTQ[2];
////        kernelTTQ[3] = TTQ[3];
////
////        // FIXME: 2019/3/20
////        //If there is no special requirements, do not change TTQ byte1
////        //if online force required , can set byte2 bit 8 = 1
////        emvHandler2.setTlv(ByteUtils.hexString2ByteArray("9F66"), kernelTTQ);
//    }
//
//
//    private fun configPaypassParameter(aid: ByteArray) {
//        //kernel configuration, enable RRP and cdcvm
//        emvHandler2!!.setTlv(
//            byteArrayOf(0xDF.toByte(), 0x81.toByte(), 0x1B.toByte()),
//            byteArrayOf(0x30.toByte())
//        )
//
//        //EMV MODE :amount >contactless cvm limit, set 60 = online pin and signature
//        emvHandler2!!.setTlv(
//            byteArrayOf(0xDF.toByte(), 0x81.toByte(), 0x18.toByte()),
//            byteArrayOf(0x60.toByte())
//        )
//        //EMV mode :amount < contactless cvm limit, set 08 = no cvm
//        emvHandler2!!.setTlv(
//            byteArrayOf(0xDF.toByte(), 0x81.toByte(), 0x19.toByte()),
//            byteArrayOf(0x08.toByte())
//        )
//
//        if (ByteUtils.byteArray2HexString(aid).uppercase(Locale.getDefault())
//                .contains("A0000000043060")
//        ) {
//            Log.d("nexgo", "======maestro===== ")
//            //maestro only support online pin
//            emvHandler2!!.setTlv(
//                byteArrayOf(0x9F.toByte(), 0x33.toByte()),
//                byteArrayOf(0xE0.toByte(), 0x40.toByte(), 0xC8.toByte())
//            )
//            emvHandler2!!.setTlv(
//                byteArrayOf(0xDF.toByte(), 0x81.toByte(), 0x18.toByte()),
//                byteArrayOf(0x40.toByte())
//            )
//            emvHandler2!!.setTlv(
//                byteArrayOf(0xDF.toByte(), 0x81.toByte(), 0x19.toByte()),
//                byteArrayOf(0x08.toByte())
//            )
//        }
//
//        //for MTIP paypass test case, can enable below lines for configuration
////        byte[] t_50 = emvHandler.getTlv(ByteUtils.hexString2ByteArray("50"), EmvDataSourceEnum.FROM_KERNEL);
////        if(t_50 != null){
////            if(ByteUtils.byteArray2HexString(t_50).toUpperCase().equalsIgnoreCase("4D434431392076312031")){
////                //MCD 19 V1.1
////                emvHandler.setTlv(new byte[]{(byte) 0x9F, (byte) 0x1d}, ByteUtils.hexString2ByteArray("6C00000000000000"));
////            }else if(ByteUtils.byteArray2HexString(t_50).toUpperCase().equalsIgnoreCase("4D534931392076312031")){
////                //MSI 19 V1.1
////                emvHandler.setTlv(new byte[]{(byte) 0x9F, (byte) 0x1d}, ByteUtils.hexString2ByteArray("4C00800000000000"));
////            }else if(ByteUtils.byteArray2HexString(t_50).toUpperCase().equalsIgnoreCase("4D534939322076312030")){
////                //MSI92 v1 0
////                emvHandler.setTlv(new byte[]{(byte) 0xDF, (byte) 0x81, (byte) 0x1B}, new byte[]{(byte) 0xB0});
////            }
////
////        }
//    }
//
//    private fun configExpressPayParameter() {
//        val kernelTTC = emvHandler2!!.getTlv(
//            ByteUtils.hexString2ByteArray("9F6E"),
//            EmvDataSourceEnum.FROM_KERNEL
//        )
//
//        //set terminal capability...
//        val TTC = ByteUtils.hexString2ByteArray("D8C00000")
//        kernelTTC[1] = TTC[1]
//
//        emvHandler2!!.setTlv(ByteUtils.hexString2ByteArray("9F6E"), kernelTTC)
//
//        //
////        //TacDefault
////        emvHandler2.setTlv(ByteUtils.hexString2ByteArray("DF8120"), ByteUtils.hexString2ByteArray("fc50b8a000"));
////
////        //TacDecline
////        emvHandler2.setTlv(ByteUtils.hexString2ByteArray("DF8121"), ByteUtils.hexString2ByteArray("0000000000"));
////
////        //TacOnline
////        emvHandler2.setTlv(ByteUtils.hexString2ByteArray("DF8122"), ByteUtils.hexString2ByteArray("fc50808800"));
//    }
//
//    private fun configPureContactlessParameter() {
//        Log.d("nexgo", "configPureContactlessParameter")
//
//        //        emvHandler2.setPureKernelCapab(ByteUtils.hexString2ByteArray("3400400A99"));
//    }
//
//    private fun configJcbContactlessParameter() {
//        Log.d("nexgo", "configJcbContactlessParameter")
//    }
//
//    override fun onTransInitBeforeGPO() {
//        Log.d("nexgo", "onAfterFinalSelectedApp")
//        val aid = emvHandler2!!.getTlv(byteArrayOf(0x4F), EmvDataSourceEnum.FROM_KERNEL)
//
//        //contactless
//        if (mExistSlot == CardSlotTypeEnum.RF) {
//            if (aid != null) {
//                if (ByteUtils.byteArray2HexString(aid).uppercase(Locale.getDefault())
//                        .contains("A000000004")
//                ) {
//                    //Paypass
//                    configPaypassParameter(aid)
//                } else if (ByteUtils.byteArray2HexString(aid).uppercase(Locale.getDefault())
//                        .contains("A000000003")
//                ) {
//                    //Paywave
////                    configPaywaveParameters();
//                } else if (ByteUtils.byteArray2HexString(aid).uppercase(Locale.getDefault())
//                        .contains("A000000025")
//                ) {
//                    //ExpressPay
////                    configExpressPayParameter();
//                } else if (ByteUtils.byteArray2HexString(aid).uppercase(Locale.getDefault())
//                        .contains("A000000541")
//                ) {
//                    //configPureContactlessParameter();
//                } else if (ByteUtils.byteArray2HexString(aid).uppercase(Locale.getDefault())
//                        .contains("A000000065")
//                ) {
//                    //configJcbContactlessParameter();
//                }
//            }
//        } else {
//            //contact terminal capability ; if different card brand(depend on aid) have different terminal capability
////            if(ByteUtils.byteArray2HexString(aid).toUpperCase().contains("A000000004")){
////                emvHandler2.setTlv(new byte[]{(byte)0x9F,(byte)0x33}, new byte[]{(byte)0xE0,(byte)0xF8,(byte)0xC8});
////            }
//        }
//
//
//        emvHandler2!!.onSetTransInitBeforeGPOResponse(true)
//    }
//
//    override fun onContactlessTapCardAgain() {
//        Log.d("nexgo", "onReadCardAgain")
//
//        //this method only used for EMV contactless card if the host response the script. Such as paywave , AMEX...
//
//        //for paywave, onOnlineProc-->onSetOnlineProcResponse->onContactlessTapCardAgain--> search contactless card ->onReadCardAgainResponse->onFinish
//
////        emvHandler.onSetReadCardAgainResponse(true);
//    }
//
//
//    override fun onConfirmCardNo(cardInfo: CardInfoEntity) {
//        Log.d("nexgo", "onConfirmCardNo")
//        Log.d("nexgo", "onConfirmCardNo" + cardInfo.tk2)
//        Log.d("nexgo", "onConfirmCardNo" + cardInfo.cardNo)
//        if (mExistSlot == CardSlotTypeEnum.RF) {
//            emvHandler2!!.onSetConfirmCardNoResponse(true)
//            return
//        }
//
//        runOnUiThread {
//            cardNo = cardInfo.cardNo
//            val alertDialog = AlertDialog.Builder(
//                mContext!!
//            )
//                .setTitle("Please Confirm Card Number")
//                .setMessage(cardNo)
//                .setPositiveButton("Confirm") { dialog, which ->
//                    emvHandler2!!.onSetConfirmCardNoResponse(
//                        true
//                    )
//                }
//                .setNegativeButton("Cancel") { dialog, which ->
//                    emvHandler2!!.onSetConfirmCardNoResponse(
//                        false
//                    )
//                }
//                .create()
//            alertDialog.setCanceledOnTouchOutside(false)
//            alertDialog.show()
//        }
//    }
//
//    override fun onCardHolderInputPin(isOnlinePin: Boolean, leftTimes: Int) {
//        Log.d("nexgo", "onCardHolderInputPin isOnlinePin = $isOnlinePin")
//        Log.d("nexgo", "onCardHolderInputPin leftTimes = $leftTimes")
//
//        runOnUiThread { showInputPin(isOnlinePin) }
//    }
//
//
//    override fun onRemoveCard() {
//        Log.d("nexgo", "onRemoveCard")
//
//        emvHandler2!!.onSetRemoveCardResponse()
//    }
//
//
//    override fun onPrompt(promptEnum: PromptEnum) {
//        Log.d("nexgo", "onPrompt->$promptEnum")
//        emvHandler2!!.onSetPromptResponse(true)
//    }
//
//
//    override fun onOnlineProc() {
//        Log.d("nexgo", "onOnlineProc")
//
//        Log.d("nexgo", "getEmvContactlessMode:" + emvHandler2!!.emvContactlessMode)
//        Log.d("nexgo", "getcardinfo:" + Gson().toJson(emvHandler2!!.emvCardDataInfo))
//        Log.d("nexgo", "getEmvCvmResult:" + emvHandler2!!.emvCvmResult)
//        Log.d("nexgo", "getSignNeed--" + emvHandler2!!.signNeed)
//
//        val tlv_5A = emvHandler2!!.getTlv(byteArrayOf(0x5A.toByte()), EmvDataSourceEnum.FROM_KERNEL)
//        Log.d("nexgo", "tlv_5A--" + ByteUtils.byteArray2HexString(tlv_5A))
//
//        val tlv_95 = emvHandler2!!.getTlv(byteArrayOf(0x95.toByte()), EmvDataSourceEnum.FROM_KERNEL)
//        Log.d("nexgo", "tlv_95--" + ByteUtils.byteArray2HexString(tlv_95))
//
//
//        val tlv_84 = emvHandler2!!.getTlv(byteArrayOf(0x84.toByte()), EmvDataSourceEnum.FROM_KERNEL)
//        Log.d("nexgo", "tlv_84--" + ByteUtils.byteArray2HexString(tlv_84))
//
//        val tlv_50 = emvHandler2!!.getTlv(byteArrayOf(0x50.toByte()), EmvDataSourceEnum.FROM_KERNEL)
//        Log.d("nexgo", "tlv_50--" + ByteUtils.byteArray2HexString(tlv_50))
//
//        val emvOnlineResult = EmvOnlineResultEntity()
//        emvOnlineResult.authCode = "123450"
//        emvOnlineResult.rejCode = "00"
//        //fill with the host response 55 field EMV data to do second auth, the format should be TLV format.
//        // for example: 910870741219600860008a023030  91 = tag, 08 = len, 7074121960086000 = value;
//        // 8a = tag, 02 = len, 3030 = value
//        emvOnlineResult.recvField55 = null
//        emvHandler2!!.onSetOnlineProcResponse(SdkResult.Success, emvOnlineResult)
//    }
//
//    private var isExpressPaySeePhoneTapCardAgain = false
//    override fun onFinish(retCode: Int, entity: EmvProcessResultEntity) {
//        Log.d("nexgo", "onFinishretCode :$retCode")
//
//        var flag = false
//        val aid = emvHandler2!!.getTlv(byteArrayOf(0x4F), EmvDataSourceEnum.FROM_KERNEL)
//        if (aid != null) {
//            if (mExistSlot == CardSlotTypeEnum.RF) {
//                if (ByteUtils.byteArray2HexString(aid).uppercase(Locale.getDefault())
//                        .contains("A000000025")
//                ) {
//                    if (retCode == SdkResult.Emv_Plz_See_Phone) {
//                        isExpressPaySeePhoneTapCardAgain = true
//                        flag = true
//                    }
//                }
//            }
//        }
//        if (!flag) {
//            isExpressPaySeePhoneTapCardAgain = false
//        }
//
//        Log.d("nexgo", "emvHandler2.getSignNeed()--" + emvHandler2!!.signNeed)
//
//        Log.d("nexgo", "getcardinfo:" + Gson().toJson(emvHandler2!!.emvCardDataInfo))
//
//        Log.d("nexgo", "getEmvCvmResult:" + emvHandler2!!.emvCvmResult)
//
//        val tlv_5A = emvHandler2!!.getTlv(byteArrayOf(0x5A.toByte()), EmvDataSourceEnum.FROM_KERNEL)
//        Log.d("nexgo", "tlv_5A--" + ByteUtils.byteArray2HexString(tlv_5A))
//
//        val tlv_95 = emvHandler2!!.getTlv(byteArrayOf(0x95.toByte()), EmvDataSourceEnum.FROM_KERNEL)
//        Log.d("nexgo", "tlv_95--" + ByteUtils.byteArray2HexString(tlv_95))
//
//
//        val tlv_84 = emvHandler2!!.getTlv(byteArrayOf(0x84.toByte()), EmvDataSourceEnum.FROM_KERNEL)
//        Log.d("nexgo", "tlv_84--" + ByteUtils.byteArray2HexString(tlv_84))
//
//        val tlv_50 = emvHandler2!!.getTlv(byteArrayOf(0x50.toByte()), EmvDataSourceEnum.FROM_KERNEL)
//        Log.d("nexgo", "tlv_50--" + ByteUtils.byteArray2HexString(tlv_50))
//
//        runOnUiThread {
//            Toast.makeText(
//                this@EmvActivity2,
//                retCode.toString() + "",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//
//        when (retCode) {
//            SdkResult.Emv_Success_Arpc_Fail, SdkResult.Success, SdkResult.Emv_Script_Fail -> {}
//            SdkResult.Emv_Qpboc_Offline, SdkResult.Emv_Offline_Accept -> {}
//            SdkResult.Emv_Qpboc_Online -> {}
//            SdkResult.Emv_Candidatelist_Empty, SdkResult.Emv_FallBack -> {}
//            SdkResult.Emv_Arpc_Fail, SdkResult.Emv_Declined -> {}
//            SdkResult.Emv_Cancel -> {}
//            SdkResult.Emv_Offline_Declined -> {}
//            SdkResult.Emv_Card_Block -> {}
//            SdkResult.Emv_App_Block -> {}
//            SdkResult.Emv_App_Ineffect -> {}
//            SdkResult.Emv_App_Expired -> {}
//            SdkResult.Emv_Other_Interface -> {}
//            SdkResult.Emv_Plz_See_Phone -> {}
//            SdkResult.Emv_Terminate -> {}
//            else -> {}
//        }
//    }
//
//    override fun onInputResult(retCode: Int, data: ByteArray) {
//        Log.d("nexgo", "onInputResult->:" + ByteUtils.byteArray2HexStringWithSpace(data))
//
//        runOnUiThread {
//            pwdAlertDialog!!.dismiss()
//            if (retCode == SdkResult.Success || retCode == SdkResult.PinPad_No_Pin_Input || retCode == SdkResult.PinPad_Input_Cancel) {
//                if (data != null) {
//                    val temp = ByteArray(8)
//                    System.arraycopy(data, 0, temp, 0, 8)
//                }
//                emvHandler2!!.onSetPinInputResponse(
//                    retCode != SdkResult.PinPad_Input_Cancel,
//                    retCode == SdkResult.PinPad_No_Pin_Input
//                )
//            } else {
//                Toast.makeText(this@EmvActivity2, "pin enter failed", Toast.LENGTH_SHORT).show()
//                emvHandler2!!.onSetPinInputResponse(false, false)
//            }
//        }
//    }
//
//    override fun onSendKey(keyCode: Byte) {
//        runOnUiThread {
//            if (keyCode == PinPadKeyCode.KEYCODE_CLEAR) {
//                pwdText = ""
//            } else {
//                pwdText += "* "
//            }
//            pwdTv!!.text = pwdText
//        }
//    }
//
//    private fun showInputPin(isOnlinPin: Boolean) {
//        pwdText = ""
//        pwdAlertDialog!!.show()
//        pwdTv!!.text = pwdText
//        val pinPad = deviceEngine!!.pinPad
//        pinPad.setPinKeyboardMode(PinKeyboardModeEnum.FIXED)
//        if (isOnlinPin) {
//            if (cardNo == null) {
////                byte[] track2 = emvHandler2.getTlv(new byte[]{0x57}, EmvDataSourceEnum.FROM_KERNEL);
////                if (track2 != null) {
////                    String strTrack2 = ByteUtils.byteArray2HexString(track2);
////                    int idx = strTrack2.toUpperCase().replace('=', 'D').indexOf('D');
////                    cardNo = strTrack2.substring(0, idx);
////                    Log.d("nexgo", "cardNo :" + cardNo);
////                }
//                cardNo = emvHandler2!!.emvCardDataInfo.cardNo
//            }
//            pinPad.inputOnlinePin(
//                intArrayOf(0x00, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c),
//                60, cardNo!!.toByteArray(), 10, PinAlgorithmModeEnum.ISO9564FMT1, this
//            )
//        } else {
//            pinPad.inputOfflinePin(
//                intArrayOf(0x00, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c),
//                60, this
//            )
//        }
//    }
//
//
//    companion object {
//        fun byte2Char(bytes: ByteArray): String {
//            val sb = StringBuilder()
//            for (i in bytes.indices) {
//                sb.append(Char(bytes[i].toUShort()))
//            }
//            return sb.toString()
//        }
//
//        fun byteArrayToInt(bytes: ByteArray): Int {
//            var value = 0
//            // 由高位到低位
//            for (i in 0..3) {
//                val shift = (4 - 1 - i) * 8
//                value += (bytes[i].toInt() and 0x000000FF) shl shift // 往高位游
//            }
//            return value
//        }
//
//        fun bytesToInt2(src: ByteArray, offset: Int): Int {
//            return (((src[offset].toInt() and 0xFF) shl 24)
//                    or ((src[offset + 1].toInt() and 0xFF) shl 16)
//                    or ((src[offset + 2].toInt() and 0xFF) shl 8)
//                    or (src[offset + 3].toInt() and 0xFF))
//        }
//    }
//}
