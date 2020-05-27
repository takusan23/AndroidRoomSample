package io.github.takusan23.androidroomsample.DataBase.Dao

import androidx.room.*
import io.github.takusan23.androidroomsample.DataBase.Entity.TestDBEntity

@Dao
interface TestDBDao {
    /** 全データ取得 */
    @Query("SELECT * FROM TestDBEntity")
    fun getAll(): List<TestDBEntity>

    /** データ更新 */
    @Update
    fun update(testDBEntity: TestDBEntity)

    /** データ追加 */
    @Insert
    fun insert(testDBEntity: TestDBEntity)

    /** データ削除 */
    @Delete
    fun delete(testDBEntity: TestDBEntity)

    /** 指定したidのデータを消す */
    @Query("DELETE FROM TestDBEntity WHERE id = :id")
    fun deleteById(id: Int)

    /** 指定したメモのデータを消す */
    @Query("DELETE FROM TestDBEntity WHERE memo = :memo")
    fun deleteByMemo(memo:String)

}