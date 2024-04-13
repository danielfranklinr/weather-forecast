package com.dfr.weather.domain.usecase.implementation

import com.dfr.weather.domain.model.SearchableCityDTO
import com.dfr.weather.domain.repository.SearchableCitiesRepository
import com.dfr.weather.domain.usecase.GetSearchableCitiesUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetSearchableCitiesUseCaseImpl @Inject constructor(
    private val getSearchableCitiesRepository: SearchableCitiesRepository,
) : GetSearchableCitiesUseCase {

    override fun getCitiesList(): Single<List<SearchableCityDTO>> {
        return getSearchableCitiesRepository.getSearchableCities()
    }
}