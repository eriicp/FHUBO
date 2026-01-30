package com.example.fhubo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel(){
    private val _nameuser = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _passwordConfirm = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()


    val nameuser : LiveData<String> = _nameuser
    val password : LiveData<String> = _password
    val passwordConfirm : LiveData<String> = _passwordConfirm
    val email : LiveData<String> = _email



    fun register(name: String, pass:String, passConfirm:String, email:String): String? {
        _password.value = pass
        _nameuser.value = name
        _passwordConfirm.value = passConfirm
        _email.value = email

        val passwordError = checkPassword(pass,passConfirm)

        if (passwordError != null){
            return passwordError
        }
        else{
            val user = User(_nameuser.value,_password.value,_email.value)
            Usuarios.addUser(user)
        }
        return null
    }

    fun checkPassword(pass: String,passConfirm: String): String? {
        if (pass.length < 8){ return "La contrasenya te menys de 8 caracters"}
        if(pass.count(Char::isDigit) <= 0){ return "La contrasenya ha de contenir un digit"}
        if (!(pass.any(Char::isLowerCase) && pass.any(Char::isUpperCase))){ return "La contrasenya ha de contindre una minuscula i una majuscula"}
        if (!(pass.any { it in "!,+^" })){ return "La contrasenya ha de tindre un careacter especial"}
        if (pass != passConfirm){return "Les contrasenyes no coincideixen"}
        return null
    }
}