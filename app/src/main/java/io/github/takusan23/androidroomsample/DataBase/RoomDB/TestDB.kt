package io.github.takusan23.androidroomsample.DataBase.RoomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.takusan23.androidroomsample.DataBase.Dao.TestDBDao
import io.github.takusan23.androidroomsample.DataBase.Entity.TestDBEntity

@Database(entities = [TestDBEntity::class], version = 1)
abstract class TestDB : RoomDatabase() {
    abstract fun testDao(): TestDBDao
}