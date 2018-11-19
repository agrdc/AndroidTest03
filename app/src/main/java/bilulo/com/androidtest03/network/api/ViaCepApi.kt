package bilulo.com.androidtest03.network.api

import bilulo.com.androidtest03.data.model.Location
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepApi {

    @GET("{CEP}/json/")
    fun getLocationByCep(@Path("CEP") CEP : String) : Call<Location>

}
