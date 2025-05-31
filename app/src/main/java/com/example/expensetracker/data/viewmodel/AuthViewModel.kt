package com.example.expensetracker.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.User
import com.example.expensetracker.data.repository.UserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> get() = _loginSuccess

    fun signUp(username: String, password: String) {
        viewModelScope.launch {
            val user = User(username = username, password = password)
            userRepository.insertUser(user)
            _loginSuccess.value = true
        }
    }

    fun signIn(username: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.getUser(username, password)
            _loginSuccess.value = user != null
        }
    }
}
