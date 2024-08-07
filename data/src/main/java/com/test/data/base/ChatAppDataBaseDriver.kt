package com.test.data.base

import android.app.Application
import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.data.base.DatabaseChat
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Singleton

lateinit var dataBaseChat: DatabaseChat

fun createDBDriver(application: Application){
    val database = application.getDatabasePath("chatApp.db")

    if (!database.exists()) {

        val inputStream = application.assets.open("chatApp.db")
        val outputStream = FileOutputStream(database.absolutePath)

        inputStream.use { input: InputStream ->
            outputStream.use { output: FileOutputStream ->
                input.copyTo(output)
            }
        }
    }

    val basedriver = AndroidSqliteDriver(DatabaseChat.Schema, application, "chatApp.db")
    dataBaseChat = DatabaseChat.invoke(basedriver)

}