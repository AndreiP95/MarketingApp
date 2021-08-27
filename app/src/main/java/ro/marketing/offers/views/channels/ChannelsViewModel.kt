package ro.marketing.offers.views.channels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ro.marketing.offers.repo.model.Specifics
import ro.marketing.offers.repo.network.dataSource.SpecificsDataSource
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChannelsViewModel @Inject
constructor(
    application: Application,
    private val specificsDataSource: SpecificsDataSource
) : AndroidViewModel(application) {

    val specifics = MutableLiveData<List<Specifics>>()
    val error = MutableLiveData(false)

    fun getSpecifics() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = specificsDataSource.fetchSpecifics()
            if (response.isSuccessful) {
                specifics.postValue(response.body()?.record)
            } else {
                error.postValue(true)
            }
        }
    }

}