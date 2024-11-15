package com.fadhiil2010.bukucatatan

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.text.CaseMap
import android.util.Log
import kotlin.math.log

class NoteDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                TITLE_COl + " TEXT," +
                CONTENT_COL + " TEXT" + ")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun getAllNotes(): List<Note>{
        val noteList = mutableListOf<Note>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COL))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(TITLE_COl))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(CONTENT_COL))

            Log.d("dbhelper","Fetch id : $id, title : $title")
            val note = Note(id, title, content)
            noteList.add(note)
        }
        cursor.close()
        db.close()
        return  noteList
    }


    fun inserNote(note: Note){


        val values = ContentValues()

        values.put(TITLE_COl, note.Title)
        values.put(CONTENT_COL, note.content)


        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    // below method is to get
    // all data from our database
    fun getName(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }

    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "notesapp.db"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "allnotes"

        // below is the variable for id column
        val ID_COL = "id"

        // below is the variable for name column
        val TITLE_COl = "Title"

        // below is the variable for age column
        val CONTENT_COL = "content"
    }
}
