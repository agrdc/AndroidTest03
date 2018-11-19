package bilulo.com.androidtest03.ui.onboarding.register

import android.content.Context
import bilulo.com.androidtest03.R
import bilulo.com.androidtest03.data.model.Location
import bilulo.com.androidtest03.data.model.User
import bilulo.com.androidtest03.data.provider.UserDataProvider
import bilulo.com.androidtest03.helper.ConnectionHelper
import bilulo.com.androidtest03.network.service.IViaCepResponse
import bilulo.com.androidtest03.network.service.ViaCepService
import java.lang.StringBuilder


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
            mView.callbackSaveSuccess()
        } else {
            mView.callbackSaveError(mContext.getString(R.string.save_error))
        }
    }

    override fun fetchLocation() {
        if (ConnectionHelper.isConnected(mContext)) {
            ViaCepService.getLocationByCep(formatCep(mView.getCep()), object : IViaCepResponse<Location?> {
                override fun onResponseSuccess(response: Location?) {
                    var location = response
                    mView.callbackLoadSuccess()
                }

                override fun onResponseError() {
                    mView.callbackLoadError()
                }

                override fun onResponseErrorNotFound() {
                    mView.callbackLoadError()
                }
            })
        }
    }

    private fun formatCep(cep: String): String {
        var stringBuilder = StringBuilder(cep)
        stringBuilder.deleteCharAt(5)
        return stringBuilder.toString()
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