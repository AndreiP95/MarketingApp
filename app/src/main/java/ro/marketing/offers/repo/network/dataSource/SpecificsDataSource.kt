package ro.marketing.offers.repo.network.dataSource

import retrofit2.Response
import ro.marketing.offers.repo.model.SpecificsResponse
import ro.marketing.offers.repo.network.services.specifics.SpecificsService
import javax.inject.Inject

class SpecificsDataSource @Inject constructor(private val specificsService: SpecificsService) {

    suspend fun fetchSpecifics(): Response<SpecificsResponse> =
        specificsService.fetchSpecifics()
}