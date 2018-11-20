package bilulo.com.androidtest03.ui.list

import android.content.Context
import bilulo.com.androidtest03.R
import bilulo.com.androidtest03.data.provider.UserDataProvider

class ListPresenter(context:Context) : IListView.Presenter {

    var mView: IListView.View = IListView.EmptyView()
    private val mContext = context

    override fun setView(view: IListView.View) {
        mView = view
    }

    override fun clearView() {
        mView = IListView.EmptyView()
    }

    override fun fetchUsers() {
        //UserDataProvider.removeAllUsers()
        val users = UserDataProvider.listAllUsers()
        if (users == null) {
            mView.callbackError(mContext.getString(R.string.list_error))
        } else {
            if (users.isEmpty()) {
                mView.callbackEmpty(mContext.getString(R.string.list_empty))
            } else {
                mView.callbackSuccess(users)
            }
        }
    }
}