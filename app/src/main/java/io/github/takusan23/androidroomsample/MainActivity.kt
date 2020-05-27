package io.github.takusan23.androidroomsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import io.github.takusan23.androidroomsample.DataBase.Entity.TestDBEntity
import io.github.takusan23.androidroomsample.DataBase.RoomDB.TestDB
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 読み込み
        loadDB()

        // 読み込みボタン押したら読み込む
        load_button.setOnClickListener {
            loadDB()
        }

        // 書き込む
        add_button.setOnClickListener {
            writeDB()
        }

        // 削除する
        delete_button.setOnClickListener {
            deleteDB()
        }

    }

    private fun deleteDB() {
        val text = editText.text.toString()
        GlobalScope.launch {
            // データベース用意。「TestDB」は実際に作られるデータベースのファイルの名前
            val database =
                Room.databaseBuilder(this@MainActivity, TestDB::class.java, "TestDB").build()
            val dao = database.testDao()
            // 削除する
            dao.deleteByMemo(text)
        }
    }

    /** データクラスに追加する */
    private fun writeDB() {
        val text = editText.text.toString()
        GlobalScope.launch {
            // データベース用意。「TestDB」は実際に作られるデータベースのファイルの名前
            val database =
                Room.databaseBuilder(this@MainActivity, TestDB::class.java, "TestDB").build()
            val dao = database.testDao()
            // 書き込むデータクラス作る
            val data = TestDBEntity(memo = text)
            // 書き込む
            dao.insert(data)
        }
    }

    /** データベースから読み込む */
    private fun loadDB() {
        GlobalScope.launch(Dispatchers.Main) {
            // まっさらに
            memo_textview.text = ""
            // UIスレッドでは実行できないためコルーチン
            val list = withContext(Dispatchers.IO) {
                // データベース用意
                val database =
                    Room.databaseBuilder(this@MainActivity, TestDB::class.java, "TestDB").build()
                val dao = database.testDao()
                dao.getAll()
            }
            // TextViewに表示
            list.forEach {
                memo_textview.append("id=${it.id} | memo=${it.memo}\n")
            }
        }
    }
}
