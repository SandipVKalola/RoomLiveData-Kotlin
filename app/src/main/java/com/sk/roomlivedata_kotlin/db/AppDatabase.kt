package com.sk.roomlivedata_kotlin.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.sk.roomlivedata_kotlin.dao.StudentModelDao
import com.sk.roomlivedata_kotlin.model.Student

@Database(entities = [Student::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentModelDao(): StudentModelDao

    companion object {
        var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE =
                        Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "StudentDatabase.db")
                            .allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

    }
}