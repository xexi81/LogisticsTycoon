package com.los3molineros.logisticstycoon.view.userMenu.avatars

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion
import com.los3molineros.logisticstycoon.common.Companion.Companion.returnCurrencyFormat
import com.los3molineros.logisticstycoon.databinding.ItemAvatarBinding
import com.los3molineros.logisticstycoon.model.data.Avatars
import com.los3molineros.logisticstycoon.model.data.UserAvatars
import com.squareup.picasso.Picasso


class AvatarAdapter(
    private var context: Context,
    private var avatars: List<Avatars>?,
    private var images: List<Uri?>?,
    private var userAvatars: List<UserAvatars>?,
    val idAvatarOnClick: (Int) -> Unit
) : RecyclerView.Adapter<AvatarAdapter.ViewHolder>() {

    private fun getAvatar(position: Int): Avatars? {
        return avatars?.get(position)
    }

    private fun getImage(position: Int): Uri? {
        return images?.get(position)
    }

    fun setAvatars(avatars: List<Avatars>) {
        this.avatars = avatars
        // Reloads the RecyclerView with new adapter data
        notifyDataSetChanged()
    }

    fun setImages(images: List<Uri?>) {
        this.images = images
        notifyDataSetChanged()
    }

    fun setUserAvatars(userAvatars: List<UserAvatars>?) {
        this.userAvatars = userAvatars
        notifyDataSetChanged()
    }


    // Creates View Holder for re-use
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_avatar, parent, false)

        return ViewHolder(view)
    }

    // Binds re-usable View for a given position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val avatar = getAvatar(position)
        val image = getImage(position)
        holder.bind(avatar, image)
    }

    // Returns total items in Adapter
    override fun getItemCount(): Int {
        return avatars?.size ?: 0
    }

    // Holds an instance to the view for re-use
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemAvatarBinding.bind(view)

        fun bind(avatar: Avatars?, image: Uri?) {
            // Typefaces
            binding.txtMoneyAvatar.typeface = Companion.returnTypefaceKingthings(context)

            // avatar image
            Picasso.get().load(image).into(binding.ivAvatar)

            // Change button text for user avatars
            if (hasUserBoughtThisAvatar(avatar?.id)) {
                binding.ivMoneyAvatar.visibility = View.GONE
                binding.txtMoneyAvatar.text = context.getString(R.string.change)
            } else {
                if (avatar?.money ?: 0 > 0) {
                    binding.ivMoneyAvatar.visibility = View.VISIBLE
                    binding.txtMoneyAvatar.text = returnCurrencyFormat(avatar?.money ?: 0)
                    binding.ivMoneyAvatar.setImageResource(R.drawable.camiones_icon)
                } else if (avatar?.gems ?: 0 > 0) {
                    binding.ivMoneyAvatar.visibility = View.VISIBLE
                    binding.txtMoneyAvatar.text = returnCurrencyFormat(avatar?.gems ?: 0)
                    binding.ivMoneyAvatar.setImageResource(R.drawable.bread_icon)
                } else {
                    binding.ivMoneyAvatar.visibility = View.GONE
                    binding.txtMoneyAvatar.text = context.getString(R.string.change)
                }
            }

            // Set on click
            binding.linearLayout.setOnClickListener {
                idAvatarOnClick(avatar?.id ?: -1)
            }
        }
    }


    private fun hasUserBoughtThisAvatar(id: Int?): Boolean {
        var hasUserThisAvatar = false

        if (userAvatars != null && id != null) {
            for (userAvatar in userAvatars!!) {
                if (userAvatar.avatarID == id) {
                    hasUserThisAvatar = true
                }
            }
        }
        return hasUserThisAvatar
    }

}