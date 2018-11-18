package bilulo.com.androidtest03.ui.onboarding.register

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import bilulo.com.androidtest03.R
import bilulo.com.androidtest03.helper.ValidationHelper.Companion.isEmpty
import bilulo.com.androidtest03.helper.ValidationHelper.Companion.isValidCep
import bilulo.com.androidtest03.helper.ValidationHelper.Companion.isValidCpf
import bilulo.com.androidtest03.ui.onboarding.OnboardingActivity
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(), IRegisterView.View {

    private var mPresenter : IRegisterView.Presenter = RegisterPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupActionBar()
    }

    override fun onStart() {
        mPresenter.setView(this)
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.register_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.ic_menu_add -> if (validateData()) {
                mPresenter.saveUser()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        mPresenter.clearView()
        super.onDestroyView()
    }

    private fun setupActionBar() {
        var actionBar = (activity as OnboardingActivity).supportActionBar
        actionBar?.title = context?.getString(R.string.title_register)
        actionBar?.show()
    }

    private fun validateData(): Boolean {
        var bool = validateName()
        bool = validateCpf() && bool
        bool = validateCep() && bool
        bool = validateAddress() && bool
        bool = validateNumber() && bool
        bool = validateNeighborhood() && bool
        bool = validateBirthDate() && bool
        return bool
    }

    private fun validateNeighborhood(): Boolean {
        return if (!isEmpty(neighborhoodEditText)) {
            true
        } else {
            neighborhoodInputLayout.error = getString(R.string.et_generic_empty_error)
            false
        }
    }

    private fun validateBirthDate(): Boolean {
        return if (!isEmpty(birthDateEditText)) {
            true
        } else {
            birthDateInputLayout.error = getString(R.string.et_generic_empty_error)
            false
        }
    }

    private fun validateNumber(): Boolean {
        return if (!isEmpty(numberEditText)) {
            true
        } else {
            numberInputLayout.error = getString(R.string.et_generic_empty_error)
            false
        }
    }

    private fun validateAddress(): Boolean {
        return if (!isEmpty(addressEditText)) {
            true
        } else {
            addressInputLayout.error = getString(R.string.et_generic_empty_error)
            false
        }
    }

    private fun validateCep(): Boolean {
        if (isEmpty(cepEditText)) {
            cepInputLayout.error = getString(R.string.et_generic_empty_error)
            return false
        } else if (!isValidCep(cepEditText.text.toString())) {
            cepInputLayout.error = getString(R.string.et_cep_error)
            return false
        }
        return true
    }

    private fun validateCpf(): Boolean {
        if (isEmpty(cpfEditText)) {
            cpfInputLayout.error = getString(R.string.et_generic_empty_error)
            return false
        } else if (!isValidCpf(cpfEditText.text.toString())) {
            cpfInputLayout.error = getString(R.string.et_cpf_error)
            return false
        }
        return true
    }

    private fun validateName(): Boolean {
        return if (!isEmpty(nameEditText)) {
            true
        } else {
            nameInputLayout.error = getString(R.string.et_generic_empty_error)
            false
        }
    }

    override fun getName(): String {
        return nameEditText.text.toString()
    }

    override fun getCpf(): String {
        return cpfEditText.text.toString()
    }

    override fun getCep(): String {
        return cepEditText.text.toString()
    }

    override fun getState(): String {
        return stateEditText.text.toString()
    }

    override fun getAddress(): String {
        return addressEditText.text.toString()
    }

    override fun getComplement(): String? {
        return complementEditText.text.toString()
    }

    override fun getAddressNumber(): Long {
        return numberEditText.text.toString().toLong()
    }

    override fun getNeighborhood(): String {
        return neighborhoodEditText.text.toString()
    }

    override fun getBirthDate(): String {
        return birthDateEditText.text.toString()
    }

    override fun callbackSaveError(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
