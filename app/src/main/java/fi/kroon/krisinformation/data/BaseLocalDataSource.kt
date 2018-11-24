package fi.kroon.krisinformation.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.Single

interface BaseLocalDataSource<in T> {

    /*@Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: List<T>): Single<List<Long>>*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: List<T>): Single<List<Long>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg entity: T): Single<Int>

    @Delete
    fun delete(vararg entity: T)
}