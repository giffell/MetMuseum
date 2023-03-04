package com.test.object_details_screen.adapter

import coil.load
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.test.object_details_screen.R
import com.test.object_details_screen.databinding.AdditionalImageItemBinding

object ImageDelegate {

    fun createImageUiDelegate() =
        adapterDelegateViewBinding<ImageItem, ImageUiItem, AdditionalImageItemBinding>(
            { layoutInflater, parent -> AdditionalImageItemBinding.inflate(layoutInflater, parent, false) }
        ) {
            bind {
                binding.additionalImage.load(item.imageUrl) {
                    placeholder(R.drawable.image_loading)
                    error(R.drawable.no_image)
                }
            }
        }
}