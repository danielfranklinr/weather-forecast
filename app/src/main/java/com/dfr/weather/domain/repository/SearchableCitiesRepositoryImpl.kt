package com.dfr.weather.domain.repository

import com.dfr.weather.domain.model.SearchableCityDTO
import io.reactivex.rxjava3.core.Single

class SearchableCitiesRepositoryImpl : SearchableCitiesRepository {

    private var searchableCities = mutableListOf<SearchableCityDTO>()

    override fun getSearchableCities(): Single<List<SearchableCityDTO>> {

        if (searchableCities.isEmpty()) {
            searchableCities.add(
                SearchableCityDTO(
                    "1", "Montevideo",
                    latitude = "-34.9058916",
                    longitude = "-56.1913095"
                )
            )
            searchableCities.add(
                SearchableCityDTO(
                    "2", "Londres",
                    latitude = "-51.5073219",
                    longitude = "-0.1276474"
                )
            )
            searchableCities.add(
                SearchableCityDTO(
                    "3", "San Pablo",
                    latitude = "-23.5506507",
                    longitude = "-46.6333824"
                )
            )
            searchableCities.add(
                SearchableCityDTO(
                    "4", "Buenos Aires",
                    latitude = "-34.6075682",
                    longitude = "-58.4370894"
                )
            )
            searchableCities.add(
                SearchableCityDTO(
                    "5", "Munich",
                    latitude = "48.1371079",
                    longitude = "11.5753822"
                )
            )
        }

        return Single.create { emitter ->
            emitter.onSuccess(searchableCities)
        }
    }

}