package com.example.android.kotlintraining.view_model.user_detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.android.kotlintraining.apis.UserDetailApi
import com.example.android.kotlintraining.apis.UserDetailProperty
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class DetailViewModel( selectedUser: String,
                      app: Application) : AndroidViewModel(app) {

    private val _properties = MutableLiveData<UserDetailProperty>()
    val properties: LiveData<UserDetailProperty>
        get() = _properties

    private var _selectedUser = MutableLiveData<String>()

    init {
        _selectedUser.value = selectedUser
        getUserDetailProperties()
    }

    private fun getUserDetailProperties() {
        viewModelScope.launch {
            try {
                _properties.value = UserDetailApi.retrofitService.getUserDetail(_selectedUser.value.toString())
            } catch (e: Exception) {
                Log.e("GetData", "$e")
            }
        }
    }

    fun formatDate(date: String) : String {
        return "Since $date"
    }
}

