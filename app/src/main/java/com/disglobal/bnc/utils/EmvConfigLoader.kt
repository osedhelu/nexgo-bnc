package com.disglobal.bnc.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.emv.AidEntity
import com.nexgo.oaf.apiv3.emv.AidEntryModeEnum
import com.nexgo.oaf.apiv3.emv.CapkEntity
import com.nexgo.oaf.apiv3.emv.EmvHandler2
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Clase de utilidad para cargar configuraciones EMV (AID y CAPK) desde archivos JSON
 */
@Singleton
class EmvConfigLoader @Inject constructor(
    private val context: Context,
    private val deviceEngine: DeviceEngine
) {
    private val TAG = "EmvConfigLoader"
    private val emvHandler: EmvHandler2 = deviceEngine.getEmvHandler2("digipay_app")

    /**
     * Inicializa las configuraciones EMV (AID y CAPK)
     * @return true si la inicialización fue exitosa, false en caso contrario
     */
    fun initEmvConfigurations(): Boolean {
        return initEmvAid() && initEmvCapk()
    }

    /**
     * Inicializa las configuraciones AID (Application Identifier)
     * @return true si la inicialización fue exitosa, false en caso contrario
     */
    fun initEmvAid(): Boolean {
        try {
            // Eliminar AIDs existentes
            emvHandler.delAllAid()
            
            // Verificar si ya hay AIDs cargados
            if (emvHandler.aidListNum <= 0) {
                val aidEntityList = loadAidList()
                if (aidEntityList == null || aidEntityList.isEmpty()) {
                    Log.e(TAG, "No se pudieron cargar los AIDs")
                    return false
                }

                val result = emvHandler.setAidParaList(aidEntityList)
                Log.d(TAG, "Resultado de carga de AIDs: $result")
                return result > 0
            } else {
                Log.d(TAG, "AIDs ya cargados: ${emvHandler.aidListNum}")
                return true
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error al inicializar AIDs", e)
            return false
        }
    }

    /**
     * Inicializa las configuraciones CAPK (Certification Authority Public Key)
     * @return true si la inicialización fue exitosa, false en caso contrario
     */
    fun initEmvCapk(): Boolean {
        try {
            // Eliminar CAPKs existentes
            emvHandler.delAllCapk()
            
            // Verificar si ya hay CAPKs cargados
            if (emvHandler.capkListNum <= 0) {
                val capkEntityList = loadCapkList()
                if (capkEntityList == null || capkEntityList.isEmpty()) {
                    Log.e(TAG, "No se pudieron cargar los CAPKs")
                    return false
                }

                val result = emvHandler.setCAPKList(capkEntityList)
                Log.d(TAG, "Resultado de carga de CAPKs: $result")
                return result > 0
            } else {
                Log.d(TAG, "CAPKs ya cargados: ${emvHandler.capkListNum}")
                return true
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error al inicializar CAPKs", e)
            return false
        }
    }

    /**
     * Verifica el número de AIDs y CAPKs cargados
     * @return Par con el número de AIDs y CAPKs cargados
     */
    fun checkAidAndCapk(): Pair<Int, Int> {
        val aidCount = emvHandler.aidListNum
        val capkCount = emvHandler.capkListNum
        Log.d(TAG, "AIDs cargados: $aidCount, CAPKs cargados: $capkCount")
        return Pair(aidCount, capkCount)
    }

    /**
     * Carga la lista de AIDs desde el archivo JSON
     * @return Lista de entidades AID o null si ocurre un error
     */
    @OptIn(ExperimentalStdlibApi::class)
    private fun loadAidList(): List<AidEntity>? {
        try {
            val aidEntityList: MutableList<AidEntity> = ArrayList()
            val gson = Gson()
            val parser = JsonParser()
            
            val aidJson = readAssetsTxt("emv_aid.json")
            if (aidJson == null) {
                Log.e(TAG, "No se pudo leer el archivo emv_aid.json")
                return null
            }

            val jsonArray = parser.parse(aidJson).asJsonArray

            for (element in jsonArray) {
                val aidEntity = gson.fromJson(element, AidEntity::class.java)
                val jsonObject = element.asJsonObject
                
                // Configurar el modo de entrada si está especificado
                if (jsonObject.has("emvEntryMode")) {
                    val emvEntryMode = jsonObject["emvEntryMode"].asInt
                    aidEntity.aidEntryModeEnum = AidEntryModeEnum.entries[emvEntryMode]
                    Log.d(TAG, "Modo de entrada EMV: ${aidEntity.aidEntryModeEnum}")
                }

                aidEntityList.add(aidEntity)
            }
            
            return aidEntityList
        } catch (e: Exception) {
            Log.e(TAG, "Error al cargar la lista de AIDs", e)
            return null
        }
    }

    /**
     * Carga la lista de CAPKs desde el archivo JSON
     * @return Lista de entidades CAPK o null si ocurre un error
     */
    private fun loadCapkList(): List<CapkEntity>? {
        try {
            val capkEntityList: MutableList<CapkEntity> = ArrayList()
            val gson = Gson()
            val parser = JsonParser()
            
            val capkJson = readAssetsTxt("emv_capk.json")
            if (capkJson == null) {
                Log.e(TAG, "No se pudo leer el archivo emv_capk.json")
                return null
            }

            val jsonArray = parser.parse(capkJson).asJsonArray

            for (element in jsonArray) {
                val capkEntity = gson.fromJson(element, CapkEntity::class.java)
                capkEntityList.add(capkEntity)
            }
            
            return capkEntityList
        } catch (e: Exception) {
            Log.e(TAG, "Error al cargar la lista de CAPKs", e)
            return null
        }
    }

    /**
     * Lee un archivo de texto desde los assets
     * @param fileName Nombre del archivo a leer
     * @return Contenido del archivo como String o null si ocurre un error
     */
    private fun readAssetsTxt(fileName: String): String? {
        try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            return String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            Log.e(TAG, "Error al leer el archivo $fileName", e)
            return null
        }
    }
}