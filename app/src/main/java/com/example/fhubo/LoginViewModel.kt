package com.example.fhubo

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    // Variables observables (LiveData) para los errores y el éxito
    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private val _loginError = MutableLiveData<String?>()
    val loginError: LiveData<String?> = _loginError

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    // Función principal: no devuelve nada, solo actualiza las variables
    fun login(email: String, pass: String) {
        var hasError = false

        // 1. Validar Email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailError.value = "El format del correu no és vàlid"
            hasError = true
        } else {
            _emailError.value = null // Limpiar el error
        }

        if (hasError) return // Si el email está mal, paramos aquí

        // 2. Validar Autenticación (Contraseña)
        if (pass.isNotEmpty()) {
            _loginError.value = null
            _loginSuccess.value = true // Login correcto
        } else {
            _loginError.value = "El correu o la contrasenya són incorrectes"
            _loginSuccess.value = false
        }
    }
}