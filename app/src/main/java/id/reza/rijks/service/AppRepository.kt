package id.reza.rijks.service

import id.reza.rijks.model.Data

class AppRepository(private val appInterface: AppInterface) {

    suspend fun getData() : Data =
        appInterface.getData()
}