package com.test.object_details_screen.di

import com.test.object_details_screen.ObjectDetailsFragment
import dagger.Component

@Component(
    dependencies = [ObjectDetailsDependencies::class]
)
interface ObjectDetailsComponent {

    fun inject(fragment: ObjectDetailsFragment)
}