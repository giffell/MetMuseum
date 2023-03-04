package com.test.search_screen.adapter.uiItems

data class EmptyUiItem(val emptyDescription: String = "") : UiItem {
    override fun getItemId(): Long = System.currentTimeMillis()
}
