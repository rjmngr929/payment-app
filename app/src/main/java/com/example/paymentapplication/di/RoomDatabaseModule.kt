package com.example.paymentapplication.di

import android.content.Context
import androidx.room.Room
import com.example.paymentapplication.R
import com.example.paymentapplication.models.Database.AppDatabase
import com.example.paymentapplication.models.Database.Dao.UserDao
import com.example.paymentapplication.repository.UserRepository
import com.example.paymentapplication.repository.UserRoomDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RoomDatabaseModule {


    @Singleton
    @Provides
    fun provideLocalDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            context.getString(R.string.database_name)
            ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun provideUserRepository(user_dao: UserDao): UserRoomDataRepository = UserRoomDataRepository(userDao = user_dao)
}