package com.makifinan.countries.model

import com.google.gson.annotations.SerializedName

class Country(
    @SerializedName("name")
    val countryName : String?,

    @SerializedName("language")
    val countryLanguage : String?,

    @SerializedName("region")
    val countryRegion  : String?,

    @SerializedName("capital")
    val countryCapital : String?,

    @SerializedName("currency")
    val countryCurrency : String?
) {
}