package com.example.contacts_mvvmroom.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {

    abstract val contactDAO: ContactDAO

    companion object{
        private var INSTANCE: ContactDatabase?=null
        fun getInstance(context: Context): ContactDatabase {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contacts_db"
                    ).build()
                }
                INSTANCE =instance
                return instance
            }
        }
    }
}