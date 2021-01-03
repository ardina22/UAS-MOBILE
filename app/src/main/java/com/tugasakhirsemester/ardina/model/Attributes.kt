package com.tugasakhirsemester.ardina.model

/*
 * Membuat data class Attributes. Data class Attributes memiliki 6 argument parameter yang harus diberikan
 * ketika menginstansiasi class ini. Argument parameter yang harus diberikan adalah:
 *      * FID bertipe integer
 *      * Kasus_Meni bertipe integer
 *      * Kasus_Posi bertipe integer
 *      * Kasus_Semb bertipe integer
 *      * Kode_Provi bertipe integer
 *      * Provinsi bertipe string
 *
 * Data class ini digunakan untuk menerima data dari response REST-API dan memberikan nilai dari
 * response data tersebut kedalam property dari data class Attributes.
 */
data class Attributes(
    val FID: Int,
    val Kasus_Meni: Int,
    val Kasus_Posi: Int,
    val Kasus_Semb: Int,
    val Kode_Provi: Int,
    val Provinsi: String
)