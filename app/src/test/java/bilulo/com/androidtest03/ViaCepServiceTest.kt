package bilulo.com.androidtest03

import bilulo.com.androidtest03.data.model.Location
import bilulo.com.androidtest03.network.api.ViaCepApi
import org.junit.Test
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Assert.*


class ViaCepServiceTest {

    private var mockWebServer: MockWebServer? = null
    private var viaCepApi: ViaCepApi? = null

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        viaCepApi = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer!!.url("").toString())
            .client(OkHttpClient.Builder().build())
            .build()
            .create<ViaCepApi>(ViaCepApi::class.java)
    }

    @Test
    fun viaCepApiNotNull() {
        assertNotNull(viaCepApi)
    }

    @Test
    fun getLocationByCep_obtainsLocationObject_validResponse() {
        mockWebServer!!.enqueue(
            MockResponse()
                .setBody(ViaCepServiceTest::class.java.classLoader!!.getResource("viaCepValidJsonResponse.json").readText()).setResponseCode(200)
        )
        val call = viaCepApi!!.getLocationByCep("")
        call.enqueue(object : retrofit2.Callback<Location> {
            override fun onResponse(call: retrofit2.Call<Location>, response: retrofit2.Response<Location>) {
                if (response.isSuccessful) {
                    val location: Location? = response.body()
                    assertNotNull(location)
                }
            }

            override fun onFailure(call: retrofit2.Call<Location>?, t: Throwable?) {
            }
        })
    }

    @Test
    fun getLocationByCep_obtainsLocationObject_emptyResponse() {
        mockWebServer!!.enqueue(
            MockResponse()
                .setBody("").setResponseCode(200)
        )
        val call = viaCepApi!!.getLocationByCep("")
        call.enqueue(object : retrofit2.Callback<Location> {
            override fun onResponse(call: retrofit2.Call<Location>, response: retrofit2.Response<Location>) {
                if (response.isSuccessful) {
                    var location: Location? = response.body()
                    assertNull(location)
                }
            }

            override fun onFailure(call: retrofit2.Call<Location>?, t: Throwable?) {
            }
        })
    }

    @After
    @Throws(Exception::class)
    fun shutDown() {
        mockWebServer?.shutdown()
    }
}