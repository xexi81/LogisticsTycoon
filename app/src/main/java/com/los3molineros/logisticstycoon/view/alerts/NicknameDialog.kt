package com.los3molineros.logisticstycoon.view.alerts

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion
import com.los3molineros.logisticstycoon.common.toast
import com.los3molineros.logisticstycoon.model.returnUriFromStorageCloud
import com.los3molineros.logisticstycoon.model.selectFirebaseParams
import com.los3molineros.logisticstycoon.model.selectFirebaseUser
import com.los3molineros.logisticstycoon.model.updateFirebaseUserNickname
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch



class NicknameDialog(context: Context): AppCompatActivity() {

    var dialog = Dialog(context)

    init {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_nickname)

        val mTxtTittle: TextView = dialog.findViewById(R.id.txtTittle)
        val mTxtNickname: TextView = dialog.findViewById(R.id.txtNickname)
        val mTxtNicknameChangeGems: TextView = dialog.findViewById(R.id.txtNicknameChangeGems)
        val mIvGems: ImageView = dialog.findViewById(R.id.ivGems)
        val mIvExit: ImageView = dialog.findViewById(R.id.ivExit)
        val mLayout: LinearLayout = dialog.findViewById(R.id.linearLayout)

        mTxtTittle.typeface = Companion.returnTypefaceKimbalt(context)
        mTxtNickname.typeface = Companion.returnTypefaceKingthings(context)
        mTxtNicknameChangeGems.typeface = Companion.returnTypefaceKingthings(context)
        mTxtNicknameChangeGems.setShadowLayer(5F, 0F, 0F, Color.BLACK)

        lifecycleScope.launch {
            val user = selectFirebaseUser()
            val params = selectFirebaseParams()


            mTxtNickname.text = user?.nickname
            mTxtNicknameChangeGems.text = params?.changeNickname.toString()
            Picasso.get().load(returnUriFromStorageCloud("gs://logistics-tycoon.appspot.com/gem_icon.png")).into(mIvGems)


            mLayout.setOnClickListener {
                if (user?.gems ?: 0 < 15) {
                    Toast.makeText(context, R.string.no_gems, Toast.LENGTH_SHORT).show()
                } else {
                    if (mTxtNickname.text.isNullOrEmpty()) {
                        Toast.makeText(context, R.string.nickname_error, Toast.LENGTH_SHORT).show()
                    } else {
                        val newGems = user?.gems?.minus((params?.changeNickname?:0 ))
                        lifecycleScope.launch {
                            updateFirebaseUserNickname(mTxtNickname.text.toString(), newGems ?: 0)
                            dialog.dismiss()
                        }
                    }
                }
            }
        }


        mIvExit.setOnClickListener {
            dialog.dismiss()
        }


        dialog.show()
    }




}