package com.example.android.kotlintraining.view_model.user_detail

import android.app.Application
import androidx.lifecycle.*
import com.example.android.kotlintraining.database.getDatabase
import com.example.android.kotlintraining.repository.UserDetailRepository
import com.example.android.kotlintraining.view_model.list_user.ApiStatus
import kotlinx.coroutines.launch

class DetailViewModel( selectedUser: String,
                      app: Application) : AndroidViewModel(app) {

    private val _properties = UserDetailRepository(getDatabase(app), selectedUser)
    val properties = _properties.userDetail

    private val _formatDate = MutableLiveData<String>()
    val formatDate: LiveData<String>
        get() = _formatDate

    private val _displayName = MutableLiveData<String>()
    val displayName: LiveData<String>
        get() = _displayName

    private var _selectedUser = MutableLiveData<String>()

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _checkMail = MutableLiveData<Boolean>()
    val checkMail: LiveData<Boolean>
        get() = _checkMail

    init {
        _selectedUser.value = selectedUser
        getUserDetailProperties()
    }

    private fun getUserDetailProperties() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _properties.refreshUser()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
            _formatDate.value = getFormatDate()
            _displayName.value = getDisplayName()
            _checkMail.value = properties.value?.blog != ""
        }
    }

    private fun getFormatDate(): String {
        var dateFormatted = "Since "

        val year = properties.value?.createdAt?.let { it.substring(0, 4) }
        val month = properties.value?.createdAt?.let { it.substring(5, 7) }
        val day = properties.value?.createdAt?.let { it.substring(8, 10) }?.toInt()

        dateFormatted += when (month) {
            "01" -> "Jan"
            "02" -> "Feb"
            "03" -> "Mar"
            "04" -> "Apr"
            "05" -> "May"
            "06" -> "Jun"
            "07" -> "Jul"
            "08" -> "Aug"
            "09" -> "Sep"
            "10" -> "Oct"
            "11" -> "Nov"
            else -> "Dec"
        }

        dateFormatted += " $day, $year"


        return dateFormatted
    }

    private fun getDisplayName(): String {
        return properties.value?.name ?: _selectedUser.value.toString()
    }
}

