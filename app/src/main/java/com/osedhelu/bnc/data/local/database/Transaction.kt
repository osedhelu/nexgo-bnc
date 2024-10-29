package com.osedhelu.bnc.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Index
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(
    tableName = "xtransaction",
    indices = [Index(value = ["ref"], unique = true)]
)
data class TransactionTableDB(
    @ColumnInfo(name = "tipo") val tipo: String,
    @ColumnInfo(name = "cedula") val cedula: String,
    @ColumnInfo(name = "telefono") val telefono: String,
    @ColumnInfo(name = "banco") val banco: String,
    @ColumnInfo(name = "monto") val monto: Double,
    @ColumnInfo(name = "namebanco") val nameBanco: String,
    @ColumnInfo(name = "ref") val ref: String,
    @ColumnInfo(name = "hora") val hora: String,
    @ColumnInfo(name = "fecha") val fecha: String,
    @ColumnInfo(name = "message") val message: String,
    @ColumnInfo(name = "state") var state: Boolean,
    @PrimaryKey(autoGenerate = true) var id: Int? = null
)


@Entity(tableName = "transaction")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "receiptId")
    var receiptId: String,
    @ColumnInfo(name = "rrn")
    var rrn: String,
    @ColumnInfo(name = "statusCode")
    var statusCode: String,
    @ColumnInfo(name = "statusDescription")
    var statusDescription: String,
    @ColumnInfo(name = "amount")
    var amount: String,
    @ColumnInfo(name = "card")
    var card: String,
    @ColumnInfo(name = "ok")
    val ok: Boolean,
    @ColumnInfo(name = "fecha")
    val fecha: String
)

@Entity(tableName = "transCount")
data class TransCount(
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "total") val total: Int,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: Transaction)

    @Query("SELECT * FROM `transaction` ORDER BY id DESC")
    fun getAll(): LiveData<List<Transaction>>

    @Query(
        "SELECT SUM(CASE WHEN tr.ok THEN tr.amount ELSE 0 END) AS amount, 1 AS id, COUNT(CASE WHEN tmpTra.ok THEN 1 END) AS total FROM `transaction` AS tr, `transaction` AS tmpTra"
    )
    fun countResult(): LiveData<TransCount>

    @Query("delete from `transaction` where receiptId = :id")
    fun deleteOneInvoice(id: String)

    @Query("delete from `transaction` where fecha != :fecha")
    fun deleteInvoiceNotToday(fecha: String)
}

