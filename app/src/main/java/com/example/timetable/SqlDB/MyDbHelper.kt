package com.example.timetable.SqlDB

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.timetable.Models.TimeWork

class MyDbHelper (context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),
    MyInterface {

    companion object{
        const val DB_NAME = "TimeWorkDataBase"

        const val DB_VERSION = 1

        const val TABLE_NAME = "MyTable_TimeWork"

        const val MY_ID = "my_id"

        const val MY_DATE = "mydate"

        const val MY_NAME = "myname"

        const val MY_FROM = "myfrom"

        const val MY_UNTIL = "myuntil"

        const val MY_TYPE = "my_type"

        const val MY_PAGER_TYPE = "my_pager_type"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_NAME($MY_ID INTEGER not null primary key autoincrement unique, $MY_DATE text not null, $MY_NAME text not null, $MY_FROM text not null, $MY_UNTIL text not null,$MY_TYPE INTEGER not null, $MY_PAGER_TYPE INTEGER not null)"

        p0!!.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addTimeWork(timeWork: TimeWork) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(MY_DATE,timeWork.date)
        contentValues.put(MY_NAME,timeWork.name)
        contentValues.put(MY_FROM,timeWork.from)
        contentValues.put(MY_UNTIL,timeWork.until)
        contentValues.put(MY_TYPE,timeWork.type)
        contentValues.put(MY_PAGER_TYPE,timeWork.pagerType)
        database.insert(TABLE_NAME,null, contentValues)
        database.close()
    }

    @SuppressLint("Recycle")
    override fun readTimeWork(): ArrayList<TimeWork> {
        val list = ArrayList<TimeWork>()
        val query = "select * from $TABLE_NAME"
        val dataBase = this.readableDatabase
        val cursor = dataBase.rawQuery(query,null)
        if (cursor.moveToFirst()) {
            do {
                val timeWork = TimeWork(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getInt(5)
                )

                list.add(timeWork)

            } while (cursor.moveToNext())
        }
        return list
    }

    override fun updateTimeWork(timeWork: TimeWork) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(MY_ID,timeWork.id)
        contentValues.put(MY_DATE,timeWork.date)
        contentValues.put(MY_NAME,timeWork.name)
        contentValues.put(MY_FROM,timeWork.from)
        contentValues.put(MY_UNTIL,timeWork.until)
        contentValues.put(MY_TYPE,timeWork.type)
        contentValues.put(MY_PAGER_TYPE,timeWork.pagerType)
        database.update(TABLE_NAME,contentValues,"$MY_ID = ?", arrayOf(timeWork.id.toString()))
    }

    override fun deleteTimeWork(timeWork: TimeWork) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME,"$MY_ID = ?", arrayOf(timeWork.id.toString()))
        database.close()
    }
}