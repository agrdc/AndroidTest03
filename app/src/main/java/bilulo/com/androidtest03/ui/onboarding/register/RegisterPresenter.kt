package bilulo.com.androidtest03.ui.onboarding.register

import bilulo.com.androidtest03.data.model.User

class RegisterPresenter : IRegisterView.Presenter {

    var mView : IRegisterView.View = IRegisterView.EmptyView()

    override fun setView(view: IRegisterView.View) {
        mView = view
    }

    override fun clearView() {
        mView = IRegisterView.EmptyView()
    }

    override fun saveUser() {
        var user = buildUserObject()
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