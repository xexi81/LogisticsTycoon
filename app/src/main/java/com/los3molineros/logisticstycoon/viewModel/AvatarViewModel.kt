package com.los3molineros.logisticstycoon.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import changeFirebaseUserAvatar
import com.los3molineros.logisticstycoon.model.data.Avatars
import com.los3molineros.logisticstycoon.model.data.UserAvatars
import com.los3molineros.logisticstycoon.model.repository.AvatarsRepository
import com.los3molineros.logisticstycoon.model.returnUriFromStorageCloud
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import selectFirebaseUserAvatars

@ExperimentalCoroutinesApi
class AvatarViewModel: ViewModel() {
    private val _avatarImages = MutableLiveData<List<Uri?>>()
    val avatarImages: LiveData<List<Uri?>> get() = _avatarImages

    private val _avatarList = MutableLiveData<List<Avatars>>()
    val avatarList: LiveData<List<Avatars>> get() = _avatarList

    private val _userAvatarList = MutableLiveData<List<UserAvatars>?>()
    val userAvatarList: LiveData<List<UserAvatars>?> get() = _userAvatarList

    init {
        viewModelScope.launch {
            _avatarList.postValue(AvatarsRepository().returnAllAvatars())
        }

        viewModelScope.launch {
            selectFirebaseUserAvatars().collect {
                _userAvatarList.value = it
            }
        }
    }

    fun updateImages(avatarList: List<Avatars>?) {
        avatarList?.let {
            viewModelScope.launch {
                val images: MutableList<Uri?> = mutableListOf()

                if (_avatarList.value?.size  ?: 0  > 0) {
                    for (avatar in _avatarList.value!!) {
                        images.add(returnUriFromStorageCloud(avatar.image))
                    }

                    _avatarImages.postValue(images)
                }
            }
        }
    }

    fun changeUserAvatar(id: Int) {
        viewModelScope.launch {
            changeFirebaseUserAvatar(id)
        }
    }
}