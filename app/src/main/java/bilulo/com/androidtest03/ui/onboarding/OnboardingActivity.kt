package bilulo.com.androidtest03.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import bilulo.com.androidtest03.R
import bilulo.com.androidtest03.ui.onboarding.register.RegisterFragment
import bilulo.com.androidtest03.ui.onboarding.welcome.WelcomeFragment
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {

    companion object {
        val WELCOME_FRAGMENT_TAG  = "welcomeFragmentTag"
        val REGISTER_FRAGMENT_TAG = "registerFragmentTag"
        val TYPE_FRAGMENT_TAG = "typeFragmentTag"

        @JvmStatic
        fun getActivityIntent(context: Context, fragmentType: FragmentType): Intent {
            return Intent(context, OnboardingActivity::class.java).apply {
                putExtra(TYPE_FRAGMENT_TAG, fragmentType)
            }
        }

        @JvmStatic
        fun initFragment(onboardingActivity: OnboardingActivity, fragmentType: FragmentType) {
            when (fragmentType) {
                FragmentType.FRAGMENT_WELCOME ->
                    onboardingActivity.supportFragmentManager.beginTransaction().replace(
                        onboardingActivity.mainContainer.id,
                        WelcomeFragment(),
                        WELCOME_FRAGMENT_TAG
                    ).commit()
                FragmentType.FRAGMENT_REGISTER ->
                    onboardingActivity.supportFragmentManager.beginTransaction().replace(
                        onboardingActivity.mainContainer.id,
                        RegisterFragment(),
                        REGISTER_FRAGMENT_TAG
                    ).commit()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        var fragmentType : FragmentType? = intent.getSerializableExtra(TYPE_FRAGMENT_TAG) as FragmentType?
        if (fragmentType == null) {
            fragmentType = FragmentType.FRAGMENT_WELCOME
        }
        initFragment(this, fragmentType )
    }
}
