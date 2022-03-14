package id.reza.rijks.ui.login

import androidx.lifecycle.ViewModel
import id.reza.rijks.model.DataUser
import id.reza.rijks.model.SatuUser
import id.reza.rijks.utils.SessionManager
import id.reza.rijks.utils.SingleLiveEvent

class LoginViewModel(private val sessionManager: SessionManager) : ViewModel() {

    val loadingEvent = SingleLiveEvent<Boolean>()
    val isErrorEvent = SingleLiveEvent<String>()
    val isLoginEvent = SingleLiveEvent<String>()

    fun cekLogin(username: String, password: String){
        if (username.isNullOrEmpty()){
            isErrorEvent.value = "Username should not be empty"
        }else if (password.isNullOrEmpty()){
            isErrorEvent.value = "Password should not be empty"
        }else{
            try{
                if (sessionManager.hasUser){
                    if (sessionManager.userAccountRegister.list!!.indexOfFirst { it.username == username } > 0){
                        val satuUser = sessionManager.userAccountRegister.list!![sessionManager.userAccountRegister.list!!.indexOfFirst { it.username == username }]
                        if (satuUser.password.equals(password)){
                            doLogin(satuUser)
                        }else{
                            isErrorEvent.value = "Authentication failed"
                        }
                    }else{
                        isErrorEvent.value = "Username is not registered yet"
                    }
                }else{
                    isErrorEvent.value = "Username is not registered yet"
                }

            }catch (e : Exception){
                isErrorEvent.value = "${e.localizedMessage}"
            }
        }
    }

    fun doLogin(satuUser: DataUser){
        sessionManager.userAccountInfo = satuUser
        sessionManager.isLogged = true

        isLoginEvent.call()

    }
}