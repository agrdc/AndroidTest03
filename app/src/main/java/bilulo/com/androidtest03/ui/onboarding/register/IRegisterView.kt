package bilulo.com.androidtest03.ui.onboarding.register

interface IRegisterView {

    interface View {
        fun getName() : String
        fun getCpf() : String
        fun getCep() : String
        fun getState(): String
        fun getAddress() : String
        fun getComplement() : String?
        fun getAddressNumber() : Long
        fun getNeighborhood() : String
        fun getBirthDate(): String
        fun showLoading()
        fun hideLoading()
        fun callbackLoadSuccess()
        fun callbackLoadError()
        fun callbackSaveSuccess()
        fun callbackSaveError(msg: String)
    }

    interface Presenter {
        fun setView(view: View)
        fun clearView()
        fun fetchLocation()
        fun saveUser()
    }

    class EmptyView : View {
        override fun callbackLoadSuccess() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun callbackLoadError() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun callbackSaveSuccess() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun showLoading() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun hideLoading() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getName(): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getCpf(): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getCep(): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getState(): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getAddress(): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getComplement(): String? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getAddressNumber(): Long {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getNeighborhood(): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getBirthDate(): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun callbackSaveError(msg: String) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}