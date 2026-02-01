package com.example.fhubo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()

    val email: LiveData<String> = _email
    val password: LiveData<String> = _password

    fun checkEmail(email: String): String? {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "El formato del correo no es válido"
        }
        return null
    }

    fun authenticate(email: String, password: String): Boolean {
        return password.isNotEmpty()
    }
}
