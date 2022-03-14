package id.reza.rijks.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.reza.rijks.model.Data
import id.reza.rijks.service.AppRepository
import id.reza.rijks.utils.SessionManager
import id.reza.rijks.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.lang.Exception

class HomeViewModel(private val sessionManager: SessionManager, private val appRepository: AppRepository) : ViewModel() {

    val errorEvent = SingleLiveEvent<String>()
    val loadingEvent = SingleLiveEvent<Boolean>()
    val dataEvent = SingleLiveEvent<Data>()

    fun getData(){
        loadingEvent.postValue(true)

        try{
            var response: Data

            viewModelScope.launch {
                with(Dispatchers.IO){
                    response = appRepository.getData()
                }

                with(Dispatchers.Main){
                    try {
                        dataEvent.value = response
                    }catch (e: Exception){
                        errorEvent.value = e.localizedMessage
                    }
                }
            }
        }catch (e: Exception){
            errorEvent.value = e.localizedMessage
        }finally {
            loadingEvent.postValue(false)
        }
    }
}