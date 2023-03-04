package com.test.object_details_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.test.core_model.ObjectDetailsInfo
import com.test.object_details_screen.adapter.AdditionalImageAdapter
import com.test.object_details_screen.adapter.AdditionalImageDecorator
import com.test.object_details_screen.adapter.ImageItem
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

    @Inject
    lateinit var additionalImageAdapter: AdditionalImageAdapter

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

        with(viewBinding.additionImagesList) {
            adapter = additionalImageAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(AdditionalImageDecorator(resources.getDimensionPixelSize(R.dimen.additional_image_margin)))
        }

        viewModel.objectDetailsUiState.flowWithLifecycle(lifecycle)
            .onEach { objectDetailsUiState ->
                when (objectDetailsUiState) {
                    is ObjectDetailsUiState.Success -> {
                        with(viewBinding) {
                            objectImage.load(objectDetailsUiState.objectDetailsInfo.primaryImage) {
                                placeholder(R.drawable.image_loading)
                                error(R.drawable.no_image)
                            }
                            objectInfo.text = mapObjectInfo(objectDetailsUiState.objectDetailsInfo)

                            val additionalImages = objectDetailsUiState.objectDetailsInfo.additionalImages
                            if (!additionalImages.isNullOrEmpty()) {
                                additionImagesList.isVisible = true
                                additionalImageAdapter.items = additionalImages.filter { it.isNotEmpty() }.map { ImageItem(it) }
                            }

                        }
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

    private fun mapObjectInfo(objectDetailsInfo: ObjectDetailsInfo): String {
        return StringBuilder()
            .append("Title: ${objectDetailsInfo.title} ")
            .append("\n\n")
            .append("Department: ${objectDetailsInfo.department}")
            .append("\n\n")
            .append("Repository: ${objectDetailsInfo.repository}")
            .append("\n\n")
            .append("Culture: ${objectDetailsInfo.culture}")
            .toString()
    }

    override fun getComponent(): ObjectDetailsComponent =
        DaggerObjectDetailsComponent.builder()
            .objectDetailsDependencies(XInjectionManager.findComponent())
            .build()

    companion object {
        private const val KEY_OBJECT_ID = "objectId"
    }
}