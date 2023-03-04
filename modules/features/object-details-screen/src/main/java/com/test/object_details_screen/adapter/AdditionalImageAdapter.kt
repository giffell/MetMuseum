package com.test.object_details_screen.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import javax.inject.Inject

class AdditionalImageAdapter @Inject constructor() : ListDelegationAdapter<List<ImageUiItem>>() {

    init {
        delegatesManager.addDelegate(ImageDelegate.createImageUiDelegate())
    }
}