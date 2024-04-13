package com.dfr.weather.domain.usecase

import com.dfr.weather.domain.model.SearchableCityDTO
import io.reactivex.rxjava3.core.Single

interface GetSearchableCitiesUseCase {
    fun getCitiesList(): Single<List<SearchableCityDTO>>
}