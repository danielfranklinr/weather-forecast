package com.dfr.weather.domain.repository

import com.dfr.weather.domain.model.SearchableCityDTO
import io.reactivex.rxjava3.core.Single

interface SearchableCitiesRepository {
    fun getSearchableCities(): Single<List<SearchableCityDTO>>

}