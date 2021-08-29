package com.los3molineros.logisticstycoon.view.userMenu.avatars

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion
import com.los3molineros.logisticstycoon.common.snakeBar
import com.los3molineros.logisticstycoon.databinding.ActivityAvatarBinding
import com.los3molineros.logisticstycoon.model.repository.AvatarsRepository
import com.los3molineros.logisticstycoon.view.fragments.MoneyFragment
import com.los3molineros.logisticstycoon.viewModel.AvatarViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class AvatarActivity : AppCompatActivity() {
    lateinit var binding: ActivityAvatarBinding
    @ExperimentalCoroutinesApi
    private val avatarViewModel: AvatarViewModel by viewModels()
    lateinit var adapter: AvatarAdapter
    val id: Int = 0

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAvatarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUI()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fBasicItems, MoneyFragment())
                .commitNow()
        }
    }

    @ExperimentalCoroutinesApi
    private fun initUI() {
        initListeners()
        initFormat()
        initRecycler()
        initSubscription()
    }

    @ExperimentalCoroutinesApi
    private fun initSubscription() {
        avatarViewModel.avatarList.observe(this) {
            adapter.setAvatars(it)
            avatarViewModel.updateImages(it)
        }

        avatarViewModel.avatarImages.observe(this) {
            adapter.setImages(it)
        }

        avatarViewModel.userAvatarList.observe(this) {
            adapter.setUserAvatars(it)
        }
    }

    private fun initListeners() {

        // Exit
        binding.ivExit.setOnClickListener {
            finish()
        }
    }

    private fun initFormat() {
        binding.idTittle.typeface = Companion.returnTypefaceKimbalt(this)
    }

    @ExperimentalCoroutinesApi
    private fun initRecycler() {
        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.rvAvatars.layoutManager = gridLayoutManager
        adapter = AvatarAdapter(this, null, null, null) { id -> onClick(id) }
        binding.rvAvatars.adapter = adapter
    }

    @ExperimentalCoroutinesApi
    private fun onClick(id: Int) {
        avatarViewModel.user.observe(this) {
            val moneyUser = it?.money ?: 0
            val gemsUser = it?.gems ?: 0
            val moneyAvatar = AvatarsRepository().selectAvatar(id).money
            val gemsAvatar = AvatarsRepository().selectAvatar(id).gems

            if (moneyUser < moneyAvatar || gemsUser < gemsAvatar) {
                binding.rvAvatars.snakeBar(getString(R.string.error_buy_avatar))
            } else {
                avatarViewModel.changeUserAvatar(id)
                finish()
            }
        }
    }
}