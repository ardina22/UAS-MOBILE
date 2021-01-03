package com.tugasakhirsemester.ardina.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tugasakhirsemester.ardina.R
import com.tugasakhirsemester.ardina.adapter.CovidAdapter
import com.tugasakhirsemester.ardina.viewModel.CovidViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    /*
     * Deklarasi lateinit variable bertipe CovidViewModel.
     */
    private lateinit var covidViewModel: CovidViewModel

    /*
     * Deklarasi lateinit variable bertipe CovidAdapter.
     */
    private lateinit var adapter: CovidAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.covid_19)

        /*
         * Menginstansiasi viewModel CovidViewModel
         */
        covidViewModel = ViewModelProvider(this).get(CovidViewModel::class.java)

        /*
         * Mengeksekusi method getCovidByProvince
         */
        covidViewModel.getCovidByProvince()

        /*
         * Mengambil nilai dari LiveData showProgress yang kemudian dapat di observable.
         * ketika nilai yang di observe bernilai true maka progress bar akan di tampilkan namun
         * jika nilai yang di observe bernilai false maka progress bar akan dihilangkan.
         */
        covidViewModel.showProgress.observe(this, Observer {
            if (it)
                    covid_progress_bar.visibility = View.VISIBLE
            else
                    covid_progress_bar.visibility = View.GONE
        })

        /*
         * Mengambil nilai dari LiveData covidList yang yang kemudian dapat di
         * observable. ketika mengobserve LiveData ini maka akan mengeksekusi setCovidList method
         * dari CovidAdapter class dengan memberikan argument dari nilai covidList yaitu Covid.
         */
        covidViewModel.covidList.observe(this, Observer {
            adapter.setCovidList(it)
        })

        /*
         * Menginstansiasi CovidAdapter class
         */
        adapter = CovidAdapter(this)

        /*
         * Set adapter dari RecyclerView dengan adapter dari CovidAdapter
         */
        rv_covid.adapter = adapter
    }
}