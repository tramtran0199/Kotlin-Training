
package com.example.android.kotlintraining.view_model.list_user

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.android.kotlintraining.database.getDatabase
import com.example.android.kotlintraining.models.UserModel
import com.example.android.kotlintraining.repository.UsersRepository
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel(application: Application) : ViewModel() {

    private val _usersRepository = UsersRepository(getDatabase(application))
    val listUser = _usersRepository.users

    private val _navigateToSelectedProperty = MutableLiveData<UserModel>()
    val navigateToSelectedProperty: LiveData<UserModel>
        get() = _navigateToSelectedProperty

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                _usersRepository.refreshListUser()
            } catch (e: Exception) {
                Log.e("GetData", "$e")
            }
        }
    }

    fun displayPropertyDetails(userModel: UserModel) {
        _navigateToSelectedProperty.value = userModel
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}
