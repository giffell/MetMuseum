package com.test.search_screen.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.test.search_screen.adapter.uiItems.UiItem
import javax.inject.Inject

class SearchResultsAdapter @Inject constructor() : ListDelegationAdapter<List<UiItem>>() {
    private var uiItems = emptyList<UiItem>()

    override fun setItems(items: List<UiItem>?) {
        val newItems = items ?: emptyList()
        val diffUtilsCallback = DiffUtilsSearchResults(uiItems, newItems)
        val diffUtilsResult = DiffUtil.calculateDiff(diffUtilsCallback)
        uiItems = newItems
        super.setItems(uiItems)
        diffUtilsResult.dispatchUpdatesTo(this)
    }

    fun setData(uiItems: List<UiItem>) {
        setItems(uiItems.toMutableList())
    }

    fun addDelegate(vararg delegates: AdapterDelegate<List<UiItem>>) {
        delegates.forEach {
            delegatesManager.addDelegate(it)
        }
    }
}