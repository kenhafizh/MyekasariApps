package com.example.myekasari
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

class RealPathUtil {
    companion object {
        fun getRealPath(contentResolver: ContentResolver, uri: Uri): String? {
            var realPath: String? = null
            val projection = arrayOf(MediaStore.Images.Media.DATA)
            val cursor: Cursor? = contentResolver.query(uri, projection, null, null, null)
            cursor?.let {
                if (it.moveToFirst()) {
                    val columnIndex: Int = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    realPath = it.getString(columnIndex)
                }
                it.close()
            }
            return realPath
        }
    }
}
