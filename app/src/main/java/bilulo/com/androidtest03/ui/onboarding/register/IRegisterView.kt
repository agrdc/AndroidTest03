package bilulo.com.androidtest03.ui.onboarding.register

import bilulo.com.androidtest03.data.model.Location
import bilulo.com.androidtest03.data.model.User

interface IRegisterView {

    interface View {
        fun getName() : String
        fun getCpf() : String
        fun getCep() : String
        fun getState(): String
        fun getAddress() : String
        fun getComplement() : String?
        fun getAddressNumber() : String
        fun getNeighborhood() : String
        fun getBirthDate(): String
        fun callbackLoadSuccess(location : Location)
        fun callbackLoadError(msg: String)
        fun callbackSaveSuccess()
        fun callbackSaveError(msg: String)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun setView(view: View)
        fun clearView()
        fun fetchLocation()
        fun saveUser()
        fun getUser() : User
    }

    class EmptyView : View {
        override fun getName(): String {
            return ""
        }

        override fun getCpf(): String {
            return ""
        }

        override fun getCep(): String {
            return ""
        }

        override fun getState(): String {
            return ""
        }

        override fun getAddress(): String {
            return ""
        }

        override fun getComplement(): String? {
            return ""
        }

        override fun getAddressNumber(): String {
            return ""
        }

        override fun getNeighborhood(): String {
            return ""
        }

        override fun getBirthDate(): String {
            return ""
        }

        override fun showLoading() {
        }

        override fun hideLoading() {
        }

        override fun callbackLoadSuccess(location: Location) {
        }

        override fun callbackLoadError(msg: String) {
        }

        override fun callbackSaveSuccess() {
        }

        override fun callbackSaveError(msg: String) {
        }
    }
}