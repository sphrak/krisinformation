package fi.kroon.krisinformation.presentation.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import fi.kroon.krisinformation.data.capmessage.model.CapMessage

class CapMessageDiffUtil(
    private val oldList: List<CapMessage>,
    private val newList: List<CapMessage>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}