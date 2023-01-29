package com.makifinan.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.makifinan.countries.model.Country

class FeedViewModel : ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryLoading = MutableLiveData<Boolean>()
    val countryError = MutableLiveData<Boolean>()

}