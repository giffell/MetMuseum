package com.test.search_screen

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.search_screen.adapter.Delegates
import com.test.search_screen.adapter.SearchResultsAdapter
import com.test.search_screen.adapter.SpacesItemDecoration
import com.test.search_screen.adapter.uiItems.EmptyUiItem
import com.test.search_screen.adapter.uiItems.SearchResultUiItem
import com.test.search_screen.databinding.FragmentSearchBinding
import com.test.search_screen.di.DaggerSearchComponent
import com.test.search_screen.di.SearchComponent
import dagger.Lazy
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import timber.log.Timber
import javax.inject.Inject

class SearchFragment : Fragment(R.layout.fragment_search), IHasComponent<SearchComponent> {

    private val viewBinding by viewBinding(FragmentSearchBinding::bind)

    @Inject
    internal lateinit var searchViewModelFactory: Lazy<SearchViewModel.Factory>

    private val viewModel: SearchViewModel by viewModels { searchViewModelFactory.get() }

    @Inject
    lateinit var router: SearchRouter

    @Inject
    lateinit var searchAdapter: SearchResultsAdapter

    private val onEditorActionListener by lazy {
        OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onSearchButtonClicked()
            }
            return@OnEditorActionListener false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        XInjectionManager.bindComponent(this).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchAdapter.addDelegate(
            Delegates.searchResultItemDelegate { router.openObjectDetailsFragment(it) },
            Delegates.emptyStateDelegate()
        )
        with(viewBinding) {
            searchAction.setOnClickListener { onSearchButtonClicked() }
            searchView.setOnEditorActionListener(onEditorActionListener)

            searchResultList.apply {
                adapter = searchAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.search_result_item_margin)))
            }
        }

        viewModel.searchUiState.flowWithLifecycle(lifecycle)
            .onEach { searchUiState ->
                when (searchUiState) {
                    is SearchUiState.Success -> {
                        searchAdapter.setData(searchUiState.searchResult.objectIDs.map { SearchResultUiItem(it) })
                    }
                    SearchUiState.EmptyResults -> {
                        searchAdapter.setData(listOf(EmptyUiItem()))
                    }
                    is SearchUiState.Loading -> {
                        viewBinding.progress.isVisible = searchUiState.showProgress
                    }
                    is SearchUiState.Error -> {
                        Timber.e(searchUiState.exception)
                        Toast.makeText(requireContext(), getString(R.string.load_error_message), Toast.LENGTH_SHORT).show()
                        searchAdapter.setData(listOf(EmptyUiItem()))
                    }
                }
            }.launchIn(lifecycleScope)
    }

    private fun onSearchButtonClicked() {
        viewModel.loadSearch(viewBinding.searchView.text.toString())
    }

    override fun getComponent(): SearchComponent =
        DaggerSearchComponent.builder()
            .searchDependencies(XInjectionManager.findComponent())
            .build()
}