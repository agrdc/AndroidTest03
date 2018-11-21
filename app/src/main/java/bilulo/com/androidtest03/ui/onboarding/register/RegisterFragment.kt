package bilulo.com.androidtest03.ui.onboarding.register

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import bilulo.com.androidtest03.R
import bilulo.com.androidtest03.data.model.Location
import bilulo.com.androidtest03.helper.DateInputMask
import bilulo.com.androidtest03.helper.ValidationHelper.Companion.isEmpty
import bilulo.com.androidtest03.helper.ValidationHelper.Companion.isValidCep
import bilulo.com.androidtest03.helper.ValidationHelper.Companion.isValidCpf
import bilulo.com.androidtest03.ui.list.ListActivity
import bilulo.com.androidtest03.ui.onboarding.OnboardingActivity
import kotlinx.android.synthetic.main.fragment_form.*

class RegisterFragment : Fragment(), IRegisterView.View {

    private val VALUE_WAIT_TIME_MILLIS : Long = 1000
    private lateinit var mPresenter: IRegisterView.Presenter
    private lateinit var mActivity : Context
    private val mHandler = Handler()
    private var runnable : Runnable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mActivity = activity!!
        mPresenter = RegisterPresenter()
        (mPresenter as RegisterPresenter).setContext(mActivity)
        initBottomButton()
        initListeners()
        setupActionBar()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.register_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.ic_menu_add -> if (validateData()) {
                mPresenter.saveUser()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        mPresenter.setView(this)
    }

    override fun onDestroyView() {
        mPresenter.clearView()
        super.onDestroyView()
    }

    private fun initBottomButton() {
        bottomButton.text = getString(R.string.bottom_button_register)
    }

    private fun initListeners() {
        DateInputMask(birthDateEditText).listen()

        cepEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                mHandler.removeCallbacks(runnable)
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mHandler.removeCallbacks(runnable)
            }

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {
                    runnable = Runnable {
                        if (validateCep()) {
                            showLoading()
                            mPresenter.fetchLocation()
                        }
                    }
                    mHandler.postDelayed(runnable, VALUE_WAIT_TIME_MILLIS)
                }
            }
        })

        bottomButton.setOnClickListener {
            mActivity.startActivity(ListActivity.getActivityIntent(mActivity))
            (mActivity as OnboardingActivity).finish()
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
        bool = validateState() && bool
        return bool
    }

    private fun validateState(): Boolean {
        return if (!isEmpty(stateEditText)) {
            true
        } else {
            stateInputLayout.error = getString(R.string.et_generic_empty_error)
            false
        }
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

    private fun populateLocation(location: Location) {
        addressEditText.setText(location.address)
        stateEditText.setText(location.state)
        complementEditText.setText(location.complement)
        neighborhoodEditText.setText(location.neighborhood)
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

    override fun showLoading() {
        (mActivity as OnboardingActivity).window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        loadingRegister.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        (mActivity as OnboardingActivity).window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        loadingRegister.visibility = View.INVISIBLE
    }

    override fun callbackSaveError(msg: String) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_LONG).show()
    }

    override fun callbackSaveSuccess() {
        activity?.startActivity(ListActivity.getActivityIntent(mActivity))
        (mActivity as OnboardingActivity).finish()
    }

    override fun callbackLoadSuccess(location: Location) {
        hideLoading()
        populateLocation(location)
    }

    override fun callbackLoadError(msg: String) {
        hideLoading()
        Toast.makeText(mActivity, msg,Toast.LENGTH_LONG).show()
    }


}
