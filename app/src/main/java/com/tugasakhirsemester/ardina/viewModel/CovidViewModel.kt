package com.tugasakhirsemester.ardina.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.tugasakhirsemester.ardina.model.Covid
import com.tugasakhirsemester.ardina.service.CovidService

/*
 * Membuat viewModel CovidViewModel yang membutuhkan 1 buah parameter ketika diinstansiasi yaitu
 * application bertipe Application. class ini sendiri mengextends AndroidViewModel yang juga
 * membutuhkan 1 buah parameter yaitu application bertipe Application.
 *
 * private val service yang bertipe CovidService diberi nilai dari instansiasi object dari
 * CovidService class dengan memberikan 1 buah argument application dari constructor CovidViewModel.
 *
 * showProgress bertipe LiveData boolean. nilai dari showProgress sendiri akan diambil dari
 * pemanggilan showProgress pada CovidService class ketika class viewModel ini diinstansiasi.
 *
 * covidList bertipe LiveData Covid. nilai dari showProgress sendiri akan diambil dari
 * pemanggilan covidList pada CovidService class ketika class viewModel ini diinstansiasi.
 *
 * Method getCovidByProvince digunakan untuk mengeksekusi method getCovidByProvince yang ada pada
 * CovicService class untuk melakukan HTTP Request Call (GET REQUEST) ke Web Server / REST API
 */
class CovidViewModel(application: Application) : AndroidViewModel(application){
    private val service: CovidService = CovidService(application)

    val showProgress: LiveData<Boolean>
    val covidList: LiveData<Covid>

    init {
        this.showProgress = service.showProgress
        this.covidList = service.covidList
    }

    fun getCovidByProvince() {
        service.getCovidByProvince()
    }
}