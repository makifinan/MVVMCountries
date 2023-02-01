package com.makifinan.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.makifinan.countries.model.Country

class CountryViewModel : ViewModel() {

    var countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(){

    }
}