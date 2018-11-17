package fi.kroon.krisinformation.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseLocalDataSource<in T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: T): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg entity: T): Int

    @Delete
    fun delete(vararg entity: T)
}