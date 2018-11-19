package bilulo.com.androidtest03.ui.list

import bilulo.com.androidtest03.data.model.User

interface IListView {
    interface View {
        fun callbackSuccess(users : List<User>)
        fun callbackEmpty(msg: String)
        fun callbackError(msg : String)
    }

    interface Presenter {
        fun setView(view: View)
        fun clearView()
        fun fetchUsers()
    }

    class EmptyView : View {
        override fun callbackError(msg: String) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun callbackEmpty(string: String) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun callbackSuccess(users: List<User>) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}