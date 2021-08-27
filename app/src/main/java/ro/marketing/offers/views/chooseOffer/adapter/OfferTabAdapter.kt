package ro.marketing.offers.views.chooseOffer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ro.marketing.offers.R
import javax.inject.Inject

class OfferTabAdapter @Inject constructor() :
    RecyclerView.Adapter<OfferTabAdapter.OfferViewHolder>() {

    var offerDetails: List<String> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val offerItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.offer_tab_item_view, parent, false)
        return OfferViewHolder(offerItemView)
    }

    override fun getItemCount(): Int = offerDetails.size

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.offerTextView.text = offerDetails[position]
    }

    class OfferViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val offerTextView: TextView = view.findViewById(R.id.textView_offer)


    }
}