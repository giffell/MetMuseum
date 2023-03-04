package com.test.search_screen.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.test.search_screen.adapter.uiItems.EmptyUiItem
import com.test.search_screen.adapter.uiItems.SearchResultUiItem
import com.test.search_screen.adapter.uiItems.UiItem
import com.test.search_screen.databinding.ListEmptyStateBinding
import com.test.search_screen.databinding.ListSearchResultItemBinding

internal object Delegates {

    fun emptyStateDelegate() =
        adapterDelegateViewBinding<EmptyUiItem, UiItem, ListEmptyStateBinding>(
            { layoutInflater, parent -> ListEmptyStateBinding.inflate(layoutInflater, parent, false) }
        ) {
            bind {
                if (item.emptyDescription.isNotEmpty()) {
                    binding.emptyState.text = item.emptyDescription
                }
            }
        }

    fun searchResultItemDelegate(itemClickListener: (objectId: Int) -> Unit) =
        adapterDelegateViewBinding<SearchResultUiItem, UiItem, ListSearchResultItemBinding>(
            { layoutInflater, parent -> ListSearchResultItemBinding.inflate(layoutInflater, parent, false) }
        ) {
            bind {
                with(binding.searchObjectId) {
                    text = item.objectId.toString()
                    setOnClickListener { itemClickListener(item.objectId) }
                }
            }
        }
}