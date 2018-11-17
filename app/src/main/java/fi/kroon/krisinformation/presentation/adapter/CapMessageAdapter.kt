package fi.kroon.krisinformation.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fi.kroon.krisinformation.R
import fi.kroon.krisinformation.common.extensions.parseToLocalDate
import fi.kroon.krisinformation.data.capmessage.model.CapMessage
import kotlinx.android.synthetic.main.capmessage_item.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class CapMessageAdapter @Inject constructor() : RecyclerView.Adapter<CapMessageAdapter.ViewHolder>() {

    internal var collection: MutableList<CapMessage> by Delegates.observable(mutableListOf()) {
        _, _, _ -> notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(capMessage: CapMessage) {
            itemView.date.text = capMessage.published.parseToLocalDate().toString()
            itemView.headline.text = capMessage.infoData.first().headline
            itemView.description.text = capMessage.infoData.first().description

            when (capMessage.infoData.first().senderName) {
                is String -> itemView.chip.text = capMessage.infoData.first().senderName
                else -> itemView.chip.visibility = View.INVISIBLE
            }

            /*when (capMessage.isNewVma) {
                true -> itemView.chip.text = "V.M.A." //&& itemView.chip.s
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.capmessage_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(collection[position])

    override fun getItemCount() = collection.size
}