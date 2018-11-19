package bilulo.com.androidtest03.data.model

import com.google.gson.annotations.SerializedName

class Location {
    @SerializedName("cep")
    var cep: String? = ""
    @SerializedName("logradouro")
    var address: String? = ""
    @SerializedName("complemento")
    var complement: String? = ""
    @SerializedName("bairro")
    var neighborhood: String? = ""
    @SerializedName("localidade")
    var city: String? = ""
    @SerializedName("uf")
    var state: String? = ""
    @SerializedName("unidade")
    var unit: String? = ""
    @SerializedName("ibge")
    var ibge: String? = ""
    @SerializedName("gia")
    var gia: String? = ""
}