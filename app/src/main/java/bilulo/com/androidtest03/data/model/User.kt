package bilulo.com.androidtest03.data.model

class User {
    var name: String? = null
    var cpf: String? = null
    var cep: String? = null
    var state: String? = null
    var address: String? = null
    var complement: String? = null
    var addressNumber: Long? = null
    var neighborhood: String? = null
    var birthDate: String? = null

    constructor() {}

    constructor(
        name: String?,
        cpf: String?,
        cep: String?,
        state: String?,
        address: String?,
        complement : String?,
        addressNumber: Long?,
        neighborhood: String?,
        birthDate: String?
    ) {
        this.name = name
        this.cpf = cpf
        this.cep = cep
        this.state = state
        this.address = address
        this.complement = complement
        this.addressNumber = addressNumber
        this.neighborhood = neighborhood
        this.birthDate = birthDate
    }

}
