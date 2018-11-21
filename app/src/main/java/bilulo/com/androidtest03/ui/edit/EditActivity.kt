package bilulo.com.androidtest03.ui.edit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import bilulo.com.androidtest03.R
import bilulo.com.androidtest03.data.model.User

class EditActivity : AppCompatActivity() {

    lateinit var mUser : User

    companion object {
        val USER_TAG = "userTag"
        val BACKUP_ID_TAG = "backupIdTag"
        @JvmStatic
        fun getActivityIntent(context: Context, user: User, backupId : Long): Intent {
            return Intent(context, EditActivity::class.java).apply {}
                .putExtra(USER_TAG, user)
                .putExtra(BACKUP_ID_TAG, backupId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        mUser = intent.getSerializableExtra(USER_TAG) as User
        var backupId = intent.getLongExtra(BACKUP_ID_TAG,0)
        mUser.id = backupId
    }

    override fun onBackPressed() {
        startActivity(bilulo.com.androidtest03.ui.list.ListActivity.getActivityIntent(this))
        finish()
        super.onBackPressed()
    }
}