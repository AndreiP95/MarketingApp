package ro.marketing.offers.views.channels.adapter

import android.content.Context
import ro.marketing.offers.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import ro.marketing.offers.repo.model.Specifics

class ChannelsAdapter(
    private val context: Context,
    private val checkEnabled: () -> Unit
) :
    RecyclerView.Adapter<ChannelsAdapter.SpecificViewHolder>() {

    var channelList: List<Specifics> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificViewHolder {
        val channelView = LayoutInflater.from(parent.context)
            .inflate(R.layout.channel_view, parent, false)
        return SpecificViewHolder(channelView)
    }

    override fun getItemCount(): Int = channelList.size

    override fun onBindViewHolder(holder: SpecificViewHolder, position: Int) {
        holder.specificTextView.text = channelList[position].type
        holder.isSelected = channelList[position].isSelected
        holder.specificTextView.setOnClickListener {
            if (!holder.isSelected) {
                holder.specificTextView.setTextColor(
                    context.resources.getColor(
                        R.color.white,
                        context.theme
                    )
                )
                holder.specificTextView.background =
                    AppCompatResources.getDrawable(context, R.drawable.selected_channel_background)
                holder.isSelected = true
                channelList.find {
                    it.type.compareTo(
                        holder.specificTextView.text.toString(),
                        true
                    ) == 0
                }?.isSelected = true
            } else {
                holder.isSelected = false
                channelList.find {
                    it.type.compareTo(
                        holder.specificTextView.text.toString(),
                        true
                    ) == 0
                }?.isSelected = false
                holder.specificTextView.setTextColor(
                    context.resources.getColor(
                        R.color.black,
                        context.theme
                    )
                )
                holder.specificTextView.background =
                    AppCompatResources.getDrawable(context, R.drawable.default_channel_background)

            }
            checkEnabled()
        }
    }

    class SpecificViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var isSelected = false
        val specificTextView: TextView = view.findViewById(R.id.textView_specifics)


    }
}