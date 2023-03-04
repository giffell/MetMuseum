package com.test.search_screen.adapter.uiItems

data class SearchResultUiItem(val objectId: Int) : UiItem {
    override fun getItemId(): Long = System.currentTimeMillis()
}
