package com.osedhelu.apiv3demo

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.nexgo.oaf.apiv3.emv.AidEntity
import com.nexgo.oaf.apiv3.emv.AidEntryModeEnum
import com.nexgo.oaf.apiv3.emv.CapkEntity
import org.w3c.dom.Element
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream

class EmvUtils(var context: Context) {
    companion object {
        val capkList: List<CapkEntity>?
            get() {
                val capkEntityList: MutableList<CapkEntity> = ArrayList()
                val gson = Gson()
                val parser = JsonParser()

                val jsonArray: JsonArray

                val capkJson = readAssetsTxt("emv_capk.json") // Store the result in a variable
                if (capkJson == null) { // Check if the result is null
                    Log.e("EmvUtils", "Failed to read emv_capk.json from assets")
                    return null
                }

                jsonArray = parser.parse(capkJson).asJsonArray

                if (jsonArray == null) {
                    return null
                }

                for (user in jsonArray) {
                    val userBean = gson.fromJson(user, CapkEntity::class.java)
                    capkEntityList.add(userBean)
                }
                return capkEntityList
            }


        val aidList: List<AidEntity>?
            get() {
                val aidEntityList: MutableList<AidEntity> = ArrayList()
                val gson = Gson()
                val parser = JsonParser()
                val jsonArray: JsonArray

                val aidJson = readAssetsTxt("emv_aid.json") // Store the result in a variable
                if (aidJson == null) { // Check if the result is null
                    Log.e("EmvUtils", "Failed to read emv_aid.json from assets")
                    return null
                }

                jsonArray = parser.parse(aidJson).asJsonArray

                if (jsonArray == null) {
                    return null
                }

                for (user in jsonArray) {
                    val userBean = gson.fromJson(user, AidEntity::class.java)
                    val jsonObject = user.asJsonObject
                    if (jsonObject != null) {
                        if (jsonObject["emvEntryMode"] != null) {
                            val emvEntryMode = jsonObject["emvEntryMode"].asInt
                            userBean.aidEntryModeEnum = AidEntryModeEnum.values()[emvEntryMode]
                            Log.d("nexgo", "emvEntryMode" + userBean.aidEntryModeEnum)
                        }
                    }

                    aidEntityList.add(userBean)
                }
                return aidEntityList
            }


        private fun readAssetsTxt(fileName: String): String? {
            try {
                val `is`: InputStream = Companion.context.getAssets().open(fileName)

                val size = `is`.available()
                // Read the entire asset into a local byte buffer.
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                // Convert the buffer into a string.
                return String(buffer, charset("utf-8"))
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return null
        }


        private fun readFile(filePath: String): String? {
            try {
                val `is`: InputStream = FileInputStream(filePath)
                val size = `is`.available()
                // Read the entire asset into a local byte buffer.
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                // Convert the buffer into a string.
                return String(buffer, charset("utf-8"))
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return null
        }


        private fun getElementAttr(element: Element?, attr: String): String {
            if (element == null) return ""
            if (element.hasAttribute(attr)) {
                return element.getAttribute(attr)
            }
            return ""
        }

        private fun getElementsByName(parent: Element, name: String): Array<Element?> {
            val resList = ArrayList<Element>()
            val nl = parent.childNodes
            for (i in 0 until nl.length) {
                val nd = nl.item(i)
                if (nd.nodeName == name) {
                    resList.add(nd as Element)
                }
            }
            val res = arrayOfNulls<Element>(resList.size)
            for (i in resList.indices) {
                res[i] = resList[i]
            }
            return res
        }
    }
}