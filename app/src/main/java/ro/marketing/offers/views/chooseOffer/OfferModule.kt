package ro.marketing.offers.views.chooseOffer

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ro.marketing.offers.views.chooseOffer.adapter.OfferTabAdapter
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OfferModule {

    @Provides
    @Singleton
    fun provideAdapter(): OfferTabAdapter = OfferTabAdapter()
}