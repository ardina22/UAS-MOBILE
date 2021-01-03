package com.tugasakhirsemester.ardina.network

import com.tugasakhirsemester.ardina.model.Covid
import retrofit2.Call
import retrofit2.http.GET

/*
 * Membuat interface CovidApi.
 *
 * Di dalam interface ini terdapat satu buah abstract method dengan nama getCovidByProvince.
 */
interface CovidApi {

    /*
     * Membuat abstract method getCovidByProvince.
     *
     * Abstract method ini mempunyai tipe kembalian Call interface dari retrofit bertipe Covid.
     *
     * Call sendiri akan mengirimkan request ke webserver atau REST API dan mengembalikan response
     * tersebut sebagai nilai kembalian.
     *
     * Pada GET decorator dari retrofit digunakan untuk melakukan get request ke webserver atau
     * REST API dengan optional value pada parameternya yaitu path url dari webserver atau REST API.
     *
     */
    @GET("indonesia/provinsi")
    fun getCovidByProvince(): Call<Covid>
}