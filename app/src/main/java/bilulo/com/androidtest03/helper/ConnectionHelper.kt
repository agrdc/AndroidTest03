package bilulo.com.androidtest03.helper

import android.content.Context
import android.net.ConnectivityManager

class ConnectionHelper {

    companion object {
        @JvmStatic
        fun isConnected(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetworkInfo

            return activeNetwork != null && activeNetwork.isConnected
        }
    }

}
