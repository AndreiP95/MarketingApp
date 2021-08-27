package ro.marketing.offers.views.offers

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ro.marketing.offers.repo.model.Offers
import ro.marketing.offers.repo.model.OffersPackages
import ro.marketing.offers.repo.network.dataSource.OffersDataSource
import javax.inject.Inject

@HiltViewModel
class OffersViewModel @Inject constructor(
    application: Application,
    private val offersDataSource: OffersDataSource
) : AndroidViewModel(application) {

    val offers = MutableLiveData<List<OffersPackages>>()

    fun getOffers(channels: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = offersDataSource.fetchOffers()
            if (response.isSuccessful) {
                offers.postValue(response.body()?.record?.filter {
                    channels.contains(it.channel)
                }?.onEach { offer -> offer.packages.sortBy { it.fee } })

            } else {
                Log.d("ERROR", "Error is ${response.errorBody()}")
            }
        }
    }
}