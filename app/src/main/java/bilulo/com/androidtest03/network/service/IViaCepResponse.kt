package bilulo.com.androidtest03.network.service

interface IViaCepResponse<T> {
    fun onResponseSuccess(response: T)
    fun onResponseError(msg: String)
}