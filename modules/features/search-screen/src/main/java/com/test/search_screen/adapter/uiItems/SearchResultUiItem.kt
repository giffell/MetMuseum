package com.test.search_screen.adapter.uiItems

data class SearchResultUiItem(val objectId: Long) : UiItem {
    override fun getItemId(): Long = System.currentTimeMillis()
}
