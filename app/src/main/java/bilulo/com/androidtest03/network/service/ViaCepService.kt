package bilulo.com.androidtest03.network.service

import bilulo.com.androidtest03.data.model.Location
import bilulo.com.androidtest03.network.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViaCepService {

    companion object {
        @JvmStatic
        fun getLocationByCep(cep : String, listener : IViaCepResponse<Location?>) {
            val call = RetrofitInitializer().getRetrofitService().getLocationByCep(cep)
            call.enqueue(object : Callback<Location> {
                override fun onResponse(call: Call<Location>, response: Response<Location>) {
                    response.let {
                        when {
                            response.isSuccessful -> listener.onResponseSuccess(response.body())
                            else -> listener.onResponseError(response.errorBody().toString())
                        }
                    }
                }
                override fun onFailure(call: Call<Location>?, t: Throwable?) {
                    listener.onResponseError(t?.message.toString())
                }
            })
        }
    }

}