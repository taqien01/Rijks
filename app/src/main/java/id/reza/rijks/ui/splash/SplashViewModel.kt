package id.reza.rijks.ui.splash

import androidx.lifecycle.ViewModel
import id.reza.rijks.utils.SessionManager
import id.reza.rijks.utils.SingleLiveEvent

class SplashViewModel(private val sessionManager: SessionManager) : ViewModel() {

    var isLoginEvent = SingleLiveEvent<Boolean>()

    fun checkLogin(){
        isLoginEvent.value = sessionManager.isLogged
    }
}