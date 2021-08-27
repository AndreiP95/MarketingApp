package ro.marketing.offers.views.offers.adapter

import ro.marketing.offers.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ro.marketing.offers.repo.model.OffersPackages
import javax.inject.Inject

class OffersAdapter @Inject constructor() :
    RecyclerView.Adapter<OffersAdapter.OfferViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val channelView = LayoutInflater.from(parent.context)
            .inflate(R.layout.channel_view, parent, false)
        return OfferViewHolder(channelView)
    }

    var offersPackages: List<OffersPackages> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onItemClickListener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = offersPackages.size

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.specificTextView.text = offersPackages[position].channel
        holder.specificTextView.setOnClickListener {
            onItemClickListener?.let {
                it(position)
            }
        }

    }

    class OfferViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val specificTextView: TextView = view.findViewById(R.id.textView_specifics)

    }
}