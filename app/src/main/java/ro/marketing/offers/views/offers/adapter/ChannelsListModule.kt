package ro.marketing.offers.views.offers.adapter

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ChannelsListModule {

    @Provides
    @Singleton
    fun provideAdapter(): OffersAdapter = OffersAdapter()
}