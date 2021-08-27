package ro.marketing.offers.repo.model

import com.squareup.moshi.JsonClass
import retrofit2.http.Field

@JsonClass(generateAdapter = true)
data class SpecificsResponse(
    val record : List<Specifics>,
)

data class Specifics(
    val type : String,
    var isSelected: Boolean = false,
    val channels : List<String>
)
