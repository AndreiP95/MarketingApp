package ro.marketing.offers.repo.network.dataSource

import retrofit2.Response
import ro.marketing.offers.repo.model.OffersResponse
import ro.marketing.offers.repo.network.services.offers.OffersService
import javax.inject.Inject

class OffersDataSource @Inject constructor(private val offersService: OffersService) {

    suspend fun fetchOffers(): Response<OffersResponse> =
        offersService.fetchOffers()
}