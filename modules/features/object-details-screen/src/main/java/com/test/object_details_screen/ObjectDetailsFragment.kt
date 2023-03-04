package com.test.object_details_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.object_details_screen.databinding.FragmentObjectDetailsBinding
import com.test.object_details_screen.di.DaggerObjectDetailsComponent
import com.test.object_details_screen.di.ObjectDetailsComponent
import dagger.Lazy
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import javax.inject.Inject

class ObjectDetailsFragment : Fragment(R.layout.fragment_object_details), IHasComponent<ObjectDetailsComponent> {
    private val viewBinding by viewBinding(FragmentObjectDetailsBinding::bind)

    @Inject
    internal lateinit var objectDetailsViewModelFactory: Lazy<ObjectDetailsViewModel.Factory>

    @Inject
    lateinit var router: ObjectDetailsRouter

    private val viewModel: ObjectDetailsViewModel by viewModels { objectDetailsViewModelFactory.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        XInjectionManager.bindComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        if (requireArguments().containsKey(KEY_OBJECT_ID)) {
            viewModel.objectDetailsInfo(requireArguments().getInt(KEY_OBJECT_ID))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.objectDetailsUiState.flowWithLifecycle(lifecycle)
            .onEach { objectDetailsUiState ->
                when (objectDetailsUiState) {
                    is ObjectDetailsUiState.Success -> {
                        // TODO
                    }
                    is ObjectDetailsUiState.Loading -> {
                        viewBinding.progress.isVisible = objectDetailsUiState.showProgress
                    }
                    is ObjectDetailsUiState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            objectDetailsUiState.exception.message ?: getString(R.string.object_details_load_error),
                            Toast.LENGTH_SHORT
                        ).show()
                        router.navigateBack()
                    }
                }
            }
            .launchIn(lifecycleScope)
    }

    override fun getComponent(): ObjectDetailsComponent =
        DaggerObjectDetailsComponent.builder()
            .objectDetailsDependencies(XInjectionManager.findComponent())
            .build()

    companion object {
        private const val KEY_OBJECT_ID = "objectId"
    }
}