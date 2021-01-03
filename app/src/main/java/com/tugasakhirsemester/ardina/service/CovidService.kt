package com.tugasakhirsemester.ardina.service

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.tugasakhirsemester.ardina.model.Covid
import com.tugasakhirsemester.ardina.network.CovidApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
 * Membuat service class CovidService.
 *
 * Class ini membutuhkan 1 buah parameter yaitu application bertipe Application bersifat private.
 *
 * Base_URL digunakan untuk menyimpan base url dari webservice atau REST API.
 *
 * showProgress yang diberi nilai awal berupa instansiasi MutableLiveData bertipe boolean.
 * showProgress sendiri digunakan untuk indicator untuk menampilkan progress bar pada view
 * ketika service dijalankan atau value dari MutableLiveData ini bernilai true dan menghide
 * progress bar ketika service telah selesai dijalankan atau value dari MutableLive data ini
 * bernilai false. showProgress sendiri bersifat observable.
 *
 * covidList yang diberi nilai awal berupa instansiasi MutableLiveData bertipe Covid. Covid sendiri
 * merupakan class turunan dari ArrayList bertipe CovidItem. digunakan untuk menampung data dari
 * web service / REST API yang telah dikonversikan atau mapping menggunakan GsonConverterFactory
 * dari data JSON menjadi data object Covid.
 *
 * Method getProvince digunakan untuk mengambil data (GET REQUEST) dari web service / REST API.
 * method ini sendiri mempunyai tipe kembalian Unit. didalam method ini akan dibuild retrofit object
 * untuk melakukan HTTP request call menggunakan base_url yang telah diset, dan response dari
 * request tersebut akan dimapping dengan bantuan GsonConverterFactory. Jika response berhasil maka
 * MutableLiveData dari covidList akan di set valuenya dengan nilai dari body response, namun jika
 * response tidak berhasil atau request call gagal maka akan menampilkan text dengan durasi yang
 * ditentukan untuk memberi tahu user bahwa HTTP request call gagal atau mengalami error.
 */
class CovidService(private val application: Application) {
    val BASE_URL = "https://api.kawalcorona.com/"

    val showProgress = MutableLiveData<Boolean>()

    val covidList = MutableLiveData<Covid>()

    fun getCovidByProvince(): Unit {
        showProgress.value = true
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(CovidApi::class.java)

        service.getCovidByProvince().enqueue(object : Callback<Covid> {
            override fun onResponse(call: Call<Covid>, response: Response<Covid>) {
                Log.d("Response", "onResponse ${response.body()}")
                showProgress.value = false
                if (response.isSuccessful) {
                    covidList.value = response.body()
                } else {
                    Toast.makeText(
                        application,
                        "Something went wrong ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Covid>, t: Throwable) {
                Log.e("Failure", "onFailure $t ")
                showProgress.value = false
                Toast.makeText(application, "Error while accessing API", Toast.LENGTH_SHORT).show()
            }
        })
    }
}