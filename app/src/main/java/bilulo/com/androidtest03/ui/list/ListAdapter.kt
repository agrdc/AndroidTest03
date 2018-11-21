package bilulo.com.androidtest03.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bilulo.com.androidtest03.R
import bilulo.com.androidtest03.data.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class ListAdapter(val listener: (User) -> Unit) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private lateinit var mUsers: List<User>

    override fun onCreateViewHolder(holder: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(holder.context).inflate(R.layout.item_user, holder, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mUsers[position], listener)
    }


    override fun getItemCount(): Int {
        return mUsers.size
    }

    fun setData(users: List<User>) {
        mUsers = users
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User, listener: (User) -> Unit) = with(itemView) {
            itemTitleTextView.text = user.name
            itemUserCpfValue.text = user.cpf
            var address = user.address + " " + user.addressNumber + " " + user.complement + "-" + user.neighborhood + "-" + user.state
            itemUserAddressValue.text = address
            setOnClickListener { listener(user) }
        }
    }
}
