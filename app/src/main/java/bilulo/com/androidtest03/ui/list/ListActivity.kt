package bilulo.com.androidtest03.ui.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import bilulo.com.androidtest03.R
import bilulo.com.androidtest03.data.model.User
import bilulo.com.androidtest03.ui.edit.EditActivity
import bilulo.com.androidtest03.ui.onboarding.FragmentType
import bilulo.com.androidtest03.ui.onboarding.OnboardingActivity
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), IListView.View {

    private lateinit var mPresenter: IListView.Presenter

    companion object {
        @JvmStatic
        fun getActivityIntent(context: Context): Intent {
            return Intent(context, ListActivity::class.java).apply {}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        showLoading()
        setupActionbar()
    }

    private fun setupActionbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.title_list)
    }

    override fun onBackPressed() {
        startActivity(OnboardingActivity.getActivityIntent(this, FragmentType.FRAGMENT_REGISTER))
        finish()
        super.onBackPressed()
    }

    override fun onStart() {
        super.onStart()
        mPresenter = ListPresenter(this)
        mPresenter.setView(this)
        mPresenter.fetchUsers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                startActivity(OnboardingActivity.getActivityIntent(this, FragmentType.FRAGMENT_REGISTER))
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        mPresenter.clearView()
        super.onDestroy()
    }

    override fun showLoading() {
        messageTextView.visibility = View.INVISIBLE
        usersRecyclerView.visibility = View.INVISIBLE
        loadingList.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingList.visibility = View.INVISIBLE
    }

    override fun callbackSuccess(users: List<User>) {
        hideLoading()
        usersRecyclerView.visibility = View.VISIBLE
        usersRecyclerView.layoutManager = LinearLayoutManager(this)
        val listAdapter = ListAdapter {
            onItemClicked(it)
        }
        listAdapter.setData(users)
        usersRecyclerView.adapter = listAdapter
    }

    override fun callbackEmpty(msg: String) {
        hideLoading()
        messageTextView.visibility = View.VISIBLE
        messageTextView.text = msg
    }

    override fun callbackError(msg: String) {
        hideLoading()
        messageTextView.visibility = View.VISIBLE
        messageTextView.text = msg
    }

    private fun onItemClicked(it: User) {
        var backupId = it.id
        startActivity(EditActivity.getActivityIntent(this,it, backupId))
        finish()
    }
}