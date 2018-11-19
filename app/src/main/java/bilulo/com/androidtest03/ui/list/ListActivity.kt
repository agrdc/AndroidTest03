package bilulo.com.androidtest03.ui.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import bilulo.com.androidtest03.R
import bilulo.com.androidtest03.data.model.User

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
    }

    override fun onStart() {
        mPresenter = ListPresenter(this)
        mPresenter.setView(this)
        mPresenter.fetchUsers()
        super.onStart()
    }

    override fun onDestroy() {
        mPresenter.clearView()
        super.onDestroy()
    }

    override fun callbackSuccess(users: List<User>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun callbackEmpty(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun callbackError(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}