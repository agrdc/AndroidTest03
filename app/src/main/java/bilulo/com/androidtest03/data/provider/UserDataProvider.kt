package bilulo.com.androidtest03.data.provider

import bilulo.com.androidtest03.data.model.User
import com.orm.SugarRecord
import kotlin.math.absoluteValue

class UserDataProvider {

    companion object {

        @JvmStatic
        fun saveUser(user: User) : Boolean {
            user.save()
            var usr = SugarRecord.findById(User::class.java,user.id.absoluteValue)
            return usr!=null
        }

        @JvmStatic
        fun listAllUsers() : List<User>? {
            return SugarRecord.listAll(User::class.java)
        }

        @JvmStatic
        fun removeAllUsers() {
            return SugarRecord.deleteAll(User::class.java)
        }
    }

}