package id.reza.rijks.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.reza.rijks.model.DataUser
import id.reza.rijks.model.SatuUser
import id.reza.rijks.service.AppRepository
import id.reza.rijks.utils.SessionManager
import id.reza.rijks.utils.SingleLiveEvent

class ProfileViewModel(private val sessionManager: SessionManager, private val appRepository: AppRepository) : ViewModel() {

    val errorEvent = SingleLiveEvent<String>()
    val userEvent = SingleLiveEvent<DataUser>()
    val logoutEvent = SingleLiveEvent<Any>()

    fun getUser(){
        userEvent.value = sessionManager.userAccountInfo
    }

    fun logout(){
        sessionManager.resetSession()
        logoutEvent.call()
    }

}