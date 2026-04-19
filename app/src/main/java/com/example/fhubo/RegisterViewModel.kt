package com.example.fhubo

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    // Variables observables separadas para cada campo de la UI
    private val _usernameError = MutableLiveData<String?>()
    val usernameError: LiveData<String?> = _usernameError

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    private val _confirmPasswordError = MutableLiveData<String?>()
    val confirmPasswordError: LiveData<String?> = _confirmPasswordError

    private val _registerSuccess = MutableLiveData<Boolean>()
    val registerSuccess: LiveData<Boolean> = _registerSuccess

    // Función principal para registrar
    fun register(name: String, pass: String, passConfirm: String, email: String) {
        var hasError = false

        // 1. Validar nom
        if (name.isBlank()) {
            _usernameError.value = "El nom d'usuari no pot estar buit"
            hasError = true
        } else {
            _usernameError.value = null
        }

        // 2. Validar email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailError.value = "El format del correu no és vàlid"
            hasError = true
        } else {
            _emailError.value = null
        }

        // 3. Validar contrasenya
        if (pass.length < 8) {
            _passwordError.value = "La contrasenya te menys de 8 caracters"
            hasError = true
        } else if (pass.count(Char::isDigit) <= 0) {
            _passwordError.value = "La contrasenya ha de contenir un digit"
            hasError = true
        } else if (!(pass.any(Char::isLowerCase) && pass.any(Char::isUpperCase))) {
            _passwordError.value = "La contrasenya ha de contindre una minuscula i una majuscula"
            hasError = true
        } else if (!(pass.any { it in "!,+^" })) {
            _passwordError.value = "La contrasenya ha de tindre un careacter especial"
            hasError = true
        } else {
            _passwordError.value = null
        }

        // 4. Validar confirmació de contrasenya
        if (pass != passConfirm) {
            _confirmPasswordError.value = "Les contrasenyes no coincideixen"
            hasError = true
        } else {
            _confirmPasswordError.value = null
        }

        // 5. Finalizar
        if (!hasError) {
            _registerSuccess.value = true // ¡Todo correcto!
        }
    }
}