package com.example.paymentapplication.models.Database.Dao

import androidx.room.*
import com.example.paymentapplication.models.Database.DataModel.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: User)

    @Update
    suspend fun updateUsers(users: User)

    @Delete
    suspend fun deleteUsers(users: User)


    @Query("DELETE FROM user")
    suspend fun nukeTable()

    @Query("SELECT * FROM user WHERE uid = :id")
    suspend fun loadUserById(id: Int): User

    @Query("SELECT * FROM user")
    fun loadUser(): Flow<List<User>>

}