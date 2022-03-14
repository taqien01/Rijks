package id.reza.rijks.ui.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import id.reza.rijks.model.DataUser
import id.reza.rijks.model.SatuUser
import id.reza.rijks.model.User
import id.reza.rijks.utils.SessionManager
import id.reza.rijks.utils.SingleLiveEvent

class RegisterViewModel(private val sessionManager: SessionManager): ViewModel() {

    val isLoadingEvent = SingleLiveEvent<Boolean>()
    val errorEvent = SingleLiveEvent<String>()
    val isRegisterEvent = SingleLiveEvent<String>()

    fun register(username: String, password: String, isAgree: Boolean){
        if (username.isNullOrEmpty()){
            errorEvent.value = "Username should not be empty"
        }else if (password.isNullOrEmpty()){
            errorEvent.value = "Password should not be empty"
        }else if (!isAgree){
            errorEvent.value = "Checkbox should be check"
        }else{
            if (sessionManager.hasUser){
                if (sessionManager.userAccountRegister.list!!.indexOfFirst { it.username == username } > 0){
                    errorEvent.value = "Username has been registered"
                }else{
                    saveData(username, password)
                }
            }else{
                saveData(username, password)
            }
        }

    }

    fun saveData(username: String, password: String){
        val satuUser : DataUser? = DataUser(username, password)

//        satuUser?.username = username
//        satuUser?.password = password

        var list : List<DataUser>? = ArrayList<DataUser>()
        var list2 : MutableList<DataUser>? = ArrayList<DataUser>()

        if (sessionManager.hasUser){
            list = sessionManager.userAccountRegister.list

            list2 = list as MutableList<DataUser>?
        }

        list!!.toMutableList().add(satuUser!!)
        list2?.add(satuUser!!)

        Log.e("Register", "Sata ${satuUser!!.username}")
        Log.e("Register", "List ${list2!![0].username}")

        val user = User()

        user?.list = list2

        Log.e("Register", "User $user")
        sessionManager.userAccountRegister = user!!

        sessionManager.hasUser = true

        isRegisterEvent.call()

    }
}