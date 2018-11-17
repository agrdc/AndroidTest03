package bilulo.com.androidtest03.ui.onboarding.register

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import bilulo.com.androidtest03.R
import bilulo.com.androidtest03.ui.onboarding.OnboardingActivity

class RegisterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupActionBar()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.register_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupActionBar() {
        var actionBar = (activity as OnboardingActivity).supportActionBar
        actionBar?.title = context?.getString(R.string.title_register)
        actionBar?.show()
    }
}
