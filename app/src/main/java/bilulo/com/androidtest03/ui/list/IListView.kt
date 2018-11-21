package bilulo.com.androidtest03.ui.list

import bilulo.com.androidtest03.data.model.User

interface IListView {
    interface View {
        fun showLoading()
        fun hideLoading()
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
        override fun hideLoading() {
        }

        override fun showLoading() {
        }

        override fun callbackError(msg: String) {
        }

        override fun callbackEmpty(string: String) {
        }

        override fun callbackSuccess(users: List<User>) {
        }
    }
}