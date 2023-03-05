package com.test.metmuseum

import android.view.View
import com.kaspersky.kaspresso.screens.KScreen
import com.test.search_screen.SearchFragment
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import org.hamcrest.Matcher
import com.test.search_screen.R as RSearch

object SearchFragmentScreen : KScreen<SearchFragmentScreen>() {
    override val layoutId: Int = RSearch.layout.fragment_search
    override val viewClass: Class<*> = SearchFragment::class.java

    val searchView = KEditText { withId(RSearch.id.searchView) }
    val searchActionButton = KButton { withId(RSearch.id.searchAction) }
    val searchResultList: KRecyclerView = KRecyclerView({
        withId(RSearch.id.searchResultList)
    }, itemTypeBuilder = {
        itemType(::SearchResultItem)
    })
}

class SearchResultItem(parent: Matcher<View>) : KRecyclerItem<SearchResultItem>(parent)