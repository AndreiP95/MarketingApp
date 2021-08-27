package ro.marketing.offers.repo.network.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ro.marketing.offers.repo.network.ServiceBuilder
import ro.marketing.offers.repo.network.services.offers.OffersService
import ro.marketing.offers.repo.network.services.specifics.SpecificsService

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    fun provideSpecificsService(serviceBuilder: ServiceBuilder): SpecificsService =
        serviceBuilder.buildService(SpecificsService::class.java)

    @Provides
    fun provideOffersService(serviceBuilder: ServiceBuilder) : OffersService =
        serviceBuilder.buildService(OffersService::class.java)
}