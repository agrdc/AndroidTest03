package bilulo.com.androidtest03.ui.onboarding.welcome

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bilulo.com.androidtest03.R
import bilulo.com.androidtest03.ui.onboarding.FragmentType
import bilulo.com.androidtest03.ui.onboarding.OnboardingActivity
import bilulo.com.androidtest03.ui.onboarding.OnboardingActivity.Companion.initFragment
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideActionBar()
        initListeners()
    }

    private fun hideActionBar() {
        (activity as OnboardingActivity).supportActionBar?.hide()
    }

    private fun initListeners() {
        buttonRegister.setOnClickListener {
            initFragment(activity as OnboardingActivity, FragmentType.FRAGMENT_REGISTER)
        }
    }
}