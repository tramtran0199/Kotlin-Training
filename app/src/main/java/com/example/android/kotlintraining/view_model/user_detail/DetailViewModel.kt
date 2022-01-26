package com.example.android.kotlintraining.view_model.user_detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.android.kotlintraining.database.getDatabase
import com.example.android.kotlintraining.repository.UserDetailRepository
import kotlinx.coroutines.launch

class DetailViewModel( selectedUser: String,
                      app: Application) : AndroidViewModel(app) {

    private val _properties = UserDetailRepository(getDatabase(app), selectedUser)
    val properties = _properties.userDetail

    private var _selectedUser = MutableLiveData<String>()

    init {
        _selectedUser.value = selectedUser
        getUserDetailProperties()
    }

    private fun getUserDetailProperties() {
        viewModelScope.launch {
            try {
                _properties.refreshUser()
            } catch (e: Exception) {
                Log.e("GetData", "$e")
            }
        }
    }

    fun formatDate(date: String) : String {
        return "Since $date"
    }
}

