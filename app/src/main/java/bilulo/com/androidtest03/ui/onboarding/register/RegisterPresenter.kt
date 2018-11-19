package bilulo.com.androidtest03.ui.onboarding.register

import android.content.Context
import bilulo.com.androidtest03.R
import bilulo.com.androidtest03.data.model.User
import bilulo.com.androidtest03.data.provider.UserDataProvider


class RegisterPresenter(context: Context) : IRegisterView.Presenter {

    var mView : IRegisterView.View = IRegisterView.EmptyView()
    private val mContext = context

    override fun setView(view: IRegisterView.View) {
        mView = view
    }

    override fun clearView() {
        mView = IRegisterView.EmptyView()
    }

    override fun saveUser() {
        if(UserDataProvider.saveUser(buildUserObject())) {
            mView.callbackSuccess()
        } else {
            mView.callbackSaveError(mContext.getString(R.string.save_error))
        }
    }

    private fun buildUserObject() : User {
        return User(
            mView.getName(), mView.getCpf(),
            mView.getCep(), mView.getState(),
            mView.getAddress(), mView.getComplement(),
            mView.getAddressNumber(), mView.getNeighborhood(), mView.getBirthDate()
        )
    }
}