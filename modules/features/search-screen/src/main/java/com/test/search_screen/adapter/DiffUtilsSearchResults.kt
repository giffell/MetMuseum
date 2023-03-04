package com.test.search_screen.adapter

import androidx.recyclerview.widget.DiffUtil
import com.test.search_screen.adapter.uiItems.UiItem

internal class DiffUtilsSearchResults(
    private val oldData: List<UiItem>,
    private val newData: List<UiItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition].getItemId() == newData[newItemPosition].getItemId()
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }
}