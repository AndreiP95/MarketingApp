package ro.marketing.offers.repo.network.services.offers

import retrofit2.Response
import retrofit2.http.GET
import ro.marketing.offers.repo.model.OffersResponse
import ro.marketing.offers.utils.KeysConstants

interface OffersService {

    @GET("b/${KeysConstants.OFFERS_KEY}/latest")
    suspend fun fetchOffers(): Response<OffersResponse>


}