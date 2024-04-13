package com.dfr.weather.domain.repository.implementation

import com.dfr.weather.domain.model.SearchableCityDTO
import com.dfr.weather.domain.repository.SearchableCitiesRepository
import io.reactivex.rxjava3.core.Single

class SearchableCitiesRepositoryImpl : SearchableCitiesRepository {

    private var searchableCities = mutableListOf<SearchableCityDTO>()

    override fun getSearchableCities(): Single<List<SearchableCityDTO>> {

        if (searchableCities.isEmpty()) {
            searchableCities.add(
                SearchableCityDTO(
                    "Montevideo",
                )
            )
            searchableCities.add(
                SearchableCityDTO(
                    "Londres",
                )
            )
            searchableCities.add(
                SearchableCityDTO(
                    "San Pablo",
                )
            )
            searchableCities.add(
                SearchableCityDTO(
                    "Buenos Aires",
                )
            )
            searchableCities.add(
                SearchableCityDTO(
                    "Munich",
                )
            )
        }

        return Single.create { emitter ->
            emitter.onSuccess(searchableCities)
        }
    }

}