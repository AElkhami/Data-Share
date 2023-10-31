package com.elkhami.instantdatashare.content_provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import com.elkhami.instantdatashare.data.local.UserDao
import com.elkhami.instantdatashare.data.local.UserDatabase

/**
 * Created by A.Elkhami on 30/10/2023.
 */
class UserProvider: ContentProvider() {

    companion object {
        const val AUTHORITY = "com.elkhami.instantdatashare.content_provider"

        private const val CODE_DIR= 1
        private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)

        init {
            MATCHER.addURI(
                AUTHORITY,
                "user_entity",
                CODE_DIR
            )
        }

    }
    private val userDB by lazy {
        context?.let {
            Room.databaseBuilder(
                it,
                UserDatabase::class.java,
                "user-database"
            ).fallbackToDestructiveMigration().build()
        }
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String?>?
    ): Int {
        return 0
    }

    override fun delete(
        uri: Uri, selection: String?,
        selectionArgs: Array<String?>?
    ): Int {
        return 0
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {

        val code = MATCHER.match(uri)
        return if (code == CODE_DIR) {
            val context = context ?: return null
            val userDao: UserDao? = userDB?.userDao()
            val cursor: Cursor? = userDao?.fetchCursor()
            cursor?.setNotificationUri(context.contentResolver, uri)
            cursor
        } else {
            throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String {
        return when (MATCHER.match(uri)) {
            CODE_DIR -> "vnd.android.cursor.dir/$AUTHORITY.user_entity"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }
}