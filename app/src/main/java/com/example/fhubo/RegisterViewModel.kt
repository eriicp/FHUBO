package com.example.fhubo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel(){
    private val _nameuser = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()

    val nameuser : LiveData<String> = _nameuser
    val password : LiveData<String> = _password

    val usersList: MutableList<User> = mutableListOf()

    fun register(name: String, pass:String){
        _password.value = pass
        _nameuser.value = name
        val user = User(_nameuser.value,_password.value)
        usersList.add(user)
        print(usersList)
    }
}