package ro.marketing.offers.repo.network.services.specifics

import retrofit2.Response
import retrofit2.http.GET
import ro.marketing.offers.repo.model.SpecificsResponse
import ro.marketing.offers.utils.KeysConstants.SPECIFICS_KEY

interface SpecificsService {

    @GET("b/${SPECIFICS_KEY}/latest")
    suspend fun fetchSpecifics(): Response<SpecificsResponse>


}