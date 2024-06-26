package com.vr.beerinformation.data.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.vr.beerinformation.data.model.Beer
import com.vr.beerinformation.data.model.DBContract
import com.vr.beerinformation.domain.repository.BDRepository

class BDHelperImpl (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION),BDRepository {

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "BeerReader.db"

        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.BeerEntry.TABLE_NAME + " (" +
                    DBContract.BeerEntry.COLUMN_ID + " TEXT PRIMARY KEY," +
                    DBContract.BeerEntry.COLUMN_NAME + " TEXT," +
                    DBContract.BeerEntry.COLUMN_FIRST_BREWED + " TEXT," +
                    DBContract.BeerEntry.COLUMN_DESCRIPTION + " TEXT," +
                    DBContract.BeerEntry.COLUMN_IMAGE_URL + " TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.BeerEntry.TABLE_NAME
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertBeer(beer: Beer): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues()
        values.apply {
            put(DBContract.BeerEntry.COLUMN_ID, beer.id)
            put(DBContract.BeerEntry.COLUMN_NAME, beer.name)
            put(DBContract.BeerEntry.COLUMN_FIRST_BREWED, beer.first_brewed)
            put(DBContract.BeerEntry.COLUMN_DESCRIPTION, beer.description)
            put(DBContract.BeerEntry.COLUMN_IMAGE_URL, beer.image_url)
        }

        // Insert the new row, returning the primary key value of the new row
        db.insert(DBContract.BeerEntry.TABLE_NAME, null, values)

        return true
    }

    override fun getAllBeer(): ArrayList<Beer> {
        val beer = ArrayList<Beer>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.BeerEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var id : Int
        var name : String
        var firstBrewed : String
        var description : String
        var imageUrl : String


        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                id = cursor.getInt(0)
                name = cursor.getString(1)
                firstBrewed = cursor.getString(2)
                description = cursor.getString(3)
                imageUrl = cursor.getString(4)

                beer.add(Beer(id, name, firstBrewed,description,imageUrl))
                cursor.moveToNext()
            }
        }
        return beer
    }

    override fun saveBeerInDB(listBeer: List<Beer>) {
        val db = writableDatabase
        onUpgrade(db,1,1)
        for (item in listBeer){
            insertBeer(item)
        }
    }
}