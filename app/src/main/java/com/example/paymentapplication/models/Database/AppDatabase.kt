package com.example.paymentapplication.models.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.paymentapplication.models.Database.Dao.UserDao
import com.example.paymentapplication.models.Database.DataModel.User
import javax.inject.Inject

@Database(entities = [User::class],  version = 1, exportSchema = false)
abstract class  AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}