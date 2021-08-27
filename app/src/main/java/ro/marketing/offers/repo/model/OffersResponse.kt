package ro.marketing.offers.repo.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class OffersResponse(
    val record: List<OffersPackages>,
)

@JsonClass(generateAdapter = true)
data class OffersPackages(
    val channel: String,
    val packages: MutableList<Offers>
)

@JsonClass(generateAdapter = true)
data class Offers(
    val fee: Int,
    val receivables: List<String>
)
