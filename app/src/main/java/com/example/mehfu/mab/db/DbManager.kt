package com.example.mehfu.mab.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mehfu.mab.models.Sport

class DbManager(val context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        const val DB_NAME = "MySportsDB"
        const val DB_VERSION = 1
        const val TABLE_SPORTS = "sports"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_ISBALLINVOLVED = "is_ball_involved"
        const val COLUMN_DURATION = "duration"
        const val COLUMN_IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS `" + TABLE_SPORTS + "`(\n" +
                "\t`" + COLUMN_ID + "`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`" + COLUMN_NAME + "`\tVARCHAR(255) UNIQUE,\n" +
                "\t`" + COLUMN_DESCRIPTION + "`\tVARCHAR(255),\n" +
                "\t`" + COLUMN_ISBALLINVOLVED + "`\tINTEGER,\n" +
                "\t`" + COLUMN_DURATION + "`\tREAL,\n" +
                "\t`" + COLUMN_IMAGE + "`\tTEXT\n" +
                ");"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val sql = "DROP TABLE IF EXISTS" + TABLE_SPORTS + ";"
        db?.execSQL(sql)
        onCreate(db)
    }

    fun addSportsNews(name: String, desc: String, is_ball_involved: Boolean, duration: Double, image: String): Boolean {

        if(!isSportsExist(name)){
            val cv = ContentValues()
            cv.put(COLUMN_NAME, name)
            cv.put(COLUMN_DESCRIPTION, desc)
            cv.put(COLUMN_ISBALLINVOLVED, is_ball_involved)
            cv.put(COLUMN_DURATION, duration)
            cv.put(COLUMN_IMAGE, image)
            val id = writableDatabase.insertOrThrow(TABLE_SPORTS, null, cv)
            return id != -1L
        }
        return false
    }

    fun isSportsExist(name: String): Boolean{
        val sql = "SELECT * FROM " + TABLE_SPORTS + " WHERE " + COLUMN_NAME +"= ?;"
        val cursor = readableDatabase.rawQuery(sql, arrayOf(name))
        return cursor.moveToFirst()
    }

    fun getAllSports() : List<Sport>?{
        val sql = "SELECT * FROM " + TABLE_SPORTS + ";"
        val sport : MutableList<Sport> = mutableListOf()
        val cursor = readableDatabase.rawQuery(sql, null)
        if(cursor.moveToFirst()){
            do{
              sport.add(Sport(
                  cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                  cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                  cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
                  cursor.getInt(cursor.getColumnIndex(COLUMN_ISBALLINVOLVED)) == 1,
                  cursor.getDouble(cursor.getColumnIndex(COLUMN_DURATION)),
                  cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE))
              ))
            }while (cursor.moveToNext())
        }
        return sport
    }
}






























