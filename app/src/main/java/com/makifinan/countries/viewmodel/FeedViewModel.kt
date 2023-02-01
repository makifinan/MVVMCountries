package com.makifinan.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.makifinan.countries.model.Country
import com.makifinan.countries.service.CountryAPIService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {

    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryLoading = MutableLiveData<Boolean>()
    val countryError = MutableLiveData<Boolean>()

    fun refershData(){

    }

    private fun getDataFromAPI(){
        countryLoading.value = true
        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(value: List<Country>?) {
                        countries.value = value
                        countryError.value=false
                        countryLoading.value=false
                    }

                    override fun onError(e: Throwable?) {
                        countryLoading.value=false
                        countryError.value=true
                        e?.printStackTrace()
                    }

                })
        )
    }

}