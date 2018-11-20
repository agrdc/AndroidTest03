package bilulo.com.androidtest03.ui.edit

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast
import bilulo.com.androidtest03.R
import bilulo.com.androidtest03.data.model.User
import bilulo.com.androidtest03.helper.DateInputMask
import bilulo.com.androidtest03.helper.ValidationHelper
import kotlinx.android.synthetic.main.fragment_form.*
import java.net.URLEncoder

class EditFragment : Fragment(), IEditView.View {
    private lateinit var mPresenter: IEditView.Presenter
    private lateinit var mActivity: Context
    private lateinit var mUser: User

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mActivity = activity!!
        mPresenter = EditPresenter()
        mUser = (mActivity as EditActivity).mUser
        (mPresenter as EditPresenter).setContext(mActivity)
        (mPresenter as EditPresenter).setUser(mUser)
        initBottomButton()
        initListeners()
        setupActionBar()
        loadFormData()
    }

    override fun onStart() {
        super.onStart()
        mPresenter.setView(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.edit_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.ic_menu_edit -> if (validateData()) {
                mPresenter.saveUser()
            }
            android.R.id.home -> {
                startActivity(bilulo.com.androidtest03.ui.list.ListActivity.getActivityIntent(mActivity))
                (mActivity as EditActivity).finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        mPresenter.clearView()
        super.onDestroyView()
    }

    private fun setupActionBar() {
        var actionBar = (mActivity as EditActivity).supportActionBar
        actionBar?.title = context?.getString(R.string.title_edit)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initBottomButton() {
        bottomButton.text = getString(R.string.bottom_button_edit)
    }

    private fun loadFormData() {
        nameEditText.setText(mUser.name)
        cpfEditText.setText(mUser.cpf)
        cepEditText.setText(mUser.cep)
        stateEditText.setText(mUser.state)
        addressEditText.setText(mUser.address)
        numberEditText.setText(mUser.addressNumber.toString())
        complementEditText.setText(mUser.complement)
        birthDateEditText.setText(mUser.birthDate)
        neighborhoodEditText.setText(mUser.neighborhood)
    }

    private fun getMapIntent(address : String) : Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse(String.format("geo:0,0?q=%s",
            URLEncoder.encode(address, "UTF-8"))))
    }

    private fun initListeners() {
        DateInputMask(birthDateEditText).listen()

        bottomButton.setOnClickListener {
            var address = getAddress()+" "+getAddressNumber()+" - "+getNeighborhood()+" - "+getState()
            startActivity(getMapIntent(address))
        }

        nameEditText.setOnFocusChangeListener { v, hasFocus -> clearNameError() }
        cpfEditText.setOnFocusChangeListener { v, hasFocus -> clearCpfError() }
        cepEditText.setOnFocusChangeListener { v, hasFocus -> clearCepError() }
        stateEditText.setOnFocusChangeListener { v, hasFocus -> clearStateError() }
        addressEditText.setOnFocusChangeListener { v, hasFocus -> clearAddressError() }
        complementEditText.setOnFocusChangeListener { v, hasFocus -> clearComplementError() }
        numberEditText.setOnFocusChangeListener { v, hasFocus -> clearNumberError() }
        neighborhoodEditText.setOnFocusChangeListener { v, hasFocus -> clearNeighborhoodError() }
        birthDateEditText.setOnFocusChangeListener { v, hasFocus -> clearBirthDateError() }
    }

    private fun clearBirthDateError() {
        birthDateInputLayout.isErrorEnabled = false
    }

    private fun clearNeighborhoodError() {
        neighborhoodInputLayout.isErrorEnabled = false
    }

    private fun clearNumberError() {
        numberInputLayout.isErrorEnabled = false
    }

    private fun clearComplementError() {
        complementInputLayout.isErrorEnabled = false
    }

    private fun clearAddressError() {
        addressInputLayout.isErrorEnabled = false
    }

    private fun clearStateError() {
        stateInputLayout.isErrorEnabled = false
    }

    private fun clearCepError() {
        cepInputLayout.isErrorEnabled = false
    }

    private fun clearCpfError() {
        cpfInputLayout.isErrorEnabled = false
    }

    private fun clearNameError() {
        nameInputLayout.isErrorEnabled = false
    }

    private fun validateData(): Boolean {
        var bool = validateName()
        bool = validateCpf() && bool
        bool = validateCep() && bool
        bool = validateAddress() && bool
        bool = validateNumber() && bool
        bool = validateNeighborhood() && bool
        bool = validateBirthDate() && bool
        bool = validateState() && bool
        return bool
    }

    private fun validateState(): Boolean {
        return if (!ValidationHelper.isEmpty(stateEditText)) {
            true
        } else {
            stateInputLayout.error = getString(R.string.et_generic_empty_error)
            false
        }
    }

    private fun validateNeighborhood(): Boolean {
        return if (!ValidationHelper.isEmpty(neighborhoodEditText)) {
            true
        } else {
            neighborhoodInputLayout.error = getString(R.string.et_generic_empty_error)
            false
        }
    }

    private fun validateBirthDate(): Boolean {
        return if (!ValidationHelper.isEmpty(birthDateEditText)) {
            true
        } else {
            birthDateInputLayout.error = getString(R.string.et_generic_empty_error)
            false
        }
    }

    private fun validateNumber(): Boolean {
        return if (!ValidationHelper.isEmpty(numberEditText)) {
            true
        } else {
            numberInputLayout.error = getString(R.string.et_generic_empty_error)
            false
        }
    }

    private fun validateAddress(): Boolean {
        return if (!ValidationHelper.isEmpty(addressEditText)) {
            true
        } else {
            addressInputLayout.error = getString(R.string.et_generic_empty_error)
            false
        }
    }

    private fun validateCep(): Boolean {
        if (ValidationHelper.isEmpty(cepEditText)) {
            cepInputLayout.error = getString(R.string.et_generic_empty_error)
            return false
        } else if (!ValidationHelper.isValidCep(cepEditText.text.toString())) {
            cepInputLayout.error = getString(R.string.et_cep_error)
            return false
        }
        return true
    }

    private fun validateCpf(): Boolean {
        if (ValidationHelper.isEmpty(cpfEditText)) {
            cpfInputLayout.error = getString(R.string.et_generic_empty_error)
            return false
        } else if (!ValidationHelper.isValidCpf(cpfEditText.text.toString())) {
            cpfInputLayout.error = getString(R.string.et_cpf_error)
            return false
        }
        return true
    }

    private fun validateName(): Boolean {
        return if (!ValidationHelper.isEmpty(nameEditText)) {
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

    override fun callbackSaveSuccess(msg: String) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_LONG).show()
        startActivity(bilulo.com.androidtest03.ui.list.ListActivity.getActivityIntent(mActivity))
        (mActivity as EditActivity).finish()
    }

    override fun callbackSaveError(msg: String) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_LONG).show()
    }


}