package bilulo.com.androidtest03.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import bilulo.com.androidtest03.R
import bilulo.com.androidtest03.ui.onboarding.register.RegisterFragment
import bilulo.com.androidtest03.ui.onboarding.welcome.WelcomeFragment
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {

    companion object {
        val WELCOME_FRAGMENT_TAG = "welcomeFragmentTag"
        val REGISTER_FRAGMENT_TAG = "registerFragmentTag"
        val SELECTED_FRAGMENT_TAG = "selectedFragmentTag"
        val TYPE_FRAGMENT_TAG = "typeFragmentTag"
        lateinit var mFragment: Fragment

        @JvmStatic
        fun getActivityIntent(context: Context, fragmentType: FragmentType): Intent {
            return Intent(context, OnboardingActivity::class.java).apply {
                putExtra(TYPE_FRAGMENT_TAG, fragmentType)
            }
        }

        @JvmStatic
        fun initFragment(onboardingActivity: OnboardingActivity, fragment: Fragment) {
            val fragmentTag: String = if (fragment is RegisterFragment) {
                REGISTER_FRAGMENT_TAG
            } else {
                WELCOME_FRAGMENT_TAG
            }
            onboardingActivity.supportFragmentManager.beginTransaction().replace(
                onboardingActivity.mainContainer.id,
                fragment,
                fragmentTag
            ).commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        mFragment = if (savedInstanceState == null) {
            val fragmentType = intent.extras?.get(TYPE_FRAGMENT_TAG)
            if (fragmentType != null) {
                if (fragmentType == FragmentType.FRAGMENT_REGISTER)
                    RegisterFragment()
                else
                    WelcomeFragment()
            } else
                WelcomeFragment()
        } else {
            supportFragmentManager.getFragment(savedInstanceState, SELECTED_FRAGMENT_TAG) as Fragment
        }
        initFragment(this, mFragment)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (outState != null) {
            supportFragmentManager.putFragment(outState, SELECTED_FRAGMENT_TAG, mFragment)
        }
    }
}
