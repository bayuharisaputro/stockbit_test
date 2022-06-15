package com.example.stockbit_test.util

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.stockbit_test.di.component.BaseApp
import com.example.stockbit_test.model.CoinInfo
import com.example.stockbit_test.model.DISPLAY
import com.example.stockbit_test.model.Data
import com.example.stockbit_test.model.USD
import java.lang.Exception
import javax.inject.Inject

class DBHelper @Inject constructor(context: BaseApp) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT," +
                FULL_NAME + " TEXT," +
                PRICE_CHANGES + " TEXT," +
                PRICE_CHANGES_USD + " TEXT," +
                LAST_PRICE + " TEXT" + ")")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addDataList(dataList: ArrayList<Data?>? ): String{
        try {
            val db = this.writableDatabase
            dataList?.forEach {
                val values = ContentValues()

                values.put(NAME, it?.CoinInfo?.Name)
                values.put(FULL_NAME, it?.CoinInfo?.FullName)
                values.put(LAST_PRICE, it?.DISPLAY?.USD?.PRICE)
                values.put(PRICE_CHANGES, it?.DISPLAY?.USD?.CHANGEPCT24HOUR)
                values.put(PRICE_CHANGES_USD, it?.DISPLAY?.USD?.CHANGE24HOUR)
                db.insert(TABLE_NAME, null, values)
            }
            db.close()
            return "Success Add Data"
        } catch (e: Exception) {
            throw e
        }

    }

    fun deleteData(): String {
        val db = this.writableDatabase
        try {
            db.delete(TABLE_NAME, null, null)
            db.close()
            return "Success Delete Data"
        } catch (e: Exception) {
            throw e
        }
    }

    fun getData(): ArrayList<Data?>? {
        val dataList = ArrayList<Data?>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from $TABLE_NAME", null)
        } catch (e: SQLiteException) {
            return ArrayList()
        }

        var name: String
        var fullName: String
        var id: String
        var priceChange: String
        var priceChangePct: String
        var lastPrice: String
        if (cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                name = cursor.getString(cursor.getColumnIndexOrThrow(NAME))
                fullName = cursor.getString(cursor.getColumnIndexOrThrow(FULL_NAME))
                id = cursor.getInt(cursor.getColumnIndexOrThrow(ID)).toString()
                priceChange = cursor.getString(cursor.getColumnIndexOrThrow(PRICE_CHANGES_USD))?:"0.0"
                priceChangePct = cursor.getString(cursor.getColumnIndexOrThrow(PRICE_CHANGES))?:"0.0"
                lastPrice = cursor.getString(cursor.getColumnIndexOrThrow(LAST_PRICE))?:"0.0"

                dataList.add(Data(CoinInfo(Id= id, Name = name, FullName = fullName), DISPLAY = DISPLAY(
                    USD(PRICE = lastPrice, CHANGE24HOUR = priceChange, CHANGEPCT24HOUR = priceChangePct)
                )))
                cursor.moveToNext()
            }
        }
        cursor.close()
        return dataList
    }

    companion object{
        private val DATABASE_NAME = "Stockbit"

        private val DATABASE_VERSION = 4

        val TABLE_NAME = "data_table"

        val ID = "id"

        val NAME = "name"

        val FULL_NAME = "fullname"

        val LAST_PRICE = "lastPrice"

        val PRICE_CHANGES = "priceChange"

        val PRICE_CHANGES_USD = "priceChangeUSD"
    }
}

