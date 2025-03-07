package com.disglobal.bnc.features.common.emv

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.disglobal.bnc.ui.test.EmvUtils
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.device.reader.CardReader
import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
import com.nexgo.oaf.apiv3.emv.EmvHandler2
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repositorio para manejar operaciones EMV
 */
@Singleton
class EmvRepository @Inject constructor(
    private val deviceEngine: DeviceEngine, private val emvUtils: EmvUtils
) {
    private val TAG = "EmvRepository"

    private var emvHandler2: EmvHandler2? = null

    init {
        // Inicializar el manejador EMV
        emvHandler2 = deviceEngine.getEmvHandler2("app2")
    }

    private fun startEmvTest(): CardReader {
        val cardReader = deviceEngine.cardReader
        val slotTypes = HashSet<CardSlotTypeEnum>()
        slotTypes.add(CardSlotTypeEnum.ICC1)
        slotTypes.add(CardSlotTypeEnum.RF)
//        cardReader.searchCard(slotTypes, 60, this)
        return cardReader
    }

    /**
     * Inicializa los AIDs para EMV
     * @return true si la inicialización fue exitosa, false en caso contrario
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun initEmvAid(): Boolean {
        emvHandler2!!.delAllAid()
        if (emvHandler2!!.aidListNum <= 0) {
            val aidEntityList = EmvUtils.aidList
            if (aidEntityList == null) {
                Log.d("nexgo", "initAID failed")
                return false
            }

            val i = emvHandler2!!.setAidParaList(aidEntityList)
            Log.d("nexgo", "setAidParaList $i")
            return true
        } else {
            Log.d("nexgo", "setAidParaList " + "already load aid")
            return false
        }
    }

    /**
     * Inicializa los CAPKs para EMV
     * @return true si la inicialización fue exitosa, false en caso contrario
     */
    fun initEmvCapk(): Boolean {
        emvHandler2!!.delAllCapk()
        val capk_num = emvHandler2!!.capkListNum
        Log.d("nexgo", "capk_num $capk_num")
        if (capk_num <= 0) {
            val capkEntityList = EmvUtils.capkList
            if (capkEntityList == null) {
                Log.d("nexgo", "initCAPK failed")
                return false
            }
            val j = emvHandler2!!.setCAPKList(capkEntityList)
            Log.d("nexgo", "setCAPKList $j")
            return true
        } else {
            Log.d("nexgo", "setCAPKList " + "already load capk")
            return false
        }
    }

    /**
     * Verifica la cantidad de AIDs y CAPKs cargados
     * @return Par con la cantidad de AIDs y CAPKs
     */
    fun checkAid(): Pair<Int, Int> {
        val aidCount = emvHandler2?.aidList?.size ?: 0
        val capkCount = emvHandler2?.capkList?.size ?: 0

        Log.d(TAG, "AID count: $aidCount, CAPK count: $capkCount")
        return Pair(aidCount, capkCount)
    }

    /**
     * Elimina todos los AIDs
     */
    fun deleteAllAids() {
        emvHandler2?.delAllAid()
    }

    /**
     * Elimina todos los CAPKs
     */
    fun deleteAllCapks() {
        emvHandler2?.delAllCapk()
    }
}