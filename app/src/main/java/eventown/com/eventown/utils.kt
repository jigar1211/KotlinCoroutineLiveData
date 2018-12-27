package eventown.com.eventown

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class utils {


    companion object {

        fun getBitmapFromURL(src: String): Bitmap? {
            return try {

                val url = URL(src)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input = connection.getInputStream()
                val myBitmap = BitmapFactory.decodeStream(input)
                Log.e("Bitmap", "returned")
                myBitmap
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("Exception", e.message)
                null
            }

        }
    }


}