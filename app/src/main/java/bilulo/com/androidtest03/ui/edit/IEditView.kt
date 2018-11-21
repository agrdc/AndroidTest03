package bilulo.com.androidtest03.ui.edit

interface IEditView {

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
        fun callbackSaveSuccess(msg : String)
        fun callbackSaveError(msg: String)
    }

    interface Presenter {
        fun setView(view: View)
        fun clearView()
        fun saveUser()
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
            return null
        }

        override fun getAddressNumber(): Long {
            return 0
        }

        override fun getNeighborhood(): String {
            return ""
        }

        override fun getBirthDate(): String {
            return ""
        }


        override fun callbackSaveSuccess(msg: String) {

        }

        override fun callbackSaveError(msg: String) {

        }

    }
}