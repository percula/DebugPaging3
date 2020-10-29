package com.example.debugpaging3

import android.app.Application
import androidx.room.Room

import com.example.debugpaging3.activityfeed.db.AppDatabase
import com.jakewharton.threetenabp.AndroidThreeTen

open class BaseApplication : Application() {

    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "local-database"
        ).fallbackToDestructiveMigration().build()
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        AndroidThreeTen.init(this)
        db
    }

    companion object {
        @JvmStatic
        lateinit var app: BaseApplication
            private set
    }

}