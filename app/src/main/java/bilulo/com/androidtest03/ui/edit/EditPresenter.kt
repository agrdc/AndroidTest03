package bilulo.com.androidtest03.ui.edit

import android.content.Context
import bilulo.com.androidtest03.R
import bilulo.com.androidtest03.data.model.User
import bilulo.com.androidtest03.data.provider.UserDataProvider
import com.orm.SugarRecord
import kotlin.math.absoluteValue

class EditPresenter : IEditView.Presenter {

    var mView: IEditView.View = IEditView.EmptyView()
    private lateinit var mContext: Context
    private lateinit var mUser: User

    override fun setView(view: IEditView.View) {
        mView = view
    }

    override fun clearView() {
        mView = IEditView.EmptyView()
    }

    override fun saveUser() {
        if (UserDataProvider.saveUser(buildEditUserObject())) {
            mView.callbackSaveSuccess(mContext.getString(R.string.edit_success))
        } else {
            mView.callbackSaveError(mContext.getString(R.string.save_error))
        }
    }

    fun setUser(user: User) {
        mUser = user
    }

    fun setContext (context: Context) {
        mContext = context
    }

    private fun buildEditUserObject(): User {
        var user = SugarRecord.findById(User::class.java, mUser.id.absoluteValue)
        user.name = mView.getName()
        user.cpf = mView.getCpf()
        user.cep = mView.getCep()
        user.state = mView.getState()
        user.address = mView.getAddress()
        user.complement = mView.getComplement()
        user.addressNumber = mView.getAddressNumber()
        user.neighborhood = mView.getNeighborhood()
        user.birthDate = mView.getBirthDate()
        return user
    }
}