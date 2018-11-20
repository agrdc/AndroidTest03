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


        override fun callbackSaveSuccess(msg: String) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun callbackSaveError(msg: String) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}