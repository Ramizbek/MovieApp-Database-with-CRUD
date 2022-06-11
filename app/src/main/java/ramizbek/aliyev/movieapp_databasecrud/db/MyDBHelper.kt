package ramizbek.aliyev.movieapp_databasecrud.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ramizbek.aliyev.movieapp_databasecrud.db.MyDBObject.ABOUT
import ramizbek.aliyev.movieapp_databasecrud.db.MyDBObject.AUTHOR
import ramizbek.aliyev.movieapp_databasecrud.db.MyDBObject.DATE
import ramizbek.aliyev.movieapp_databasecrud.db.MyDBObject.DB_NAME
import ramizbek.aliyev.movieapp_databasecrud.db.MyDBObject.ID
import ramizbek.aliyev.movieapp_databasecrud.db.MyDBObject.NAME
import ramizbek.aliyev.movieapp_databasecrud.db.MyDBObject.TABLE_NAME
import ramizbek.aliyev.movieapp_databasecrud.db.MyDBObject.VERSION

class MyDBHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, VERSION), MyDBInterface {
    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "CREATE TABLE $TABLE_NAME ($ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $NAME TEXT NOT NULL, $AUTHOR TEXT NOT NULL, $ABOUT TEXT NOT NULL, $DATE TEXT NOT NULL)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

    override fun createUser(user: User) {
        val dataBase = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(NAME, user.name)
        contentValue.put(AUTHOR, user.author)
        contentValue.put(ABOUT, user.about)
        contentValue.put(DATE, user.date)
        dataBase.insert(TABLE_NAME, null, contentValue)
        dataBase.close()
    }

    override fun readUser(): ArrayList<User> {
        val list = ArrayList<User>()
        val query = "SELECT * FROM $TABLE_NAME"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val user = User()
                user.id = cursor.getInt(0)
                user.name = cursor.getString(1)
                user.author = cursor.getString(2)
                user.about = cursor.getString(3)
                user.date = cursor.getString(4)
                list.add(user)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun updateUser(user: User): Int {
        val contentValue = ContentValues()
        contentValue.put(ID, user.id)
        contentValue.put(NAME, user.name)
        contentValue.put(AUTHOR, user.author)
        contentValue.put(ABOUT, user.about)
        contentValue.put(DATE, user.date)
        return writableDatabase.update(TABLE_NAME, contentValue, "$ID = ?", arrayOf("${user.id}"))
    }

    override fun deleteUser(user: User) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, "$ID = ?", arrayOf("${user.id}"))
        database.close()
    }
}