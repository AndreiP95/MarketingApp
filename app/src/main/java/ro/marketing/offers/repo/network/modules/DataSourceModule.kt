package ro.marketing.offers.repo.network.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ro.marketing.offers.repo.network.dataSource.SpecificsDataSource
import ro.marketing.offers.repo.network.services.specifics.SpecificsService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providesSpecificsDataSource(userServices: SpecificsService) = SpecificsDataSource(userServices)
}