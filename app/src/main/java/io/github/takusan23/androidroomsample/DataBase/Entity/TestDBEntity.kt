package io.github.takusan23.androidroomsample.DataBase.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TestDBEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // 主キー
    @ColumnInfo(name = "memo") val memo: String
)